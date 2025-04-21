package kr.ac.hansung.cse.controller;

import kr.ac.hansung.cse.exception.OfferNotFoundException;
import kr.ac.hansung.cse.model.Offer;
import kr.ac.hansung.cse.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController  // @Controller + @ResponseBody
@RequestMapping("/api/offers")
public class OfferRestController {

    @Autowired
    private OfferService offerService;

    // Retrieve single offer
    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOffer(@PathVariable("id") int id) {

        Offer offer = offerService.getOfferById(id);
        if(offer== null) {
            throw new OfferNotFoundException(id);
        }
        return new ResponseEntity<Offer>(offer, HttpStatus.OK) ; // body, status

    }

    // Retrieve All Offers
    @GetMapping
    public ResponseEntity<List<Offer>> getOffers() {

        List<Offer> offers = offerService.getAllOffers();
        if(offers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Offer>>(offers, HttpStatus.OK) ; // body, status

    }

    // Create new offer
    @PostMapping
    public ResponseEntity<Void> createOffer(@RequestBody Offer offer) {

        offerService.insertOffer(offer);

        HttpHeaders headers = new HttpHeaders();
        // url 생성
        URI locationUri = ServletUriComponentsBuilder
                .fromCurrentRequest()  // 현재 요청 URL 기준 (예: /api/offers)
                .path("/{id}")        // 여기에 /{id} 추가 → /api/offers/{id}
                .buildAndExpand(offer.getId())  // {id}를 실제 ID로 치환
                .toUri();  // URI 객체로 변환

        headers.setLocation(locationUri);

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

    }

    //Update an offer
    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable("id") int id, @RequestBody Offer offer){

        Offer currentOffer = offerService.getOfferById(id);
        if(currentOffer == null)
            throw new OfferNotFoundException(id);

        currentOffer.setName(offer.getName());
        currentOffer.setEmail(offer.getEmail());
        currentOffer.setText(offer.getText());

        offerService.updateOffer(currentOffer);

        return new ResponseEntity<Offer>(currentOffer, HttpStatus.OK);
    }

    // delete an offer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable("id") int id) {

        Offer currentOffer = offerService.getOfferById(id);
        if(currentOffer == null)
            throw new OfferNotFoundException(id);

        offerService.deleteOfferById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }
}
