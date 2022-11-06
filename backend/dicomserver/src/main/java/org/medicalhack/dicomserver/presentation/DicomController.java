package org.medicalhack.dicomserver.presentation;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.medicalhack.dicomserver.domain.usecase.MarkupInteractor;
import org.medicalhack.dicomserver.domain.data.dicom.DicomData;
import org.medicalhack.dicomserver.domain.data.markup.ImageMarkup;
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
    MarkupInteractor interactor;

    @PostMapping("/")
    ResponseEntity<String> postDicom(@RequestParam("file") MultipartFile file) {
        byte[] data;
        String result = null;
        HttpStatus status = HttpStatus.OK;
        try {
            data = file.getBytes();
            Long dicomId = interactor.extract(data).orElseThrow();
            result = "{\"dicomId\":" + dicomId + "}";
        } catch (IOException | NoSuchElementException e) {
            logger.error("Unable to process request", e);
            status = HttpStatus.UNPROCESSABLE_ENTITY;
        }
        return new ResponseEntity<>(result, status);
    }

    @GetMapping("/{dicomId}/fields")
    ResponseEntity<DicomData> getDicomData(@PathVariable String dicomId) {
        return interactor.getDicomData(Long.parseLong(dicomId))
                .map(file -> new ResponseEntity<>(file, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{dicomId}/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<byte[]> getDicomImage(@PathVariable String dicomId,
                                         @PathVariable String imageId) {
        return interactor.getImage(Long.parseLong(dicomId), Long.parseLong(imageId))
                .map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{dicomId}/image/{imageId}/markup")
    ResponseEntity<ImageMarkup> getDicomImageMarkup(@PathVariable String dicomId,
                                                    @PathVariable String imageId) {
        return interactor.getImageMarkup(Long.parseLong(dicomId), Long.parseLong(imageId))
                .map(markup -> new ResponseEntity<>(markup, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{dicomId}/image/{imageId}/markup")
    ResponseEntity<ImageMarkup> postDicomImageMarkup(@PathVariable String dicomId,
                                                     @PathVariable String imageId, @RequestBody ImageMarkup body) {
        body.setDicomId(Long.parseLong(dicomId));
        body.setImageId(Long.parseLong(imageId));
        interactor.setImageMarkup(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
