package demo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumWrapper {
     private WebDriver driver;
    public WebDriverWait wait; 

    public  SeleniumWrapper(WebDriver driver, Duration timeout){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void search(String searchTerm) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(searchTerm);
                System.out.println("Item searched sucessfully");
             
}
public void pressEnter(WebElement element) {
    try {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(Keys.ENTER);
    } catch (Exception e) {
        System.out.println("Error pressing Enter on element: " + element);
        e.printStackTrace();
    }
}



public void ratingCount(WebDriver driver) throws InterruptedException{
    
    try {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Add to Compare'])[1]")));

    List<WebElement> ratingElements = driver.findElements(By.xpath("//div[@class='col col-7-12']/div[2]/span/div"));
    
    int count = 0;
    
    for (WebElement ratingElement : ratingElements) {
        try {
            // Refresh the element in case it becomes stale
            ratingElement = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(ratingElement)));
            
            String ratingText = ratingElement.getText();
            double rating = Double.parseDouble(ratingText);
            if (rating <= 4) {
                count++;
            }
        } catch (StaleElementReferenceException e) {
            // Handle stale element reference by re-locating the element
            ratingElements = driver.findElements(By.xpath("//div[@class='col col-7-12']/div[2]/span/div"));
        } catch (NumberFormatException e) {
            System.out.println("Invalid rating format: " + ratingElement.getText());
        }
    }
    System.out.println("Count of the product with rating less than or equal to 4 is " + count);
    System.out.println("End of testcase 1");
} catch (Exception e) {
    System.out.println("Exception occurred: " + e.getMessage());
}
}
public void titleAndDisount(){
    List<WebElement> iphoneElements = driver.findElements(By.xpath("//div[@class='_75nlfW']"));

    for(WebElement iphone : iphoneElements){
       List<WebElement> titleElement = iphone.findElements(By.xpath(".//div[@class='KzDlHZ']"));
       if(!titleElement.isEmpty()){
       String title =titleElement.get(0).getText();
    
       List<WebElement> discountElements = iphone.findElements(By.xpath(".//div[@class='UkUFwK']"));
       if (!discountElements.isEmpty()) {
        String discountText = discountElements.get(0).getText().replace("% off", "");
         
            if (!discountText.isEmpty()) {
              int  discount = Integer.parseInt(discountText);
            
            if (discount > 17) {
                System.out.println("Title: " + title);
                System.out.println("Discount Percentage: " + discount + "%");
                System.out.println("---------------------");

                              }
                           }
                         }
                       }
                     }
                  }

                  public void clickFourStar(WebDriver driver){
                    
                    try {
                        WebElement fourStar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='XqNaEv'])[1]")));
                        fourStar.click();
                        System.out.println("fourstar click successfully");
                    } catch (Exception e) {
                        System.out.println("Error clicking four star: " + e.getMessage());
                    }
                }
             public List<WebDriver>  getTop5ItemsWithHighestReviews(){
                
                    List<WebElement> products = driver.findElements(By.xpath("//div[@class='slAVV4']"));
                    List<WebElement> topProducts = new ArrayList<>();
                 
                    for (WebElement product : products) {
                       
                        String title = product.findElement(By.xpath("//a[@class='wjcEIp']")).getText();
                        String imageUrl = product.findElement(By.xpath("//a[@class='VJA3rP']")).getAttribute("href");

                        String reviewCountStr = product.findElement(By.xpath("//span[@class='Wphh3N']")).getText();
                        int reviewCount = Integer.parseInt(reviewCountStr.replaceAll("\\D+", "")); 

                        topProducts.add(new Product(title, imageUrl, reviewCount));

             }
             topProducts.sort(Comparator.comparing(Product::getReviewCount).reversed());

             // Print details of top 5 products
             for (int i = 0; i < Math.min(5, topProducts.size()); i++) {
                 Product product = topProducts.get(i);
                 System.out.println("Title: " + product.getTitle());
                 System.out.println("Image URL: " + product.getImageUrl());
                 System.out.println("Review Count: " + product.getReviewCount());
                 System.out.println();
             }
     

    }
}

                    
                  




