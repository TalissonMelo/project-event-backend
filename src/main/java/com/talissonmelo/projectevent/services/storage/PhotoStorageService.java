package com.talissonmelo.projectevent.services.storage;

import java.io.InputStream;
import java.util.UUID;

public interface PhotoStorageService {

	void savePhoto(NewPhoto photo);

	default String newNameFile(String name) {
		return UUID.randomUUID().toString() + "__" + name;
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
