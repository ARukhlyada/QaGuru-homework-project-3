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
        // Настройка браузера перед всеми тестами
        Configuration.browserSize = "1920x1080"; // Размер окна
        Configuration.baseUrl = "https://qa-guru.github.io/one-page-form"; // Базовый URL
        Configuration.holdBrowserOpen = true; // Чтобы браузер не закрывался сразу
    }

    @Test
    void fillFormTest() {
        // Самый простой тест — заполнение формы
        open("/automation-practice-form.html"); // Открываем страницу

        // Находим элемент по id и вводим текст
        $("#firstName").setValue("Иван");
        $("#lastName").setValue("Петров");
        $("#userEmail").setValue("ivan@example.com");

        // Кликаем по radio button (пол)
        $("#gender-radio-1").parent().click(); // parent() поднимается к label

        // Вводим номер телефона
        $("#userNumber").setValue("1234567890");

        // Простейшая проверка — поле заполнилось
        $("#firstName").shouldHave(value("Иван"));
        $("#lastName").shouldHave(value("Петров"));

        // Небольшая задержка, чтобы увидеть результат
        sleep(2000);
    }
}