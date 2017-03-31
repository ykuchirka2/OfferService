package edu.ykuchirka.Service;

import edu.ykuchirka.Entity.Offer;
import edu.ykuchirka.Integration.OfferGateway;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;


/**
 * Created by Yura on 30.03.2017.
 */
@Component
public class IntegrationService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("edu.ykuchirka.Service.IntegrationService");

    @Autowired
    private OfferService offerService;

    @PostConstruct
    public void init(){
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
                        "edu/ykuchirka/OfferServiceXML-context.xml");

        OfferGateway gw =  ctx.getBean(OfferGateway.class);

        Collection<Offer> offers = gw.push();
        offerService.setAllOffers(offers);

        // TODO: need to clarify task if this WEB server should await new file from FTP all the time
        offers = gw.push();
        offerService.setAllOffers(offers);

        ctx.close();
    }

}
