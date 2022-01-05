package com.bbc.framework.webdriverfactory;

import com.bbc.framework.testconfiguration.TestConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Chrome implements Browser {

    // This Class is the Chrome Browser initializer

    private WebDriver driver;
    private ChromeOptions options;
    private DesiredCapabilities capabilities;



    @Override
    public WebDriver GetBrowser(TestConfiguration config) throws Exception {
        try {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(SetBrowserOption(config));
            return driver;

        } catch (Exception e) {
            throw new Exception(" Please Make Sure that the Chrome Browser is installed in your system : \n "+e.fillInStackTrace());

        }

    }

    private ChromeOptions SetBrowserOption(TestConfiguration config)
    {
        options = new ChromeOptions();
        capabilities= new DesiredCapabilities();
        if(config.getExecutionMode().toString().equalsIgnoreCase("HEADLESS"))
        {
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-extensions");
            options.addArguments("--proxy-server='direct://'");
            options.addArguments("--proxy-bypass-list=*");
            options.addArguments("--start-maximized");
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--allow-running-insecure-content");
            options.addArguments("--user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.50 Safari/537.36");
        }
        if(config.getWindowType().toString().equalsIgnoreCase("PRIVATE"))
        {
            options.addArguments("--incognito");

        }
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return  options.merge(capabilities);

    }
}
