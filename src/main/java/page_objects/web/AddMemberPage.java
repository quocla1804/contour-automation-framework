package page_objects.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddMemberPage {

    @FindBy(id = "first_name")
    public static WebElement FirstNameTxb;

    @FindBy(id = "last_name")
    public static WebElement LastNameTxb;

    @FindBy(id = "title")
    public static WebElement TitleTxb;

    @FindBy(id = "company")
    public static WebElement CompanyTxb;

    @FindBy(id = "phone")
    public static WebElement PhoneTxb;

    @FindBy(id = "website")
    public static WebElement WebsiteTxb;

    @FindBy(id = "email")
    public static WebElement EmailTxb;

    @FindBy(id = "remember")
    public static WebElement AgreeTermAndConditionCxb;

    @FindBy(css = "button[type=submit]")
    public static WebElement SubmitBtn;
}
