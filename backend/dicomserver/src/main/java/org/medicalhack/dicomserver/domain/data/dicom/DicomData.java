package org.medicalhack.dicomserver.domain.data.dicom;

public class DicomData {
    long dicomId;
    long imagesNumber;
    String modality;

    public DicomData(long dicomId, long imagesNumber, String modality) {
        this.dicomId = dicomId;
        this.imagesNumber = imagesNumber;
        this.modality = modality;
    }

    public long getDicomId() {
        return dicomId;
    }

    public long getImagesNumber() {
        return imagesNumber;
    }

    public String getModality() {
        return modality;
    }
}
