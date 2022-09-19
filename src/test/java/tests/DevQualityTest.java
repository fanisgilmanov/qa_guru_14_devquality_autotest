package tests;

import com.codeborne.selenide.Selenide;
import helpers.DriverUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



public class DevQualityTest extends BaseTest {


    @Test
    @DisplayName("Проверка локализации")
    void localizationCheckTest() {
        step("Открываем страницу", () -> {
            Selenide.open("http://devquality.ru/");
        });

        step("Наводим на иконку переключения языка", () -> {
            $(".header__content").shouldBe(visible).hover();
        });

        step("Кликаем другой язык", () -> {
            $$(".header__langs a").find(text("en")).shouldBe(visible).click();
        });

        step("Проверяем изменение языка", () -> {
            $$(".banner__content h1").shouldHave(texts("Don't worry about your next release."));
        });

        step("Возвращаем русский язык", () -> {
            $(".header__content").hover().$$(".header__langs a").find(text("ru")).click();
            ;
        });

    }

    @Test
    @DisplayName("Проверка полей у формы обратной связи")
    void formOpeningTest() {

        step("Открываем страницу", () -> {
            Selenide.open("http://devquality.ru/");
        });

        step("Открытие формы через кнопку в баннере", () -> {
            $(".btn_banner").shouldBe(visible).click();
        });

        step("Проверка соответствия полей в форме", () -> {
            $(".request_popup").shouldHave(text("Оставьте заявку"), text("Остальное мы сделаем сами"));
            $("[name=phone-mail]").shouldBe(visible).closest("div").
                    shouldHave(text("Ваш телефон или e-mail"));
            $("[name=ask]").shouldBe(visible).closest("div").shouldHave(text("Текст обращения"));
            $$(".request_popup button").shouldHave(texts("Отправить заявку"));
        });

        step("Закрытия формы обратной связи", () -> {
            $(byXpath("/html/body/div/div[3]/div/div[1]")).click();
        });

    }

    @Test
    @DisplayName("Проверка перехода по иконке Telegram")
    void openTelegramTest() {
        step("Открываем страницу", () -> {
            Selenide.open("http://devquality.ru/");
        });

        step("Открытие Telegram", () -> {
            $$(".social__list a").get(0).shouldBe(visible).click();
            webdriver().shouldHave(url("https://t.me/kostyaitsme"));
            Selenide.open("http://devquality.ru/");
        });
    }

    @Test
    @DisplayName("Проверка перехода по иконке WhatSapp")
    void openWhatSappTest() {
        step("Открываем страницу", () -> {
            Selenide.open("http://devquality.ru/");
        });

        step("Открытие WhatSapp", () -> {
            $$(".social__list a").get(1).shouldBe(visible).click();
            webdriver().shouldHave(url("https://api.whatsapp.com/send/?phone=79168668724&text&" +
                    "type=phone_number&app_absent=0"));
            Selenide.open("http://devquality.ru/");
        });
    }

    @Test
    @DisplayName("Проверка перехода по иконке Skype")
    void openSkypeTest() {
        step("Открываем страницу", () -> {
            Selenide.open("http://devquality.ru/");
        });

        step("Открытие WhatSapp", () -> {
            $$(".social__list a").get(2).shouldBe(visible).click();
            webdriver().shouldHave(url("https://join.skype.com/invite/ogAeInJLABXk"));
            Selenide.open("http://devquality.ru/");
        });
    }

    @Test
    @DisplayName("Проверка консоля на ошибки")
    void consoleShouldNotHaveErrorsTest() {
        step("Открываем страницу", () -> {
            Selenide.open("http://devquality.ru/");
        });

        step("Проверяем консоль на текст ошибки: 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";
            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }

}
