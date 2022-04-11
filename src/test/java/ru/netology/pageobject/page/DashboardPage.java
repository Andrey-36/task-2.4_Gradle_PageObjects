package ru.netology.pageobject.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.pageobject.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private static final ElementsCollection cards = $$(".list__item");
    private static final SelenideElement cardOne = $x("//div[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private static final SelenideElement cardTwo = $x("//div[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";
    private final SelenideElement button = $("button");
    private final SelenideElement update = $("[data-test-id='action-reload']");
    private static final SelenideElement heading = $("[data-test-id='dashboard']");
    private static int summaFinal;

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage cardToTransfer(DataHelper.CardInfo info) {
        cards.findBy(text(info.getCardNumber().substring(12, 16))).$("button").click();
        return new TransferPage();
    }

    public static int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = cards.findBy(text(cardInfo.getCardNumber().substring(12, 16))).getText();
        return extractBalance(text);
    }

    public int getCardBalanceRandom(DataHelper.CardInfo cardInfo) {
        var text = cards.findBy(text(cardInfo.getCardNumber().substring(12, 16))).getText();
        return extractBalance(text);
    }

    private static int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
