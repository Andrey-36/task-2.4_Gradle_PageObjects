package ru.netology.pageobject.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.pageobject.data.DataHelper;
import ru.netology.pageobject.page.DashboardPage;
import ru.netology.pageobject.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    public void shouldTransferOfFundsToCardOne() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var cardInfo1 = DataHelper.getCardOne();
        var cardInfo2 = DataHelper.getCardTwo();
        int sumR = DataHelper.summaRandom(cardInfo2);
        var expectedBalanceCardOne = DashboardPage.getCardBalance(cardInfo1) + sumR;
        var expectedBalanceCardTwo = DashboardPage.getCardBalance(cardInfo2) - sumR;
        var transferPage = dashboardPage.cardToTransfer(cardInfo1);
        transferPage.transfer(cardInfo2, sumR);
        var actualBalanceCardOne = DashboardPage.getCardBalance(cardInfo1);
        var actualBalanceCardTwo = DashboardPage.getCardBalance(cardInfo2);
        Assertions.assertEquals(expectedBalanceCardOne, actualBalanceCardOne);
        Assertions.assertEquals(expectedBalanceCardTwo, actualBalanceCardTwo);
    }

    @Test
    public void shouldTransferOfFundsToCardTwo() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var cardInfo1 = DataHelper.getCardOne();
        var cardInfo2 = DataHelper.getCardTwo();
        int amount = DataHelper.summaRandom(cardInfo1);
        var expectedBalanceCardOne = DashboardPage.getCardBalance(cardInfo1) - amount;
        var expectedBalanceCardTwo = DashboardPage.getCardBalance(cardInfo2) + amount;
        var transferPage = dashboardPage.cardToTransfer(cardInfo2);
        transferPage.transfer(cardInfo1, amount);
        var actualBalanceCardOne = DashboardPage.getCardBalance(cardInfo1);
        var actualBalanceCardTwo = DashboardPage.getCardBalance(cardInfo2);
        Assertions.assertEquals(expectedBalanceCardOne, actualBalanceCardOne);
        Assertions.assertEquals(expectedBalanceCardTwo, actualBalanceCardTwo);
    }
}