import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;


public class Four {

	private static WebDriverWait wait;
	private static WebDriver driver;
	private static WebElement hiddenElement;
	private static JavascriptExecutor js;
	private static String text;
	
	
	public static void main(String[] args) {
		
		
		driver = DriverUtils.setupNewDriver();
		wait = new WebDriverWait(driver, 30);
		driver.get("http://accounts.google.com/Login");
		
		//Wait For DOM To Load Before Checking For Hidden Element
		Predicate<WebDriver> pageLoaded = wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete");
		new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).until(pageLoaded);
		
		hiddenElement = driver.findElement(By.xpath("//label[@class='hidden-label'][@for='Email']"));
		
		hiddenElemGetText(hiddenElement);
		
		hiddenElemClick(hiddenElement);
		DriverUtils.disposeDriver(driver);

	}
	

	/**
	 * @author mark1983
	 * Selenium Returns Nothing For Hidden Element getText().
	 * Use JavascriptExecutor arguments[0].innerHTML to get Text of hidden element.
	 * 
	 */
	public static void hiddenElemGetText(WebElement hidden){
		
		System.out.println("Selenium Returns Nothing For Hidden Element getText() = " + hiddenElement.getText());
		
		text = (String)((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML", hiddenElement);
		System.out.println("JavascriptExecutor arguments[0].innerHTML = " + text.trim());
		
	}
	
	
	/**
	 * @author mark1983
	 * Selenium Click Event For Hidden Element throws ElementNotVisibleException .
	 * Use JavascriptExecutor arguments[0].click() to Click hidden element.
	 * 
	 */
	public static void hiddenElemClick(WebElement hidden){
		
		try {
			hiddenElement.click();
		} catch (ElementNotVisibleException e) {
			
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", hidden);
			System.out.println("JavascriptExecutor arguments[0].click() Success");
			
		}
		
	}

}
