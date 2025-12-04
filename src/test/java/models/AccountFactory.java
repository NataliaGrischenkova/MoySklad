package models;

import com.github.javafaker.Faker;

public class AccountFactory {

    static Faker faker = new Faker();

    public static Account get() {
        return Account.builder()
                .name(faker.name().firstName())
                .secondname(faker.gameOfThrones().character())
                .lastname(faker.gameOfThrones().dragon())
                .phone(faker.phoneNumber().phoneNumber())
                .position(faker.gameOfThrones().quote())
                .INN("")
                .build();
    }
}
