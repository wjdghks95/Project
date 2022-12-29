package com.io.rol.controller;

import com.io.rol.service.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final FileStore fileStore;

    @Value("classpath:${resource.path.fileStore}")
    private Resource resource;

    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws IOException {
        return new UrlResource("file:" + fileStore.createPath(filename));
    }
}
