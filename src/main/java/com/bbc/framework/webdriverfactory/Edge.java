package com.bbc.framework.webdriverfactory;

import com.bbc.framework.testconfiguration.TestConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Edge implements Browser {

    // This Class is the Edge Browser initializer

    private WebDriver driver;
    private EdgeOptions options;
    private DesiredCapabilities capabilities;


    @Override
    public WebDriver GetBrowser(TestConfiguration config) throws Exception {
        try {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(SetBrowserOption(config));
            return driver;

        } catch (Exception e) {
            throw new Exception(" Please Make Sure that the Edge Browser is installed in your system : \n "+e.fillInStackTrace());

        }

    }

    private EdgeOptions SetBrowserOption(TestConfiguration config)
    {
        options = new EdgeOptions();
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
        }
        if(config.getWindowType().toString().equalsIgnoreCase("PRIVATE"))
        {
            options.addArguments("inprivate");

        }
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return  options.merge(capabilities);

    }
}
