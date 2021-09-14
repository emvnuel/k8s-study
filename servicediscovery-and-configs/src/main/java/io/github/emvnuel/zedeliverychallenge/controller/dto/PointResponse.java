package io.github.emvnuel.zedeliverychallenge.controller.dto;

import lombok.Getter;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

@Getter
public class PointResponse {

    List<Double> coordinates;

    public PointResponse(GeoJsonPoint geoJsonPoint) {
        this.coordinates = geoJsonPoint.getCoordinates();
    }

    public String getType() {
        return "Point";
    }
}
