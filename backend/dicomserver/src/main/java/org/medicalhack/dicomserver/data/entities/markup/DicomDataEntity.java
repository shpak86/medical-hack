package org.medicalhack.dicomserver.data.entities.markup;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "DICOM_DATA")
public class DicomDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    long dicomId;

    @Temporal(TemporalType.DATE)
    Date creationDate;

    long imagesNumber;

    String modality;

    public DicomDataEntity(long dicomId, long imagesNumber, String modality) {
        this.dicomId = dicomId;
        this.imagesNumber = imagesNumber;
        this.modality = modality;
    }

    public DicomDataEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDicomId() {
        return dicomId;
    }

    public void setDicomId(long dicomId) {
        this.dicomId = dicomId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getImagesNumber() {
        return imagesNumber;
    }

    public void setImagesNumber(long imagesNumber) {
        this.imagesNumber = imagesNumber;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }
}
