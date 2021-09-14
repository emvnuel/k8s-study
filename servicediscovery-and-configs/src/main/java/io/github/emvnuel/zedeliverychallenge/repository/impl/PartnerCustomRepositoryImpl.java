package io.github.emvnuel.zedeliverychallenge.repository.impl;

import io.github.emvnuel.zedeliverychallenge.model.Partner;
import io.github.emvnuel.zedeliverychallenge.repository.PartnerCustomRepository;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;

public class PartnerCustomRepositoryImpl implements PartnerCustomRepository {

    private final MongoTemplate mongoTemplate;

    public PartnerCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<Partner> findNearestPartnerWithinCoverageArea(Double lat, Double lon) {
        Point location = new Point(lat, lon);
        return Optional.ofNullable(
                mongoTemplate.findOne(new Query(Criteria.where("address").nearSphere(location)
                        .and("coverageArea").intersects(new GeoJsonPoint(location))), Partner.class));
    }
}
