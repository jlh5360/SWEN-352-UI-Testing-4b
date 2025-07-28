package edu.rit.swen253.page.hoursandlocations;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * A Page Object for the Baeldung Home page.
 *
 * @author Matthew Peck
 */
public class HoursAndLocationsPage extends AbstractPage {
    
  /**
   * A finder to extract the main body content.
   * WARN: this is fragile code
   */
  private static final By MAIN_CONTENT_FINDER =
    By.cssSelector("hours-and-locations");
  private static final By DINING_FINDER =
    By.cssSelector("Dining");

  private DomElement mainContentPanel;

  public HoursAndLocationsPage() {
    super();
    // validate basic page structure
    try {
      mainContentPanel = findOnPage(MAIN_CONTENT_FINDER);
      
    } catch (TimeoutException e) {
      fail("Main content panel not found");
    }
  }


  public Location getFirstLocation() {
    return new Location(
      findOnPage(DINING_FINDER)
      .findChildBy(By.cssSelector("div > div"))
      .findChildBy(By.xpath("div[9]"))
      .findChildBy(By.xpath("div[2]"))
      .findChildBy(By.xpath("div[4]"))
      );
  }
}
