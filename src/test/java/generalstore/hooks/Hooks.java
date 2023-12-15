package generalstore.hooks;

import generalstore.utils.ConfigReader;
import generalstore.utils.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;


public class Hooks {
    @BeforeAll
    public static void beforeAll() {
        Driver.serverBaslat(ConfigReader.getProperty("localIPAdres"), Integer.parseInt(ConfigReader.getProperty("localPort")));

    }

    @Before
    public void setUp() {
        Driver.getDriver().activateApp("com.androidsample.generalstore");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshotAs = Driver.getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotAs, "image/png", "HataResmi");
            Driver.getDriver().terminateApp("com.androidsample.generalstore");

            // 26. satirda hata olursa package kismini yerine ativity i al cmd den
        }



        Driver.getDriver().terminateApp("com.androidsample.generalstore");
    }

    @AfterAll
    public static void afterAll() {
        Driver.uygulamayiKapat();
        Driver.serverKapat();


    }
}