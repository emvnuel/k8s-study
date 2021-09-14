package io.github.emvnuel.zedeliverychallenge.controller.dto;

import io.github.emvnuel.zedeliverychallenge.model.Partner;
import lombok.Value;

import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class PartnerRequest {

    @NotEmpty
    String tradingName;

    @NotEmpty
    String ownerName;

    @NotEmpty
    String document;

    @NotNull
    GeoJsonMultiPolygon coverageArea;

    @NotNull
    GeoJsonPoint address;

    public Partner toModel() {
        return new Partner(tradingName, ownerName, document, coverageArea, address);
    }
}
