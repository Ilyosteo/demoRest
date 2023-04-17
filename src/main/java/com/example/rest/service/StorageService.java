package com.example.rest.service;

import com.example.rest.model.ImageData;
import com.example.rest.repository.StorageRepository;
import com.example.rest.util.ImageUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    private StorageRepository repository;

    @Autowired
    public StorageService(StorageRepository storageRepository) {
        this.repository = storageRepository;
    }

    @Transactional
    public String uploadImage(MultipartFile file)throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
            if (imageData != null)
                return "file uploaded successfully: " + file.getOriginalFilename();

            return null;
    }

    @Transactional
    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images = ImageUtil.decompressImage(dbImageData.get().getImageData());
        return images;
    }

}
