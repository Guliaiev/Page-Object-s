package ru.netology.web.data;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.val;
public class Datahelper {
    private Datahelper(){

    }
    @Value
    public static class AuthInfo{
        public String login;
        public  String password;
    }
    public static AuthInfo getAuthInfoValid() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getAuthInfoInvalid() {
        return new AuthInfo("vasya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String codeForVerification;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    @Value
    @AllArgsConstructor
    public static class CardsInfo {
        public String numberOfCard;
    }

    public static CardsInfo getFirstCardInfo() {
        return new CardsInfo("5559 0000 0000 0001");
    }

    public static CardsInfo getSecondCardInfo() {
        return new CardsInfo("5559 0000 0000 0002");
    }

    public static int getBalanceCardPlus(int balance, int transfer) {
        int cardBalancePlus = balance + transfer;
        return cardBalancePlus;
    }

    public static int getBalanceCardMinus(int balance, int transfer) {
        int cardBalanceMinus = balance - transfer;
        if (cardBalanceMinus < 0) {
            return balance;
        }
        return cardBalanceMinus;
    }
}
