package io.github.emvnuel.zedeliverychallenge.repository;

import io.github.emvnuel.zedeliverychallenge.model.Partner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends MongoRepository<Partner, String>, PartnerCustomRepository {

}
