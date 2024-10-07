
package com.innovus.nearestpoi.resources;

import com.innovus.nearestpoi.dto.PointOfInterestDTO;
import com.innovus.nearestpoi.exception.PointOfInterestNotFoundException;
import com.innovus.nearestpoi.service.PointOfInterestService;
import jakarta.inject.Inject;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/v1/pois")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PointOfInterestResource {

    @Inject
    private PointOfInterestService poiService;

    @GET
    @Path("/nearest")
    public Response getNearestPoint(
            @QueryParam("latitude") 
                @NotNull(message = "Oops! Looks like you forgot the latitude. Please provide it!")
                @DecimalMin(value = "-90.0", message = "Latitude must be greater than or equal to -90")
                @DecimalMax(value = "90.0", message = "Latitude must be less than or equal to 90")
                @Digits(integer = 3, fraction = 6, message = "Latitude must be a decimal number with at most 3 integer and 6 fraction digits")

                    Double latitude,
            @QueryParam("longitude") 
                @NotNull(message = "Oops! Looks like you forgot the longitude. Please provide it!")
                @DecimalMin(value = "-180.0", message = "Longitude must be greater than or equal to -180")
                @DecimalMax(value = "180.0", message = "Longitude must be less than or equal to 180")
                @Digits(integer = 3, fraction = 6, message = "Longitude must be a decimal number with at most 3 integer and 6 fraction digits")
                    Double longitude    
        ) {
        try {
            PointOfInterestDTO nearestPoint = poiService.findNearestPoint(latitude, longitude);
            return Response.ok(nearestPoint).build(); // Return 200 OK with the point
        } catch (PointOfInterestNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("No Points of Interest found near the specified location.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("It’s coffee o’clock. Please contact the support team at antoinevava@outlook.com.").build();
        }
    }

    @GET
    @Path("/popular")
    public Response getPointsWithCountGreaterThan(
            @QueryParam("count") 
                @Min(value = 0, message = "Request count must be greater than or equal to zero")
                    int count) 
        {
        try {
            List<PointOfInterestDTO> pois = poiService.findPointsWithRequestCountGreaterThan(count);
            if (pois.isEmpty())
                return Response.status(Response.Status.NOT_FOUND).entity("No points found with request count greater than " + count).build();
            return Response.ok(pois).build(); // Return 200 OK with the list of points
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("It’s coffee o’clock. Please contact the support team at antoinevava@outlook.com.").build();
        }
    }

}

