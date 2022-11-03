package org.medicalhack.dicomserver.data.repositories;

import org.medicalhack.dicomserver.data.entities.markup.DicomDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DicomDataRepositoryDB extends JpaRepository<DicomDataEntity, Long> {
    DicomDataEntity findByDicomId(Long dicomId);
}
