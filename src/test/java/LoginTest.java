import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class LoginTest {

    public static void main(String[] args) {
        // Mengatur WebDriverManager untuk mengelola driver Chrome
        WebDriverManager.chromedriver().setup();

        // Membuat instance WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Buka halaman login
            driver.get("https://practicetestautomation.com/practice-test-login/");
            System.out.println("Halaman login dibuka.");

            // Masukkan username
            WebElement usernameField = driver.findElement(By.id("username"));
            usernameField.sendKeys("student");
            System.out.println("Username dimasukkan.");

            // Masukkan password
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("Password123");
            System.out.println("Password dimasukkan.");

            // Klik tombol login
            WebElement loginButton = driver.findElement(By.id("submit"));
            loginButton.click();
            System.out.println("Tombol login diklik.");

            // Tunggu beberapa saat untuk memastikan halaman baru terbuka
            Thread.sleep(2000);

            // Verifikasi URL halaman baru
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("practicetestautomation.com/logged-in-successfully/")) {
                System.out.println("URL verifikasi berhasil.");
            } else {
                System.out.println("URL verifikasi gagal.");
            }

            // Tunggu hingga elemen teks yang diharapkan muncul
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Increased wait time
            WebElement messageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Congratulations')]"))); // Use presenceOfElementLocated
            if (messageElement.isDisplayed()) {
                System.out.println("Teks verifikasi berhasil.");
            } else {
                System.out.println("Teks verifikasi gagal.");
            }

            // Verifikasi tombol Log out ditampilkan di halaman baru
            WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Log out']")));
            if (logoutButton.isDisplayed()) {
                System.out.println("Tombol Log out ditampilkan.");
            } else {
                System.out.println("Tombol Log out tidak ditampilkan.");
            }
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan selama pengujian: ");
            e.printStackTrace();
        } finally {
            // Tutup browser
            driver.quit();
            System.out.println("Browser ditutup.");
        }
    }
}
