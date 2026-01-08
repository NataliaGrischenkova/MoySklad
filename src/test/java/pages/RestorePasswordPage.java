package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePasswordPage extends BasePage {

    private static final By SIGN_UP_BUTTON = By.id("submit");

    public RestorePasswordPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return isExist(SIGN_UP_BUTTON);
    }

    @Override
    public void open() {

    }
}
