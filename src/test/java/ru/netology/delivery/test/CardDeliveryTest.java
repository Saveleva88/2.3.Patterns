package ru.netology.delivery.test;

import org.junit.jupiter.api.Test;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.data.RegistrationByCardInfo;

import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;


public class CardDeliveryTest {

    @Test
    void shouldRegisterByAccountNumber() {
        open("http://localhost:9999");

        RegistrationByCardInfo firstSending = DataGenerator.Registration.generateByCard("ru");

        String firstDate = DataGenerator.generateDate(7);
        String secondDate = DataGenerator.generateDate(11);

        // firstSending

        $("[placeholder='Город']").setValue(firstSending.getCity());
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstDate);
        $("[name='name']").setValue(firstSending.getName());
        $("[name='phone']").setValue(firstSending.getPhone());
        $("[data-test-id='agreement']").click();
        $$(".button__text").find(exactText("Запланировать")).click();
        $(".notification__content")
                .shouldBe(visible).shouldHave(exactText("Встреча успешно запланирована на " + firstDate), Duration.ofSeconds(15));

        //secondSending

        $("[placeholder='Дата встречи']").doubleClick().sendKeys(BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondDate);
        $$(".button__text").find(exactText("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldBe(visible).shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $$(".button__text").find(exactText("Перепланировать")).click();
        $(".notification__content")
                .shouldBe(visible).shouldHave(exactText("Встреча успешно запланирована на " + secondDate), Duration.ofSeconds(15));

    }
}
