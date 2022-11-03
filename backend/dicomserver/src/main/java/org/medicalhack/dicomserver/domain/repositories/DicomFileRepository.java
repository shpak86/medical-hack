package org.medicalhack.dicomserver.domain.repositories;

import java.io.File;
import java.util.Optional;

public interface DicomFileRepository {

    long add(byte[] data);

    Optional<File> get(long dicomId);
   
}
