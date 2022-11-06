package org.medicalhack.dicomserver.domain.data.search;

import java.util.ArrayList;
import java.util.List;

public class MarkupSearchResult {
    private List<String> tags;
    private List<MarkupSearchResultItem> markup;

    public MarkupSearchResult(List<String> tags, List<MarkupSearchResultItem> markup) {
        this.tags = tags;
        this.markup = markup;
    }

    public MarkupSearchResult() {
        this.tags = new ArrayList<>();
        this.markup = new ArrayList<>();
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<MarkupSearchResultItem> getMarkup() {
        return markup;
    }

    public void setMarkup(List<MarkupSearchResultItem> markup) {
        this.markup = markup;
    }
}
