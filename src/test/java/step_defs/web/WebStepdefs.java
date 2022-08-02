package step_defs.web;

import cucumber.api.java.bs.A;
import cucumber.api.java.en.*;
import io.cucumber.datatable.DataTable;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page_objects.web.AddMemberPage;
import page_objects.web.HomePage;
import page_objects.web.SearchMemberPage;
import page_objects.web.ViewMemberPage;
import utils.Config;
import utils.factory.DriverFactory;

import java.util.*;

public class WebStepdefs {

    @When("I go to web url")
    public void iGoToWebUrl() {
        DriverFactory.getWebDriver().get(Config.ENV.WEB_URL());
    }

    @And("I click on tab {string}")
    public void iClickOnTab(String tab) {
        switch(tab) {
            case "Home":
                HomePage.HomeTab.click();
                break;
            case "Add Member":
                HomePage.AddMemberTab.click();
                break;
            case "Search Member":
                HomePage.SearchMemberTab.click();
                break;
            case "View Member":
                HomePage.ViewMemberTab.click();
                break;
            default:
        }
    }

    @And("I add member with information")
    public void iAddMemberWithInformation(DataTable data) {
        List<Map<String, String>> list = data.asMaps(String.class, String.class);
        AddMemberPage.FirstNameTxb.sendKeys(list.get(0).get("FirstName"));
        AddMemberPage.LastNameTxb.sendKeys(list.get(0).get("LastName"));
        AddMemberPage.TitleTxb.sendKeys(list.get(0).get("Title"));
        AddMemberPage.CompanyTxb.sendKeys(list.get(0).get("Company"));
        AddMemberPage.PhoneTxb.sendKeys(list.get(0).get("PhoneNumber"));
        AddMemberPage.WebsiteTxb.sendKeys(list.get(0).get("WebURL"));
        AddMemberPage.EmailTxb.sendKeys(list.get(0).get("Email"));
        AddMemberPage.AgreeTermAndConditionCxb.click();
        AddMemberPage.SubmitBtn.click();
    }

    @Then("I verify member has been added successfully with information")
    public void iVerifyMemberHasBeenAddedSuccessfullyWithInformation(DataTable data) {
        List<Map<String, String>> list = data.asMaps(String.class, String.class);
        String memberName = list.get(0).get("FirstName") + " " +  list.get(0).get("LastName");
        int rows = HomePage.TableList.size();
        boolean isExist = false;
        SoftAssertions softly = new SoftAssertions();
        for (int i=0; i < rows; i++) {
            List<WebElement> cells = HomePage.TableList.get(i).findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.println("content >>   " + cell.getText());
                if (cell.getText().contains(memberName)) {
                    isExist = true;
                    break;
                }
            }
            break;
        }
        softly.assertThat(isExist).isTrue();
    }

    @And("I search with text {string}")
    public void iSearchWithText(String text) {
        SearchMemberPage.SearchTxb.sendKeys(text);
        SearchMemberPage.SearchBtn.click();
    }

    @Then("I verify the table contain text {string} in all rows")
    public void iVerifyTheTableContainTextInAllRows(String text) {
        int rows = HomePage.TableList.size();
        boolean isContain = false;
        SoftAssertions softly = new SoftAssertions();
        List<Boolean> list =new ArrayList<Boolean>(Arrays.asList(new Boolean[10]));
        for (int i=0; i < rows; i++) {
            List<WebElement> cells = HomePage.TableList.get(i).findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().trim().contains(text.trim())) {
                    isContain = true;
                    Collections.fill(list, isContain);
                    break;
                }
                System.out.println("content >>   " + cell.getText());
            }
        }
        boolean isAllTrue = Arrays.asList(list).stream().allMatch(val -> true);
        softly.assertThat(isAllTrue).isTrue();
    }

    @And("I search with id {string}")
    public void iSearchWithId(String id) {
        ViewMemberPage.SearchTxb.sendKeys(id);
        ViewMemberPage.SearchBtn.click();
    }

    @Then("I verify user can view member with information")
    public void iVerifyUserCanViewMemberWithInformation(DataTable data) {
        List<Map<String, String>> list = data.asMaps(String.class, String.class);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ViewMemberPage.FirstNameTxb.getText()).isEqualTo(list.get(0).get("FirstName"));
        softly.assertThat(ViewMemberPage.LastNameTxb.getText()).isEqualTo(list.get(0).get("LastName"));
        softly.assertThat(ViewMemberPage.TitleTxb.getText()).isEqualTo(list.get(0).get("Title"));
        softly.assertThat(ViewMemberPage.CompanyTxb.getText()).isEqualTo(list.get(0).get("Company"));
        softly.assertThat(ViewMemberPage.PhoneTxb.getText()).isEqualTo(list.get(0).get("PhoneNumber"));
        softly.assertThat(ViewMemberPage.WebsiteTxb.getText()).isEqualTo(list.get(0).get("WebURL"));
        softly.assertThat(ViewMemberPage.EmailTxb.getText()).isEqualTo(list.get(0).get("Email"));
    }
}
