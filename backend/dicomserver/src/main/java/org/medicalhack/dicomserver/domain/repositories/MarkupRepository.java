package org.medicalhack.dicomserver.domain.repositories;

import org.medicalhack.dicomserver.domain.entities.markup.ImageMarkup;

public interface MarkupRepository {

    boolean add(ImageMarkup makup);

    ImageMarkup get(long dicomId, long imageId);

}
