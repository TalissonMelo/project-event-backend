package com.talissonmelo.projectevent.services.storage.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.talissonmelo.projectevent.services.exceptions.StorageException;
import com.talissonmelo.projectevent.services.storage.PhotoStorageService;

@Service
public class PhotoLocalStorageService implements PhotoStorageService {

	@Value("${default.storage.local}")
	private String pathFile;

	@Override
	public void savePhoto(NewPhoto photo) {
		try {

			Path path = getFilePath(photo.getNameFile());
			FileCopyUtils.copy(photo.getStream(), Files.newOutputStream(path));

		} catch (IOException e) {
			throw new StorageException("Falha ao salvar Arquivo local", e.getCause());
		}
	}

	@Override
	public void removePhoto(String namePhoto) {
		try {
			Path path = getFilePath(namePhoto);
			Files.deleteIfExists(path);
		} catch (IOException e) {
			throw new StorageException("Falha ao remover Arquivo local", e.getCause());
		}
	}

	private Path getFilePath(String nameFile) {
		return Paths.get(pathFile, nameFile);
	}

	@Override
	public InputStream findByPhoto(String namePhoto) {
		try {
			Path path = getFilePath(namePhoto);
			return Files.newInputStream(path);
		} catch (IOException e) {
			throw new StorageException("Falha ao buscar arquivo " + namePhoto + " .", e.getCause());
		}
	}

	@Override
	public InputStream findByPhotoUser(String namePhoto) {
		try {
			Path path = Paths.get("D://Pi//userPhotos", namePhoto);
			return Files.newInputStream(path);
		} catch (IOException e) {
			throw new StorageException("Falha ao buscar arquivo " + namePhoto, e.getCause());
		}
	}

}
