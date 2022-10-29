package org.medicalhack.dicomserver.domain.entities.responces;

import java.util.List;

import org.medicalhack.dicomserver.domain.entities.markup.MarkupItem;

public class RespDicomImageMarkup {

    String dicomId;
    int imageId;
    List<String> tags;
    List<MarkupItem> makup;

    public RespDicomImageMarkup(String dicomId, int imageId, List<String> tags, List<MarkupItem> makup) {
        this.dicomId = dicomId;
        this.imageId = imageId;
        this.tags = tags;
        this.makup = makup;
    }

    public String getDicomId() {
        return dicomId;
    }

    public int getImageId() {
        return imageId;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<MarkupItem> getMakup() {
        return makup;
    }

}
