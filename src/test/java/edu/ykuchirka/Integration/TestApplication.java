package edu.ykuchirka.Integration;

import edu.ykuchirka.Application;
import org.apache.ftpserver.ftplet.FtpException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by Yura on 31.03.2017.
 */
public class TestApplication {

    public static final Logger LOGGER = LoggerFactory.getLogger(FtpServerTest.class);

    @Test
    public void testWholeApplication() throws ExecutionException, InterruptedException {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        Future<String> futureFtpServer = executor.submit(new Callable<String>() {
            public String call() throws InterruptedException, FtpException, IOException {
                LOGGER.info("=====================FTP server starting...=======================");

                (new FtpServerTest()).setupFtpServer();

                LOGGER.info("=====================FTP server started===========================");

                TimeUnit.SECONDS.sleep(60);

                LOGGER.info("=====================FTP server finished==========================");
                return "";
            }
        });

        Future<String> futureWebApp = executor.submit(new Callable<String>() {
            public String call() throws InterruptedException {
                LOGGER.info("=====================WEB app starting...=======================");

                SpringApplication.run(Application.class);

                LOGGER.info("=====================WEB app started===========================");

                TimeUnit.SECONDS.sleep(60);

                LOGGER.info("=====================WEB app finished==========================");
                return "";
            }
        });

        futureFtpServer.get();
        futureWebApp.get();
    }

}
