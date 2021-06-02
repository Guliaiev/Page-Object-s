package ru.netology.web.page;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.Datahelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    public SelenideElement amountInput = $("[data-test-id=amount] input");
    public SelenideElement cardFromInput = $("[data-test-id=from] input");
    public SelenideElement buttonTransferAction = $("[data-test-id=action-transfer]");

    public void transferFromSecondToFirst(Datahelper.CardsInfo info, int transfer) {
        amountInput.setValue(String.valueOf(transfer));
        cardFromInput.setValue(info.getNumberOfCard());
        buttonTransferAction.click();
    }

    public void transferFromFirstToSecond(Datahelper.CardsInfo info, int transfer) {
        amountInput.setValue(String.valueOf(transfer));
        cardFromInput.setValue(info.getNumberOfCard());
        buttonTransferAction.click();
    }
}