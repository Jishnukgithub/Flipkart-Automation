package demo;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases2 {
    ChromeDriver driver;
    public  TestCases2()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
       

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void TestCases1(){
        System.out.println("Start Test case: testCase01");

        
       
       
        System.out.println("end Test case: testCase02");
    
    }
}



