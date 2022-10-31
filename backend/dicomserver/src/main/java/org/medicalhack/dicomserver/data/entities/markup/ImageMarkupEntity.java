package org.medicalhack.dicomserver.data.entities.markup;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGE_MARKUP")
public class ImageMarkupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    long dicomId;
    long imageId;

    @ElementCollection
    @CollectionTable(name = "MARKUP_TAGS", joinColumns = @JoinColumn(name = "IMAGE_MARKUP_ID"))
    List<String> tags;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "IMAGE_MARKUP_ID")
    List<MarkupEntity> markup;

    public ImageMarkupEntity() {
    }

    public ImageMarkupEntity(long dicomId, long imageId, List<String> tags, List<MarkupEntity> markup) {
        this.dicomId = dicomId;
        this.imageId = imageId;
        this.tags = tags;
        this.markup = markup;
    }

    public long getId() {
        return id;
    }

    public long getDicomId() {
        return dicomId;
    }

    public long getImageId() {
        return imageId;
    }

    public List<MarkupEntity> getMarkup() {
        return markup;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDicomId(long dicomId) {
        this.dicomId = dicomId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setMarkup(List<MarkupEntity> markup) {
        this.markup = markup;
    }
    

}
