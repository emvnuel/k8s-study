package io.github.emvnuel.zedeliverychallenge.repository;

import io.github.emvnuel.zedeliverychallenge.model.Partner;

import java.util.Optional;

public interface PartnerCustomRepository {
    Optional<Partner> findNearestPartnerWithinCoverageArea(Double lat, Double lon);
}
