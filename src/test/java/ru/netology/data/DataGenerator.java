package ru.netology.data;

import lombok.Value;

public class DataGenerator {

    public static AuthInfo getAuthInfo() {return  new AuthInfo("vasya","qwerty123");}

    public static AuthInfo getOtherAuthInfo (AuthInfo original) {
        return new AuthInfo("petya","123qwery");}

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo){
        return new VerificationCode("12345");
    }

    public static CardInfo getFirstCardInf () {
        return new CardInfo ("5559 0000 0000 0001");
    }

    public static CardInfo getSecondCardInf () {
        return new CardInfo ("5559 0000 0000 0002");
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;

    }

    @Value
       public static class VerificationCode{
        private String code;
    }

    @Value
    public static class CardInfo{
        private String cardNumber;
    }



}

