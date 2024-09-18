package base;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.*;

public class Hooks extends BasePage {

    public Hooks() throws IOException {
        super();
    }

    @AfterSuite
    public void setup() throws IOException {
        getDriver().get(getUrl());
    }

    @AfterSuite
    public void tearDown() {
        WebDriverInstance.cleanupDriver();
    }

}
