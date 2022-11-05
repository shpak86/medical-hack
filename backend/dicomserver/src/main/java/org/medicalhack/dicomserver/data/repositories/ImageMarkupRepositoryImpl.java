package org.medicalhack.dicomserver.data.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.medicalhack.dicomserver.data.entities.markup.ImageMarkupEntity;
import org.medicalhack.dicomserver.data.entities.markup.MarkupEntity;
import org.medicalhack.dicomserver.data.entities.markup.PointEntity;
import org.medicalhack.dicomserver.domain.entities.markup.ImageMarkup;
import org.medicalhack.dicomserver.domain.entities.markup.MarkupItem;
import org.medicalhack.dicomserver.domain.entities.markup.MarkupPoint;
import org.medicalhack.dicomserver.domain.repositories.MarkupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageMarkupRepositoryImpl implements MarkupRepository {

    @Autowired
    ImageMarkupRepositoryDB repo;

    @Override
    public boolean add(ImageMarkup markup) {
        List<MarkupEntity> markupEntities = new ArrayList<>();
        for (MarkupItem item : markup.getMarkup()) {
            List<PointEntity> geometry = new ArrayList<>();
            long position = 0;
            for (MarkupPoint point : item.getGeometry()) {
                geometry.add(new PointEntity(position++, point.getX(), point.getY()));
            }
            markupEntities.add(new MarkupEntity(item.getType(), geometry));
        }
        ImageMarkupEntity imageMarkup = new ImageMarkupEntity(markup.getDicomId(), markup.getImageId(),
                markup.getTags(), markupEntities);
        ImageMarkupEntity entity = repo.findByDicomIdAndImageId(markup.getDicomId(), markup.getImageId());
        if (entity != null) {
            repo.deleteById(entity.getId());
        }
        repo.save(imageMarkup);
        return false;
    }

    @Override
    public Optional<ImageMarkup> get(long dicomId, long imageId) {
        ImageMarkupEntity entity = repo.findByDicomIdAndImageId(dicomId, imageId);
        Optional<ImageMarkup> result = Optional.empty();
        if (entity != null) {
            List<MarkupItem> markupItems = new ArrayList<>();
            for (MarkupEntity item : entity.getMarkup()) {
                List<MarkupPoint> geometry = new ArrayList<>();
                for (PointEntity point : item.getGeometry()) {
                    geometry.add(new MarkupPoint(point.getX(), point.getY()));
                }
                markupItems.add(new MarkupItem(item.getType(), geometry));
            }
            ImageMarkup imageMarkup = new ImageMarkup(dicomId, imageId, entity.getTags(), markupItems);
            result = Optional.of(imageMarkup);
        }
        return result;

    }

}
