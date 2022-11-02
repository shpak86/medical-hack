package org.medicalhack.dicomserver.domain.repositories;

import java.nio.file.Path;

public interface ImagesRepository {

    boolean add(long dicomId, long imageId, byte[] data);

    byte[] get(long dicomId, long imageId);

    Path getJpegPath(long dicomId, long imageId);

}
