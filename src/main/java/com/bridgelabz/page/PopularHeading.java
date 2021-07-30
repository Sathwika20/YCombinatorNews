package com.bridgelabz.page;

public class PopularHeading {
    private String heading;
    private Integer count;

    public PopularHeading() {

    }

    public PopularHeading(String heading, Integer count) {
        this.heading = heading;
        this.count = count;
    }


    public String getHeading() {

        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
