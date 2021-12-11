package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id='dashboard']");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public DashboardPage(){
        heading.shouldBe(Condition.visible);
    }

    public int getCardBalance (DataGenerator.CardInfo cardInfo) {
        val text = cards.findBy(text(cardInfo.getCardNumber().substring(16,19))).getText();
        return extractBalance(text);
    }



    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(),finish);
        return Integer.parseInt(value);
    }
    public TransferPage selectCardForTransfer (DataGenerator.CardInfo cardInfo){
        cards.findBy(text(cardInfo.getCardNumber().substring(16,19))).$("button").click();

        return new TransferPage();
    }




}
