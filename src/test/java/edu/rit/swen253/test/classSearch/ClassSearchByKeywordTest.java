package edu.rit.swen253.test.classSearch;

import edu.rit.swen253.page.classSearch.ClassSearchPage;
import edu.rit.swen253.page.classSearch.SearchResultsView;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;

/**
 * UI Test for Class Search by Keyword functionality in TigerCenter.
 */
public class ClassSearchByKeywordTest extends AbstractWebTest {
    private static final Logger logger = Logger.getLogger(ClassSearchByKeywordTest.class.getName());
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
     * User Story: As a public user I want to search for classes by keyword so that I can find courses relevant to my interests.
     * Scenario #1 Part 2: Perform successful search for a valid keyword and term
     */
    @Test
    @DisplayName("Scenario #1 - Part 2: Perform successful search for a valid keyword and term")
    void performSuccessfulSearchForKeywordAndTerm() {
        //Action: Enter "PHILOSOPHY" into the search keyword input field.
        String keyword = "PHILOSOPHY";
        logger.info("Action: Entering keyword '" + keyword + "' into search field.");
        classSearchPage.enterKeyword(keyword);
        logger.info("System response: Keyword entered.");

        //Action: Select "2025-26 Fall (2251)" from the term dropdown.
        String term = "2025-26 Fall (2251)";
        logger.info("Action: Selecting term '" + term + "' from dropdown.");
        classSearchPage.selectTerm(term);
        logger.info("System response: Term selected.");

        //Action: Click the "Search" button.
        logger.info("Action: Clicking 'Search' button.");
        SearchResultsView searchResultsView = classSearchPage.clickSearchButton();

        //System response: System displays a list of courses related to "PHILOSOPHY" for the 2025-26 Fall term.
        logger.info("System response: Attempting to assert search results displayed.");
        searchResultsView.assertSearchResultsDisplayed();
        logger.info("Assertion: Search results displayed for keyword '" + keyword + "' and term '" + term + "'.");

        //Goal Achieved: The user has successfully searched for classes and seen the results.
        logger.info("Test 'performSuccessfulSearchForKeywordAndTerm' passed. GOAL ACHIEVED.");
    }
}
