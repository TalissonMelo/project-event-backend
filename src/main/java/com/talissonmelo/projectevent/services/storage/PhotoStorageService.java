package com.talissonmelo.projectevent.services.storage;

import java.io.InputStream;
import java.util.UUID;

public interface PhotoStorageService {

	InputStream findByPhoto(String namePhoto);
	
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
