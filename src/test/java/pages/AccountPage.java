package pages;

import elements.Button;
import elements.Input;
import models.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;

public class AccountPage extends BasePage {

    private static final By LABEL = By.xpath("//span[text() = 'Отчество']");
    private static final By CONFIRM_NOTIFICATION = By.xpath("//td[@class = 'dialogMiddleCenter']" +
            "//div[@class = 'gwt-Label']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return isExist(LABEL);
    }

    @Override
    public void open() {
        driver.get(BASE_URL + "app/#account");
    }

    public void createSettings(Account account) {
        new Input(driver, "Имя").clearAndWrite(account.getName());
        new Input(driver, "Отчество").clearAndWrite(account.getSecondname());
        new Input(driver, "Фамилия").clearAndWrite(account.getLastname());
        new Input(driver, "Телефон").clearAndWrite(account.getPhone());
        new Input(driver, "Должность").clearAndWrite(account.getPosition());
        new Input(driver, "ИНН").clearAndWrite(account.getINN());

        new Button(driver).clickButton("Сохранить");
    }

    public boolean isSettingsSaved() {
        return driver.findElement(CONFIRM_NOTIFICATION).isDisplayed();
    }
}
