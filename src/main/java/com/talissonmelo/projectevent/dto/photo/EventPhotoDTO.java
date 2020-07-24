package com.talissonmelo.projectevent.dto.photo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.talissonmelo.projectevent.config.validation.FileContentType;
import com.talissonmelo.projectevent.config.validation.FileSize;

public class EventPhotoDTO {

	@NotNull
	@FileSize(max = "800KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_PDF_VALUE })
	private MultipartFile file;
	
	@NotBlank
	private String description;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
