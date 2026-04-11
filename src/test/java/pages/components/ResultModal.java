package pages.components;

import static TestData.TestData.wrongNumberNotice;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ResultModal {

    public void modalShouldAppear() {
        $("#resultModal").shouldBe(visible);
    }

    public void modalNotAppear() {
        $("#resultModal").shouldNotBe(visible);
    }

    public void errorResult(String expectedErrorText) {
        $("#formError").shouldHave(cssClass("error"));
        $("#formError").shouldHave(text(expectedErrorText));
    }

    public void checkResult(String label, String value) {
        $("#resultBody").shouldHave(text(value));
    }

    public void closeModal() {
        $("#closeModal").click();
    }
}