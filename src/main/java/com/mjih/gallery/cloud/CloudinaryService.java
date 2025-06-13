package com.mjih.gallery.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public Map uploadFile(MultipartFile file) throws IOException{
        String contentType = file.getContentType();
        String resourceType = "auto"; //Permite entrada de imagen y video - raw
        return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type",resourceType));
    }
}
