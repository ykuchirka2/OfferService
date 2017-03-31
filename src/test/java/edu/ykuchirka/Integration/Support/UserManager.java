package edu.ykuchirka.Integration.Support;

import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.usermanager.AnonymousAuthentication;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.UsernamePasswordAuthentication;
import org.apache.ftpserver.usermanager.impl.AbstractUserManager;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.ConcurrentLoginPermission;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.util.Arrays;

/**
 * Created by Yura on 25.03.2017.
 */
public class UserManager extends AbstractUserManager {
    private BaseUser testUser;
    private BaseUser anonimUser;

    private String tastLogin;
    private String testPassword;

    public UserManager(String homeDirectory) {
        super("admin", new ClearTextPasswordEncryptor());

        tastLogin = PropertiesManager.getInstance().getProperty("userid");
        testPassword = PropertiesManager.getInstance().getProperty("password");

        testUser = new BaseUser();
        testUser.setAuthorities(Arrays.asList(new Authority[] {new ConcurrentLoginPermission(1,1), new WritePermission()}));
        testUser.setEnabled(true);
        testUser.setHomeDirectory(homeDirectory);
        testUser.setMaxIdleTime(10000);
        testUser.setName(tastLogin);
        testUser.setPassword(testPassword);
    }

    @Override
    public User getUserByName(String s) throws FtpException {
        if (tastLogin.equals(s)) {
            return testUser;
        } else if(anonimUser.getName().equals(s)) {
            return anonimUser;
        }
        return null;
    }

    @Override
    public String[] getAllUserNames() throws FtpException {
        return new String[] {tastLogin, anonimUser.getName()};
    }

    @Override
    public void delete(String s) throws FtpException {
        throw new UnsupportedOperationException("Deliting of user is not supported");
    }

    @Override
    public void save(User user) throws FtpException {
        throw new UnsupportedOperationException("Saving of user is not supported");
    }

    @Override
    public boolean doesExist(String s) throws FtpException {
        return (tastLogin.equals(s) || anonimUser.getName().equals(s)) ? true : false;
    }

    @Override
    public User authenticate(Authentication authentication) throws AuthenticationFailedException {
        if(UsernamePasswordAuthentication.class.isAssignableFrom(authentication.getClass())) {
            UsernamePasswordAuthentication userNamePasswordAuthentication = (UsernamePasswordAuthentication)authentication;

            if(tastLogin.equals(userNamePasswordAuthentication.getUsername()) && testPassword.equals(userNamePasswordAuthentication.getPassword())) {
                return testUser;
            }

            if(anonimUser.getName().equals(userNamePasswordAuthentication.getUsername())) {
                return anonimUser;
            }
        } else if (AnonymousAuthentication.class.isAssignableFrom(authentication.getClass())) {
            return  anonimUser;
        }
        return null;
    }
}
