package com.assignment.operations;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Part;

import com.assignment.jpa.ImageInfo;

public interface DBOperationInterface {
	
	boolean addImage(Part part, String userName) throws IOException;

	List<ImageInfo> getImages(String userName);

	boolean editImageInfo(int imageId, String newName);

    boolean deleteImage(int imageId);

}
