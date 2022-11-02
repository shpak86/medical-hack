package org.medicalhack.dicomserver.data.utils;

import java.io.File;
import java.io.IOException;
import org.medicalhack.dicomserver.domain.repositories.DicomFileRepository;
import org.medicalhack.dicomserver.domain.repositories.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pixelmed.dicom.*;
import com.pixelmed.display.SourceImage;
import com.pixelmed.display.ConsumerFormatImageMaker;

@Component
public class DicomExtractor {

    private ImagesRepository imagesRepository;
    private DicomFileRepository fileRepository;
    private String storagePath;

    private AttributeList attributeList = new AttributeList();

    @Autowired
    public DicomExtractor(ImagesRepository imagesRepository, DicomFileRepository fileRepository,
            @Value("${storage.path}") String storagePath) {
        this.imagesRepository = imagesRepository;
        this.fileRepository = fileRepository;
        this.storagePath = storagePath;
    }

    public long extract(byte[] data) {
        long dicomId = fileRepository.add(data);
        File dicomFile = fileRepository.get(dicomId);
        try {
            attributeList.read(dicomFile);
            String modality = getTag(attributeList, TagFromName.Modality);
            SourceImage img = new SourceImage(attributeList);
            long numberOfFrames = img.getNumberOfFrames();

            String imagePath = imagesRepository.getJpegPath(dicomId, 0).toString();
            ConsumerFormatImageMaker.convertFileToEightBitImage(dicomFile.getPath(), imagePath, "jpeg", 0);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DicomException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dicomId;
    }

    private static String getTag(AttributeList list, AttributeTag tag) {
        return Attribute.getDelimitedStringValuesOrEmptyString(list, tag);
    }

}