package com.group18.rental_web.payload.request;

public class SelectorRequest {

    private int startPricePerMonth;
    private int endPricePerMonth;
    private boolean isSuite;
    private int floor;
    private int startSize;
    private int endSize;
    public int getStartPricePerMonth() {
        return startPricePerMonth;
    }

    public void setStartPricePerMonth(int startPricePerMonth) {
        this.startPricePerMonth = startPricePerMonth;
    }

    public int getEndPricePerMonth() {
        return endPricePerMonth;
    }

    public void setEndPricePerMonth(int endPricePerMonth) {
        this.endPricePerMonth = endPricePerMonth;
    }

    public boolean isSuite() {
        return isSuite;
    }

    public void setSuite(boolean suite) {
        isSuite = suite;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getStartSize() {
        return startSize;
    }

    public void setStartSize(int startSize) {
        this.startSize = startSize;
    }

    public int getEndSize() {
        return endSize;
    }

    public void setEndSize(int endSize) {
        this.endSize = endSize;
    }

}
