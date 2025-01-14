package TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.SearchResultsPage;
import Utils.ExcelHandler;
import Utils.TakeScreenShots;

public class EbayAddToCart extends BaseTest {
    @Test
    public void searchItem(){
        
        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        var excelFilePath = "src/test/resources/testdata/TestData.xlsx";
        var sheetName = "Data";
        ExcelHandler excel = new ExcelHandler(excelFilePath, sheetName);

        // Read data
        var searchTerm = excel.getCellData(1, 1); // Row 1, Column 1
        
        // Step 1: Search for graphics card
        homePage.search(searchTerm);
        setReportName("Add To Cart Scenario");
        startTest("Test Case 2 - Search graphics card and add to cart");
        test = extent.createTest("Successful Searched", "System Successfully searched the item and get the result");
        var path = TakeScreenShots.takeScreenshot(driver, "Search done - Test Case 2");
        test.pass("System Successfully searched the item and get the result").addScreenCaptureFromPath(path);

        //Step 2: Select first product and navigate to product page
        searchResultsPage.selectFirstProduct();

        //Step 3 get product name
        var productName = productPage.getName();
        productPage.addToCart();

        //Step 4: Compare product in cart
        test = extent.createTest("Checking cart Items", "Check the items added to the cart.");
        try {
            String path1 = TakeScreenShots.takeScreenshot(driver, "Cart Items");
            var cartItemName = cartPage.getFirstItemName();
            Assert.assertEquals(cartItemName, productName, "Items added to cart not matching");
            test.pass("Items sucessfully added to cart").addScreenCaptureFromPath(path1);
        } catch (AssertionError e) {
            // Capture screenshot on failure
            String path2 = TakeScreenShots.takeScreenshot(driver, "Cart Items");
            test.fail("Assertion failed: " + e.getMessage()).addScreenCaptureFromPath(path2);
            throw e; 
        }
    }

    @AfterTest
    public void close() {
        tearDown();
    }

}
