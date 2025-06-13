package com.mjih.gallery.controller;

import com.mjih.gallery.entity.Memory;
import com.mjih.gallery.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/memories")
@RequiredArgsConstructor
@CrossOrigin
public class MemoryController {

    private final MemoryService memoryService;

    @PostMapping("/upload")
    public Memory upload(@RequestParam MultipartFile file,
                         @RequestParam String description) throws IOException{
        return memoryService.save(file, description);
    }

    @GetMapping
    public List<Memory> getAll() {
        return memoryService.getAll();
    }
    @GetMapping("/{id}")
    public Memory getById(@PathVariable Long id){
        return memoryService.getById(id);
    }

    @PutMapping("/edit/{id}")
    public Memory update(@PathVariable Long id, @RequestParam String description) {
        return memoryService.update(id, description);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        memoryService.delete(id);
    }

    @PutMapping("/react/{id}")
    public Memory react(@PathVariable Long id, @RequestParam String reaction){
        return memoryService.react(id, reaction);
    }

}
