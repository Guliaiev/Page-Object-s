package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DahsboardPage {
    public SelenideElement dashboard = $("[data-test-id=dashboard]");
    public SelenideElement value1 = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
    public SelenideElement value2 = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");
    private SelenideElement buttonForTransferFrom2To1 = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"] [type='button']");
    private SelenideElement buttonForTransferFrom1To2 = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"] [type='button']");

    public DahsboardPage() {
        dashboard.shouldBe(visible);
    }

    public TransferPage replenishBalanceFirsCard() {
        buttonForTransferFrom2To1.click();
        return new TransferPage();
    }

    public TransferPage replenishBalanceSecondCard() {
        buttonForTransferFrom1To2.click();
        return new TransferPage();
    }

    public int getBalanceOfFirstCard() {
        String valueFirst = value1.getText();
        String balanceOfFirstCard = valueFirst.substring(29, valueFirst.indexOf(" ", 29));
        return Integer.parseInt(balanceOfFirstCard);
    }

    public int getBalanceOfSecondCard() {
        String valueSecond = value2.getText();
        String balanceOfSecondCard = valueSecond.substring(29, valueSecond.indexOf(" ", 29));
        return Integer.parseInt(balanceOfSecondCard);
    }
}