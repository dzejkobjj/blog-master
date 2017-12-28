package pl.dzejkobdevelopment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.dzejkobdevelopment.storageservice.FileErrorResponse;
import pl.dzejkobdevelopment.storageservice.FileUploadedResponse;
import pl.dzejkobdevelopment.storageservice.FileSystemStorageService;
import pl.dzejkobdevelopment.storageservice.UploadingResponse;

/**
 * Created by Jakub Michałowski on 01.11.2017.
 * All rights reserved.
 */
@RestController
public class FileUploadController {

    private final FileSystemStorageService storageService;

    @Autowired
    public FileUploadController(FileSystemStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload_file")
    public UploadingResponse uploadFile(@RequestParam("file") MultipartFile file){
        try {
            storageService.store(file);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new FileErrorResponse("3");
        }
        return new FileUploadedResponse("uploaded/" + file.getOriginalFilename());
    }

    @GetMapping("/uploaded/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file;
        try {
            file = storageService.loadAsResource(filename);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
