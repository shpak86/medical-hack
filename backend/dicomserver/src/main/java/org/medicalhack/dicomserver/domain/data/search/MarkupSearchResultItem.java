package org.medicalhack.dicomserver.domain.data.search;

public class MarkupSearchResultItem {
    private long dicomId;
    private long imageId;

    public MarkupSearchResultItem() {
    }

    public MarkupSearchResultItem(long dicomId, long imageId) {
        this.dicomId = dicomId;
        this.imageId = imageId;
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
}
