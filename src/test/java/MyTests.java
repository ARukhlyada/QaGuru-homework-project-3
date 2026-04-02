package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MyTests {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://qa-guru.github.io/one-page-form";
        Configuration.holdBrowserOpen = false;
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
        System.gc();
        System.out.println("Браузер закрыт");

    }

    @Test
    void fillFormWithPopupHandling() {
        open("https://qa-guru.github.io/one-page-form/automation-practice-form.html");

        // Закрываем рекламный попап
        closeAdPopup();

        // Находим элемент по id и вводим текст
        $("#firstName").setValue("Вася");
        $("#lastName").setValue("Васин");
        $("#userEmail").setValue("ivan@example.com");
        //проверка
        $("#firstName").shouldHave(value("Вася"));
        $("#lastName").shouldHave(value("Васин"));
        $("#userEmail").shouldHave(value("ivan@example.com"));

        // Выбор пола
        $("#gender-radio-1").parent().click();
        //проверка
        $("#gender-radio-1").shouldBe(checked);

        // Вводим номер телефона
        $("#userNumber").setValue("1234567890");
        //проверка
        $("#userNumber").shouldHave(value("1234567890"));

        // Вводим дату рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__day--015").click();
        //проверка
        $("#dateOfBirthInput").shouldHave(value("15 Jul 1995"));

        // Выбираем предмет
        $("#subjectsInput").setValue("Biology").pressEnter();
        //проверка
        $(byText("Biology")).shouldBe(visible);

        // Выбор хобби
        $("#hobbies-checkbox-1").parent().click();
        //проверка
        $("#hobbies-checkbox-1").shouldBe(checked);

        //прокручиваем страницу вниз
        executeJavaScript("window.scrollBy(0, 500)");

        // Загружаем картинку
        $("#uploadPicture").uploadFile(new File("src/test/resources/images.jpg"));
        System.out.println("Файл images.jpg загружен");
        //проверка - не прокатило, там один элемент и я так и не придумал как из него вытащить текст, просто написал что фал загружен
        //$(".modal-body").shouldHave(text("images.jpg"));

        // Ввод адреса
        $("#currentAddress").setValue("Deathclaw st. 69");
        //проверка
        $("#currentAddress").shouldHave(value("Deathclaw st. 69"));


        // Выбор штата
        $("#state").click();
        $(byText("NCR")).click();

        // Выбор города
        $("#city").click();
        $(byText("Delhi")).click();
        //проверки
        // Проверка выбранного значения в выпадающем списке
        $("#state").shouldHave(text("NCR"));
        $("#city").shouldHave(text("Delhi"));
    }

    @Test
    void RequiredFieldsOnly() {
        open("https://qa-guru.github.io/one-page-form/automation-practice-form.html");

        // Закрываем рекламный попап
        closeAdPopup();

        // Находим элемент по id и вводим текст
        $("#firstName").setValue("Вася");
        $("#lastName").setValue("Васин");

        // Выбор пола
        $("#gender-radio-1").parent().click();

        // Вводим номер телефона
        $("#userNumber").setValue("1234567890");

        //жмем ввод
        $("#submit").scrollTo().click();

        // Проверяем, что модалка открылась
        $(".modal-content").shouldBe(visible);
        // Проверяем каждый элемент
        $(".modal-body").shouldHave(text("Вася Васин"));
        $(".modal-body").shouldHave(text("Male"));
        $(".modal-body").shouldHave(text("1234567890"));

        // Закрываем модалку
        $("#closeLargeModal").click();
    }
    // Метод закрытия попапа
    private void closeAdPopup() {
        SelenideElement closeButton = $("button[aria-label='Close']");
        if (closeButton.exists() && closeButton.isDisplayed()) {
            closeButton.click();
            System.out.println("Рекламный попап закрыт");
        } else {
            System.out.println("Попап не найден или уже закрыт");
        }
}
}


