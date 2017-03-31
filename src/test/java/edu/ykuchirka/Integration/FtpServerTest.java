package edu.ykuchirka.Integration;

import edu.ykuchirka.Integration.Support.FtpServerManager;
import org.apache.commons.io.FileUtils;
import org.apache.ftpserver.ftplet.FtpException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Yura on 31.03.2017.
 */
public class FtpServerTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(FtpServerTest.class);

    public static FtpServerManager ftpServer;

    public static final File baseFolder = new File("target" + File.separator + "toSent");

    @Test
    public  void setupFtpServer() throws FtpException, IOException, InterruptedException {
        ftpServer = new edu.ykuchirka.Integration.Support.FtpServerManager();

        ConfigurableApplicationContext ctx =
                new ClassPathXmlApplicationContext("FtpOutboundChannelAdapter-context.xml");

        MessageChannel ftpChannel = ctx.getBean("ftpChannel", MessageChannel.class);

        baseFolder.mkdirs();

        final File fileToSendA = new File(baseFolder,"file1.csv");
        final File fileToSendB = new File(baseFolder,"file2.csv");

        final InputStream inputStreamA = FtpServerTest.class.getResourceAsStream("/test-files/file1.csv");
        final InputStream inputStreamB = FtpServerTest.class.getResourceAsStream("/test-files/file2.csv");

        FileUtils.copyInputStreamToFile(inputStreamA, fileToSendA);
        FileUtils.copyInputStreamToFile(inputStreamB, fileToSendB);

        final Message<File> messageA = MessageBuilder.withPayload(fileToSendA).build();
        final Message<File> messageB = MessageBuilder.withPayload(fileToSendB).build();

        try {
            ftpChannel.send(messageA);
        } catch (Exception e) {
            LOGGER.error("Error sending messageA:",e);
        }
        try {
            ftpChannel.send(messageB);
        } catch (Exception e) {
            LOGGER.error("Error sending messageB:",e);
        }

        LOGGER.info("Succesfully transfered");

        Thread.sleep(20000);

        ctx.close();
    }

}
