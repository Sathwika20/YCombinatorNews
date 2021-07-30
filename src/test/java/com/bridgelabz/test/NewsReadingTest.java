package com.bridgelabz.test;

import com.bridgelabz.base.Base;
import com.bridgelabz.page.NewsHeadings;
import com.bridgelabz.page.PopularHeading;
import org.testng.annotations.Test;

public class NewsReadingTest extends Base {
    @Test
    public void news_print() throws InterruptedException {
        NewsHeadings newsHeadings = new NewsHeadings(driver);
        PopularHeading popularHeading = newsHeadings.findPopularHeading();
        System.out.println("Popular Heading is:" + popularHeading.getHeading());
        System.out.println("Popular Heading points are:" + popularHeading.getCount());

    }
}
