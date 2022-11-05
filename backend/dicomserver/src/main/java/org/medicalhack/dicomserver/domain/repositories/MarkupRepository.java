package org.medicalhack.dicomserver.domain.repositories;

import org.medicalhack.dicomserver.domain.data.search.MarkupSearchOptions;
import org.medicalhack.dicomserver.domain.data.search.MarkupSearchResult;
import org.medicalhack.dicomserver.domain.data.markup.ImageMarkup;

import java.util.List;
import java.util.Optional;

public interface MarkupRepository {

    boolean add(ImageMarkup makup);

    Optional<ImageMarkup> get(long dicomId, long imageId);

    Optional<MarkupSearchResult> findByTag(List<String> tags, List<String> modalities);

    Optional<MarkupSearchOptions> getOptions();
}
