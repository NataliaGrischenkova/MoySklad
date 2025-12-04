package tests;

import models.Account;
import models.AccountFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AccountTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(AccountTest.class);

    @Test
    void settingsTest() throws InterruptedException {
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
