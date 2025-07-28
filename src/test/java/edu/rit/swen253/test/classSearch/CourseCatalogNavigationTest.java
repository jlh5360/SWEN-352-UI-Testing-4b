package edu.rit.swen253.test.classSearch;

import edu.rit.swen253.page.classSearch.ClassSearchPage;
import edu.rit.swen253.page.classSearch.CourseCatalogView;
import edu.rit.swen253.page.classSearch.CourseDetailView;
import edu.rit.swen253.page.classSearch.CourseSectionsPage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;

/**
 * UI Test for Course Catalog Navigation functionality in TigerCenter.
 */
public class CourseCatalogNavigationTest extends AbstractWebTest {
    private static final Logger logger = Logger.getLogger(CourseCatalogNavigationTest.class.getName());
    private static final String TIGER_CENTER_HOME_URL = "https://tigercenter.rit.edu/tigerCenterApp/landing";

    private ClassSearchPage classSearchPage;
    
    @BeforeAll
    void setupClassSearchPage() {
        logger.info("Setting up: Navigating to Class Search page for test.");
        TigerCenterHomePage tigerCenterHomePage = seleniumUtils.navigateToPage(TIGER_CENTER_HOME_URL, TigerCenterHomePage::new);
        tigerCenterHomePage.selectClassSearch();   //This navigates to the Class Search page
        classSearchPage = new ClassSearchPage();   //Instantiate the page object after navigation
        assertTrue(classSearchPage.hasDomElement(By.cssSelector("class-search")), "Expected to be on Class Search page during setup.");
        logger.info("Setup complete: On Class Search page.");
    }

    /**
     * User Story: As a public user I want to search for classes by keyword so that I can find courses relevant to my interests.
     * Scenario #1 Part 1: Verify navigation to Class Search page
     */
    @Test
    @DisplayName("Scenario #1 - Part 1: Verify navigation to Class Search page")
    void verifyNavigationToClassSearchPage() {
        assertTrue(classSearchPage.hasDomElement(By.cssSelector("class-search")), "Expected to be on Class Search page after navigation.");
        logger.info("Assertion: Successfully navigated to Class Search page.");
        logger.info("Test 'verifyNavigationToClassSearchPage' passed.");
    }

    /**
     * User Story: As a public user I want to browse classes through the Course Catalog so that I can explore available courses by subject area.
     * Scenario #1: Navigate to an available section of a specific course via Course Catalog
     */
    @Test
    @DisplayName("Scenario #1 - Part 2: Navigate to an available section of a specific course via Course Catalog")
    void performSuccessfulnavigationToAvailableSectionViaCourseCatalog() {
        //Action 3: Click on the "Course Catalog" link/button.
        logger.info("Action 3: Clicking on 'Course Catalog' link/button.");
        CourseCatalogView courseCatalogView = classSearchPage.clickCourseCatalogLink(); // Returns CourseCatalogView
        logger.info("System response 3: Course Catalog overlay displayed.");
        //System response: System displays a course catalog overlay of a list of academic subjects or an expandable tree structure.

        //Action 4: Click on "CAD - College of Art and Design".
        String CAD = "College of Art and Design";
        logger.info("Action 4: Expanding Course Catalog subject: '" + CAD + "'.");
        courseCatalogView.expandCourseCatalogSubject(CAD);   //Returns CourseCatalogView
        logger.info("System response 4: Subject '" + CAD + "' expanded.");
        //System response: System displays opening of the expandable tree or accordion of various subjects.

        //Action 5: Click on "ARED - Art Education".
        String ARED = "Art Education";
        logger.info("Action 5: Expanding Course Catalog subject: '" + ARED + "'.");
        courseCatalogView.expandCourseCatalogSubject(ARED);   //Call on CourseCatalogView
        logger.info("System response 5: Subject '" + ARED + "' expanded.");
        //System response: System displays opening of the expandable tree or accordion of various courses.

        //Action 6: Click on "Child Development in Art".
        String courseName = "Child Development in Art";
        logger.info("Action 6: Selecting course from catalog: '" + courseName + "'.");
        CourseDetailView courseDetailView = courseCatalogView.selectCourseFromCatalog(courseName); // Returns CourseDetailView
        logger.info("System response 6: Detailed information for '" + courseName + "' displayed.");
        //System response: System displays detailed information for "Child Development in Art".

        //Action 7: Select "2025 - 26 Fall (2251)" from the term dropdown within the course details.
        String term = "2025 - 26 Fall (2251)";
        logger.info("Action 7: Selecting term '" + term + "' within course details.");
        CourseSectionsPage courseSectionsPage = courseDetailView.selectTermInCourseDetails(term);   //Returns CourseSectionsPage
        logger.info("System response 7: Term '" + term + "' selected in course details.");

        //System response: System displays a list of available sections for "Child Development in Art" for the 2025-26 Fall term.
        logger.info("System response: Attempting to assert sections displayed.");
        courseSectionsPage.assertSectionsDisplayed(); // Call on CourseSectionsPage
        logger.info("Assertion: Available sections displayed for course '" + courseName + "' for term '" + term + "'.");

        //Goal Achieved: The user has successfully navigated to and viewed available sections for a specific course via the Course Catalog.
        logger.info("Test 'navigateToAvailableSectionViaCourseCatalog' passed for Course Catalog Navigation.  GOAL ACHIEVED.");
    }
}
