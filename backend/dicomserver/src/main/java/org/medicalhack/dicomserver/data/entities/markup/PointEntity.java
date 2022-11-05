package org.medicalhack.dicomserver.data.entities.markup;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Embeddable
public class PointEntity {

    @NonNull
    private Long position;

    @NonNull
    private Double x;

    @NonNull
    private Double y;

    public PointEntity(long position, double x, double y) {
        this.position = position;
        this.x = x;
        this.y = y;
    }

    public PointEntity() {
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

}
