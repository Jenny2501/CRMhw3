package ru.geekbrains.ermakova.srm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateProject {

    private static WebDriver driver;
    private static final String lOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String STUDENT_LOGIN = "Applanatest";
    private static final String STUDENT_PASSWORD = "Student2020!";
    private static final String expensesMenu="//*[@id=\"main-menu\"]/ul/li[3]/a/span";
    private static final String expensesSubMenu ="//*[@id=\"main-menu\"]/ul/li[3]/ul/li[4]/a/span";
    private static final String button = "//*[@id=\"container\"]/div[1]/div/div/div[2]/div/div/a";


    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            login();
        driver.findElement(By.xpath(expensesMenu)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(expensesSubMenu)).click();
        Thread.sleep(2000);
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(button))));
        driver.findElement(By.xpath(button)).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains("/create"));
        driver.findElement(By.xpath("//*[@id=\"crm_project_name-uid-5fc747254b518\"]")).sendKeys("TestOrganization");
        Select addOrganizationDropDown = new Select(driver.findElement(By.xpath("//*[@id=\"s2id_crm_project_contactMain-uid-5fc74a911ea57\"]/a/span[1]")));
        addOrganizationDropDown.selectByValue("1");
        driver.findElement(By.xpath("//*[@id=\"crm_project_type_1-uid-5fc74a90be9aa\"];")).click();
        Select middlePriorityDropDown = new Select(driver.findElement(By.xpath("//*[@id=\"crm_project_priority-uid-5fc74a90c3fcf\"]")));
        middlePriorityDropDown.selectByValue("4");
        Select clientsResourcesDropDown = new Select(driver.findElement(By.xpath("//*[@id=\"crm_project_financeSource-uid-5fc74a90c9924\"]")));
        clientsResourcesDropDown.selectByValue("3");
        Select partitionDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"crm_project_businessUnit-uid-5fc74a90e2321\"]")));
        partitionDropDown.selectByValue("3");
       Select curatorProjectDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"crm_project_curator-uid-5fc74a91191ac\"]")));
        curatorProjectDropDown.selectByValue("3");
       Select leaderDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"crm_project_rp-uid-5fcae8dca6679\"]")));
        leaderDropDown.selectByValue("3");
        Select managerDropDown = new Select (driver.findElement(By.xpath("//*[@id=\"crm_project_manager-uid-5fcae8dc92fdc\"]")));
        managerDropDown.selectByValue("3");
        driver.findElement(By.xpath("//*[@id=\"crm_project-uid-5fcae8dccb13f\"]/div[1]/div/div/div[2]/div[1]/div[4]/button")).click();
        tearDown();


    }

    private static void login() throws InterruptedException {
        driver.get(lOGIN_PAGE_URL);
        WebElement LoginTextInput = driver.findElement(By.name("_username"));
        LoginTextInput.sendKeys(STUDENT_LOGIN);
        WebElement PasswordTextInput = driver.findElement(By.name("_password"));
        PasswordTextInput.sendKeys(STUDENT_PASSWORD);
        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();
        Thread.sleep(5000);
    }

    private static void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }
}