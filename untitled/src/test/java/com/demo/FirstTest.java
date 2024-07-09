package com.demo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FirstTest {
    @Test
    void shouldOpen() {
        String date = "28.11.2020";
        LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }

}
