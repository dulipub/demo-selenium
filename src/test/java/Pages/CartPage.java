package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    WebDriver driver;

    // Constructor to initialize elements
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    //return the product title of the first item in the shopping cart page.
    public String getFirstItemName(){
        WebElement titleElement = driver.findElement(By.cssSelector(".item-title.text-truncate-multiline.black-link.lines-2"));
        return titleElement.getText();
    }
}