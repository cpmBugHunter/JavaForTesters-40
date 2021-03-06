package ru.pft40.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.pft40.mantis.model.AccountData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {
    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private DbHelper dbHelper;
    private NavigationHelper navigationHelper;
    private AccountHelper accountHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        try (FileReader fr = new FileReader(new File (String.format("src/test/resources/%s.properties", target))))
        {
            properties.load(fr);
            dbHelper = new DbHelper();
        }
    }


    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public AccountHelper account() {
        if (accountHelper == null) {
            accountHelper = new AccountHelper(this);
        }
        return accountHelper;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }
    
    public DbHelper db() {
        return dbHelper;
    }

    public NavigationHelper goTo() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            }
            else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            }
            else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
}
