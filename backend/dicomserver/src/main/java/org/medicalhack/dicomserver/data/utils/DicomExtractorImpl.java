package org.medicalhack.dicomserver.data.utils;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.medicalhack.dicomserver.domain.entities.dicom.DicomData;
import org.medicalhack.dicomserver.domain.repositories.DicomDataRepository;
import org.medicalhack.dicomserver.domain.repositories.DicomFileRepository;
import org.medicalhack.dicomserver.domain.repositories.ImagesRepository;
import org.medicalhack.dicomserver.domain.utils.DicomExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pixelmed.dicom.*;
import com.pixelmed.display.SourceImage;
import com.pixelmed.display.ConsumerFormatImageMaker;

@Component
public class DicomExtractorImpl implements DicomExtractor {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ImagesRepository imagesRepository;
    private DicomFileRepository fileRepository;
    private DicomDataRepository dicomDataRepository;
    private AttributeList attributeList = new AttributeList();

    @Autowired
    public DicomExtractorImpl(ImagesRepository imagesRepository, DicomFileRepository fileRepository, DicomDataRepository dicomDataRepository) {
        this.imagesRepository = imagesRepository;
        this.fileRepository = fileRepository;
        this.dicomDataRepository = dicomDataRepository;
    }

    @Override
    public Optional<Long> extract(byte[] data) {
        Optional<Long> result = Optional.empty();
        try {
            long dicomId = fileRepository.add(data);
            File dicomFile = fileRepository.get(dicomId).orElseThrow();
            attributeList.read(dicomFile);
            String modality = getTag(attributeList, TagFromName.Modality);
            SourceImage img = new SourceImage(attributeList);
            int imagesNumber = img.getNumberOfFrames();
            dicomDataRepository.add(new DicomData(dicomId, imagesNumber, modality));
            for (int imageId = 0; imageId < imagesNumber; imageId++) {
                String imagePath = imagesRepository.getJpegPath(dicomId, imageId).toString();
                ConsumerFormatImageMaker.convertFileToEightBitImage(dicomFile.getPath(), imagePath, "jpeg", imageId);
            }
            result = Optional.of(dicomId);
        } catch (IOException | DicomException | NoSuchElementException e) {
            logger.error("Unable to extract frames", e);
        }
        return result;
    }

    private static String getTag(AttributeList list, AttributeTag tag) {
        return Attribute.getDelimitedStringValuesOrEmptyString(list, tag);
    }

}