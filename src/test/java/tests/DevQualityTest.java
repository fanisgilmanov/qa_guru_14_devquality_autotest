package tests;

import com.codeborne.selenide.Selenide;
import helpers.DriverUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DevQualityTest  extends  BaseTest{

    @Test
    @DisplayName("Проверка консоля на ошибки")
    void consoleShouldNotHaveErrorsTest() {
        step("Открываем URL 'http://devquality.ru/'", () ->
                Selenide.open("http://devquality.ru/"));

        step("Проверяем консоль на текст ошибки: 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }





}
