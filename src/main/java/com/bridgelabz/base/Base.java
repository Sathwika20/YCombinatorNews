package com.bridgelabz.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Base {
    public static WebDriver driver;
    //Before execution
    @BeforeTest
    public void setUp() throws InterruptedException {
        //launches the chromedriver using WebDriver interface
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //Used to maximise the window
        driver.manage().window().maximize();
        driver.get("https://news.ycombinator.com/");
        Thread.sleep(3000);
    }
    //After execution
    @AfterTest
    public void tearDown() {
        //used to close the current window
        driver.close();
    }

}
