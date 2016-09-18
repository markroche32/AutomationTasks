
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Function;

public class Two {

	private static WebDriver driver;
	private static Wait<WebDriver> wait;
	private static By freight = By.xpath("//div[@id='singleSort']//a[contains(text(), 'Freight')]");
	private static By shipCountry = By.xpath("//div[@id='singleSort']//a[contains(text(), 'Ship Country')]");
	private static By orderDate = By.xpath("//div[@id='singleSort']//a[contains(text(), 'Order Date')]");
	private static WebElement elemColumn;
	
	public static void main(String[] args) {
		
		driver = DriverUtils.setupNewDriver();
		wait = new WebDriverWait(driver, 30);
		driver.get("http://demos.telerik.com/aspnet-mvc/grid/sorting");
			
		sortByColumn(freight, 1);
		sortByColumn(shipCountry, 2);
		sortByColumn(orderDate, 1);
		
		DriverUtils.disposeDriver(driver);
	}


	/**
	 * @param column = column to sort.
	 * @param clicks = 1 to sort in Ascending Order, 2 to sort Descending.
	 */
	public static void sortByColumn(By column, int clicks) {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(column));
		elemColumn = driver.findElement(column);
		
		int i = 0;
		while(i < clicks){
			
			elementIsClickable(elemColumn).click();
			i++;
		}		
	}

	public static WebElement elementIsClickable(WebElement elem) {

		wait.until(ExpectedConditions.elementToBeClickable(elem));
		wait.until(ExpectedConditions.visibilityOf(elem));

		return elem;

	}

}
