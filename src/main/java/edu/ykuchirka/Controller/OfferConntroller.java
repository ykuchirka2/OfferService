package edu.ykuchirka.Controller;

import edu.ykuchirka.Entity.Offer;
import edu.ykuchirka.Service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Yura on 16.03.2017.
 */
@RestController
@RequestMapping("/offers")
public class OfferConntroller {

    @Autowired
    private OfferService offerService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Offer> getAllOffers(){
        return offerService.getAllOffers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Offer getOfferById(@PathVariable("id") int id) {
        return offerService.getOfferById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeOfferById(@PathVariable("id") int id) {
        offerService.removeOfferById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateOffer(@RequestBody Offer offer) {
        offerService.updateOffer(offer);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertOffer(@RequestBody Offer offer) {
        offerService.insertOffer(offer);
    }

}
