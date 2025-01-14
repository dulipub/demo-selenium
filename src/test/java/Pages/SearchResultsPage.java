package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class SearchResultsPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"s0-62-0-13-8-4-1-0-5[0]-70-39-1-content-menu\"]/li[4]/a")
    WebElement sortFromLowestPrice;

    // Constructor to initialize elements
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to navigate to Products Page
    public void selectFirstProduct() {
        String mainWindowHandle = driver.getWindowHandle();
        WebElement firstItem = driver.findElement(By.cssSelector("ul.srp-results.srp-list.clearfix > li[data-view='mi:1686|iid:1']"));
        WebElement link = firstItem.findElement(By.cssSelector("a.s-item__link"));
        link.click();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
    
    //return the price of the first product listed in search results
    public double getFirstItemPrice (){
        WebElement item1 = driver.findElement(By.cssSelector("ul.srp-results.srp-list.clearfix > li[data-view='mi:1686|iid:1']"));
        return getItemPrice(item1);
    }

    //return the price of the second product listed in search results
    public double getSecondItemPrice (){
        WebElement item2 = driver.findElement(By.cssSelector("ul.srp-results.srp-list.clearfix > li[data-view='mi:1686|iid:2']"));
        return getItemPrice(item2);
    }

    //click the sort dropdown and then click the sort from lowest-to-highest option
    public void sortByLowestPrice(){
        // Explicit wait for the sort button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Use Duration for timeout

        WebElement sortButton = driver.findElement(By.cssSelector(".srp-sort.srp-sort--filter-evolution .fake-menu-button.srp-controls__control button"));
        wait.until(ExpectedConditions.elementToBeClickable(sortButton));
        sortButton.click();

        sortFromLowestPrice.click();
    }

    //return the total price(item price + shipping cost) of an item in search results.
    private double getItemPrice(WebElement item){
        WebElement itemPrice = item.findElement(By.className("s-item__price"));
        String priceText = itemPrice.getText();
        double price = Double.parseDouble(priceText.replace("$", "").replace(",", ""));
        System.out.println("Parsed Price: " + price);

        WebElement itemShipping = item.findElement(By.className("s-item__shipping"));
        String shippingText = itemShipping.getText();
        double shipping = Double.parseDouble(shippingText.replace("+$", "").replace(" delivery", ""));
        
        return price + shipping;
    } 

}

