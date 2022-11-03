package org.medicalhack.dicomserver.domain;

import org.medicalhack.dicomserver.domain.entities.dicom.DicomData;
import org.medicalhack.dicomserver.domain.entities.markup.ImageMarkup;

import java.util.Optional;

public interface MainUseCase {

    Optional<Long> extract(byte[] dicom);

    Optional<DicomData> getDicomData(long dicomId);

    Optional<byte[]> getImage(long dicomId, long imageId);

    Optional<ImageMarkup> getImageMarkup(long dicomId, long imageId);

    boolean setImageMarkup(ImageMarkup markup);

}
