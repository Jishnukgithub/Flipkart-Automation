import java.beans.Transient;
import java.sql.Wrapper;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import demo.SeleniumWrapper;
import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;



public class TestCases{
    WebDriver driver;
    SeleniumWrapper wrapper;

@BeforeSuite(alwaysRun = true)
public void setup() {
    System.out.println("Constructor: Driver");
     WebDriverManager.chromedriver().timeout(30).setup();
    driver = new ChromeDriver();
    
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
    driver.manage().window().maximize();
    wrapper = new SeleniumWrapper(driver, Duration.ofSeconds(50));
    System.out.println("Sucessfully Created driver");
}

@Test
public void testCase01() throws InterruptedException{
    System.out.println("Start test case: Testcase1");
    driver.get("https://www.flipkart.com/");
    Thread.sleep(3000);
    WebElement searchbutton = wrapper.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='Pke_EE']")));
    wrapper.search("Washing Machine");
    wrapper.pressEnter(searchbutton);
    WebElement searchClick = wrapper.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
    searchClick.click();
    WebElement popularity = wrapper.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Popularity']")));
    popularity.click();
    
    wrapper.ratingCount(driver);
    
    
    }
    @Test
    public void testCase02(){
        System.out.println("Start test case: Testcase2");
    driver.get("https://www.flipkart.com/");
        WebElement searchbutton = wrapper.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='Pke_EE']")));
    wrapper.search("iphone");
    wrapper.pressEnter(searchbutton);
    wrapper.titleAndDisount();
    System.out.println("End test case: Testcase2");

    }
     @Test
    public void testCases03(){
        System.out.println("Start test case: Testcase3");
        driver.get("https://www.flipkart.com/");
        WebElement searchbutton = wrapper.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='Pke_EE']")));
        wrapper.search("Coffee Mug");
        wrapper.pressEnter(searchbutton);
        wrapper.clickFourStar(driver);
        wrapper.getTop5ItemsWithHighestReviews();
        System.out.println("End test case: Testcase3");

    }

}



    
    


    

