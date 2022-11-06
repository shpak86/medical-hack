package org.medicalhack.dicomserver.domain.data.markup;

import java.util.List;

public class MarkupItem {

    String type;
    List<MarkupPoint> geometry;

    public MarkupItem() {
    }

    public MarkupItem(String type, List<MarkupPoint> geometry) {
        this.type = type;
        this.geometry = geometry;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MarkupPoint> getGeometry() {
        return geometry;
    }

    public void setGeometry(List<MarkupPoint> geometry) {
        this.geometry = geometry;
    }

}
