package io.github.emvnuel.zedeliverychallenge.controller.dto;

import io.github.emvnuel.zedeliverychallenge.model.Partner;
import lombok.Getter;

@Getter
public class PartnerResponse {

    private String id;

    private String tradingName;

    private String ownerName;

    private String document;

    private MultiPolygonResponse coverageArea;

    private PointResponse address;

    public PartnerResponse(Partner partner) {
        this.id = partner.getId();
        this.tradingName = partner.getTradingName();
        this.ownerName = partner.getOwnerName();
        this.document = partner.getDocument();
        this.coverageArea = new MultiPolygonResponse(partner.getCoverageArea());
        this.address = new PointResponse(partner.getAddress());
    }

}
