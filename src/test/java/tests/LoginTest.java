package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @DataProvider(name = "Negative data")
    public Object[][] inputForITechTask() {
        return new Object[][]{
                {"", "abvgd12345", "Чтобы войти, укажите имя пользователя"},
                {"admin@pwutuw", "", "Чтобы войти, укажите пароль"},
                {"", "", "Чтобы войти, укажите имя пользователя"},
                {"adminpwutuw", "abvgd12345", "Неверный формат имени пользователя. Укажите свою учетную запись, например admin@romashka."},
        };
    }

    @Test(dataProvider = "Negative data")
    public void errorMessages(String email, String password, String errorMessage) {
        loginPage.open();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                errorMessage, "Сообщение об ошибке неверное");
    }

    @Test(description = "Проверка открытия страницы авторизации")
    void loginPageShouldBeOpened() {
        loginPage.open();

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");
    }

    @Test(description = "Проверка успешной авторизации с валидными логином и паролем и перехода на главную страницу")
    void userShouldBeLoginWithValidLoginAndPassword() {
        loginPage.open();
        loginPage.enterEmail("admin@pwutuw");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();
        homePage.isPageOpened();

        assertTrue(homePage.isPageOpened(), "Страница не открыта");
    }

    @Test(description = "Проверка появления ошибки при попытке входа с пустым полем Email")
    void loginWithEmptyFieldEmailShouldShowError() {
        loginPage.open();
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Чтобы войти, укажите имя пользователя",
                "Сообщение об ошибке неверное");
    }

    @Test(description = "Проверка появления ошибки при попытке входа с пустым полем Пароль")
    void loginWithEmptyFieldPasswordShouldShowError() {
        loginPage.open();
        loginPage.enterEmail("admin@pwutuw");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Чтобы войти, укажите пароль",
                "Сообщение об ошибке неверное");
    }

    @Test(description = "Проверка появления ошибки при попытке входа с пустыми полями Email и Пароль")
    void loginWithEmptyFieldsShouldShowError() {
        loginPage.open();
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Чтобы войти, укажите имя пользователя",
                "Сообщение об ошибке неверное");
    }

    @Test(description = "Проверка появления ошибки при вводе неверного пароля")
    void loginWithWrongPasswordShouldShowError() {
        loginPage.open();
        loginPage.enterEmail("admin@pwutuw");
        loginPage.enterPassword("123456as");
        loginPage.clickLoginButton();

        assertTrue(loginPage.getErrorMessage()
                        .contains("Неправильный пароль или имя пользователя. Посмотрите, что можно сделать."),
                "Сообщение об ошибке неверное");
    }

    @Test(description = "Проверка максимальной длины поля Email (255 символов)")
    void emailFieldShouldNotAllowMoreThan255Characters() {
        loginPage.open();
        String longEmail = "a".repeat(256);
        loginPage.enterEmail(longEmail);
        String actualText = loginPage.getEmailValue();

        assertEquals(actualText.length(), 255);
    }

    @Test(description = "Проверка ошибки валидации при вводе Email в некорректном формате")
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

    @Test(description = "Проверка ошибки при Email без доменной части")
    void shouldShowErrorForEmailWithoutDomain() {
        loginPage.open();
        loginPage.enterEmail("admin@");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Неверный формат имени пользователя. Укажите свою учетную запись, например admin@romashka.",
                "Ошибка валидации email отображается неверно");
    }

    @Test(description = "Проверка ошибки при Email без имени пользователя")
    void shouldShowErrorForEmailWithoutUsername() {
        loginPage.open();
        loginPage.enterEmail("@pwutuw");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Неверный формат имени пользователя. Укажите свою учетную запись, например admin@romashka.",
                "Ошибка валидации email отображается неверно");
    }

    @Test(description = "Проверка ошибки при Email с пробелами")
    void shouldShowErrorForEmailWithSpaces() {
        loginPage.open();
        loginPage.enterEmail("ad min@pwutuw");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Неправильный пароль или имя пользователя. Посмотрите, что можно сделать.",
                "Ошибка валидации email отображается неверно");
    }

    @Test(description = "Проверка ошибки при Email с русскими буквами")
    void shouldShowErrorForEmailWithRussianLetters() {
        loginPage.open();
        loginPage.enterEmail("админ@pwutuw");
        loginPage.enterPassword("abvgd12345");
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(),
                "Неправильный пароль или имя пользователя. Посмотрите, что можно сделать.",
                "Ошибка валидации email отображается неверно");
    }

    @Test(description = "Проверка ошибки при Email с недопустимыми символами")
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
