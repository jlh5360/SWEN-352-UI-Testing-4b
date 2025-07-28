package edu.rit.swen253.page.classSearch;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Represents the search results view after performing a class search.
 */
public class SearchResultsView extends AbstractAngularPage {
    private static final String CLASS_SEARCH_ANGULAR_VIEW_TAG_NAME = "class-search";

    public SearchResultsView() {
        super(CLASS_SEARCH_ANGULAR_VIEW_TAG_NAME);
    }

    /**
     * Asserts that course results are displayed. This is a generic check and might need
     * to be more specific based on the actual DOM structure of search results.
     */
    public void assertSearchResultsDisplayed() {
        DomElement searchResultsContainer = findOnPage(By.cssSelector("div.classSearchBasicResultsMargin"));
        assertTrue(searchResultsContainer.isDisplayed(), "Search results should be displayed.");
    }
}
