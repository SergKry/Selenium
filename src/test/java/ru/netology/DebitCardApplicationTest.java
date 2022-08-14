package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;


public class DebitCardApplicationTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }
    SelenideElement form = $("form");
    @Test
    void notShouldValidData() {

        form.$("[data-test-id=name] input").setValue("Николаев Иван");
        form.$("[data-test-id=phone] input").setValue("+79380195501");
        form.$("[data-test-id=agreement]").click();
        form.$("[type = button]").click();
        $("[data-test-id = order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}

