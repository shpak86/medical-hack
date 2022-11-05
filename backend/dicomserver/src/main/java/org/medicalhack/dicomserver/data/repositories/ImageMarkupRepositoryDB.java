package org.medicalhack.dicomserver.data.repositories;

import org.medicalhack.dicomserver.data.entities.markup.ImageMarkupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMarkupRepositoryDB extends JpaRepository<ImageMarkupEntity, Long> {

    ImageMarkupEntity findByDicomIdAndImageId(Long dicomId, Long imageId);
    void deleteByDicomIdAndImageId(Long dicomId, Long imageId);

}
