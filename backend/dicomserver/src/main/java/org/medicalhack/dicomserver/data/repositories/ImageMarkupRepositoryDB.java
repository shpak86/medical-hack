package org.medicalhack.dicomserver.data.repositories;

import org.medicalhack.dicomserver.data.entities.markup.ImageMarkupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageMarkupRepositoryDB extends JpaRepository<ImageMarkupEntity, Long> {

    ImageMarkupEntity findByDicomIdAndImageId(Long dicomId, Long imageId);

    void deleteByDicomIdAndImageId(Long dicomId, Long imageId);

    @Query(value = "select distinct im.* from markup_tags t, image_markup im, dicom_data dd where t.image_markup_id = im.id and im.dicom_id = dd.dicom_id and t.tags in :tags and dd.modality in :modalities", nativeQuery = true)
    List<ImageMarkupEntity> findByTagsAndModalities(@Param("tags") List<String> tags, @Param("modalities") List<String> modalities);

    @Query(value = "select distinct tags from markup_tags", nativeQuery = true)
    List<String> getTags();

    @Query(value = "select distinct modality from dicom_data", nativeQuery = true)
    List<String> getModalities();

}
