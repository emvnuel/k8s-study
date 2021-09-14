package io.github.emvnuel.zedeliverychallenge.controller;

import io.github.emvnuel.zedeliverychallenge.controller.dto.PartnerRequest;

import io.github.emvnuel.zedeliverychallenge.controller.dto.PartnerResponse;
import io.github.emvnuel.zedeliverychallenge.model.Partner;
import io.github.emvnuel.zedeliverychallenge.repository.PartnerRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;

@RestController
@RequestMapping("/partners")
@Validated
public class PartnerController {

    private final PartnerRepository partnerRepository;

    public PartnerController(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createPartner(@Valid @RequestBody PartnerRequest request) {
        Partner partner = partnerRepository.save(request.toModel());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(partner.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartnerResponse> loadPartnerById(@PathVariable("id") String id) {
        return partnerRepository.findById(id)
                .map(partner -> ResponseEntity.ok(new PartnerResponse(partner)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<PartnerResponse> searchPartner(@RequestParam @Min(-90) @Max(90) Double lat,
                                                 @RequestParam @Min(-180) @Max(180) Double lon) {
        return partnerRepository.findNearestPartnerWithinCoverageArea(lat, lon)
                .map(partner -> ResponseEntity.ok(new PartnerResponse(partner)))
                .orElse(ResponseEntity.notFound().build());
    }

}
