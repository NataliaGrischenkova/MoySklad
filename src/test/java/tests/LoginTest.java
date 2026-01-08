package tests;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Owner("natalia")
public class LoginTest extends BaseTest {

    @DataProvider(name = "negativeLoginData")
    public Object[][] inputForITechTask() {
        return new Object[][]{
                {"", "abvgd12345", "Чтобы войти, укажите имя пользователя"},
                {"admin@pwutuw", "", "Чтобы войти, укажите пароль"},
                {"", "", "Чтобы войти, укажите имя пользователя"},
                {"adminpwutuw", "abvgd12345", "Неверный формат имени пользователя. Укажите свою учетную запись, например admin@romashka."}
        };
    }

    @Test(dataProvider = "negativeLoginData", description = "Проверка отображения ошибки при вводе невалидных данных")
    @Issue("MS-1023")
    @TmsLink("MSTC-0")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldShowErrorForInvalidLoginData(String email, String password, String errorMessage) {
        loginPage.open();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        step("Ожидаемый результат: отображается корректное сообщение об ошибке", () ->
                assertEquals(loginPage.getErrorMessage(), errorMessage, "Сообщение об ошибке неверное"));
    }

    @Test(description = "Проверка открытия страницы авторизации")
    @Description("Проверка открытия страницы авторизации")
    @Issue("MS-1024")
    @TmsLink("MSTC-1")
    @Severity(SeverityLevel.CRITICAL)
    void loginPageShouldBeOpened() {
        loginPage.open();

        assertTrue(loginPage.isPageOpened(), "Страница логина не открылась");
    }

    @Test(description = "Проверка успешной авторизации с валидными логином и паролем и перехода на главную страницу")
    @Description("Проверка успешной авторизации с валидными логином и паролем и перехода на главную страницу")
    @Issue("MS-1025")
    @TmsLink("MSTC-2")
    @Severity(SeverityLevel.CRITICAL)
    void userShouldBeLoginWithValidLoginAndPassword() {
        loginPage.open();
        loginPage.enterEmail(System.getProperty("email", "admin@pwutuw"))
                .enterPassword(System.getProperty("password", "abvgd12345"))
                .clickLoginButton();

        step("Ожидаемый результат: Страница HomePage открыта", () ->
                assertTrue(homePage.isPageOpened(), "Страница не открыта"));
    }

    @Test(description = "Проверка появления ошибки при вводе неверного пароля")
    @Description("Пользователь вводит корректный email и неверный пароль и получает сообщение об ошибке авторизации")
    @Issue("MS-1026")
    @TmsLink("MSTC-3")
    @Severity(SeverityLevel.CRITICAL)
    void loginWithWrongPasswordShouldShowError() {
        loginPage.open();
        loginPage.enterEmail("admin@pwutuw")
                .enterPassword("123456as")
                .clickLoginButton();

        step("Ожидаемый результат: отображается сообщение об ошибке авторизации", () ->
                assertTrue(loginPage.getErrorMessage()
                        .contains("Неправильный пароль или имя пользователя. Посмотрите, что можно сделать."),
                        "Сообщение об ошибке неверное"));
    }

    @Test(description = "Проверка максимальной длины поля Email (не более 255 символов)")
    @Description("Проверка, что поле Email не принимает более 255 символов")
    @Issue("MS-1027")
    @TmsLink("MSTC-4")
    @Severity(SeverityLevel.NORMAL)
    void emailFieldShouldNotAllowMoreThan255Characters() {
        loginPage.open();
        String longEmail = "a".repeat(256);
        loginPage.enterEmail(longEmail);
        String actualText = loginPage.getEmailValue();

        step("Ожидаемый результат: в поле Email сохранено не более 255 символов", () ->
                assertEquals(actualText.length(), 255));
    }

    @Test(description = "Проверка ошибки при Email без доменной части")
    @Description("Проверка валидации email при отсутствии доменной части")
    @Issue("MS-1028")
    @TmsLink("MSTC-5")
    @Severity(SeverityLevel.NORMAL)
    void shouldShowErrorForEmailWithoutDomain() {
        loginPage.open();
        loginPage.enterEmail("admin@")
                .enterPassword("abvgd12345")
                .clickLoginButton();

        step("Ожидаемый результат: отображается ошибка некорректного формата Email", () ->
                assertEquals(loginPage.getErrorMessage(),
                "Неверный формат имени пользователя. Укажите свою учетную запись, например admin@romashka.",
                "Ошибка валидации email отображается неверно"));
    }

    @Test(description = "Проверка ошибки при Email без имени пользователя")
    @Description("Проверка валидации email при отсутствии имени пользователя")
    @Issue("MS-1029")
    @TmsLink("MSTC-6")
    @Severity(SeverityLevel.NORMAL)
    void shouldShowErrorForEmailWithoutUsername() {
        loginPage.open();
        loginPage.enterEmail("@pwutuw")
                .enterPassword("abvgd12345")
                .clickLoginButton();

        step("Ожидаемый результат: отображается ошибка некорректного формата Email", () ->
                assertEquals(loginPage.getErrorMessage(),
                "Неверный формат имени пользователя. Укажите свою учетную запись, например admin@romashka.",
                "Ошибка валидации email отображается неверно"));
    }

