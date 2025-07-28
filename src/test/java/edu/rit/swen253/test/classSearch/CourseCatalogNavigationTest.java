package edu.rit.swen253.test.classSearch;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.classSearch.ClassSearchPage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.Disabled;

/**
 * UI Test for Course Catalog Navigation functionality in TigerCenter.
 */
// @Disabled
public class CourseCatalogNavigationTest extends AbstractWebTest {
    private static final Logger logger = Logger.getLogger(CourseCatalogNavigationTest.class.getName());
    private static final String TIGER_CENTER_HOME_URL = "https://tigercenter.rit.edu/tigerCenterApp/landing";

    /**
     * User Story: As a public user I want to browse classes through the Course Catalog so that I can explore available courses by subject area.
     * Scenario #1: Navigate to an available section of a specific course via Course Catalog
     */
    @Test
    @DisplayName("Scenario #1: Navigate to an available section of a specific course via Course Catalog")
    void navigateToAvailableSectionViaCourseCatalog() {
        logger.info("Starting test: navigateToAvailableSectionViaCourseCatalog");

        //Action 1: Navigate to Tiger Center homepage.
        logger.info("Action 1: Navigating to Tiger Center homepage: " + TIGER_CENTER_HOME_URL);
        TigerCenterHomePage homePage = seleniumUtils.navigateToPage(TIGER_CENTER_HOME_URL, TigerCenterHomePage::new);
        logger.info("System response 1: Navigated to homepage. Current URL: " + seleniumUtils.getCurrentUrl() + ", Title: " + seleniumUtils.getTitle());

        //Action 2: Click on the "Class Search" button.
        logger.info("Action 2: Clicking on 'Class Search' button.");
        homePage.selectClassSearch();
        
        //System response 2: System responds with the Class Search page in the same tag.
        ClassSearchPage classSearchPage = new ClassSearchPage(); // Create new page object for the new view
        logger.info("System response 2: Class Search page loaded. Current URL: " + seleniumUtils.getCurrentUrl());
        assertTrue(seleniumUtils.getCurrentUrl().contains("class-search"), "Expected to be on Class Search page.");
        logger.info("Assertion: Successfully navigated to Class Search page.");

        //Action 3: Click on the "Course Catalog" link/button.
        classSearchPage.clickCourseCatalogLink();
        //System response: System displays a course catalog overlay of a list of academic subjects or an expandable tree structure.

        //Action 4: Click on "CAD - College of Art and Design".
        classSearchPage.expandCourseCatalogSubject("College of Art and Design");
        //System response: System displays opening of the expandable tree or accordion of various subjects.

        //Action 5: Click on "ARED - Art Education".
        classSearchPage.expandCourseCatalogSubject("Art Education");
        //System response: System displays opening of the expandable tree or accordion of various courses.

        //Action 6: Click on "Child Development in Art".
        classSearchPage.selectCourseFromCatalog("Child Development in Art");
        //System response: System displays detailed information for "Child Development in Art".

        //Action 7: Select "2025 - 26 Fall (2251)" from the term dropdown within the course details.
        classSearchPage.selectTermInCourseDetails("2025 - 26 Fall (2251)");

        //System response: System displays a list of available sections for "Child Development in Art" for the 2025-26 Fall term.
        classSearchPage.assertSectionsDisplayed();

        //Goal Achieved: The user has successfully navigated to and viewed available sections for a specific course via the Course Catalog.
        System.out.println("Test 'navigateToAvailableSectionViaCourseCatalog' passed for Course Catalog Navigation.");
    }
}
