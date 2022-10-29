package org.medicalhack.dicomserver.domain.entities.responces;

public class RespDicomImages {
    final String dicomId;
    final Integer imageId;

    RespDicomImages(String dicomId, Integer imageId) {
        this.dicomId = dicomId;
        this.imageId = imageId;
    }

    public String getDicomId() {
        return dicomId;
    }

    public Integer getImageId() {
        return imageId;
    }
}
