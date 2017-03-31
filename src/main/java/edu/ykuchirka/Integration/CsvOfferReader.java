package edu.ykuchirka.Integration;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import edu.ykuchirka.Entity.Offer;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yura on 28.03.2017.
 */
@Component
public class CsvOfferReader {

    // TODO: add check MAX_LENGTH file
    // TODO: make it adjustable from user.properties
    private final int MAX_LENGTH = 100;
    // TODO: make it adjustable from user.properties
    public static final char CSV_QUOTE_CHAR = '\'';
    // TODO: make it adjustable from user.properties
    public static final char CSV_SEPARATOR = '~';

    @Transformer
    public List<Offer> readOffersFromCsv(GenericMessage message) throws BadMessagePayloadException, FileDoestExistException, CsvReadingException, FileNotFoundException {
        String fileName = inputMessageToFileName(message);
        checkFile(fileName);
        CSVReader reader = createReader(fileName);
        return readAndBindFile(reader);
    }

    private String inputMessageToFileName(GenericMessage message) throws BadMessagePayloadException {

        File file;

        try {
            file = (File) message.getPayload();
        } catch (ClassCastException e) {
            throw new BadMessagePayloadException("Cant convert payload to file.", e);
        }

        return file.toString();
    }

    private void checkFile(String fileName) throws FileDoestExistException {
        File file = new File(fileName);
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            String errMessage = "System cant read file %s or it doesn't exists.";
            errMessage = String.format(errMessage, file);
            throw new FileDoestExistException(errMessage);
        }
    }

    private CSVReader createReader(String fileName) throws FileNotFoundException {
        CSVReader reader = new CSVReader(new FileReader(fileName), CSV_SEPARATOR, CSV_QUOTE_CHAR);
        return reader;
    }

    // TODO: change level of availability
    // TODO: add different strategy 1)map by name 2)map by column position 3)manualy map
    public List<Offer> readAndBindFile(CSVReader reader) throws CsvReadingException {

        List<Offer> beanList = new ArrayList<>();

        try {
            HeaderColumnNameMappingStrategy<Offer> strategy = new HeaderColumnNameMappingStrategy<Offer>();
            strategy.setType(Offer.class);
            CsvToBean<Offer> csvToBean = new CsvToBean<Offer>();

            beanList = csvToBean.parse(strategy, reader);
        } catch (Exception e) {
            // TODO: Add filename
            throw new CsvReadingException("Error reading/binding file ", e);
        }

        return beanList;

    }

}

