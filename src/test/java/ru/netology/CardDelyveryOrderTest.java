package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//import static com.codeborne.selenide.Selenide.$;
//import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.*;

public class CardDelyveryOrderTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldCardDelyveryOrder() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        String currentDate = generateDate(10);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(currentDate);
        $("[data-test-id='name'] input").setValue("Иванов-Соколов Иван");
        $("[data-test-id='phone'] input").setValue("+79099099999");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
                $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + currentDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }
}
