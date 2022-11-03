package org.medicalhack.dicomserver.data.repositories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.medicalhack.dicomserver.domain.repositories.DicomFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DicomFileRepositoryImpl implements DicomFileRepository {

    private static final String DICOM_DIRECTORY = "dicom";
    private static final String IMAGES_DIRECTORY = "images";
    private String storagePath;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DicomFileRepositoryImpl(@Value("${storage.path}") String storagePath) {
        this.storagePath = storagePath;
    }

    @Override
    public long add(byte[] data) {
        long dicomId = System.currentTimeMillis();
        Path dicomStoragePath = Paths.get(storagePath, Long.toString(dicomId), DICOM_DIRECTORY);
        Path imagesStoragePath = Paths.get(storagePath, Long.toString(dicomId), IMAGES_DIRECTORY);
        Path dicomFilePath = Paths.get(dicomStoragePath.toString(), Long.toString(dicomId) + ".dcm");
        long result = 0;
        try {
            Files.createDirectory(Paths.get(storagePath, Long.toString(dicomId)));
            Files.createDirectory(dicomStoragePath);
            Files.createDirectory(imagesStoragePath);
            Files.write(dicomFilePath, data);
            result = dicomId;
        } catch (IOException e) {
            logger.error("Can't save DICOM to the storage", e);
        }
        return result;
    }

    @Override
    public Optional<File> get(long dicomId) {
        Optional<File> result = Optional.empty();
        try {
            Path dicomFilePath = Paths.get(storagePath, Long.toString(dicomId), DICOM_DIRECTORY, dicomId + ".dcm");
            result = Optional.of(dicomFilePath.toFile());
        } catch (Exception e) {
            logger.error("Unable to get DICOM file");
        }
        return result;
    }

}
