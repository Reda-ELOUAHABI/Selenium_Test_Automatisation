package test.automation.school.api;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class DriverApi {
    public static void main(String[] args) throws Exception {





        CompletableFuture<Void> future2 = CompletableFuture.runAsync(()->{
            try {
                FullLogin("hamza@gmail.com","hamza");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<Void> future3 = CompletableFuture.runAsync(()->{
            try {
                FullLogin("hamza123@gmail.com","hamza");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<Void> future = CompletableFuture.allOf( future2, future3);
        try {
            future.get(); // this line waits for all to be completed
        } catch (InterruptedException  | ExecutionException e) {
            // Handle
        }


    }
public static void FullLogin(String usernameValue, String passwordvValue) throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "drivers/currentChromeDriver/chromedriver.exe");
    WebDriver driver = new ChromeDriver();

    driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.manage().window().setSize(new Dimension(300, 450));

//        WebDriverWait wait = new WebDriverWait(driver, 10);
    boolean loginSucceded = false;
    while (!loginSucceded) {
        try {
            loginSucceded=true;
            driver.get("http://localhost:3000/");

            System.out.println("Current Page Title is: " + driver.getTitle());
            System.out.println("Current URL is: " + driver.getCurrentUrl());

            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys(usernameValue, Keys.ENTER);

            WebDriverWait wait = new WebDriverWait(driver, 5);

            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(passwordvValue, Keys.ENTER);

//        List<WebElement> webElementList = driver.findElements();
//        WebElement submit = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[1]/form[1]/input[1]"));
            WebElement submit = driver.findElement(By.id("login"));
            Thread.sleep(3000);
            submit.click();
        }

        catch (Exception e) {
            System.out.println("Loggin ...");
        }
    }
//        refresh to make sure all assets are loaded
//        driver.navigate().refresh();
//        driver.close();
//        driver.quit();
    System.out.println("1");
    boolean loaded = false;
    Thread.sleep(3000);
    driver.navigate().refresh();

    while (!loaded) {
        try {      loaded = !loaded;
            WebDriverWait wait = new WebDriverWait(driver, 5);
            WebElement element =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'navbarDropdown\']")));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}}





/*
*
* public class SeliniumTest{

public static void main(String[] args) {
    System.setProperty("webdriver.chrome.driver", "drivers/currentChromeDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();


       driver.manage().deleteAllCookies();
       driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

       driver.manage().window().maximize();

       driver.manage().window().setSize(new Dimension(300, 450));

        driver.get("http://www.google.com");

        System.out.println("Current Page Title is: " + driver.getTitle());
        System.out.println("Current URL is: " + driver.getCurrentUrl());

        WebElement webElement = driver.findElement(By.name("q"));
//        List<WebElement> webElementList = driver.findElements();


        driver.close();
        driver.quit();


        // 2
        // .getText()
        WebElement logoWebElement = driver.findElement(By.name("hplogo"));
        System.out.println("Text of a web element: " + logoWebElement.getText());


        // .click()
        WebElement inputFieldWebElement = driver.findElement(By.name("q"));


        // .isDisplayed
        inputFieldWebElement.isDisplayed();
        inputFieldWebElement.isEnabled();

        inputFieldWebElement.sendKeys("text for inputting into input field");


        inputFieldWebElement.click();

        // 3 ELEMENT PRESENCE VS ERROR no such element
                // element is not displayed, but is present in the DOM - hence findElement() does not throw NoSuchElemenetException
                WebElement notDisplayedButPresentElement = driver.findElement(By.id("finish"));
                //returns 'false'
                System.out.println(notDisplayedButPresentElement.isDisplayed());

// No SUCH ELT
                   // element is not present in the DOM - hence findElement() throws NoSuchElemenetException
        WebElement notDisplayedButPresentElement = driver.findElement(By.id("finish"));

           // following code will not be executed due to thrown exception
           System.out.println(notDisplayedButPresentElement.isDisplayed());

                //TODO - Don`t forget to close the browser manually

        // 4  VERY GOOD CODE SEARCH GOOGLE ACTIONS
        WebElement searchInputElement = driver.findElement(By.name("q"));

        // sendKeys() and Selenium`s Keys enum
        searchInputElement.sendKeys("apple", Keys.ENTER);
        searchInputElement = driver.findElement(By.name("q"));

        //when using sendkeys multiple times, then it will append the text
        searchInputElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), "banana");
        searchInputElement.sendKeys("new banana");

        // clear()
        // need to clear the input first if smth already is written. Otherwise, text will be concatenated
        searchInputElement.clear();
        searchInputElement.sendKeys("apple");

        //submit()
        searchInputElement.submit();
        List<WebElement> elementList = driver.findElements(By.className("g"));

        // click()
        //chaining example
        elementList.get(0).findElement(By.tagName("a")).click();

        // 5  GOOD ONE [ CODE ] , but no .State-NSW such element Error , but It get ELEMENTS STATES


        WebElement searchInputElement = driver.findElement(By.name("q"));

        //isDisplayed()
        System.out.println(searchInputElement.isDisplayed());

        driver.get("http://the-internet.herokuapp.com/checkboxes");
        WebElement firstCheckBox = driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]"));

        //isSelected()
        System.out.println("isSelected(): " + firstCheckBox.isSelected());
        //click()
        System.out.println("getAttribute(): " + firstCheckBox.getAttribute("checked"));
        firstCheckBox.click();
        //isSelected()
        System.out.println("isSelected() :" + firstCheckBox.isSelected());
        //getAttribute()
        System.out.println("getAttribute(): " + firstCheckBox.getAttribute("checked"));

        //getText()
        WebElement mainHeader = driver.findElement(By.cssSelector(".example h3"));
        System.out.println("Main Header text: " + mainHeader.getText());

        //getText() from sub-elements (child elements)
        WebElement divElement = driver.findElement(By.cssSelector("div.example"));
        System.out.println("Retrieving text also from sub-elements: " + divElement.getText());


        driver.get("https://jedwatson.github.io/react-select/");
        WebElement statesDropdown = driver.findElement(By.cssSelector(".State-NSW"));
        //isEnabled()
        System.out.println("Is dropdown enabled: " + statesDropdown.isEnabled());
        WebElement disableStatesDropdownCheckbox = driver.findElement(By.xpath("//input[following-sibling::*[text()='Disabled']]"));
        System.out.println("isSelected() :" + disableStatesDropdownCheckbox.isSelected());

        disableStatesDropdownCheckbox.click();

        statesDropdown = driver.findElement(By.cssSelector(".State-NSW"));
        //will return true since given dropdown is not a input field and isEnabled() method works only on <input> elements
        System.out.println("Is dropdown enabled: " + statesDropdown.isEnabled());
        System.out.println("isSelected() :" + disableStatesDropdownCheckbox.isSelected());
        System.out.println("getCssValue(visibility): " + statesDropdown.getCssValue("visibility"));
        System.out.println("getCssValue(opacity): " + statesDropdown.getCssValue("opacity"));


        driver.get("http://akveo.com/blur-admin-mint/#/ui/typography");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement redText = driver.findElement(By.cssSelector(".red-text p"));
        System.out.println("getCssValue(color): " + redText.getCssValue("color"));
        // 6 Unit Tests GOODE CODE , IT RUns ANYWAY
        @Test
        public void myFirstTest() {
            driver.navigate().to("http://www.google.com");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.name("q")).sendKeys("webdriver");
            driver.findElement(By.name("btnG")).click();
            wait.until(titleIs("webdriver - Поиск в Google"));
        }

        @Test
        public void mySecondTest() {
            driver.navigate().to("http://www.google.com");
            wait.until(d -> d.findElement(By.name("q"))).sendKeys("webdriver");
            driver.findElement(By.name("btnG")).click();
            wait.until(titleIs("webdriver - Поиск в Google"));
        }

        @Test
        public void myThirdTest() {
            driver.navigate().to("http://www.google.com");
            driver.findElement(By.name("q")).sendKeys("webdriver");
            driver.findElement(By.name("btnG")).click();
            wait.until(titleIs("webdriver - Поиск в Google"));
        }


        public void waitUntilElemIsDisabled(WebElement element) {
            try {
                wait.until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return !element.isEnabled();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public void waitUntilElemIsDisabledLambda(WebElement element) {

            wait.until(d -> !element.isEnabled());

        }

        // 7 GOOD ONE CODE ALSO, It does THE CLICK with EXPLICIT WAIT

        WebElement startButton = driver.findElement(By.cssSelector("#start button"));
        startButton.click();

        // creating WebDriverWait instance and providing time to wait
        WebDriverWait wait = new WebDriverWait(driver, 5);

        // providing condition to wait for into until() method
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish")));

//        WebElement helloWorldText = driver.findElement(By.cssSelector("#finish"));
//        System.out.println("Is element displayed: " + helloWorldText.isDisplayed());

        System.out.println("Is element displayed: " + element.isDisplayed());


        // 8 GOOD CODE , IT SEARCH FOR STH , IMPLICIT WAIT
                //sets maximum 10 second wait for each 'findElement/s()' method
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                driver.get("https://www.macys.com/");

                // waits under 10 seconds before element is located in the DOM, otherwise NuSuchElementException is thrown
                WebElement closeButton = driver.findElement(By.id("closeButton"));
                closeButton.click();

                //...

                // also waits under 10 seconds before element is located in the DOM, otherwise NuSuchElementException is thrown
                WebElement globalSearchInputField = driver.findElement(By.id("globalSearchInputField"));
                globalSearchInputField.sendKeys("smth");
            }

        // 9 GOOD ONE CODE , IT DOES THE CLICK , with THREAD SLEEP
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

        WebElement startButton = driver.findElement(By.cssSelector("#start button"));
        startButton.click();

        //just stop code execution for 5 seconds
        Thread.sleep(5000);

        WebElement helloWorldText = driver.findElement(By.cssSelector("#finish"));
        System.out.println("Is element displayed: " + helloWorldText.isDisplayed());



    }
*
* */