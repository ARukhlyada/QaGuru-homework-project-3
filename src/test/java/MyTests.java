package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

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
    void fillFormTest() {
        open("/automation-practice-form.html");

        // Находим элемент по id и вводим текст
        $("#firstName").setValue("Иван");
        $("#lastName").setValue("Петров");
        $("#userEmail").setValue("ivan@example.com");

        // Кликаем по radio button (пол)
        $("#gender-radio-1").parent().click();

        // Вводим номер телефона
        $("#userNumber").setValue("1234567890");

        // проверка — поле заполнилось
        $("#firstName").shouldHave(value("Иван"));
        $("#lastName").shouldHave(value("Петров"));

    }
}