import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardRequestTest {
    @Test
    public void test_CardRequest() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Хамитова Ольга");
        $("[data-test-id=phone] input").setValue("+79991111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    public void test_ValidationCallbackForm() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("KhamitovaOlga");
        $("[data-test-id=phone] input").setValue("+7999111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("#root > div > form > div:nth-child(1) > span > span > span.input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}
