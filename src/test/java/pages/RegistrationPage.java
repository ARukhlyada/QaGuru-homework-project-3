package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.DatePicker;
import pages.components.StateAndCity;
import pages.components.ResultModal;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement phoneInput = $("#userNumber");
    private final SelenideElement addressInput = $("#currentAddress");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement uploadPictureInput = $("#uploadPicture");
    private final SelenideElement submitButton = $("#submit");

    private final DatePicker datePicker = new DatePicker();
    private final StateAndCity stateCityComponent = new StateAndCity();
    private final ResultModal resultModalComponent = new ResultModal();

    public RegistrationPage openPage() {
        open("/automation-practice-form.html");
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        $("#genterWrapper").$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setPhone(String phone) {
        phoneInput.setValue(phone);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        $("#hobbiesWrapper").$(byText(hobby)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String fileName) {
        uploadPictureInput.uploadFromClasspath(fileName);
        return this;
    }

    public RegistrationPage setBirthDate(String month, String year, int day) {
        $("#dateOfBirthInput").click();
        datePicker.setDate(month, year, day);
        return this;
    }

    public RegistrationPage setState(String state) {
        stateCityComponent.selectState(state);
        return this;
    }
public RegistrationPage setCity(String city) {
        stateCityComponent.selectCity(city);
        return this;
    }

    public RegistrationPage submitForm() {
        submitButton.scrollTo().click();
        return this;
    }

    public RegistrationPage checkResult(String key, String value) {
        resultModalComponent.checkResult(key, value);
        return this;
    }

    public RegistrationPage checkModalAppears() {
        resultModalComponent.modalShouldAppear();
        return this;
    }

    public RegistrationPage modalNotAppear() {
        resultModalComponent.modalNotAppear();
        return this;
    }

    public RegistrationPage errorResult(String expectedErrorText) {
        resultModalComponent.errorResult(expectedErrorText);
        return this;
    }

    public RegistrationPage closeModal() {
        resultModalComponent.closeModal();
        return this;
    }

    public RegistrationPage closeAdPopup() {
        SelenideElement closeButton = $("button[aria-label='Close']");
        if (closeButton.exists() && closeButton.isDisplayed()) {
            closeButton.click();
            System.out.println("Рекламный попап закрыт");
        } else {
            System.out.println("Попап не найден или уже закрыт");
        }
        return this;
    }
}

