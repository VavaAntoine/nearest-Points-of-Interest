
package com.innovus.nearestpoi.service;

import com.innovus.nearestpoi.dto.PointOfInterestDTO;
import java.util.List;

public interface PointOfInterestService {
    PointOfInterestDTO findNearestPoint(double latitude, double longitude);
    List<PointOfInterestDTO> findPointsWithRequestCountGreaterThan(int count);
}
