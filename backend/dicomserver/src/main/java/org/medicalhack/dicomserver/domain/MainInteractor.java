package org.medicalhack.dicomserver.domain;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.medicalhack.dicomserver.domain.entities.dicom.DicomData;
import org.medicalhack.dicomserver.domain.entities.dicom.DicomTag;
import org.medicalhack.dicomserver.domain.entities.markup.ImageMarkup;
import org.medicalhack.dicomserver.domain.repositories.DicomDataRepository;
import org.medicalhack.dicomserver.domain.repositories.ImagesRepository;
import org.medicalhack.dicomserver.domain.repositories.MarkupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainInteractor implements MainUseCase {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private DicomDataRepository dicomDataRepo;
    private ImagesRepository imagesRepo;
    private MarkupRepository markupRepo;

    @Autowired
    public MainInteractor(DicomDataRepository dicomDataRepo, ImagesRepository imagesRepo, MarkupRepository markupRepo) {
        this.dicomDataRepo = dicomDataRepo;
        this.imagesRepo = imagesRepo;
        this.markupRepo = markupRepo;
    }

    @Override
    public long extract(byte[] dicom) {
        long dicomId = System.currentTimeMillis();
        List<DicomTag> tags = new ArrayList<>();
        tags.add(new DicomTag("tag1", "XX", "tag1", "0"));
        List<Long> images = Arrays.asList(1L, 2L);
        DicomData dicomData = new DicomData(dicomId, tags, images);
        dicomDataRepo.add(dicomData);
        try {
            byte[] fileData1 = Files.readAllBytes(
                    Paths.get("/home/ash/projects/medical-hack/backend/dicomserver/src/main/resources/mock/1.jpg"));
            byte[] fileData2 = Files.readAllBytes(
                    Paths.get("/home/ash/projects/medical-hack/backend/dicomserver/src/main/resources/mock/2.jpg"));
            imagesRepo.add(dicomId, 1, fileData1);
            imagesRepo.add(dicomId, 2, fileData2);
        } catch (Exception e) {
            logger.error("Somethong went wrong", e);
        }
        return dicomId;
    }

    @Override
    public DicomData getDicomData(long dicomId) {
        return dicomDataRepo.get(dicomId);
    }

    @Override
    public ImageMarkup getImageMarkup(long dicomId, long imageId) {
        return markupRepo.get(dicomId, imageId);
    }

    @Override
    public boolean setImageMarkup(ImageMarkup markup) {
        return markupRepo.add(markup);
    }

    @Override
    public byte[] getImage(long dicomId, long imageId) {
        return imagesRepo.get(dicomId, imageId);
    }

}
