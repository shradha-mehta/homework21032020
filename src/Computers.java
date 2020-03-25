import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class Computers {
    /*
Class Computers
url = https://demo.nopcommerce.com/"
Test 1 : Click on the Computers
         Navigate to Computers page successfully
         verify the Text Computers
Test 2 : Product added to Cart
         Click on Computer
         Click on first item - Desktops
         Clink on Build your own computer or Add to Cart
         (Goes to next page)
         Select radio button for  HDD * 400 GB [+$100.00]
         Then Add to Cart
         And verify message "The product has been added to your shopping cart"
 */
    WebDriver driver;

    @Before
    //opening google browser with nopcommerce website
    public void openBrowser() {
        String baseUrl = "https://demo.nopcommerce.com/";
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);


    }

    @Test
    //user navigate to computer web element
    public void verifyUserShouldNavigateToComputerPage() throws InterruptedException {

        WebElement computers = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]"));
        computers.click();

        WebElement comp = driver.findElement(By.xpath("//div[@class='page-title']//h1[contains(text(),'Computers')]"));

        Thread.sleep(6000);

        String expectedResult = "Computers";
        String actualResult = comp.getText();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deskTopPage() throws InterruptedException {


        WebElement compu = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]"));
        compu.click();

        WebElement com = driver.findElement(By.xpath("//div[@class='page-title']//h1[contains(text(),'Computers')]"));
        com.click();

        WebElement desktop = driver.findElement(By.xpath("//h2[@class='title']//a[contains(text(),'Desktops')]"));
        desktop.click();

        WebElement build = driver.findElement(By.xpath("//h2[@class='product-title']//a[contains(text(),'Build your own computer')]"));
        build.click();

        WebElement gb = driver.findElement(By.xpath("//input[@id='product_attribute_3_7']"));
        gb.click();

        WebElement add = driver.findElement(By.xpath("//input[@id='add-to-cart-button-1']"));
        add.click();

        WebElement added = driver.findElement(By.xpath("//p[@class='content']"));
        added.isDisplayed();

        WebElement shop = driver.findElement(By.xpath("//p[@class='content']"));
        String expectedResult = "The product has been added to your shopping cart";
        String actualResult = shop.getText();
        Assert.assertEquals(expectedResult, actualResult);

        Thread.sleep(6000);
    }

    @After
    public void closingBrowser() {
        driver.close();
    }



}
