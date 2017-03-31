package edu.ykuchirka.Integration;

import com.opencsv.CSVReader;
import edu.ykuchirka.Entity.Offer;
import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yura on 28.03.2017.
 */
public class CsvOfferReaderTest {

    private static final String TEST_STRING = "'TM_activity_id'~'activity_group_id'~'campaign_type_cd'~'store_id'~'offer_id'~'content_id'~'offer_desc'\n" +
            "'16030001'~'16030001'~'3'~'0'~'1'~'222222'~'Cukierki, lizaki, zelki, draze, gumy, chalwa i sezamki'\n" +
            "'16030001'~'16030001'~'3'~'0'~'2'~'333333'~'Jaja'";
    private static final String TEST_STRING_S1 = "'offer_id'~'offer_desc'\n" +
            "'1'~'Cukierki, lizaki, zelki, draze, gumy, chalwa i sezamki'\n" +
            "'2'~'Jaja'";
    public static final char CSV_QUOTE_CHAR = '\'';
    public static final char CSV_SEPARATOR = '~';


    @Test
    public void readAndBindFile() throws Exception {
        CsvOfferReader csvOfferReader = new CsvOfferReader();
        CSVReader reader = createReader(TEST_STRING);

        List<Offer> beanList = csvOfferReader.readAndBindFile(reader);

        assertEquals(2, beanList.size());

        List<String> expectedOfferDesc = Arrays.asList("Cukierki, lizaki, zelki, draze, gumy, chalwa i sezamki", "Jaja");
        List<Integer> offerId = Arrays.asList(1, 2);
        for (Offer bean : beanList) {
            assertTrue(expectedOfferDesc.contains(bean.getOfferDesc()));
            assertTrue(offerId.contains(bean.getOfferId()));
        }
    }

    private CSVReader createReader(String testString) {
        StringReader reader = new StringReader(testString);
        return new CSVReader(reader, CSV_SEPARATOR, CSV_QUOTE_CHAR);
    }

}