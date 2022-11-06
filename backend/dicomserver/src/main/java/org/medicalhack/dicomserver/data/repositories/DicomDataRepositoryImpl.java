package org.medicalhack.dicomserver.data.repositories;

import org.medicalhack.dicomserver.data.entities.markup.DicomDataEntity;
import org.medicalhack.dicomserver.domain.data.dicom.DicomData;
import org.medicalhack.dicomserver.domain.repositories.DicomDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DicomDataRepositoryImpl implements DicomDataRepository {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final DicomDataRepositoryDB repo;

    @Autowired
    DicomDataRepositoryImpl(DicomDataRepositoryDB repo) {
        this.repo = repo;
    }

    @Override
    public boolean add(DicomData data) {
        boolean result = false;
        try {
            repo.save(new DicomDataEntity(data.getDicomId(), data.getImagesNumber(), data.getModality()));
            result = true;
        } catch (Exception e) {
            logger.warn("Unable to save dicom data");
        }
        return result;
    }

    @Override
    public Optional<DicomData> get(long dicomId) {
        Optional<DicomData> result = Optional.empty();
        try {
            DicomDataEntity entity = repo.findByDicomId(dicomId);
            result = Optional.of(new DicomData(entity.getDicomId(), entity.getImagesNumber(), entity.getModality()));
        } catch (Exception e) {
            logger.warn("Unable to get dicom data");
        }
        return result;
    }

}
