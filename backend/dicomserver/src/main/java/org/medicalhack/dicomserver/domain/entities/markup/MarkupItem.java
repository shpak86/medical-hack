package org.medicalhack.dicomserver.domain.entities.markup;

import java.util.List;

public class MarkupItem {
    String type;
    List<List<Double>> geometry;

    public MarkupItem(String type, List<List<Double>> geometry) {
        this.type = type;
        this.geometry = geometry;
    }

    public String getType() {
        return type;
    }

    public List<List<Double>> getGeometry() {
        return geometry;
    }

    
}
