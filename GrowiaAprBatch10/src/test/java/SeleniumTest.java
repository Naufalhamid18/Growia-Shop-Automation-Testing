import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.service.DriverFinder;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;

import java.time.Duration;

public class SeleniumTest {
    private static final Logger logger = LogManager.getLogger(DriverFinder.class);

    WebDriver driver;


    @Test
    public void logintest(){
        // Open browser
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Klik tombol "Make Appointment"
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.id("btn-make-appointment"))));
        driver.findElement(By.id("btn-make-appointment")).click();

        // Input Username dan password
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.id("txt-username"))));
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();

        // Melakukan Book Appointment
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chk_hospotal_readmission\"]"))));
        driver.findElement(By.xpath("//*[@id=\"chk_hospotal_readmission\"]")).click();
        driver.findElement(By.id("txt_visit_date")).sendKeys("18/02/2024");
        driver.findElement(By.id("txt_comment")).sendKeys("I Love GROWIA Education");
        driver.findElement(By.id("btn-book-appointment")).click();

        // Verify Berhasil Melakukan Book Appointment
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[.='Appointment Confirmation']"))));
        String TxtSecureAreaActual = driver.findElement(By.xpath("//h2[.='Appointment Confirmation']")).getText();
        String TxtSecureAreaExpected = "Appointment Confirmation";

        Assert.assertEquals(TxtSecureAreaActual,TxtSecureAreaExpected);

        // Close Browser
        driver.quit();



    }
}
