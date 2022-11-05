package org.medicalhack.dicomserver.domain.utils;

import java.util.Optional;

public interface DicomExtractor {
    public Optional<Long> extract(byte[] data);
}
