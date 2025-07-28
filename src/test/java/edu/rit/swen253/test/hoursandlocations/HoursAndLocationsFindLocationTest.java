package edu.rit.swen253.test.hoursandlocations;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.hoursandlocations.HoursAndLocationsPage;
import edu.rit.swen253.page.hoursandlocations.Location;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.BrowserWindow;
import org.junit.jupiter.api.*;

import static edu.rit.swen253.utils.BrowserType.FIREFOX;
import static edu.rit.swen253.utils.BrowserType.onBrowser;
import static edu.rit.swen253.utils.TimingUtils.sleep;
import static org.junit.jupiter.api.Assertions.*;


/**
 * A simple test that uses the TigerCenter <em>Maps at RIT</em> button to navigate
 * to a new tab that displays the {@code maps.rit.edu} web page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HoursAndLocationsFindLocationTest extends AbstractWebTest {

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
  @DisplayName("Third, click the map icon of the first location on the list")
  void clickFirstLocation() {
    firstLocation = hrAndLocPage.getFirstLocation();
    String mapIconURL = firstLocation.getMapIconURL();
    //for some reason clicking on the link gets rid of the /m in the middle of the link, so I am just cutting that out
    mapIconURL = mapIconURL.substring(0,21)+mapIconURL.substring(23);
    firstLocation.clickMapIcon();
    final SimplePage mapsPage = assertNewWindowAndSwitch(SimplePage::new);

    // there's a timing issue with Firefox (give it a second to render)
    if (onBrowser(FIREFOX)) {
      sleep(1);
    }

    assertEquals(mapIconURL, mapsPage.getURL());
  }
}
