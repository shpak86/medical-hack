package org.medicalhack.dicomserver.domain.data.markup;

public class MarkupPoint {
    private double x;
    private double y;

    public MarkupPoint() {
    }

    public MarkupPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

}
