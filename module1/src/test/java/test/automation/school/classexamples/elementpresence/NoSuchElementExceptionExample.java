package test.automation.school.classexamples.elementpresence;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NoSuchElementExceptionExample {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "drivers/currentChromeDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

        // element is not present in the DOM - hence findElement() throws NoSuchElemenetException
        WebElement notDisplayedButPresentElement = driver.findElement(By.id("finish"));

        // following code will not be executed due to thrown exception
        System.out.println(notDisplayedButPresentElement.isDisplayed());


        //TODO - Don`t forget to close the browser manually
    }

}
