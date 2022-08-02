package page_objects.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage {

    @FindBy(css = "a[href='/']")
    public static WebElement HomeTab;

    @FindBy(css = "a[href='/add-member']")
    public static WebElement AddMemberTab;

    @FindBy(css = "a[href='/search-member']")
    public static WebElement SearchMemberTab;

    @FindBy(css = "a[href='/view-member']")
    public static WebElement ViewMemberTab;

    @FindBy(css = "table tbody tr")
    public static List<WebElement> TableList;

}
