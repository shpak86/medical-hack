package org.medicalhack.dicomserver.domain.data.search;

import java.util.ArrayList;
import java.util.List;

public class MarkupSearchOptions {

    private List<String> tags;
    private List<String> modalities;

    public MarkupSearchOptions() {
        tags = new ArrayList<>();
        modalities = new ArrayList<>();
    }

    public MarkupSearchOptions(List<String> tags, List<String> modalities) {
        this.tags = tags;
        this.modalities = modalities;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getModalities() {
        return modalities;
    }

    public void setModalities(List<String> modalities) {
        this.modalities = modalities;
    }
}
