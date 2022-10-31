package org.medicalhack.dicomserver.data.repositories;

import org.medicalhack.dicomserver.domain.entities.dicom.DicomData;
import org.medicalhack.dicomserver.domain.repositories.DicomDataRepository;
import org.springframework.stereotype.Component;

@Component
public class DicomDataRepositoryImpl implements DicomDataRepository{

    @Override
    public boolean add(DicomData data) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public DicomData get(long dicomId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
