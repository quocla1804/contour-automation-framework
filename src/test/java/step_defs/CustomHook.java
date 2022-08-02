package step_defs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.factory.DriverFactory;

import java.net.MalformedURLException;

public class CustomHook {
    private final static Logger LOGGER = LogManager.getLogger("Cucumber");

    @Given("I open a web browser")
    @Before("@web")
    public void iOpenAWebBrowser() throws MalformedURLException {
        DriverFactory.createWebInstance();
        DriverFactory.initPages("page_objects.web", DriverFactory.getWebDriver());
    }

    @After()
    public void afterEachScenario(Scenario scenarioResult) {
        try {
            if (scenarioResult.isFailed()) {
                testDataEmbeddedOnFail(scenarioResult);
                screenshotOnFail(scenarioResult);
            }
        } catch (Exception e) {
            LOGGER.error("Unable to take screenshot");
        } finally {
            DriverFactory.dismissInstances();
        }
    }

    private void testDataEmbeddedOnFail(Scenario scenario) {
        String url = "";
        if (DriverFactory.isWebDriverRun) {
            url = "\nURL: " + DriverFactory.getWebDriver().getCurrentUrl();
        }
        scenario.write("TEST DATA USED:\n" + "{YOUR CUSTOM OUTPUT}" + url);
    }

    private void screenshotOnFail(Scenario scenarioResult) {
        takeScreenshot(scenarioResult);
        String yourCustomOutput = "";
        LOGGER.error(String.format("The Test was failed with following test data: %s", yourCustomOutput));
        LOGGER.info(String.format("Test result: %s", scenarioResult.getStatus()));
    }

    private void takeScreenshot(Scenario scenario) {
        if (DriverFactory.isWebDriverRun) {
            LOGGER.info("Embedding Web SS");
            byte[] webss = ((RemoteWebDriver) DriverFactory.getWebDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(webss, "image/png");
        }
    }
}
