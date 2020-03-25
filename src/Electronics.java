import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Electronics {
    /*
     url = "https://demo.nopcommerce.com/"
Class Electronics
Test 1  mouseHoverToElectronics
          mouse hover to Electronics
          mouse hover to Camera & photo
          verify text Camera & Photo
Test 2 : Position High to Low
         verify descending order
*/
    WebDriver driver;
    String baseUrl = "https://demo.nopcommerce.com/";
    private JavascriptExecutor jse;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        jse = (JavascriptExecutor) driver;

    }

    @Test
    public void userShouldNavigateToElectronicPage() throws InterruptedException {
        jse.executeScript("window.scrollBy(0, 800)");

        WebElement electro = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Electronics')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electro).perform();
        Thread.sleep(3000);

        WebElement camera = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Camera & photo')]"));
        actions.moveToElement(camera).click().perform();

        WebElement camera1 = driver.findElement(By.xpath("//h1[contains(text(),'Camera & photo')]"));

        String ExpectedResult = "Camera & photo";
        String actualReault = camera1.getText();
        Assert.assertEquals(ExpectedResult, actualReault);

    }

    @Test
    public void positionLowToHigh() throws InterruptedException {

        jse.executeScript("window.scrollBy(0, 800)");

        Thread.sleep(3000);

        WebElement electro = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Electronics')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electro).perform();


        WebElement camera1 = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Camera & photo')]"));
        actions.moveToElement(camera1).click().perform();

        WebElement cameraVarification = driver.findElement(By.xpath("//option[contains(text(),'Price: Low to High')]"));
        cameraVarification.click();

        WebElement sortBy = driver.findElement(By.xpath("//select[@id='products-orderby']"));

        Select select = new Select(sortBy);
        select.selectByVisibleText("Price: Low to High");

        List<WebElement> elements = driver.findElements(By.className("prices"));
        List<String> WebSortedPrices = elements.stream().map(WebElement::getText).collect(Collectors.toList());

        List<String> mySortedPrices = new ArrayList<>();
        for (WebElement element : elements) {
            String text = element.getText();
            mySortedPrices.add(text);
        }
        Assert.assertEquals(WebSortedPrices, mySortedPrices);
        System.out.println(mySortedPrices);

    }

    @Test
    public void browserClosing() {
        driver.close();
    }
}
