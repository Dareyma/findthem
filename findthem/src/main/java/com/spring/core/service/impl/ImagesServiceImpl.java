package com.spring.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.core.entity.Images;
import com.spring.core.model.ImagesModel;
import com.spring.core.repository.ImagesRepository;
import com.spring.core.service.ImagesService;

@Service("imagesServiceImpl")
public class ImagesServiceImpl implements ImagesService{
	
	@Autowired
	@Qualifier("imagesRepository")
	private ImagesRepository imagesRepository;
	
	@Autowired
    private DozerBeanMapper dozer;
	
	@Override
    public List<ImagesModel> listAllImages() {
        List<Images> listImages=imagesRepository.findAll();
        List<ImagesModel> listaCM=new ArrayList<>();
        listImages.forEach(a->{
        	ImagesModel imagesModel=transform(a);
            listaCM.add(imagesModel);
        });

        // Ordenar por nombre
        // Collections.sort(listaCM, (ReplyModel r1, ReplyModel r2) -> c1.getNombre().compareTo(c2.getNombre()));

        return listaCM;
    }

	@Override
    public ImagesModel addImages(ImagesModel imagesModel) {
		Images images=transform(imagesModel);
        return transform(imagesRepository.save(images));
    }
	
	@Override
    public Images transform(ImagesModel imagesModel) {
        return dozer.map(imagesModel, Images.class);
    }
	
	@Override
    public ImagesModel transform(Images images) {
        return dozer.map(images, ImagesModel.class);
    }
}
