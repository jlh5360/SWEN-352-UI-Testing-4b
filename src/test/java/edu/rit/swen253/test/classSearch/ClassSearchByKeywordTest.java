package edu.rit.swen253.test.classSearch;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.classSearch.ClassSearchPage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.Disabled;

/**
 * UI Test for Class Search by Keyword functionality in TigerCenter.
 */
// @Disabled
public class ClassSearchByKeywordTest extends AbstractWebTest {
    private static final Logger logger = Logger.getLogger(ClassSearchByKeywordTest.class.getName());
    private static final String TIGER_CENTER_HOME_URL = "https://tigercenter.rit.edu/tigerCenterApp/landing";

    /**
     * User Story: As a public user I want to search for classes by keyword so that I can find courses relevant to my interests.
     * Scenario #1: Successful search for a valid keyword and term
     */
    @Test
    @DisplayName("Scenario #1: Successful search for a valid keyword and term")
    void successfulSearchForKeywordAndTerm() {
        logger.info("Starting test: successfulSearchForKeywordAndTerm");
        
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
        
        //Assert that we are on the Class Search page by checking its specific elements or URL segment
        assertTrue(seleniumUtils.getCurrentUrl().contains("class-search"), "Expected to be on Class Search page.");
        logger.info("Assertion: Successfully navigated to Class Search page.");

        //Action 3: Enter "PHILOSOPHY" into the search keyword input field.
        String keyword = "PHILOSOPHY";
        logger.info("Action 3: Entering keyword '" + keyword + "' into search field.");
        classSearchPage.enterKeyword(keyword);
        logger.info("System response 3: Keyword entered.");

        //Action 4: Select "2025-26 Fall (2251)" from the term dropdown.
        String term = "2025-26 Fall (2251)";
        logger.info("Action 4: Selecting term '" + term + "' from dropdown.");
        classSearchPage.selectTerm(term);
        logger.info("System response 4: Term selected.");

        //Action 5: Click the "Search" button.
        logger.info("Action 5: Clicking 'Search' button.");
        classSearchPage.clickSearchButton();

        //System response: System displays a list of courses related to "PHILOSOPHY" for the 2025-26 Fall term.
        logger.info("System response: Attempting to assert search results displayed.");
        classSearchPage.assertSearchResultsDisplayed();
        logger.info("Assertion: Search results displayed for keyword '" + keyword + "' and term '" + term + "'.");

        //Goal Achieved: The user has successfully searched for classes and seen the results.
        logger.info("Test 'successfulSearchForKeywordAndTerm' passed for Class Search by Keyword.  GOAL ACHIEVED.");
    }
}
