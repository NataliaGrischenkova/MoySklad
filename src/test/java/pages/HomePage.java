package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final By MENU_SUB_ITEM = By.xpath("//span[@title='Начало работы' and @class='subMenuItem-new active']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return isExist(MENU_SUB_ITEM);
    }

    @Override
    public void open() {
        driver.get(BASE_URL + "/app/#homepage");
    }
}
