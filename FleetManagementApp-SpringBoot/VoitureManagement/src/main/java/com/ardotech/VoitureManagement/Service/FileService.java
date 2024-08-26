package com.ardotech.VoitureManagement.Service;

import com.ardotech.VoitureManagement.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService implements FileRepository {

    @Value("${project.image}")
    private String uploadDir;

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String filePath = path + File.separator + filename;
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs(); // create directories if they don't exist
        }
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        return filename;
    }

    @Override
    public InputStream getResourceFile(String path, String filename) throws FileNotFoundException {
        String filePath = path + File.separator + filename;
        return new FileInputStream(filePath);
    }
}
