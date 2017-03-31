package edu.ykuchirka.Integration;

import edu.ykuchirka.Entity.Offer;

import java.util.Collection;

/**
 * Defines a contract that decouples client from the Spring Integration framework.
 */
public interface OfferGateway {

    Collection<Offer> push();

}
