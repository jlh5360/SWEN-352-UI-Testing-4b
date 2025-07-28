package edu.rit.swen253.test.hoursandlocations;

import edu.rit.swen253.page.hoursandlocations.HoursAndLocationsPage;
import edu.rit.swen253.page.hoursandlocations.Location;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import org.junit.jupiter.api.*;

import static edu.rit.swen253.utils.TimingUtils.sleep;
import static org.junit.jupiter.api.Assertions.*;


/**
 * A simple test that uses the TigerCenter <em>Maps at RIT</em> button to navigate
 * to a new tab that displays the {@code maps.rit.edu} web page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HoursAndLocationsStoreHoursTest extends AbstractWebTest {

  private TigerCenterHomePage homePage;
  private HoursAndLocationsPage hrAndLocPage;
  private Location firstLocation;

  //
  // Test sequence
  //

  @Test
  @Order(1)
  @DisplayName("First, navigate to the Tiger Center Home page.")
  void navigateToHomePage() {
    homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
    assertNotNull(homePage);
  }

  @Test
  @Order(2)
  @DisplayName("Second, click on the Hours & Locations button and validate navigation.")
  void navigateToHoursAndLocations() {
    homePage.selectHoursAndLocations();
    hrAndLocPage = new HoursAndLocationsPage();
    assertTrue(seleniumUtils.getCurrentUrl().contains("hours-and-locations"));
  }

  @Test
  @Order(3)
  @DisplayName("Third, click the first location on the list and assert that the hours are displayed")
  void clickFirstLocation() {
    firstLocation = hrAndLocPage.getFirstLocation();
    firstLocation.click();
    assertTrue(firstLocation.dropdownisDisplayed());
  }
}
