package com.mjih.gallery.service.impl;

import com.mjih.gallery.cloud.CloudinaryService;
import com.mjih.gallery.entity.Memory;
import com.mjih.gallery.repository.MemoryRepository;
import com.mjih.gallery.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemoryServiceImpl implements MemoryService {

    private final MemoryRepository memoryRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public Memory save(MultipartFile file, String description) throws IOException {
        Map result = cloudinaryService.uploadFile(file);

        Memory memory = Memory.builder()
                .url((String) result.get("secure_url"))
                .descripcion(description)
                .type((String) result.get("resource_type"))
                .date(LocalDate.now().toString())
                .likes(0)
                .pompompurin(0)
                .persona3(0)
                .sparkle(0)
                .build();

        return memoryRepository.save(memory);
    }

    @Override
    public List<Memory> getAll() {
        return memoryRepository.findAll();
    }

    @Override
    public Memory getById(Long id) {
        return memoryRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        memoryRepository.deleteById(id);
    }

    @Override
    public Memory update(Long id, String newDescription) {
        Memory m = getById(id);
        if(m != null){
            m.setDescripcion(newDescription);
            return memoryRepository.save(m);
        }

        return null;
    }

    @Override
    public Memory react(Long id, String reactionType) {
        Memory m = getById(id);
        if (m != null){
            switch (reactionType.toLowerCase()){
                case "like" -> m.setLikes(m.getLikes()+1);
                case "pompompurin" -> m.setPompompurin(m.getPompompurin()+1);
                case "persona3" -> m.setPersona3(m.getPersona3()+1);
                case "sparkle" -> m.setSparkle(m.getSparkle()+1);
            }
            return memoryRepository.save(m);
        }

        return null;
    }
}
