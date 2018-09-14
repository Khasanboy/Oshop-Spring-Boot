package com.oshop.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	
	public String storeFile(MultipartFile file);
	
	@SuppressWarnings("unused")
	public Resource loadFileAsResource(String fileName);
	
	public void deleteFile(String fileName);

}
