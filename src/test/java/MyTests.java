package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;

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
        open("/automation-practice-form.html");
        // Закрываем рекламный попап
        closeAdPopup();

        // Находим элемент по id и вводим текст
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        // Выбор пола
        $("#genterWrapper").$(byText(gender)).click();
        // Вводим номер телефона
        $("#userNumber").setValue(userNumber);
        // Вводим дату рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(birthMonth);
        $(".react-datepicker__year-select").selectOption(birthYear);
        $(".react-datepicker__day--0" + birthDay).click();
        // Выбираем предмет
        $("#subjectsInput").setValue(subject).pressEnter();
        // Выбор хобби
        $("#hobbiesWrapper").$(byText(hobby)).click();
        //прокручиваем страницу вниз
        executeJavaScript("window.scrollBy(0, 500)");
        // Загружаем картинку
        $("#uploadPicture").uploadFile(pictureFile);
        // Ввод адреса
        $("#currentAddress").setValue(currentAddress);
        // Выбор штата
        $("#state").click();
        $("#state").parent().$(byText(state)).click();
        // Выбор города
        $("#city").click();
        $("#city").parent().$(byText(city)).click();
        //жмем ввод
        $("#submit").scrollTo().click();

        //проверки:
        // Проверяем, что модалка открылась
        $("#resultModal").shouldBe(visible);
        // Проверяем каждый элемент
        $("#resultModal").shouldHave(text(fullName));
        $("#resultModal").shouldHave(text(userEmail));
        $("#resultModal").shouldHave(text(gender));
        $("#resultModal").shouldHave(text(userNumber));
        $("#resultModal").shouldHave(text(fullData));
        $("#resultModal").shouldHave(text(subject));
        $("#resultModal").shouldHave(text(hobby));
        $("#resultModal").shouldHave(text(pictureFileResult));
        $("#resultModal").shouldHave(text(currentAddress));
        $("#resultModal").shouldHave(text(stateAndCity));
    }

    @Test
    void requiredFieldsOnly() {
        open("/automation-practice-form.html");
        // Закрываем рекламный попап
        closeAdPopup();

        // заполняем имя и фамилию
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        // Выбор пола
        $("#genterWrapper").$(byText(gender)).click();
        // Вводим номер телефона
        $("#userNumber").setValue(userNumber);
        //жмем ввод
        $("#submit").scrollTo().click();

        // Проверяем, что модалка открылась
        $("#resultModal").shouldBe(visible);
        // Проверяем каждый элемент
        $("#resultModal").shouldHave(text(fullName));
        $("#resultModal").shouldHave(text(gender));
        $("#resultModal").shouldHave(text(userNumber));
        // Закрываем модалку
        $("#closeModal").click();
    }

    @Test
    void negativeTest1 () {
        open("/automation-practice-form.html");
        // Закрываем рекламный попап
        closeAdPopup();

        // заполняем ТОЛЬКО имя и фамилию
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        // жмем ввод
        $("#submit").scrollTo().click();

        // Модалка не открыласт
        $("#resultModal").shouldNotBe(visible);
        //проверка на наличие ошибки
        $("#formError").shouldHave(cssClass("error"));
    }

    @Test
    void negativeTest2 () {
        open("/automation-practice-form.html");
        // Закрываем рекламный попап
        closeAdPopup();

        //ввод имени и фамилии
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        // Выбор пола
        $("#genterWrapper").$(byText(gender)).click();
        // Вводим короткий номер телефона
        $("#userNumber").setValue(shortNumber);
        // жмем ввод
        $("#submit").scrollTo().click();

        // Модалка не открылась
        $("#resultModal").shouldNotBe(visible);
        //проверка на наличие ошибки
        $("#formError").shouldHave(cssClass("error"));
        $("#formError").shouldHave(text(wrongNumberNotice));
        }

        @Test
        void negativeTest3 () { //в системе баг, если не заполнить какое-либо поле, то ошибка будет про некорректный номер, я бы сделал на это автотест, если бы знал какой текст должен был бы быть
            open("/automation-practice-form.html");
            // Закрываем рекламный попап
            closeAdPopup();
            // Заполянем ТОЛЬКО иммя
            $("#firstName").setValue(firstName);
            // Выбор пола
            $("#gender-radio-1").parent().click();
            // Вводим номер телефона
            $("#userNumber").setValue(userNumber);
            $("#submit").scrollTo().click();
            // Модалка не появилась
            $("#resultModal").shouldNotBe(visible);
            //проверка на наличие ошибки
            $("#formError").shouldHave(cssClass("error"));
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


