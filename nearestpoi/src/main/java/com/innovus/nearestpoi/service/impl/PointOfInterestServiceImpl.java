
package com.innovus.nearestpoi.service.impl;

import com.innovus.nearestpoi.dto.PointOfInterestDTO;
import com.innovus.nearestpoi.entity.PointOfInterest;
import com.innovus.nearestpoi.exception.PointOfInterestNotFoundException;
import com.innovus.nearestpoi.repository.PointOfInterestRepository;
import com.innovus.nearestpoi.service.PointOfInterestService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.GeodeticCalculator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;

@Stateless
public class PointOfInterestServiceImpl implements PointOfInterestService {

    @Inject
    private PointOfInterestRepository poiRepository;
    
    private final Map<Long, PointOfInterest> poiCache = new HashMap<>();
    private final GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
    private final ModelMapper modelMapper = new ModelMapper();
    
    public PointOfInterestServiceImpl() {
    }
   
    public PointOfInterestServiceImpl(PointOfInterestRepository poiRepository) {
        this.poiRepository = poiRepository;
        loadCache();  // Load the POIs into cache on startup
    }

    @PostConstruct
    private void loadCache() {
        // Load all POIs from the database into memory for caching
        List<PointOfInterest> pois = poiRepository.findAll();
        poiCache.clear();
        for (PointOfInterest poi : pois) {
            poiCache.put(poi.getId(), poi);
        }
    }

    public PointOfInterestDTO findNearestPoint(double latitude, double longitude) {
        // Use GeoTools GeodeticCalculator to compute distances
        Point userLocation = geometryFactory.createPoint(new Coordinate(longitude, latitude));
        GeodeticCalculator calculator = new GeodeticCalculator();

        PointOfInterest nearestPoint = null;
        double shortestDistance = Double.MAX_VALUE;

        // Iterate over the cached POIs instead of querying the database
        for (PointOfInterest poi : poiCache.values()) {
            Point poiLocation = geometryFactory.createPoint(new Coordinate(poi.getLongitude(), poi.getLatitude()));

            // Set the starting and destination points
            calculator.setStartingGeographicPoint(userLocation.getX(), userLocation.getY());
            calculator.setDestinationGeographicPoint(poiLocation.getX(), poiLocation.getY());

            // Get the distance in meters
            double distance = calculator.getOrthodromicDistance();

            // Find the nearest point
            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestPoint = poi;
            }
        }

        // Increment request count for the nearest point if found
        if (nearestPoint != null) {
            poiRepository.incrementRequestCountForNearestPoint(nearestPoint.getId());

            // Update the cache after incrementing the request count
            nearestPoint.setRequestCount(nearestPoint.getRequestCount() + 1);
            poiCache.put(nearestPoint.getId(), nearestPoint);
        } else
            throw new PointOfInterestNotFoundException("No Points of Interest found near the specified location.");

        return modelMapper.map(nearestPoint,PointOfInterestDTO.class); // Return the nearest point
    }
   
    public List<PointOfInterestDTO> findPointsWithRequestCountGreaterThan(int count) {
        // Use the cache to filter by request count
        return poiCache.values().stream()
                .filter(p -> p.getRequestCount() > count)
                .map(p -> modelMapper.map(p, PointOfInterestDTO.class))
                .collect(Collectors.toList());
    }   
    
}
