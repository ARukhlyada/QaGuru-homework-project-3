
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testdata.TestBase;

import static testdata.TestData.*;

public class PracticeFormTests extends TestBase {

    private RegistrationPage registrationPage = new RegistrationPage();


    @Test
    void fillFormWithPopupHandling() {
        registrationPage
                .openPage()
                .closeAdPopup()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setPhone(userNumber)
                .setBirthDate(birthMonth, birthYear, birthDay)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadPicture(pictureFileName)
                .setAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submitForm();


        //проверки:
        registrationPage
                .checkModalAppears()
                .checkResult("Full Name", fullName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", fullData)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", pictureFileName)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", stateAndCity)
                .closeModal();
    }

    @Test
    void requiredFieldsOnly() {
        registrationPage
                .openPage()
                .closeAdPopup()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setPhone(userNumber)
                .submitForm();

        registrationPage
                .checkModalAppears()
                .checkResult("Full Name", fullName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .closeModal();
    }

    @Test
    void negativeTest1 () {
        registrationPage
                .openPage()
                .closeAdPopup()
                .setFirstName(firstName)
                .setLastName(lastName)
                .submitForm();

        registrationPage
                .modalNotAppear()
                .errorResult(wrongNumberNotice);
    }

    @Test
    void negativeTest2 () {
        registrationPage
                .openPage()
                .closeAdPopup()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setPhone(shortNumber)
                .submitForm();
        registrationPage
                .modalNotAppear()
                .errorResult(wrongNumberNotice);
    }

    @Test
    void negativeTest3 () {
        registrationPage
                .openPage()
                .closeAdPopup()
                .setFirstName(firstName)
                .setGender(gender)
                .setPhone(userNumber)
                .submitForm();
        registrationPage
                .modalNotAppear()
                .errorResult(wrongNumberNotice);
    }
}


