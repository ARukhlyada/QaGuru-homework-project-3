package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MyTests {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://qa-guru.github.io/one-page-form";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormWithPopupHandling() {
        open("https://qa-guru.github.io/one-page-form/automation-practice-form.html");

        // Ждём загрузки страницы и закрываем попап
        try {
            // Пытаемся найти и закрыть попап
            $(".popup-close").shouldBe(visible).click();
            System.out.println("Попап закрыт");
        } catch (Throwable e) {
            // Если попапа нет — идём дальше
            System.out.println("Попап не обнаружен");
        }

        // Находим элемент по id и вводим текст
        $("#firstName").setValue("Вася");
        $("#lastName").setValue("Васин");
        $("#userEmail").setValue("ivan@example.com");

        // Кликаем по radio button (пол)
        $("#gender-radio-1").parent().click();

        // Вводим номер телефона
        $("#userNumber").setValue("1234567890");

        // Вводим дату рождения
        $("dateOfBirthInput").click();
        $("react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__day--015").click();

        // Выбираем предмет
        $("subjectsInput").click();
        $(byText("Biology"));

        // Выбор хобби
        $("hobbies-checkbox-1").parent().click();

        // Загружаем картинку
        $("#uploadPicture").uploadFile(new File("src/test/resources/images.png"));

        // Ввод адреса
        $("currentAddress").setValue("Deathclaw st. 69");

        // Выбор штата
        $("state").click();
        $(byText("NCR")).click();

        //выбор города
        $("#city").click();
        $(byText("Delhi")).click();

        // проверка — поле заполнилось
        $("#firstName").shouldHave(value("Иван"));
        $("#lastName").shouldHave(value("Петров"));

    }
}