package ru.netology.delivery.test;

import org.junit.jupiter.api.Test;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.data.RegistrationByCardInfo;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldRegisterByAccountNumber() {
        open("http://localhost:9999");

        RegistrationByCardInfo info = DataGenerator.Registration.generateByCard("ru");

        System.out.println(info);
    }
}
