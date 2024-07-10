import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardRequestTest {
    @Test
    public void testCardRequest() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Хамитова Ольга");
        $("[data-test-id=phone] input").setValue("+79991111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void testValidationCallbackFormInvalidName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("KhamitovaOlga");
        $("[data-test-id=phone] input").setValue("+79991111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void testValidationCallbackFormInvalidPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Хамитова Ольга");
        $("[data-test-id=phone] input").setValue("+7999111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void testValidationCallbackFormEmpty() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void testValidationCallbackFormEmptyName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79991111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void testValidationCallbackFormEmptyPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Хамитова Ольга");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void testCardRequestWithoutCheckbox() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Хамитова Ольга");
        $("[data-test-id=phone] input").setValue("+79991111111");
        $("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldBe(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"), visible);
    }
}
