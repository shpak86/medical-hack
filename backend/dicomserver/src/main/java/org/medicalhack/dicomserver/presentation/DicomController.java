package org.medicalhack.dicomserver.presentation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.medicalhack.dicomserver.domain.entities.markup.MarkupItem;
import org.medicalhack.dicomserver.domain.entities.responces.RespDicomData;
import org.medicalhack.dicomserver.domain.entities.responces.RespDicomImageMarkup;
import org.medicalhack.dicomserver.domain.entities.responces.RespDicomUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.type.MapType;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("dicom")
public class DicomController {
    private final Logger logger = LoggerFactory.getLogger(DicomController.class);

    private static final String MOCK_ID = "3148aa24-b70c-44aa-bdd7-14715114753f";

    @PostMapping("/")
    RespDicomUpload postDicom(@RequestParam("data") MultipartFile file) {
        RespDicomUpload response = new RespDicomUpload(MOCK_ID);
        return response;
    }

    @GetMapping("/{dicomId}")
    RespDicomData getDicom(@PathVariable String dicomId) {
        return new RespDicomData(MOCK_ID, new ArrayList<>());
    }

    @GetMapping(value = "/{dicomId}/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<byte[]> getDicomImage(@PathVariable String dicomId, @PathVariable String imageId) {
        byte[] image = null;
        try {
            image = Files.readAllBytes(Paths.get(
                    "/home/ash/projects/medical-hack/backend/dicomserver/src/main/resources/static/3148aa24-b70c-44aa-bdd7-14715114753f.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(image, HttpStatus.OK);
    }

    @GetMapping("/{dicomId}/image/{imageId}/markup")
    ResponseEntity<RespDicomImageMarkup> getDicomImageMarkup(@PathVariable String dicomId,
            @PathVariable String imageId) {
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");
        tags.add("tag3");
        String type = "rectangle";
        List<List<Double>> geometry = Arrays.asList(Arrays.asList(0.4, 0.4), Arrays.asList(0.6, 0.6));
        MarkupItem markupItem = new MarkupItem(type, geometry);
        RespDicomImageMarkup markup = new RespDicomImageMarkup(MOCK_ID, 1, tags, Arrays.asList(markupItem));
        return new ResponseEntity<RespDicomImageMarkup>(markup, HttpStatus.OK);
    }

    @PostMapping("/{dicomId}/image/{imageId}/markup")
    ResponseEntity<RespDicomImageMarkup> postDicomImageMarkup(@PathVariable String dicomId,
            @PathVariable String imageId) {
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");
        tags.add("tag3");
        String type = "rectangle";
        List<List<Double>> geometry = Arrays.asList(Arrays.asList(0.4, 0.4), Arrays.asList(0.6, 0.6));
        MarkupItem markupItem = new MarkupItem(type, geometry);
        RespDicomImageMarkup markup = new RespDicomImageMarkup(MOCK_ID, 1, tags, Arrays.asList(markupItem));
        return new ResponseEntity<RespDicomImageMarkup>(markup, HttpStatus.OK);
    }

}
