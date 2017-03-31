package edu.ykuchirka.Integration.Support;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.Listener;
import org.apache.ftpserver.listener.ListenerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


/**
 * Created by Yura on 24.03.2017.
 */
public class FtpServerManager {

    private static final Logger LOGGER = LoggerFactory.getLogger("edu.ykuchirka.Integration.Support.FtpServerManager");

    private static final String FTP_ROOT_DIR = "target" + File.separator + "ftproot";

    private static FtpServer server;

    public FtpServerManager() throws FtpException {

        File ftpRoot = new File(FTP_ROOT_DIR);
        ftpRoot.mkdirs();

        UserManager userManager = new UserManager(ftpRoot.getAbsolutePath());

        FtpServerFactory serverFactory = new FtpServerFactory();
        serverFactory.setUserManager(userManager);
        ListenerFactory listenerFactory = new ListenerFactory();

        String sServerPort = PropertiesManager.getInstance().getProperty("serverPort");
        int serverPort = Integer.parseInt(sServerPort);
        listenerFactory.setPort(serverPort);

        serverFactory.addListener("main", listenerFactory.createListener());

        server = serverFactory.createServer();

        server.start();

        Listener listener = serverFactory.getListeners().values().iterator().next();

        serverPort = listener.getPort();
        LOGGER.info("FTP server uses port: " + serverPort);
    }
}
