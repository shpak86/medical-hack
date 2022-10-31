package org.medicalhack.dicomserver.domain.entities.dicom;

import java.util.List;

public class DicomData {
    long id;
    List<DicomTag> tags;
    List<Long> images;

    public DicomData(long id, List<DicomTag> tags, List<Long> images) {
        this.id = id;
        this.tags = tags;
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public List<DicomTag> getTags() {
        return tags;
    }

    public List<Long> getImages() {
        return images;
    }

}
