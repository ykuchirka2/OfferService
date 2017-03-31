package edu.ykuchirka.Service;

import edu.ykuchirka.Dao.OfferDao;
import edu.ykuchirka.Entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Yura on 16.03.2017.
 */
@Service
@Qualifier("offerService")
public class OfferService {

    @Autowired
    @Qualifier("offerDao")
    private OfferDao OfferDao;

    public Collection<Offer> getAllOffers(){
        return OfferDao.findAll();
    }

    public void setAllOffers(Collection<Offer> offers){
        for (Offer offer: offers ) {
            this.OfferDao.insert(offer);
        }
    }

    public Offer getOfferById(int id){
        return (Offer) this.OfferDao.find(id);
    }

    public void removeOfferById(int id) {
        this.OfferDao.remove(id);
    }

    public void updateOffer(Offer offer) {
        this.OfferDao.update(offer);
    }

    public void insertOffer(Offer offer) {
        this.OfferDao.insert(offer);
    }
}
