package com.bbc.framework.webdriverfactory;


import com.bbc.framework.testconfiguration.TestConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class DriverFactory {

    // this class is the web driver factory where the browser driver is init

    private TestConfiguration configuration;
    private WebDriver driver;
    private String WebURL;
    protected static final Logger logger = LogManager.getLogger(DriverFactory.class);

    // constructor to take the config from config builder
    public DriverFactory(TestConfiguration configuration)
    {
        this.configuration=configuration;

    }

    // Function to return webdriver
    public WebDriver OpenBrowser() throws Exception {

        if(configuration.getBrowserType()!=null){
            switch (configuration.getBrowserType()) {
                case CHROME:
                    driver = new Chrome().GetBrowser(configuration);
                    driver =  SetupConfig(driver);
                    logger.debug("invoking Chrome web browser");
                    break;
                case EDGE:
                    driver = new Edge().GetBrowser(configuration);
                    driver =  SetupConfig(driver);
                    logger.debug("invoking Edge web browser");
                    break;
                case FIREFOX:
                    driver = new FireFox().GetBrowser(configuration);
                    logger.debug("invoking FireFox browser");
                    driver =  SetupConfig(driver);
                    break;

            }
            return driver;
        }else
        {
            logger.error("couldn't invoke web browser");
            throw new Exception(" Unable to create browser driver, make sure to set a browser type");

        }
    }

    // Function to return Setup the webDriver with the config
    private WebDriver SetupConfig(WebDriver driver) throws Exception {

        if (configuration.getBrowserSize()==null && configuration.getBrowseDimensions()!=null){

            driver.manage().window().setSize(configuration.getBrowseDimensions());
        }else if (configuration.getBrowserSize()!=null && configuration.getBrowseDimensions()==null) {

            if (configuration.getBrowserSize().toString().equalsIgnoreCase("MIN")) {

                driver.manage().window().minimize();

            } else {
                driver.manage().window().maximize();
            }
        }
        else if(configuration.getBrowserSize()==null && configuration.getBrowseDimensions()==null) {


        }else
        {
            throw new Exception(" Browser widows size conflict, Please Make sure to provide one way for windows size setting ");

        }
        return driver;
    }

}
