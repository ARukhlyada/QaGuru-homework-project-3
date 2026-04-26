package pages.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StateAndCity {

    public void selectState(String state) {
        $("#state").scrollTo();
        $("#state").click();
        $("#state").parent().$(byText(state)).click();
    }

    public void selectCity(String city) {
        $("#city").scrollTo();
        $("#city").click();
        $("#city").parent().$(byText(city)).click();
    }
}