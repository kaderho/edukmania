package com.eduKmania.site.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.eduKmania.site.exception.FileNotFoundException;
import com.eduKmania.site.exception.FileStorageException;
import com.eduKmania.site.model.DemandeRepetiteurBD;
import com.eduKmania.site.properties.FileStorageProperties;
import com.eduKmania.site.repository.DemandeRepetiteurRepository;

@Service
public class FileStorageService {

    @Autowired
    private DemandeRepetiteurRepository demandeRepetiteurRepository;
    
    private final Path fileStorageLocation;
    
    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        
        String uuid = UUID.randomUUID().toString(); 
        String[] t = file.getContentType().split("/");
        String ext = null;
        for (String s : t) {
        	if(s.equals("pdf")){
        		ext = s;
        	}
		}

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(uuid+"."+ext);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            return uuid+"."+ext;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DemandeRepetiteurBD getFile(String fileId) {
        return demandeRepetiteurRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}