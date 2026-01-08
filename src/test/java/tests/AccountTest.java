package tests;

import io.qameta.allure.Owner;
import models.Account;
import models.AccountFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Owner("natalia")
public class AccountTest extends BaseTest {

    @Test
    void validPersonalDataShouldBeSavedSuccessfully() {
        loginPage.open();
        loginPage.enterEmail("admin@pwutuw");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();
        accountPage.open();

        Account account = AccountFactory.get();
        accountPage.createSettings(account);

        assertTrue(accountPage.isSettingsSaved());
    }
}
