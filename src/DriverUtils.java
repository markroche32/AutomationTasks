import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DriverUtils {
	

	private static FirefoxDriver driver;
	private static WebDriverWait wait;


	public static WebDriver setupNewDriver(){
		
		System.setProperty("webdriver.firefox.marionette","C:\\Users\\mark1983\\Downloads\\geckodriver-v0.9.0-win64\\geckodriver.exe");
		//System.setProperty("webdriver.gecko.driver","C:\\Users\\mark1983\\Downloads\\geckodriver-v0.9.0-win64\\geckodriver.exe");
		
		//String currentDir = System.getProperty("user.dir");
		//String driverLocation = currentDir + "/DriverFolder/geckodriver.exe";
		//System.setProperty("webdriver.firefox.marionette",driverLocation);
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	
	public static void disposeDriver(WebDriver driver){
		
		driver.close();
		driver.quit();
	}

}
