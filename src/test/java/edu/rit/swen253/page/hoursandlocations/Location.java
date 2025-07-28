package edu.rit.swen253.page.hoursandlocations;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class Location {

    private final DomElement viewContainer;
    /**
    * The View container is the input that contains the search.
    */
    public Location(final DomElement viewContainer) {
        this.viewContainer = viewContainer;
    }

    public void click() {
        viewContainer.click();
    }

    public boolean isDisplayed() {
        return viewContainer.findChildBy(By.xpath("div[3]")).findChildBy(By.xpath("div[1]")).isDisplayed();
    }
}
