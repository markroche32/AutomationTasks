import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Three {

	private static WebDriver driver;
	private static Wait<WebDriver> wait;
	private static By dateTime = By.id("datetimepicker");
	private static WebElement dateTimeElem;
	private static SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
	private static Date dateTimeObject = null;
	static String dateTimeObjectString;
	
	public static void main(String[] args) {
	
		
		driver = DriverUtils.setupNewDriver();
		wait = new WebDriverWait(driver, 30);
		driver.get("http://demos.telerik.com/aspnet-mvc/datetimepicker/index");
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dateTime));
		wait.until(ExpectedConditions.elementToBeClickable(dateTime));
		
		dateTimeElem = driver.findElement(dateTime);
		
		String dtString = dateTimeElem.getAttribute("value");
		System.out.println("(String)JavaScript = " + dtString);
		
		stringToDateTime(dtString);
		
		DriverUtils.disposeDriver(driver);
		
	}
	
	/**
	 * @author mark1983
	 * Convert String to Java Date Time.
	 * Print Java Date Time.
	 * Print Formatted Java Date Time.
	 * Format = "MM/dd/yyyy hh:mm a"
	 */
	public static void stringToDateTime(String dt){
		
		//9/17/2016 12:41 AM
		try {
			//date = format.parse("1/15/2014 9:57:03 AM");
			dateTimeObject = format.parse(dt);
			System.out.println("(Java Date Time 1 = " + dateTimeObject);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		dateTimeObjectString = format.format(dateTimeObject);
		
		System.out.println("(String)Java Date Time = " + dateTimeObjectString);
		
	}

}
