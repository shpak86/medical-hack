package org.medicalhack.dicomserver.domain.repositories;

public interface ImagesRepository {

    boolean add(long dicomId, long imageId, byte[] data);

    byte[] get(long dicomId, long imageId);

}
