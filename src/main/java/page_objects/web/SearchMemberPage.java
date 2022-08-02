package page_objects.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchMemberPage {

    @FindBy(id = "default-search")
    public static WebElement SearchTxb;

    @FindBy(css = "button[type=submit]")
    public static WebElement SearchBtn;
}
