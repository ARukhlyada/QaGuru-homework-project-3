package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class DatePicker {

    public void setDate(String month, String year, int day) {
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        String daySelector = String.format(".react-datepicker__day--%03d", day);
        $(daySelector).click();
    }
}