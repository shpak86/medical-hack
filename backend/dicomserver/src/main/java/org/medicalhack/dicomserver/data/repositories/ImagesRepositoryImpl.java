package org.medicalhack.dicomserver.data.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.medicalhack.dicomserver.domain.repositories.ImagesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImagesRepositoryImpl implements ImagesRepository {

    private static final String IMAGES_DIRECTORY = "images";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String storagePath;
    private OpenOption[] openOptions = { StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE };

    @Autowired
    public ImagesRepositoryImpl(@Value("${storage.path}") String storagePath) {
        this.storagePath = storagePath;
    }

    @Override
    public boolean add(long dicomId, long imageId, byte[] data) {
        Path basePath = Paths.get(storagePath, Long.toString(dicomId));
        Path imagesPath = Paths.get(basePath.toString(), IMAGES_DIRECTORY);
        Path imagePath = Paths.get(imagesPath.toString(), Long.toString(imageId) + ".jpg");
        boolean result = true;
        try {
            if (Files.notExists(basePath)) {
                Files.createDirectory(basePath);
            }
            if (Files.notExists(imagesPath)) {
                Files.createDirectory(imagesPath);
            }
            Files.write(imagePath, data, openOptions);

        } catch (IOException e) {
            logger.error("Can't add image to the storage", e);
            result = false;
        }
        return result;
    }

    @Override
    public byte[] get(long dicomId, long imageId) {
        byte[] result = new byte[] {};
        try {
            Path imagePath = Paths.get(storagePath, Long.toString(dicomId), IMAGES_DIRECTORY,
                    Long.toString(imageId) + ".jpg");
            result = Files.readAllBytes(imagePath);
        } catch (IOException e) {
            logger.error("Can't read image from the storage", e);
        }
        return result;
    }

}
