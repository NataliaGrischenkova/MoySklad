package tests;

import models.Account;
import models.AccountFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AccountTest extends BaseTest {

    @Test
    void validPersonalDataShouldBeSavedSuccessfully() throws InterruptedException {
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
