package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    void loginPageShouldBeOpened() {
        loginPage.open();

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");
    }

    @Test
    void userShouldBeLoginWithValidLoginAndPassword() {
        loginPage.open();
        loginPage.enterEmail("admin@pwutuw");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();
        homePage.isPageOpened();

        assertTrue(homePage.isPageOpened(), "Страница не открыта");
    }

    @Test
    void loginWithEmptyFieldEmailShouldShowError() {
        loginPage.open();
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Чтобы войти, укажите имя пользователя",
                "Сообщение об ошибке неверное");
    }

    @Test
    void loginWithEmptyFieldPasswordShouldShowError() {
        loginPage.open();
        loginPage.enterEmail("admin@pwutuw");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Чтобы войти, укажите пароль",
                "Сообщение об ошибке неверное");
    }

    @Test
    void loginWithEmptyFieldsShouldShowError() {
        loginPage.open();
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Чтобы войти, укажите имя пользователя",
                "Сообщение об ошибке неверное");
    }

    @Test
    void loginWithWrongCredentialsShouldShowError() {
        loginPage.open();
        loginPage.enterEmail("admin@pwutuw");
        loginPage.enterPassword("123456as");
        loginPage.clickLoginButton();

        assertTrue(loginPage.getErrorMessage()
                        .contains("Неправильный пароль или имя пользователя. Посмотрите, что можно сделать."),
                "Сообщение об ошибке неверное");
    }

    @Test
    void emailFieldShouldNotAllowMoreThan255Characters() {
        loginPage.open();
        String longEmail = "a".repeat(256);
        loginPage.enterEmail(longEmail);
        String actualText = loginPage.getEmailValue();

        assertEquals(actualText.length(), 255);
    }

    @Test
    void invalidEmailFormatShouldShowError() {
        loginPage.open();
        loginPage.enterEmail("adminpwutuw");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();
        String actualError = loginPage.getErrorMessage();

        assertEquals(actualError,
                "Неверный формат имени пользователя. Укажите свою учетную запись, например admin@romashka.",
                "Ошибка валидации email отображается неверно");
    }

    @Test
    void shouldShowErrorForEmailWithoutDomain() {
        loginPage.open();
        loginPage.enterEmail("admin@");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Неверный формат имени пользователя. Укажите свою учетную запись, например admin@romashka.",
                "Ошибка валидации email отображается неверно");
    }

    @Test
    void shouldShowErrorForEmailWithoutUsername() {
        loginPage.open();
        loginPage.enterEmail("@pwutuw");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Неверный формат имени пользователя. Укажите свою учетную запись, например admin@romashka.",
                "Ошибка валидации email отображается неверно");
    }

    @Test
    void shouldShowErrorForEmailWithSpaces() {
        loginPage.open();
        loginPage.enterEmail("ad min@pwutuw");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Неправильный пароль или имя пользователя. Посмотрите, что можно сделать.",
                "Ошибка валидации email отображается неверно");
    }

    @Test
    void shouldShowErrorForEmailWithRussianLetters() {
        loginPage.open();
        loginPage.enterEmail("админ@pwutuw");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Неправильный пароль или имя пользователя. Посмотрите, что можно сделать.",
                "Ошибка валидации email отображается неверно");
    }

    @Test
    void shouldShowErrorForEmailWithInvalidSymbols() {
        loginPage.open();
        loginPage.enterEmail("admin!#$@pwutuw");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Неправильный пароль или имя пользователя. Посмотрите, что можно сделать.",
                "Ошибка валидации email отображается неверно");
    }
}
