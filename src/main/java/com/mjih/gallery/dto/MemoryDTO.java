package com.mjih.gallery.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemoryDTO {
    private MultipartFile file;
    private String descripcion;
}
