package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class DatePicker {

    public void setDate(String month, String year, int day) {
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day).click();
    }
}