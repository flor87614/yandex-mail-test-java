package ru.yandex.mail;

import my.selenium.com.google.pages.MainPage;
import my.selenium.ru.yandex.mail.LoginPage;
import my.selenium.ru.yandex.mail.MailPage;
import my.selenium.ru.yandex.mail.SendMailForm;
import my.selenium.ru.yandex.mail.WelcomePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class YandexMailTest {
    private WebDriver driver;
    private WelcomePage welcomePage;

    @BeforeClass
    public void init() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

        driver = new ChromeDriver(dc);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void setUp() {
        welcomePage = new WelcomePage(driver);
        driver.get("https://mail.yandex.ru");
    }

    @Test
    public void testSendMailWithSpecifiedSubject_IncreasedCountByOne() {
        LoginPage loginPage = welcomePage.enter();
        MailPage mailPage = loginPage.login("simbirsoft91@yandex.ru", "Nvd-fvE-Y2i-Z6P");
        int mailsCount = mailPage.countMailsBySubject("Simbirsoft theme");

        SendMailForm sendMailForm = mailPage.createMail();
        mailPage = sendMailForm.sendMail("Simbirsoft theme", "simbirsoft91@yandex.ru", "Найдено " + String.valueOf(mailsCount));

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ComposeDoneScreen-Wrapper']")));
        driver.navigate().refresh();

        Assert.assertEquals(mailPage.countMailsBySubject("Simbirsoft theme"), mailsCount + 1);
    }
}
