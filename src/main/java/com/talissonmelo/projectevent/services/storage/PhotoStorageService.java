package com.talissonmelo.projectevent.services.storage;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

public interface PhotoStorageService {

	InputStream findByPhoto(String namePhoto);
	
	InputStream findByPhotoUser(String namePhoto);
	
	void savePhoto(NewPhoto photo);

	void removePhoto(String namePhoto);
	
	default String newNameFile(String name) {
		return UUID.randomUUID().toString() + "__" + name;
	}
	
	default void replacePhoto(String nameFileExist, NewPhoto newPhoto) {
		this.savePhoto(newPhoto);

		if(nameFileExist != null) {
			this.removePhoto(nameFileExist);
		}
	}
	
	default void mediaTypePhotoExistAccept(MediaType mediaTypePhoto, List<MediaType> mediaTypesAccept) throws HttpMediaTypeNotAcceptableException {
		boolean compatible = mediaTypesAccept
				.stream()
				.anyMatch(mediaTypesPhotoAccept -> mediaTypesPhotoAccept.isCompatibleWith(mediaTypePhoto));
		if(!compatible) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAccept);
		}
	}
	
	class NewPhoto {
		private String nameFile;
		private InputStream stream;
		public String getNameFile() {
			return nameFile;
		}
		public void setNameFile(String nameFile) {
			this.nameFile = nameFile;
		}
		public InputStream getStream() {
			return stream;
		}
		public void setStream(InputStream stream) {
			this.stream = stream;
		}
		
	}
}
