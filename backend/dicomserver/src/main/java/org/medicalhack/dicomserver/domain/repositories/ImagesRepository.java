package org.medicalhack.dicomserver.domain.repositories;

import java.nio.file.Path;
import java.util.Optional;

public interface ImagesRepository {

    boolean add(long dicomId, long imageId, byte[] data);

    Optional<byte[]> get(long dicomId, long imageId);

    Path getJpegPath(long dicomId, long imageId);

}
