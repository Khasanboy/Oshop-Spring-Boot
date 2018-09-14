package com.oshop.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.oshop.exception.FileStorageException;
import com.oshop.exception.MyFileNotFoundException;
import com.oshop.property.FileStorageProperties;
import com.oshop.service.FileStorageService;


@Service
public class FileStorageServiceImpl implements FileStorageService {
	
	private final Path fileStorageLocation;
	
	@Autowired
	public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		}catch(Exception ex) {
			throw new FileStorageException("Couldn't create the directory where the uploaded files will be stored");
		}
	}
	
	public String storeFile(MultipartFile file) {
		
		String name = StringUtils.cleanPath(file.getOriginalFilename().split("\\.")[0]);
		String type = StringUtils.cleanPath(file.getOriginalFilename().split("\\.")[1]);
		String fileName = name+new Date().getTime()+"."+type;
		
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename conatins invalid path sequence "+fileName);
			}
		
		Path targetLocation = this.fileStorageLocation.resolve(fileName);
		Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		
		return fileName;
		
		}catch(IOException ex) {
			throw new FileStorageException("Couldn't store file "+ fileName + ". Please try again!", ex);
		}
		
	}
	
	@SuppressWarnings("unused")
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			
			if(resource.exists()) {
				return resource;
			}else {
				throw new MyFileNotFoundException("File not found "+ fileName);
			}
		}catch(MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found "+fileName, ex);
		}
	}
	
	public void deleteFile(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Files.deleteIfExists(filePath);
		}catch(MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found "+ fileName, ex);
		} catch (IOException e) {
			throw new MyFileNotFoundException("Couldn't delete file "+ fileName, e);
		}
	}

}
