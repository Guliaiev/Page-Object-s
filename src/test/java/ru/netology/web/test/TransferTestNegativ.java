package ru.netology.web.test;
import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.Datahelper;

import ru.netology.web.page.DahsboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTestNegativ {

    @Test
    void shouldInvalidVerification() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = Datahelper.getAuthInfoInvalid();
        loginPage.invalidLogin(authInfo);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondMoreThanExist() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = Datahelper.getAuthInfoValid();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = Datahelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);

        val dahsboardPage = new DahsboardPage();
        int startBalanceOfFirstCard = dahsboardPage.getBalanceOfFirstCard();
        int startBalanceOfSecondCard = dahsboardPage.getBalanceOfSecondCard();

        val transferPage = dahsboardPage.replenishBalanceSecondCard();
        val transferFrom1To2Card = Datahelper.getFirstCardInfo();
        int transfer = 20001;
        transferPage.transferFromFirstToSecond(transferFrom1To2Card, transfer);
        val balanceFirstCardAfterTrans = Datahelper.getBalanceCardMinus(startBalanceOfFirstCard, transfer);
        val balanceSecondCardAfterTrans = Datahelper.getBalanceCardPlus(startBalanceOfSecondCard, transfer);

        assertEquals(balanceFirstCardAfterTrans, dahsboardPage.getBalanceOfFirstCard());
        assertEquals(balanceSecondCardAfterTrans, dahsboardPage.getBalanceOfSecondCard());
    }
}