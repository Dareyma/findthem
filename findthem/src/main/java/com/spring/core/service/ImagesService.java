package com.spring.core.service;

import java.util.List;

import com.spring.core.entity.Images;
import com.spring.core.model.ImagesModel;

public interface ImagesService {
	
	public abstract List<ImagesModel> listAllImages();
	
	public abstract ImagesModel addImages(ImagesModel imagesModel);
	
	//transformar entidad a modelo
    public abstract Images transform(ImagesModel imagesModel);

    //transformar modelo a entidad
    public abstract ImagesModel transform(Images images);
}
