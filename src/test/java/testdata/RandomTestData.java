package testdata;

import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

public class RandomTestData {

    private static final Random random = new Random();

    private static final Faker faker = new Faker(new Locale("ru"));

    public static String getRandomFirstName() {
        String[] names = {"Алексей", "Иван", "Петр", "Дмитрий", "Максим", "Андрей", "Сергей", "Владимир"};
        return names[random.nextInt(names.length)];
    }

    public static String getRandomLastName(int length) {
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        SecureRandom rnd = new SecureRandom();
        for (int i = 0; i < length; i++)
            result.append(LETTERS.charAt(rnd.nextInt(LETTERS.length())));
        return result.toString();
    }

    public static String getRandomEmail() {
        return "test_" + System.currentTimeMillis() + "_" + random.nextInt(1000) + "@example.com";
    }

    public static String getRandomAddress () {
        return faker.address().streetAddress() + ", " + faker.address().city();
    }

    public static String getRandomPhone() {
        StringBuilder phone = new StringBuilder("8");
        for (int i = 0; i < 9; i++) {
            phone.append(random.nextInt(10));
        }
        return phone.toString();
    }
    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return genders[random.nextInt(genders.length)];
    }

    public static String getRandomBirthMonth() {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return months[random.nextInt(months.length)];
    }

    public static String getRandomBirthYear() {
        return String.valueOf(random.nextInt(1900, 2021));
    }

    public static int getRandomBirthDay() {
        int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
        return days[random.nextInt(days.length)];
    }

    public static String getRandomSubject() {
        String[] subjects = {"Biology", "Chemistry", "Physics", "Maths", "Computer Science", "History", "English"};
        return subjects[random.nextInt(subjects.length)];
    }

    public static String getRandomHobby() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return hobbies[random.nextInt(hobbies.length)];
    }

    public static String getRandomState() {
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
        }

    }
}