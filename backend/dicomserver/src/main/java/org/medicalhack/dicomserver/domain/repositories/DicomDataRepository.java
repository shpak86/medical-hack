package org.medicalhack.dicomserver.domain.repositories;

import org.medicalhack.dicomserver.domain.entities.dicom.DicomData;

public interface DicomDataRepository {

    boolean add(DicomData data);

    DicomData get(long dicomId);
    
}
