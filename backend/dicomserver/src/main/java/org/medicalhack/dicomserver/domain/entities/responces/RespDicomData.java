package org.medicalhack.dicomserver.domain.entities.responces;

import java.util.ArrayList;
import java.util.List;

public class RespDicomData {

    String dicomId;
    List<String> images = new ArrayList<>();

    public RespDicomData(String dicomId, List<String> images) {
        this.dicomId = dicomId;
        this.images = images;
    }

    public String getDicomId() {
        return dicomId;
    }

    public List<String> getImages() {
        return images;
    }

}