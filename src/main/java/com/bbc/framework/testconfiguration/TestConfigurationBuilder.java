package com.bbc.framework.testconfiguration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;

// This Class is the Configuration Builder

public class TestConfigurationBuilder {

    private BrowserType Browser;
    private ExecutionMode Mode =ExecutionMode.HEADFULL;
    private ExecutionPrivacy Privacy = ExecutionPrivacy.PUBLIC;
    private Dimension BrowseDimensions;
    private BrowserSize size;
    private Integer ImplicitWait;
    protected static final Logger logger = LogManager.getLogger(TestConfigurationBuilder.class);


    public TestConfigurationBuilder setExecutionMode(ExecutionMode Mode) {
        this.Mode= Mode;
        logger.debug("Execution mode is " + Mode.toString());
        return this;
    }
    public TestConfigurationBuilder setWindowType(ExecutionPrivacy ExecutionPrivacy) {
        this.Privacy = ExecutionPrivacy;
        logger.debug("Execution privacy is " + Privacy.toString());
        return this;
    }

    public TestConfigurationBuilder setBrowserCustomDimensions(Integer BrowserWidth,Integer BrowserHeight ) {
         this.BrowseDimensions = new Dimension(BrowserWidth,BrowserHeight);
        logger.debug("Execution Dimensions is " + BrowseDimensions.toString());
        return this;
    }
    public TestConfigurationBuilder setBrowserCustomDimensions(BrowserSize size) {
        this.size=size;
        logger.debug("Execution Dimensions is " + size.toString());
        return this;
    }


    public TestConfigurationBuilder setBrowser(BrowserType Browser ) {
        this.Browser= Browser;
        logger.debug("Execution Browser is " + Browser.toString());
        return this;
    }
    public TestConfigurationBuilder setImplicitWaitInSec(Integer ImplicitWait ) {
        this.ImplicitWait= ImplicitWait;
        logger.debug("implicate wait set to be " + ImplicitWait.toString());
        return this;
    }

    public TestConfiguration Build()
    {
        return new TestConfiguration(Browser, Privacy,BrowseDimensions,size, Mode,ImplicitWait);
    }

}
