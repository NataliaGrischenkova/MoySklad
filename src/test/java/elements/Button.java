package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;

public class Button {

    WebDriver driver;
    String buttonLocator = "//span[text()='%s']";

    public Button(WebDriver driver) {
        this.driver = driver;
    }

    public void  clickButton(String buttonText) {
        driver.findElement(By.xpath(format(buttonLocator, buttonText))).click();
    }
}
