package org.medicalhack.dicomserver.domain.repositories;

import org.medicalhack.dicomserver.domain.entities.markup.ImageMarkup;

import java.util.Optional;

public interface MarkupRepository {

    boolean add(ImageMarkup makup);

    Optional<ImageMarkup> get(long dicomId, long imageId);

}
