package org.medicalhack.dicomserver.domain.repositories;

import java.io.File;

public interface DicomFileRepository {

    long add(byte[] data);

    File get(long dicomId);
   
}