    @Test(description = "Проверка ошибки при Email с пробелами")
    @Description("Проверка ошибки авторизации при вводе email с пробелами")
    @Issue("MS-1030")
    @TmsLink("MSTC-7")
    @Severity(SeverityLevel.NORMAL)
    void shouldShowErrorForEmailWithSpaces() {
        loginPage.open();
        loginPage.enterEmail("ad min@pwutuw")
                .enterPassword("abvgd12345")
                .clickLoginButton();

        step("Ожидаемый результат: отображается сообщение об ошибке авторизации", () ->
                assertEquals(loginPage.getErrorMessage(),
                "Неправильный пароль или имя пользователя. Посмотрите, что можно сделать.",
                "Ошибка валидации email отображается неверно"));
    }

    @Test(description = "Проверка ошибки при Email с русскими буквами")
    @Description("Проверка ошибки авторизации при вводе email с кириллицей")
    @Issue("MS-1031")
    @TmsLink("MSTC-8")
    @Severity(SeverityLevel.NORMAL)
    void shouldShowErrorForEmailWithRussianLetters() {
        loginPage.open();
        loginPage.enterEmail("админ@pwutuw")
                .enterPassword("abvgd12345")
                .clickLoginButton();

        step("Ожидаемый результат: отображается сообщение об ошибке авторизации", () ->
                assertEquals(loginPage.getErrorMessage(),
                "Неправильный пароль или имя пользователя. Посмотрите, что можно сделать.",
                "Ошибка валидации email отображается неверно"));
    }

    @Test(description = "Проверка ошибки при Email с недопустимыми символами")
    @Description("Проверка ошибки авторизации при вводе email со специальными символами")
    @Issue("MS-1032")
    @TmsLink("MSTC-9")
    @Severity(SeverityLevel.NORMAL)
    void shouldShowErrorForEmailWithInvalidSymbols() {
        loginPage.open();
        loginPage.enterEmail("admin!#$@pwutuw")
                .enterPassword("abvgd12345")
                .clickLoginButton();

        step("Ожидаемый результат: отображается сообщение об ошибке авторизации", () ->
                assertEquals(loginPage.getErrorMessage(),
                "Неправильный пароль или имя пользователя. Посмотрите, что можно сделать.",
                "Ошибка валидации email отображается неверно"));
    }

    @Test(description = "Проверка кликабельности ссылки Регистрация")
    @Description("Проверка перехода со страницы логина на страницу регистрации")
    @Issue("MS-1033")
    @TmsLink("MSTC-10")
    @Severity(SeverityLevel.CRITICAL)
    void registrationLinkShouldOpenRegistrationPage() {
        loginPage.open();
        loginPage.clickRegistration();

        step("Ожидаемый результат: Страница регистрации открыта", () ->
                assertTrue(registrationPage.isPageOpened(),
                        "Страница регистрации не открылась"));
    }

    @Test(description = "Проверка кликабельности ссылки Забыли пароль")
    @Description("Проверка перехода со страницы логина на страницу восстановления пароля")
    @Issue("MS-1034")
    @TmsLink("MSTC-11")
    @Severity(SeverityLevel.CRITICAL)
    void forgotPasswordLinkShouldOpenRestorePasswordPage() {
        loginPage.open();
        loginPage.clickForgotPassword();

        step("Ожидаемый результат: Страница восстановления пароля открыта", () ->
                assertTrue(restorePasswordPage.isPageOpened(),
                        "Страница восстановления пароля не открылась"));
    }

    @Test(description = "Проверка переключения языка интерфейса на English")
    @Description("Проверка смены языка интерфейса на английский язык")
    @Issue("MS-1035")
    @TmsLink("MSTC-12")
    @Severity(SeverityLevel.NORMAL)
    void shouldSwitchLanguageToEnglish() {
        loginPage.open();
        loginPage.switchToEnglish();

        step("Ожидаемый результат: Страница логина открыта на английском языке", () ->
                assertTrue(loginPage.isPageOpened(),
                        "Страница логина не отображается после смены языка"));
    }

    @Test(description = "Проверка переключения языка интерфейса на Русский")
    @Description("Проверка смены языка интерфейса на русский язык")
    @Issue("MS-1036")
    @TmsLink("MSTC-13")
    @Severity(SeverityLevel.NORMAL)
    void shouldSwitchLanguageToRussian() {
        loginPage.open();
        loginPage.switchToEnglish()
                .switchToRussian();

        step("Ожидаемый результат: Страница логина открыта на русском языке", () ->
                assertTrue(loginPage.isPageOpened(),
                        "Страница логина не отображается после смены языка"));
    }
}
