
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testdata.TestBase;

import static testdata.TestData.*;
import static testdata.RandomTestData.*;

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
    void fillFormWithRandomData() {
        String randomFirstName = getRandomFirstName();
        String randomLastName = getRandomLastName(7);
        String randomFullName = randomFirstName + " " + randomLastName;
        String randomEmail = getRandomEmail();
        String randomPhone = getRandomPhone();
        String randomGender = getRandomGender();
        String randomBirthMonth = getRandomBirthMonth();
        String randomBirthYear = getRandomBirthYear();
        int randomBirthDay = getRandomBirthDay();
        String randomSubject = getRandomSubject();
        String randomHobby = getRandomHobby();
        String randomState = getRandomState();
        String randomCity = getRandomCityForState(randomState);
        String randomAddress = getRandomAddress();

        registrationPage
                .openPage()
                .closeAdPopup()
                .setFirstName(randomFirstName)
                .setLastName(randomLastName)
                .setEmail(randomEmail)
                .setGender(randomGender)
                .setPhone(randomPhone)
                .setBirthDate(randomBirthMonth, randomBirthYear, randomBirthDay)
                .setSubject(randomSubject)
                .setHobby(randomHobby)
                .uploadPicture(pictureFileName)
                .setAddress(randomAddress)
                .setState(randomState)
                .setCity(randomCity)
                .submitForm();

        registrationPage
                .checkModalAppears()
                .checkResult("Full Name", randomFullName)
                .checkResult("Student Email", randomEmail)
                .checkResult("Gender", randomGender)
                .checkResult("Mobile", randomPhone)
                .checkResult("Date of Birth", randomBirthDay + " " + randomBirthMonth + "," + randomBirthYear)
                .checkResult("Hobbies", randomHobby)
                .checkResult("Picture", pictureFileName)
                .checkResult("Address", randomAddress)
                .checkResult("State and City", randomState + " " + randomCity)
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


