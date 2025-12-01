package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final By LOGO = By.xpath ("//img[@class='topMenuItem-img-new']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return isExist(LOGO);
    }

    @Override
    public void open() {
        driver.get(BASE_URL + "/app/#homepage");
    }
}
