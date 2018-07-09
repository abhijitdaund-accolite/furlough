package com.accolite.furlough.rest;
/* 
 * @author = guru shankar
 * accepts cross origin from localhost:4200
 * @RequestParam should be similar as that of input tag
 * Validations done
 * 	1. Check for file type extensions
 *  2. Upload only .xls
 * Upload is done by file storage service
 * 
 * 
 * reviewed by Vignesh.B
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.accolite.furlough.service.FileStorageService;

@CrossOrigin(origins="http://localhost:4200")
@Controller
public class FileUploadController {

	@Autowired
	FileStorageService filestorageService;

	String fileName=new String();

	@PostMapping("/post")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		try  {
		//Check for .xls file or not
		fileName=file.getOriginalFilename();
		int dotIndex = fileName.lastIndexOf('.');
	    String extension= (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
		if(extension.equals("xls"))	{
			//Upload only .xls files
			filestorageService.store(file);
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
			}
		else	{
			message = "wrong file type " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}
		} 
		
		catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
}