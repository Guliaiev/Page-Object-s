package ru.netology.web.test;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.web.data.Datahelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.DahsboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTestPositiv {

    @BeforeEach
    void shouldVerify() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = Datahelper.getAuthInfoValid();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = Datahelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void shouldTransferMoneyFromSecondToFirst(int transfer) {

        val dahsboardPage = new DahsboardPage();
        int startBalanceOfFirstCard = dahsboardPage.getBalanceOfFirstCard();
        int startBalanceOfSecondCard = dahsboardPage.getBalanceOfSecondCard();

        val transferPage = dahsboardPage.replenishBalanceFirsCard();
        val transferFrom2To1Card = Datahelper.getSecondCardInfo();
        transferPage.transferFromSecondToFirst(transferFrom2To1Card, transfer);
        val balanceFirstCard = Datahelper.getBalanceCardPlus(startBalanceOfFirstCard, transfer);
        val balanceSecondCard = Datahelper.getBalanceCardMinus(startBalanceOfSecondCard, transfer);

        assertEquals(balanceFirstCard, dahsboardPage.getBalanceOfFirstCard());
        assertEquals(balanceSecondCard, dahsboardPage.getBalanceOfSecondCard());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void shouldTransferMoneyFromFirstToSecond(int transfer) {

        val dahsboardPage = new DahsboardPage();
        int startBalanceOfFirstCard = dahsboardPage.getBalanceOfFirstCard();
        int startBalanceOfSecondCard = dahsboardPage.getBalanceOfSecondCard();

        val transferPage = dahsboardPage.replenishBalanceSecondCard();
        val transferFrom1To2Card = Datahelper.getFirstCardInfo();
        transferPage.transferFromFirstToSecond(transferFrom1To2Card, transfer);
        val balanceFirstCardAfterTrans = Datahelper.getBalanceCardMinus(startBalanceOfFirstCard, transfer);
        val balanceSecondCardAfterTrans = Datahelper.getBalanceCardPlus(startBalanceOfSecondCard, transfer);

        assertEquals(balanceFirstCardAfterTrans, dahsboardPage.getBalanceOfFirstCard());
        assertEquals(balanceSecondCardAfterTrans, dahsboardPage.getBalanceOfSecondCard());
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondMinus() {

        val dahsboardPage = new DahsboardPage();
        int startBalanceOfFirstCard = dahsboardPage.getBalanceOfFirstCard();
        int startBalanceOfSecondCard = dahsboardPage.getBalanceOfSecondCard();

        val transferPage = dahsboardPage.replenishBalanceSecondCard();
        val transferFrom1To2Card = Datahelper.getFirstCardInfo();
        int transfer = -1000;
        transferPage.transferFromFirstToSecond(transferFrom1To2Card, transfer);
        val balanceFirstCardAfterTrans = startBalanceOfFirstCard + transfer;
        val balanceSecondCardAfterTrans = startBalanceOfSecondCard - transfer;

        assertEquals(balanceFirstCardAfterTrans, dahsboardPage.getBalanceOfFirstCard());
        assertEquals(balanceSecondCardAfterTrans, dahsboardPage.getBalanceOfSecondCard());
    }
}