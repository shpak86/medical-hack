package org.medicalhack.dicomserver.data.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.medicalhack.dicomserver.data.entities.markup.ImageMarkupEntity;
import org.medicalhack.dicomserver.data.entities.markup.MarkupEntity;
import org.medicalhack.dicomserver.data.entities.markup.PointEntity;
import org.medicalhack.dicomserver.domain.data.search.MarkupSearchOptions;
import org.medicalhack.dicomserver.domain.data.search.MarkupSearchResult;
import org.medicalhack.dicomserver.domain.data.search.MarkupSearchResultItem;
import org.medicalhack.dicomserver.domain.data.markup.ImageMarkup;
import org.medicalhack.dicomserver.domain.data.markup.MarkupItem;
import org.medicalhack.dicomserver.domain.data.markup.MarkupPoint;
import org.medicalhack.dicomserver.domain.repositories.MarkupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageMarkupRepositoryImpl implements MarkupRepository {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ImageMarkupRepositoryDB repo;

    @Override
    public boolean add(ImageMarkup markup) {
        List<MarkupEntity> markupEntities = new ArrayList<>();
        for (MarkupItem item : markup.getMarkup()) {
            List<PointEntity> geometry = new ArrayList<>();
            int position = 0;
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
                List<PointEntity> points = item.getGeometry();
                points.sort((a, b) -> a.getPosition() - b.getPosition());
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

    @Override
    public Optional<MarkupSearchResult> findByTag(List<String> tags, List<String> modalities) {
        Optional<MarkupSearchResult> result = Optional.empty();
        try {
            List<ImageMarkupEntity> markups = repo.findByTagsAndModalities(tags, modalities);
            result = Optional.of(new MarkupSearchResult(
                    tags,
                    markups.stream().map(it -> new MarkupSearchResultItem(it.getDicomId(), it.getImageId())).collect(Collectors.toList())
            ));

        } catch (Exception e) {
            logger.error("Unable to find markup by tags", e);
        }
        return result;
    }

    @Override
    public Optional<MarkupSearchOptions> getOptions() {
        MarkupSearchOptions result = new MarkupSearchOptions(new ArrayList<>(), new ArrayList<>());
        try {
            result.setTags(repo.getTags());
            result.setModalities(repo.getModalities());
        } catch (Exception e) {
            logger.error("Error occurred during getting search options");
        }
        return Optional.of(result);
    }


}
