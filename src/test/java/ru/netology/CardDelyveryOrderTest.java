package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

class CardDelyveryOrderTest {

    @Test
    void test() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $x("//input[@placeholder=\"Город\"]").setValue("Москва");
        $("[type=\"tel\"]").setValue("21.01.2023");
        $("[name=\"name\"]").setValue("Иванов-Соколов Иван");
        $("[name=\"phone\"]").setValue("+79099099999");
        $x("//span[@class=\"checkbox__box\"]").click();
        $x("//span[@class=\"button__text\"]").click();
        $x("//*[contains(text(),\"Успешно!\")]").hover();

    }
}
