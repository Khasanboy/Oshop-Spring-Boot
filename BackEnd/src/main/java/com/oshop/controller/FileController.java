package com.oshop.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oshop.payload.UploadFileResponse;
import com.oshop.service.FileStorageService;


@RestController
@RequestMapping("/api/files")
public class FileController {

	@Autowired FileStorageService fileStorageService;
	
	@PostMapping("/upload")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		
		String fileName = fileStorageService.storeFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("api/files/download/")
				.path(fileName)
				.toUriString();
		
		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
	@PostMapping("/upload-multiple")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
		
		return Arrays.asList(files)
				.stream()
				.map(file-> uploadFile(file))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		}catch(IOException ex){
			System.out.println("Couldn't determine file type");
		}
		
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
				.body(resource);
	}
	
	
}
