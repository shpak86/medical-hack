package org.medicalhack.dicomserver.domain.entities.dicom;

public class DicomTag {
    String tag;
    String vr;
    String name;
    String value;

    public DicomTag(String tag, String vr, String name, String value) {
        this.tag = tag;
        this.vr = vr;
        this.name = name;
        this.value = value;
    }

}
