package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.Datahelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    public SelenideElement verification = $("[data-test-id=code] input");
    public SelenideElement codeInput = $("[data-test-id=code] input");
    public SelenideElement buttonVerify = $("[data-test-id=action-verify]");

    public VerificationPage() {
        verification.shouldBe(visible);
    }

    public void validVerify(Datahelper.VerificationCode verificationCode) {
        codeInput.setValue(verificationCode.getCodeForVerification());
        buttonVerify.click();
    }
}
