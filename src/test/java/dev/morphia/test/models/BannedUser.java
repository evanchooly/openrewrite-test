package dev.morphia.test.models;

import java.time.LocalDate;

import dev.morphia.annotations.Entity;

import static java.time.LocalDate.now;

@Entity("banned")
public class BannedUser extends User {
    public BannedUser() {
        super("", now());
    }

    public BannedUser(String name, LocalDate joined, String... likes) {
        super(name, joined, likes);
    }
}
