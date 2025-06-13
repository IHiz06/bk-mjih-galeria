package com.mjih.gallery.service;

import com.mjih.gallery.entity.Memory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MemoryService {
    Memory save(MultipartFile file, String description) throws IOException;
    List<Memory> getAll();
    Memory getById(Long id);
    void delete(Long id);
    Memory update(Long id, String newDescription);
    Memory react(Long id, String reactionType);

}
