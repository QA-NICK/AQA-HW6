package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement sumField = $ ("[data-test-id='amount'] input");
    private SelenideElement fromField = $("[data-test-id='from'] input");
    private SelenideElement topUpButton =$("[data-test-id='action-transfer']");



    public DashboardPage makeTransfer(String transferSum, DataGenerator.CardInfo cardInfo) {
        sumField.setValue(transferSum);
        fromField.setValue(cardInfo.getCardNumber());
        topUpButton.click();
        return new DashboardPage();
    }

}
