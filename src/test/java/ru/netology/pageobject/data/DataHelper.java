package ru.netology.pageobject.data;

import com.github.javafaker.Faker;
import lombok.Value;
import ru.netology.pageobject.page.DashboardPage;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
    }

    public static CardInfo getCardOne() {
        return new CardInfo("5559000000000001");
    }

    public static CardInfo getCardTwo() {
        return new CardInfo("5559000000000002");
    }

    private static final Faker faker = new Faker(new Locale("en"));

    public static int summaRandom(CardInfo v) {
        DashboardPage i = new DashboardPage();
        int x = i.getCardBalanceRandom(v);
        int sumStr = 0;
        if (x >= 10_000) {
            sumStr = faker.number().numberBetween(1, 2000);
        } else if (x > 7000 & x < 10_000) {
            sumStr = faker.number().numberBetween(1, 1000);
        } else if (x > 5000 & x < 7000) {
            sumStr = faker.number().numberBetween(1, 500);
        } else if (x > 3000 & x < 5000) {
            sumStr = faker.number().numberBetween(1, 200);
        } else if (x > 1000 & x < 3000) {
            sumStr = faker.number().numberBetween(1, 50);
        } else if (x > 100 & x < 500) {
            sumStr = faker.number().numberBetween(1, 10);
        } else if (x > 1 & x <= 100) {
            sumStr = faker.number().numberBetween(1, 1);
        }
        return sumStr;
    }
}
