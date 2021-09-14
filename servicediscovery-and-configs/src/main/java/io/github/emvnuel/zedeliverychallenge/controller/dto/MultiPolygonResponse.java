package io.github.emvnuel.zedeliverychallenge.controller.dto;

import lombok.Getter;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MultiPolygonResponse {
    List<List<List<List<Double>>>> coordinates;

    public MultiPolygonResponse(GeoJsonMultiPolygon geoJsonMultiPolygon) {
        this.coordinates = geoJsonMultiPolygon
                .getCoordinates()
                .stream().map(x -> x.getCoordinates()
                        .stream().map(y -> y.getCoordinates()
                            .stream().map(z -> Arrays.asList(z.getX(), z.getY()))
                                .collect(Collectors.toList()))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public String getType() {
        return "MultiPolygon";
    }
}
