package edu.ykuchirka.Dao;

import edu.ykuchirka.Entity.Offer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by Yura on 29.03.2017.
 */
@Repository
@Qualifier("offerDao")
public class OfferCashedProxy extends CashedProxy implements OfferDao {

    public OfferCashedProxy() {
        super(Offer.class);
    }

    @Override
    public Offer find(int id) {
        // TODO: completely change
        if (list != null) {
            for (Object o: list) {
                Offer offer = (Offer) o;
                if (offer.getOfferId() == id) {
                    return offer;
                }
            }
        } else {
            throw new RuntimeException(CASED_LIST_IS_EMPTY);
        }

        return null;
    }
}
