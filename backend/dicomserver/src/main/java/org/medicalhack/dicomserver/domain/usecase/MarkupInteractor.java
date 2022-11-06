package org.medicalhack.dicomserver.domain.usecase;


import org.medicalhack.dicomserver.domain.data.dicom.DicomData;
import org.medicalhack.dicomserver.domain.data.markup.ImageMarkup;
import org.medicalhack.dicomserver.domain.repositories.DicomDataRepository;
import org.medicalhack.dicomserver.domain.repositories.ImagesRepository;
import org.medicalhack.dicomserver.domain.repositories.MarkupRepository;
import org.medicalhack.dicomserver.domain.utils.DicomExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MarkupInteractor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private DicomDataRepository dicomDataRepo;
    private ImagesRepository imagesRepo;
    private MarkupRepository markupRepo;
    private DicomExtractor dicomExtractor;

    public MarkupInteractor(DicomDataRepository dicomDataRepo, ImagesRepository imagesRepo, MarkupRepository markupRepo,
                            DicomExtractor dicomExtractor) {
        this.dicomDataRepo = dicomDataRepo;
        this.imagesRepo = imagesRepo;
        this.markupRepo = markupRepo;
        this.dicomExtractor = dicomExtractor;
    }

    public Optional<Long> extract(byte[] dicom) {
        return dicomExtractor.extract(dicom);
    }

    public Optional<DicomData> getDicomData(long dicomId) {
        return dicomDataRepo.get(dicomId);
    }

    public Optional<ImageMarkup> getImageMarkup(long dicomId, long imageId) {
        return markupRepo.get(dicomId, imageId);
    }

    public boolean setImageMarkup(ImageMarkup markup) {
        return markupRepo.add(markup);
    }

    public Optional<byte[]> getImage(long dicomId, long imageId) {
        return imagesRepo.get(dicomId, imageId);
    }

}
