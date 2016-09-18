import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class One {

	private static WebDriver driver;
	private static Wait<WebDriver> wait;
	private static String email = "lewisAutoTest@gmail.com";
	private static String pass = "AutoTest";
	private static String notPass = "fail";
	
	public static void main(String[] args) {
	
		driver = DriverUtils.setupNewDriver();
		wait = new WebDriverWait(driver, 30);
		successLogin();
		DriverUtils.disposeDriver(driver);
		
		driver = DriverUtils.setupNewDriver();
		wait = new WebDriverWait(driver, 30);
		failLogin();
		DriverUtils.disposeDriver(driver);

	}
	
	
	public static void successLogin() {

		driver.get("http://accounts.google.com/Login");

		driver.findElement(By.id("Email")).sendKeys(email);

		WebElement next = driver.findElement(By.id("next"));
		elementIsClickable(next).click();

		driver.findElement(By.id("Passwd")).sendKeys(pass);

		WebElement signIn = driver.findElement(By.id("signIn"));
		elementIsClickable(signIn).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h1[@class='lE']")));
		WebElement account = driver.findElement(By.xpath("//h1[@class='lE']"));

		System.out.println(account.getText().equals("My Account") ? "successLogin() Passed" : "successLogin() Failed");
		
	}

	public static void failLogin() {

		driver.get("http://accounts.google.com/Login");

		driver.findElement(By.id("Email")).sendKeys(email);

		WebElement next = driver.findElement(By.id("next"));
		elementIsClickable(next).click();

		driver.findElement(By.id("Passwd")).sendKeys(notPass);

		WebElement signIn = driver.findElement(By.id("signIn"));
		elementIsClickable(signIn).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='error-msg' and contains(text(), 'Wrong password. Try again.')]")));
		WebElement account = driver.findElement(By.xpath("//span[@class='error-msg' and contains(text(), 'Wrong password. Try again.')]"));

		System.out.println(account.getText().equals("Wrong password. Try again.") ? "failLogin() Passed" : "failLogin() Failed");
	}

	public static WebElement elementIsClickable(WebElement elem) {

		wait.until(ExpectedConditions.elementToBeClickable(elem));
		wait.until(ExpectedConditions.visibilityOf(elem));

		return elem;

	}

}
