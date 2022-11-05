package org.medicalhack.dicomserver.domain.repositories;

import org.medicalhack.dicomserver.domain.data.dicom.DicomData;

import java.util.Optional;

public interface DicomDataRepository {

    boolean add(DicomData data);

    Optional<DicomData> get(long dicomId);
    
}
