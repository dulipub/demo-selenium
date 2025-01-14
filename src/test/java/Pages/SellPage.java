package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SellPage {
    WebDriver driver;

    // Locators using @FindBy
    @FindBy(xpath = "//*[@id='gh-p-2']") // Sell Button
    WebElement sellButton;

    @FindBy(xpath = "//*[@id='categorized_links_1']/div/div/div[1]/ul/li[1]/a") // Search button
    WebElement sellerCenter;

    @FindBy(xpath = "//*[@id='mainContent']/div[1]/div[1]/div/h1")
    WebElement infoText;

    // Constructor to initialize elements
    public SellPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    // Navigate to the seller page and click on sell center link
    public String SellerInfo() {
        sellButton.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        sellerCenter.click();
        String info = infoText.getText();
        return info.replaceAll("[\\r\\n]", " ").trim();


    }
}
