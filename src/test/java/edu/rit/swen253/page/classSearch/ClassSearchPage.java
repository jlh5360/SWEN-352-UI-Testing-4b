package edu.rit.swen253.page.classSearch;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.TimingUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Represents the Class Search page of the TigerCenter Angular web application.
 */
public class ClassSearchPage extends AbstractAngularPage {
    private static final String CLASS_SEARCH_ANGULAR_VIEW_TAG_NAME = "class-search";
    private static final By SEARCH_INPUT_FINDER = By.cssSelector("input[type='search']");
    private static final By SEARCH_BUTTON_FINDER = By.cssSelector("button[class='classSearchSearchButton ng-star-inserted']");
    private static final By SEARCH_TERM_FINDER = By.cssSelector("select[name='termSelector']");
    private static final By COURSE_CATALOG_LINK_FINDER = By.linkText("Course Catalog");

    public ClassSearchPage() {
        super(CLASS_SEARCH_ANGULAR_VIEW_TAG_NAME);

        //Validate basic page structure
        try {
            findOnPage(SEARCH_INPUT_FINDER);
            findOnPage(SEARCH_TERM_FINDER);
        } catch (TimeoutException e) {
            fail("Class Search page elements not found: " + e.getMessage());
        }
    }

    /**
     * Enters a keyword into the class search input field.
     * @param keyword The keyword to enter.
     */
    public void enterKeyword(String keyword) {
        DomElement keywordInputField = findKeywordInputField();
        keywordInputField.clear(); // Clear any existing text
        keywordInputField.sendKeys(keyword);
    }

    /**
     * Selects a term from the term dropdown.
     * @param term The visible text of the term to select (e.g., "2025-26 Fall (2251)").
     */
    public void selectTerm(String term) {
        DomElement termDropdown = findTermDropdown();
        Select select = new Select(termDropdown.getWebElement());
        select.selectByVisibleText(term);
    }

    /**
     * Clicks the Search button and returns a SearchResultsView.
     * @return A new SearchResultsView page object.
     */
    public SearchResultsView clickSearchButton() {
        DomElement searchButton = findSearchButton();
        searchButton.click();
        TimingUtils.sleep(3); // Wait for results to load
        return new SearchResultsView();
    }

    /**
     * Clicks the Course Catalog link/button and returns a CourseCatalogView.
     * @return A new CourseCatalogView page object.
     */
    public CourseCatalogView clickCourseCatalogLink() {
        DomElement courseCatalogLink = findCourseCatalogLink();
        courseCatalogLink.click();
        TimingUtils.sleep(3); // Wait for overlay to load
        return new CourseCatalogView();
    }

    /**
     * Asserts that a message prompting to select a term is displayed.
     */
    public void assertSelectTermMessageDisplayed() {
        DomElement selectTermMessage = findOnPage(By.xpath("//*[contains(text(), 'You must select a term')]"));
        assertTrue(selectTermMessage.isDisplayed(), "Message to select a term should be displayed.");
    }

    /**
     * Helper function to find keyword input field.
     */
    private DomElement findKeywordInputField() {
        return angularView.findChildBy(SEARCH_INPUT_FINDER);
    }

    /**
     * Helper function to find term dropdown.
     */
    private DomElement findTermDropdown() {
        return angularView.findChildBy(SEARCH_TERM_FINDER);
    }

    /**
     * Helper function to find search button.
     */
    private DomElement findSearchButton() {
        return angularView.findChildBy(By.cssSelector("button.classSearchSearchButton"));
    }

    /**
     * Helper function to find course catalog link.
     */
    private DomElement findCourseCatalogLink() {
        return angularView.findChildBy(COURSE_CATALOG_LINK_FINDER);
    }
}
