package ru.netology.web.test;


import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;
import static ru.netology.web.page.DashboardPage.pushFirstCardButton;
import static ru.netology.web.page.DashboardPage.pushSecondCardButton;

class MoneyTransferTest {
    @BeforeEach
    public void openPage() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV2();
        val authInfo = getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldTransferMoneyFromFirstCardToSecondCard() {
        int amount = 3_000;
        val dashboardPage = new DashboardPage();
        val firstCardBalanceBeforeTransfer = dashboardPage.getFirstCardBalance();
        val secondCardBalanceBeforeTransfer = dashboardPage.getSecondCardBalance();
        val transactionPage = pushSecondCardButton();
        transactionPage.transferMoney(amount, getFirstCardNumber());
        val firstCardBalance = firstCardBalanceBeforeTransfer - amount;
        val secondCardBalance = secondCardBalanceBeforeTransfer + amount;

        assertEquals(firstCardBalance, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardBalance, dashboardPage.getSecondCardBalance());
    }

    @Test
    public void shouldTransferMoneyFromSecondCardToFirstCard() {
        int amount = 6_000;
        val dashboardPage = new DashboardPage();
        val firstCardBalanceBeforeTransfer = dashboardPage.getFirstCardBalance();
        val secondCardBalanceBeforeTransfer = dashboardPage.getSecondCardBalance();
        val transactionPage = pushFirstCardButton();
        transactionPage.transferMoney(amount, getSecondCardNumber());
        val firstCardBalance = firstCardBalanceBeforeTransfer + amount;
        val secondCardBalance = secondCardBalanceBeforeTransfer - amount;

        assertEquals(firstCardBalance, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardBalance, dashboardPage.getSecondCardBalance());
    }

    @Test
    public void shouldHaveNotGoodBalance() {
        int amount = 11_000;
        val dashboardPage = new DashboardPage();
        val firstCardBalanceBeforeTransfer = dashboardPage.getFirstCardBalance();
        val secondCardBalanceBeforeTransfer = dashboardPage.getSecondCardBalance();
        val transactionPage = pushFirstCardButton();
        transactionPage.transferMoney(amount, getSecondCardNumber());
        TransferPage.getErrorLimit();
    }
}

