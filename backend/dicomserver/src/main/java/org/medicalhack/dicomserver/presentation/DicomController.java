package org.medicalhack.dicomserver.presentation;

import org.medicalhack.dicomserver.domain.MainInteractor;
import org.medicalhack.dicomserver.domain.entities.markup.ImageMarkup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("dicom")
public class DicomController {
    private final Logger logger = LoggerFactory.getLogger(DicomController.class);

    @Autowired
    MainInteractor interactor;

    @PostMapping("/")
    ResponseEntity<Long> postDicom(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<Long>(interactor.extract(new byte[] {}), HttpStatus.OK);
    }

    @GetMapping(value = "/{dicomId}/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<byte[]> getDicomImage(@PathVariable String dicomId,
            @PathVariable String imageId) {
        byte[] image = null;
        HttpStatus status;
        try {
            image = interactor.getImage(Long.parseLong(dicomId), Long.parseLong(imageId));
            status = HttpStatus.OK;
        } catch (Exception e) {
            logger.warn("Image not found", e);
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<byte[]>(image, status);
    }

    @GetMapping("/{dicomId}/image/{imageId}/markup")
    ResponseEntity<ImageMarkup> getDicomImageMarkup(@PathVariable String dicomId,
            @PathVariable String imageId) {
        // todo: give nice names
        ImageMarkup imageMarkup = interactor.getImageMarkup(Long.parseLong(dicomId), Long.parseLong(imageId));
        HttpStatus status;
        if (imageMarkup == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.OK;
        }
        return new ResponseEntity<ImageMarkup>(imageMarkup, status);
    }

    @PostMapping("/{dicomId}/image/{imageId}/markup")
    ResponseEntity<ImageMarkup> postDicomImageMarkup(@PathVariable String dicomId,
            @PathVariable String imageId, @RequestBody ImageMarkup body) {
        body.setDicomId(Long.valueOf(dicomId));
        body.setImageId(Long.valueOf(imageId));
        interactor.setImageMarkup(body);
        return new ResponseEntity<ImageMarkup>(body, HttpStatus.OK);
    }

}
