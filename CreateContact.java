package ru.geekbrains.ermakova.srm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CreateContact {

    private static WebDriver driver;
    private static final String lOGIN_PAGE_URL="https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest";
    private static final String STUDENT_PASSWORD = "Student2020!";
    private static final String expensesMenu = "//*[@id=\"main-menu\"]/ul/li[1]/a/span";
    private static final String expensesSubMenu ="//*[@id=\"main-menu\"]/ul/li[1]/ul/li[4]/a/span";
    private static final String button ="//*[@id=\"history-content\"]/ul/li[1]/a";
    private static final String saveButton="button[class='btn btn-success action-button']";


    public static void main (String []args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");


        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        login();
        driver.findElement(By.xpath(expensesMenu)).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath(expensesSubMenu)).click();
        Thread.sleep(2000);
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(button))));
        driver.findElement(By.xpath(button)).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains("/create"));
        driver.findElement(By.xpath("//*[@id=\"crm_contact_lastName-uid-5fc46dcac6294\"]")).sendKeys("Ivanov");
        driver.findElement(By.xpath("//*[@id=\"crm_contact_firstName-uid-5fc46dcac61e1\"")).sendKeys("Ivan");
        Select  createOrgDropDown = new Select(driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input")));
        createOrgDropDown.selectByValue("1");
        driver.findElement(By.xpath("//*[@id=\"crm_contact_jobTitle-uid-5fc46dcac6385\"]")).sendKeys("Tester");
        driver.findElement(By.xpath(saveButton)).click();
        tearDown();

    }

    private static void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }

    private static void login() {
        driver.get(lOGIN_PAGE_URL);
        WebElement LoginTextInput = driver.findElement(By.name("_username"));
        LoginTextInput.sendKeys(STUDENT_LOGIN);
        WebElement PasswordTextInput = driver.findElement(By.name("_password"));
        PasswordTextInput.sendKeys(STUDENT_PASSWORD);
        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();
    }


}
