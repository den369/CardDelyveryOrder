package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

class CardDelyveryOrderTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void test() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $x("//input[@placeholder=\"Город\"]").setValue("Москва");
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[name=\"name\"]").setValue("Иванов-Соколов Иван");
        $("[name=\"phone\"]").setValue("+79099099999");
        $x("//span[@class=\"checkbox__box\"]").click();
        $x("//span[@class=\"button__text\"]").click();
        $x("//*[contains(text(),\"Успешно!\")]").hover();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(30))
                .shouldBe(Condition.visible);

    }
}
