package edu.rit.swen253.page.hoursandlocations;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class Location {

    private final DomElement viewContainer;
    private final DomElement mapIcon;
    /**
    * The View container is the input that contains the search.
    */
    public Location(final DomElement viewContainer) {
        this.viewContainer = viewContainer;
        this.mapIcon = viewContainer.findChildBy(By.xpath("div[2]")).findChildBy(By.xpath("div[2]")).findChildBy(By.cssSelector("a"));
    }

    public void click() {
        viewContainer.click();
    }

    public boolean dropdownisDisplayed() {
        return viewContainer.findChildBy(By.xpath("div[3]")).findChildBy(By.xpath("div[1]")).isDisplayed();
    }

    public void clickMapIcon() {
        mapIcon.click();
    }

    public String getMapIconURL() {
        return mapIcon.getAttribute("href");
    }
}
