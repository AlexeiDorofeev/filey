package controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import services.FileService;

import java.io.IOException;
import java.nio.file.Path;
//import java.nio.file.Path;

@Controller
@AllArgsConstructor
public class FileUploadController {
    FileService fileService;

    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Save the file to the server
            Path filePath = fileService.saveFile(file);

            // Return a successful response
            return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded successfully: " + filePath);
        } catch (IOException e) {
            // Return a failure response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }
}
