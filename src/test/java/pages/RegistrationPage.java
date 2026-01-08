package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    private static final By RESTORE_PASSWORD_BUTTON = By.id("submit");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return isExist(RESTORE_PASSWORD_BUTTON);
    }

    @Override
    public void open() {
        driver.get(BASE_URL + "/restorePassword");
    }
}
