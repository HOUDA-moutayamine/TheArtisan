package com.project.artisan.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class UploadFile {
	private String folder="C://Users//HOUDA//Documents//workspace-spring//artisan//image//";
	public String saveImage(MultipartFile file) throws IOException{
		if(!file.isEmpty()) {
			byte [] bytes=file.getBytes();
			Path path= Paths.get(folder+file.getOriginalFilename());
			Files.write(path, bytes);
			return file.getOriginalFilename();
		}
		return "default.jpeg";
	}
	
	public void deleteImage(String name) {
		String loca="image//";
		File file= new File(loca+name);
		file.delete();
	}

}
