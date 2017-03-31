package edu.ykuchirka.Integration;

import edu.ykuchirka.Entity.Offer;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Yura on 29.03.2017.
 */
@Component
public class OfferListFilter {

    // TODO: change it implement Design Pattern "Strategy"
    // TODO: make it adjusable from XML
    @Transformer
    public Collection<Offer> checkOfferList(Collection<Offer> offerCollection) {
        Collection<Offer> checkedOfferList = new ArrayList<>();

        for (Offer offer: offerCollection) {
            if (accept(offer)) {
                checkedOfferList.add(offer);
            }
        }

        return checkedOfferList;
    }

    public boolean accept(Offer offer) {

        boolean result = (offer.getOfferDesc() != null) && (offer.getOfferDesc() != "");
        result = result && (offer.getOfferId() != 0);
        return result;

    }

}
