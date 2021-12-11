package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void shouldCheckBalance() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var autoInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(autoInfo);
        var verificationInfo = DataGenerator.getVerificationCodeFor(autoInfo);
        var dashBordPage = verificationPage.validVerify(verificationInfo);
        var firstCArdInfo = DataGenerator.getFirstCardInf();
        var secondCardInfo = DataGenerator.getSecondCardInf();
        var balanceFirst = dashBordPage.getCardBalance(firstCArdInfo);
        var balanceSecond = dashBordPage.getCardBalance(secondCardInfo);

        TransferPage transferPage = new TransferPage();
        if (balanceFirst > balanceSecond) {
            transferPage = dashBordPage.selectCardForTransfer(secondCardInfo);
            dashBordPage = transferPage.makeTransfer(String.valueOf(10000 - balanceSecond), firstCArdInfo);
        } else if (balanceSecond > balanceFirst) {
            transferPage = dashBordPage.selectCardForTransfer(firstCArdInfo);
            dashBordPage = transferPage.makeTransfer(String.valueOf(10000 - balanceFirst), secondCardInfo);
        }

    }


    @Test
    void shouldTransferAmountFromFirstToSecondCard() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var autoInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(autoInfo);
        var verificationInfo = DataGenerator.getVerificationCodeFor(autoInfo);
        var dashBordPage = verificationPage.validVerify(verificationInfo);
        var firstCArdInfo = DataGenerator.getFirstCardInf();
        var secondCardInfo = DataGenerator.getSecondCardInf();
        int transferSum = 1000;
        var expectedFirstCardBalance = dashBordPage.getCardBalance(firstCArdInfo) + transferSum;
        var expectedSecondCardBalance = dashBordPage.getCardBalance(secondCardInfo) - transferSum;
        var transferPage = dashBordPage.selectCardForTransfer(firstCArdInfo);
        dashBordPage = transferPage.makeTransfer(String.valueOf(transferSum), secondCardInfo);
        var actualFirstCardBalance = dashBordPage.getCardBalance(firstCArdInfo);
        var actualSecondCardBalance = dashBordPage.getCardBalance(secondCardInfo);
        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);


    }

    @Test
    void shouldTransferAmountFromSecondToFirstCard() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var autoInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(autoInfo);
        var verificationInfo = DataGenerator.getVerificationCodeFor(autoInfo);
        var dashBordPage = verificationPage.validVerify(verificationInfo);
        var firstCArdInfo = DataGenerator.getFirstCardInf();
        var secondCardInfo = DataGenerator.getSecondCardInf();
        int transferSum = 5000;
        var expectedFirstCardBalance = dashBordPage.getCardBalance(firstCArdInfo) - transferSum;
        var expectedSecondCardBalance = dashBordPage.getCardBalance(secondCardInfo) + transferSum;
        var transferPage = dashBordPage.selectCardForTransfer(secondCardInfo);
        dashBordPage = transferPage.makeTransfer(String.valueOf(transferSum), firstCArdInfo);
        var actualFirstCardBalance = dashBordPage.getCardBalance(firstCArdInfo);
        var actualSecondCardBalance = dashBordPage.getCardBalance(secondCardInfo);
        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }

    @Test
    void shouldTransferZeroAmountFromSecondToFirstCard() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var autoInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(autoInfo);
        var verificationInfo = DataGenerator.getVerificationCodeFor(autoInfo);
        var dashBordPage = verificationPage.validVerify(verificationInfo);
        var firstCArdInfo = DataGenerator.getFirstCardInf();
        var secondCardInfo = DataGenerator.getSecondCardInf();
        int transferSum = 0;
        var expectedFirstCardBalance = dashBordPage.getCardBalance(firstCArdInfo) - transferSum;
        var expectedSecondCardBalance = dashBordPage.getCardBalance(secondCardInfo) + transferSum;
        var transferPage = dashBordPage.selectCardForTransfer(secondCardInfo);
        dashBordPage = transferPage.makeTransfer(String.valueOf(transferSum), firstCArdInfo);
        var actualFirstCardBalance = dashBordPage.getCardBalance(firstCArdInfo);
        var actualSecondCardBalance = dashBordPage.getCardBalance(secondCardInfo);
        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);


    }

    @Test
    void shouldTransferZeroAmountFromFirstToSecondCard() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var autoInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(autoInfo);
        var verificationInfo = DataGenerator.getVerificationCodeFor(autoInfo);
        var dashBordPage = verificationPage.validVerify(verificationInfo);
        var firstCArdInfo = DataGenerator.getFirstCardInf();
        var secondCardInfo = DataGenerator.getSecondCardInf();
        int transferSum = 0;
        var expectedFirstCardBalance = dashBordPage.getCardBalance(firstCArdInfo) + transferSum;
        var expectedSecondCardBalance = dashBordPage.getCardBalance(secondCardInfo) - transferSum;
        var transferPage = dashBordPage.selectCardForTransfer(firstCArdInfo);
        dashBordPage = transferPage.makeTransfer(String.valueOf(transferSum), secondCardInfo);
        var actualFirstCardBalance = dashBordPage.getCardBalance(firstCArdInfo);
        var actualSecondCardBalance = dashBordPage.getCardBalance(secondCardInfo);
        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);


    }

    @Test
    void shouldTransferAmountEqualBalanceFromSecondToFirstCard() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var autoInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(autoInfo);
        var verificationInfo = DataGenerator.getVerificationCodeFor(autoInfo);
        var dashBordPage = verificationPage.validVerify(verificationInfo);
        var firstCArdInfo = DataGenerator.getFirstCardInf();
        var secondCardInfo = DataGenerator.getSecondCardInf();
        int transferSum = 10000;
        var expectedFirstCardBalance = dashBordPage.getCardBalance(firstCArdInfo) - transferSum;
        var expectedSecondCardBalance = dashBordPage.getCardBalance(secondCardInfo) + transferSum;
        var transferPage = dashBordPage.selectCardForTransfer(secondCardInfo);
        dashBordPage = transferPage.makeTransfer(String.valueOf(transferSum), firstCArdInfo);
        var actualFirstCardBalance = dashBordPage.getCardBalance(firstCArdInfo);
        var actualSecondCardBalance = dashBordPage.getCardBalance(secondCardInfo);
        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }
//    @Test
//    void shouldTransferAmountOverBalanceFromSecondToFirstCard() {
//        open("http://localhost:9999/");
//        var loginPage = new LoginPage();
//        var autoInfo = DataGenerator.getAuthInfo();
//        var verificationPage = loginPage.validLogin(autoInfo);
//        var verificationInfo = DataGenerator.getVerificationCodeFor(autoInfo);
//        var dashBordPage = verificationPage.validVerify(verificationInfo);
//        var firstCArdInfo = DataGenerator.getFirstCardInf();
//        var secondCardInfo = DataGenerator.getSecondCardInf();
//        int transferSum = 20000;
//        var expectedFirstCardBalance = dashBordPage.getCardBalance(firstCArdInfo) ;
//        var expectedSecondCardBalance = dashBordPage.getCardBalance(secondCardInfo) ;
//        var transferPage = dashBordPage.selectCardForTransfer(secondCardInfo);
//        dashBordPage = transferPage.makeTransfer(String.valueOf(transferSum), firstCArdInfo);
//        var actualFirstCardBalance = dashBordPage.getCardBalance(firstCArdInfo);
//        var actualSecondCardBalance = dashBordPage.getCardBalance(secondCardInfo);
//        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
//        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
//    }
}

