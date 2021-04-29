import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class Main {
    String baseUrl = "https://anotepad.com/create_account";
    String driverPath = "chromedriver";
    public WebDriver driver ;
    @BeforeTest
    public void beforTest(){
        System.out.println("launching chrome browser");
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();

    }
    //Test input email not exited
    @Test
    public void loginEmailNotExits() throws InterruptedException {
        driver.get(baseUrl);
        sleep(1000);
        driver.findElement(By.id("loginEmail")).sendKeys("abc@gmail.com");//Input user name incorrect
        sleep(1000);
        driver.findElements(By.id("password")).get(1).sendKeys("12345678a");//Input user password correct
        sleep(1000);
        driver.findElements(By.id("submit")).get(1).click();//click login button
        sleep(1000);
        String result = driver.findElement(By.xpath("//strong[contains(text(),'Email and password do not match')]")).getText();
        if(result.equals("Email and password do not match")){
            System.out.println("Passed");
        }else{
            System.out.println("Fail");
        }
    }

    //Test input password invalid
    @Test
    public void loginPassworkInvalid() throws InterruptedException {
        driver.get(baseUrl);
        sleep(1000);
        driver.findElement(By.id("loginEmail")).sendKeys("herejib411@quossum.com");//Input user name incorrect
        sleep(1000);
        driver.findElements(By.id("password")).get(1).sendKeys("12345678bcd");//Input user password correct
        sleep(1000);
        driver.findElements(By.id("submit")).get(1).click();//click login button
        sleep(1000);
        String result = driver.findElement(By.xpath("//strong[contains(text(),'Email and password do not match')]")).getText();
        if(result.equals("Email and password do not match")){
            System.out.println("Passed");
        }else{
            System.out.println("Fail");
        }
    }

    //Test input valid account (email, password)
    @Test
    public void loginValidEmail() throws InterruptedException {
        driver.get(baseUrl);
        sleep(1000);
        driver.findElement(By.id("loginEmail")).sendKeys("herejib411@quossum.com");//Input user name incorrect
        sleep(1000);
        driver.findElements(By.id("password")).get(1).sendKeys("Zxcv1234!@");//Input user password correct
        sleep(1000);
        driver.findElements(By.id("submit")).get(1).click();//click login button
        sleep(1000);
        String result = driver.findElement(By.xpath("//span[contains(text(),'Logout')]")).getText();
        if(result.equals("Logout")){
            System.out.println("Passed");
        }else{
            System.out.println("Fail");
        }
    }

    @AfterTest
    public void aftertest(){
        driver.close();
    }

}
