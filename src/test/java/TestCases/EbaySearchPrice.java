package TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.HomePage;
import Pages.SearchResultsPage;
import Utils.ExcelHandler;
import Utils.TakeScreenShots;

//TEST CASE 1
public class EbaySearchPrice extends BaseTest {
    
    @Test
    public void searchByLowestPrice(){
        
        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        var excelFilePath = "src/test/resources/testdata/TestData.xlsx";
        var sheetName = "Data";
        ExcelHandler excel = new ExcelHandler(excelFilePath, sheetName);

        // Read data
        var searchTerm = excel.getCellData(1, 1); // Row 1, Column 1

        // Step 1: Search for graphics card
        homePage.search(searchTerm);
        setReportName("Sort By Price Scenario");
        startTest("Test Case 1 - Search lowest and highest price graphics card");
        test = extent.createTest("Successful Searched", "System Successfully searched the item and get the result");
        var screenshot1 = TakeScreenShots.takeScreenshot(driver, "Search done - Test Case 1");
        test.pass("System Successfully searched the item and get the result").addScreenCaptureFromPath(screenshot1);

        //Step 2: Sort from lowest price
        searchResultsPage.sortByLowestPrice();
        var firstprice = searchResultsPage.getFirstItemPrice();
        var secondprice = searchResultsPage.getSecondItemPrice();

        //Step 3: Compare prices of lowest and highest
        test = extent.createTest("Comparing prices", "Comparing prices from lowest price to highest");
        try {
            String path1 = TakeScreenShots.takeScreenshot(driver, "Price Comparison");
            Assert.assertTrue(firstprice <= secondprice, "Price of second item is lower than first item");
            test.pass("Items are sorted from lowest to highest price").addScreenCaptureFromPath(path1);
        } catch (AssertionError e) {
            // Capture screenshot on failure
            String path2 = TakeScreenShots.takeScreenshot(driver, "Price Comparison");
            test.fail("Assertion failed: " + e.getMessage()).addScreenCaptureFromPath(path2);
            throw e; 
        }
    }

    @AfterTest
    public void close() {
        tearDown();
    }

}
