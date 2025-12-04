package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;

public class Input {

    WebDriver driver;
    String label;
    String inputLocator = "//span[text() = '%s']/ancestor::tr[@class]//input";

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void clearAndWrite(String text) {
        driver.findElement(By.xpath(format(inputLocator, this.label))).clear();
        driver.findElement(By.xpath(format(inputLocator, this.label))).sendKeys(text);
    }
}
