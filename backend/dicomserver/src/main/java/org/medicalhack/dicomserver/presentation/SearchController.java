package org.medicalhack.dicomserver.presentation;

import org.medicalhack.dicomserver.domain.data.search.MarkupSearchOptions;
import org.medicalhack.dicomserver.domain.data.search.MarkupSearchResult;
import org.medicalhack.dicomserver.domain.usecase.SearchInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController {

    private static final String SEPARATOR = "\\s*,\\s*";
    @Autowired
    SearchInteractor searchInteractor;

    @PostMapping("/markup")
    ResponseEntity<MarkupSearchResult> postMarkup(@RequestParam("tags") String tags, @RequestParam("modalities") String modalities) {
        ResponseEntity<MarkupSearchResult> result = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        if (tags != null && modalities != null) {
            List<String> searchTags = Arrays.asList(tags.toLowerCase().split(SEPARATOR));
            List<String> searchModalities = Arrays.asList(modalities.toLowerCase().split(SEPARATOR));
            if (!searchTags.isEmpty() && !searchModalities.isEmpty()) {
                result = searchInteractor.findMarkup(searchTags, searchModalities)
                        .map(markup -> new ResponseEntity<>(markup, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY));
            }
        }
        return result;
    }

    @GetMapping("/options")
    ResponseEntity<MarkupSearchOptions> getOptions() {
        return searchInteractor.getOptions()
                .map(it -> new ResponseEntity<>(it, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY));
    }


}
