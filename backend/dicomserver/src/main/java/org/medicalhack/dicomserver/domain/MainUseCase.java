package org.medicalhack.dicomserver.domain;

import org.medicalhack.dicomserver.domain.entities.dicom.DicomData;
import org.medicalhack.dicomserver.domain.entities.markup.ImageMarkup;

public interface MainUseCase {

    long extract(byte[] dicom);

    DicomData getDicomData(long dicomId);

    byte[] getImage(long dicomId, long imageId);

    ImageMarkup getImageMarkup(long dicomId, long imageId);

    boolean setImageMarkup(ImageMarkup markup);

}
