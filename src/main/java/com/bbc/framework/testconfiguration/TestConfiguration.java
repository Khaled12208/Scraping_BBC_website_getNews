package com.bbc.framework.testconfiguration;

import org.openqa.selenium.Dimension;

// This Class is the Configuration Holder for executing the tests
public class TestConfiguration {

    // The Browser Configuration parameters
    private final BrowserType Browser;
    private final ExecutionMode Mode;
    private final ExecutionPrivacy Privacy;
    private final BrowserSize size;
    private final Dimension BrowseDimensions;
    private final Integer ImplicitWait;

    protected TestConfiguration(BrowserType browser, ExecutionPrivacy Privacy, Dimension BrowseDimensions, BrowserSize size, ExecutionMode Mode, Integer ImplicitWait) {
        this.size=size;
        this.Privacy = Privacy;
        this.Browser = browser;
        this.BrowseDimensions = BrowseDimensions;
        this.ImplicitWait= ImplicitWait;
        this.Mode=Mode;
    }
    public BrowserSize getBrowserSize() {
        return size;
    }

    public BrowserType getBrowserType() {
        return Browser;
    }

    public ExecutionPrivacy getWindowType() {
        return Privacy;
    }


    public Dimension getBrowseDimensions() {
        return BrowseDimensions;
    }

    public Integer getImplicitWait() {
        return ImplicitWait;
    }

    public ExecutionMode getExecutionMode() {
        return Mode;
    }
}
