package ru.pft40.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FtpHelper {

    private final ApplicationManager appMngr;
    private final FTPClient ftp;


    public FtpHelper(ApplicationManager appMngr) {
        this.appMngr = appMngr;
        ftp = new FTPClient();
    }

    public void upload(File file, String target, String backup) throws IOException {
        ftp.connect(appMngr.getProperty("ftp.host"));
        ftp.login(appMngr.getProperty("ftp.login"), appMngr.getProperty("ftp.password"));
        ftp.deleteFile(backup);
        ftp.rename(target, backup);
        ftp.enterLocalPassiveMode();
        ftp.storeFile(target, new FileInputStream(file));
        ftp.disconnect();
    }

    public void restore(String backup, String target) throws IOException {
        ftp.connect(appMngr.getProperty("ftp.host"));
        ftp.login(appMngr.getProperty("ftp.login"), appMngr.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup, target);
        ftp.disconnect();
    }
}
