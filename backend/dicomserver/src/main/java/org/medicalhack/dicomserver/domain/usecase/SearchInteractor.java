package org.medicalhack.dicomserver.domain.usecase;

import org.medicalhack.dicomserver.domain.data.search.MarkupSearchOptions;
import org.medicalhack.dicomserver.domain.data.search.MarkupSearchResult;
import org.medicalhack.dicomserver.domain.repositories.MarkupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SearchInteractor {

    private MarkupRepository markupRepository;

    @Autowired
    public SearchInteractor(MarkupRepository markupRepository) {
        this.markupRepository = markupRepository;
    }

    public Optional<MarkupSearchResult> findMarkup(List<String> tags, List<String> modalities) {
        return markupRepository.findByTag(tags, modalities);
    }

    public Optional<MarkupSearchOptions> getOptions() {
        return markupRepository.getOptions();
    }
}
