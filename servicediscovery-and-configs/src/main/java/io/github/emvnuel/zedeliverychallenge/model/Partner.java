package io.github.emvnuel.zedeliverychallenge.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document
public class Partner {

    @Id
    private String id;

    @NotEmpty
    private String tradingName;

    @NotEmpty
    private String ownerName;

    @NotEmpty
    private String document;

    @NotNull
    private GeoJsonMultiPolygon coverageArea;

    @NotNull
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint address;

    public Partner(@NotEmpty String tradingName,
                   @NotEmpty String ownerName,
                   @NotEmpty String document,
                   @NotNull GeoJsonMultiPolygon coverageArea,
                   @NotNull GeoJsonPoint address) {
        this.tradingName = tradingName;
        this.ownerName = ownerName;
        this.document = document;
        this.coverageArea = coverageArea;
        this.address = address;
    }

}
