package com.bbc.framework.webdriverfactory;

import com.bbc.framework.testconfiguration.TestConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FireFox implements Browser{

    // This Class is the FireFox Browser initializer

    private WebDriver driver;
    private DesiredCapabilities capabilities;
    private FirefoxProfile  profile;
    private FirefoxOptions options;

    @Override
    public WebDriver GetBrowser(TestConfiguration config) throws Exception {

        try
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(SetBrowserOption(config));
            return driver;

        }catch (Exception e)
        {
            throw new Exception(" Please Make Sure that the firefox is installed in your system : \n "+e.fillInStackTrace());

        }
    }

    private FirefoxOptions SetBrowserOption(TestConfiguration config)
    {
        profile = new FirefoxProfile();
        capabilities= new DesiredCapabilities();
        if(config.getExecutionMode().toString().equalsIgnoreCase("HEADLESS"))
        {
            options.setHeadless(true);
            options.addArguments("--headless");

        }
        if(config.getWindowType().toString().equalsIgnoreCase("PRIVATE"))
        {
           // profile.setPreference("browser.private.browsing.autostart",true);
        //    logger.debug("PRIVATE mode is turned ON");

        }
       // capabilities.setCapability("moz:firefoxOptions",options);
        return  options;

    }
}
