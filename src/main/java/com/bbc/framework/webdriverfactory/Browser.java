package com.bbc.framework.webdriverfactory;

import com.bbc.framework.testconfiguration.TestConfiguration;
import org.openqa.selenium.WebDriver;

public interface Browser  {

    WebDriver GetBrowser(TestConfiguration config) throws Exception ;

}
