package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProductPage {
    WebDriver driver;

    // Locators
    @FindBy(id = "atcBtn_btn_1") // Add to Cart button
    WebElement addToCartButton;

    @FindBy(id = "watchBtn_btn_1") // Proceed to checkout button
    WebElement addToWatchList;

    @FindBy(id = "binBtn_btn_1") // Proceed to Buy It Now
    WebElement buyItNow;

    @FindBy(id = "wrong element") // Proceed to Buy It Now
    WebElement wrongElement;

    // Constructor to initialize elements
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //clicks the add to cart button in product page.
    public void addToCart() {
        addToCartButton.click();
    }

    //returns the product title.
    public String getName(){
        WebElement titleElement = driver.findElement(By.cssSelector("h1.x-item-title__mainTitle > span.ux-textspans"));
        return titleElement.getText();
    }
}