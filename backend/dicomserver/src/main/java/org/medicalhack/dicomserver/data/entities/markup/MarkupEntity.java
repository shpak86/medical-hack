package org.medicalhack.dicomserver.data.entities.markup;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "MARKUP")
public class MarkupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String type;

    @ElementCollection
    @CollectionTable(name = "MARKUP_POINT", joinColumns = @JoinColumn(name = "MARKUP_ID"))
    private List<PointEntity> geometry;

    public MarkupEntity() {
    }

    public MarkupEntity(String type, List<PointEntity> geometry) {
        this.type = type;
        this.geometry = geometry;
    }

    public String getType() {
        return type;
    }

    public List<PointEntity> getGeometry() {
        return geometry;
    }

    public long getMarkupItemId() {
        return id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGeometry(List<PointEntity> geometry) {
        this.geometry = geometry;
    }

}
