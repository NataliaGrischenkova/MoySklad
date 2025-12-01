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

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");

        loginPage.enterEmail("admin@pwutuw");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();
        homePage.isPageOpened();

        assertTrue(homePage.isPageOpened(), "Страница не открыта");
    }

    @Test
    void loginWithEmptyFieldEmailShouldShowError() {
        loginPage.open();

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");

        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Чтобы войти, укажите имя пользователя",
                "Сообщение об ошибке неверное");
    }

    @Test
    void loginWithEmptyFieldPasswordShouldShowError() {
        loginPage.open();

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");

        loginPage.enterEmail("admin@pwutuw");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Чтобы войти, укажите пароль",
                "Сообщение об ошибке неверное");
    }

    @Test
    void loginWithEmptyFieldsShouldShowError() {
        loginPage.open();

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");

        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Чтобы войти, укажите имя пользователя",
                "Сообщение об ошибке неверное");
    }

    @Test
    void loginWithWrongCredentialsShouldShowError() {
        loginPage.open();

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");

        loginPage.enterEmail("admin@pwutuw");
        loginPage.enterPassword("123456as");
        loginPage.clickLoginButton();

        assertTrue(loginPage.getErrorMessage()
                        .contains("Неправильный пароль или имя пользователя. Посмотрите, что можно сделать."),
                "Сообщение об ошибке неверное");
    }

    @Test
    public void emailFieldShouldNotAllowMoreThan255Characters() {
        loginPage.open();

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");

        String longEmail = "a".repeat(300);
        loginPage.enterEmail(longEmail);
        String actualText = loginPage.getEmailValue();

        assertEquals(actualText.length(), 255);
    }

    @Test
    void invalidEmailFormatShouldShowError() {
        loginPage.open();
        
        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");

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

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");

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

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");

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

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");

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

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");

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

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");

        loginPage.enterEmail("admin!#$@pwutuw");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Неправильный пароль или имя пользователя. Посмотрите, что можно сделать.",
                "Ошибка валидации email отображается неверно");
    }
}
