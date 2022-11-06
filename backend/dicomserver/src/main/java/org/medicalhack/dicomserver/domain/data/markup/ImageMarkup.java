package org.medicalhack.dicomserver.domain.data.markup;

import java.util.List;

public class ImageMarkup {
    long dicomId;
    long imageId;
    List<String> tags;
    List<MarkupItem> markup;

    public ImageMarkup() {
    }

    public ImageMarkup(long dicomId, long imageId, List<String> tags, List<MarkupItem> markup) {
        this.dicomId = dicomId;
        this.imageId = imageId;
        this.tags = tags;
        this.markup = markup;
    }

    public long getDicomId() {
        return dicomId;
    }

    public void setDicomId(long dicomId) {
        this.dicomId = dicomId;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<MarkupItem> getMarkup() {
        return markup;
    }

    public void setMarkup(List<MarkupItem> markup) {
        this.markup = markup;
    }
    
    
}
