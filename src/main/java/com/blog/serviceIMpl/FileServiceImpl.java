package com.blog.serviceIMpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.blog.services.FileService;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadimage(MultipartFile file, String path) throws IOException {
	   
		  String name=file.getOriginalFilename();
		  
		  String randomId= UUID.randomUUID().toString();
		  
		  String fileName= randomId.concat(name.substring(name.lastIndexOf(".")));
		  
		  String filepath= path+File.separator+fileName;
		 
		 File f= new File(path);
		 
		 if (!f.exists()) {
			f.mkdir();
		}
		  
		 Files.copy(file.getInputStream(), Paths.get(filepath));
		 
		return fileName;
	}

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
		
		String fullpath=path+File.separator+filename;
		InputStream inputStream = new FileInputStream(fullpath);
		
		return inputStream;
	}
   
}
