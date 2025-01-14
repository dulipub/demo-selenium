package Pages;

import Base.BaseTest;
import Utils.TakeScreenShots;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DealsPage extends BaseTest {
    WebDriver driver;

    // Locators using @FindBy
    @FindBy(xpath = "//*[@id=\"vl-flyout-nav\"]/ul/li[11]/a") // Deals Button
    WebElement deals;

    @FindBy(xpath = "//*[@id=\"gf-l\"]/li[1]/a") // AboutEbay
    WebElement aboutEbay;

    @FindBy(xpath = "//*[@id=\"refit-spf-container\"]/div[1]/h1/a") // words aboutEbay button
    WebElement wordsAboutEbay;

    // Constructor to initialize elements
    public DealsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to searchFor
    public String dealsInfoAssertion() {
        // Click On Deals
        deals.click();
        test = extent.createTest("Successful Navigate to Global Deals Page", "Successful Navigate to Global Deals Page");
        String screenshotPath1 = TakeScreenShots.takeScreenshot(driver, "GlobalDealsPage");
        test.pass("System Successfully Navigate to Global Deals Page").addScreenCaptureFromPath(screenshotPath1);
        // Scroll to the bottom of the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        test = extent.createTest("Successfully Navigate to Bottom Of the Page", "Successfully Navigate to Bottom Of the Page");
        String screenshotPath2 = TakeScreenShots.takeScreenshot(driver, "PageBottom");
        test.pass("System Successfully Navigate to Bottom Of the Page").addScreenCaptureFromPath(screenshotPath2);
        String aboutEbay = wordsAboutEbay.getText();
        return aboutEbay;


    }
}