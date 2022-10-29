package org.medicalhack.dicomserver.domain.entities.responces;

public class RespDicomUpload {

    String dicomId;

    public RespDicomUpload(String dicomId) {
        this.dicomId = dicomId;
    }

    public String getDicomId() {
        return dicomId;
    }

}
