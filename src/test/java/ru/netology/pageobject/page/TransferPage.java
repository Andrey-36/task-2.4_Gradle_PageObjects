package ru.netology.pageobject.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.pageobject.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {
    private final SelenideElement clear = $("[data-test-id='action-cancel']");
    private final SelenideElement sum = $("[data-test-id='amount'] input");
    private final SelenideElement from = $x("//*[@data-test-id='from']//input");
    private final SelenideElement button = $x("//*[@data-test-id='action-transfer']");

    public DashboardPage transfer(DataHelper.CardInfo info, int info2) {
        sum.val(String.valueOf(info2));
        from.val(info.getCardNumber());
        button.click();
        return new DashboardPage();
    }
}