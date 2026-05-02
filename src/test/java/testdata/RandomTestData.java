package testdata;

import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

public class RandomTestData {

    private static final Random random = new Random();

    private static final Faker faker = new Faker(new Locale("ru"));

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName(int length) {
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return  faker.internet().emailAddress();
    }

    public static String getRandomAddress () {
        return faker.address().streetAddress() + ", " + faker.address().city();
    }

    public static String getRandomPhone() {
        return faker.numerify("##########");
    }

    public static String getRandomGender() {
        //String[] genders = {"Male", "Female", "Other"};
        //return genders[random.nextInt(genders.length)];
        return faker.options().option("Male", "Female", "Other");
    }

    public static String getRandomBirthMonth() {
        return faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
    }

    public static String getRandomBirthYear() {
        return String.valueOf(faker.number().numberBetween(1900, 2021));
    }

    public static int getRandomBirthDay() {
        return faker.number().numberBetween(1, 29);
    }

    public static String getRandomSubject() {
        //String[] subjects = {"Biology", "Chemistry", "Physics", "Maths", "Computer Science", "History", "English"};
        //return subjects[random.nextInt(subjects.length)];
        return faker.options().option("Biology", "Chemistry", "Physics", "Maths", "Computer Science", "History", "English");
    }

    public static String getRandomHobby() {
        //String[] hobbies = {"Sports", "Reading", "Music"};
        //return hobbies[random.nextInt(hobbies.length)];
        return faker.options().option("Sports", "Reading", "Music");
    }

    /*public static String getRandomState() {
        String[] states = {"NCR", "Uttar Pradesh", "Rajasthan", "Haryana"};
        return states[random.nextInt(states.length)];
    }

    public static String getRandomCityForState(String state) {
        switch (state) {
            case "NCR":
                String[] ncrCities = {"Delhi", "Gurgaon", "Noida"};
                return ncrCities[random.nextInt(ncrCities.length)];
            case "Uttar Pradesh":
                String[] upCities = {"Lucknow", "Agra", "Kanpur"};
                return upCities[random.nextInt(upCities.length)];
            case "Rajasthan":
                String[] rajasthanCities = {"Jaipur", "Udaipur", "Jodhpur"};
                return rajasthanCities[random.nextInt(rajasthanCities.length)];
            case "Haryana":
                String[] haryanaCities = {"Karnal", "Panipat"};
                return haryanaCities[random.nextInt(haryanaCities.length)];
            default:
                return "Delhi";
        }*/

    public static String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Rajasthan", "Haryana");
    }

    public static String getRandomCityForState(String state) {
        switch (state) {
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh":
                return faker.options().option("Lucknow", "Agra", "Kanpur");
            case "Rajasthan":
                return faker.options().option("Jaipur", "Udaipur", "Jodhpur");
            case "Haryana":
                return faker.options().option("Karnal", "Panipat");
            default:
                return "Delhi";
        }
    }
}