package com.assignment.operations;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.assignment.hibernateutil.HibernateConnection;
import com.assignment.jpa.IMUConstants;
import com.assignment.jpa.ImageInfo;

public class DBOperationImpl implements DBOperationInterface{
	private String sizeValidationResult;
	private List<ImageInfo> imageInfoList = new ArrayList<>();
	
	@Override
	public boolean addImage(Part part, String userName) throws IOException{
		Transaction transaction = null;
		InputStream inp = null;
		boolean uploadResult;
		ImageInfo imageInfo = new ImageInfo();

		if(part != null) {
			
			// check for file size(0 or not)
			if (part.getSize() != 0) {
				inp = part.getInputStream();
				// Creating a byte array to store image
				byte[] byt = new byte[inp.available()];
				inp.read(byt);
				imageInfo.setName(part.getSubmittedFileName());
				imageInfo.setImage(byt);

				if (validateSize(userName, part.getSize())) {

					imageInfo.setSize(part.getSize());
					imageInfo.setUserName(userName);
					try (Session session = HibernateConnection.getSessionFactory().openSession()) {
						// start a transaction
						transaction = session.beginTransaction(); 
						
						//Saving object
						session.save(imageInfo);    
						session.getTransaction().commit();

						sizeValidationResult = "Image Uploaded Successfully!";
						uploadResult = true;

					} catch (Exception e) {
						if (transaction != null) {
							transaction.rollback();
						}
						System.out.println("Error in session");
						uploadResult = false;
					}
				} else {
					uploadResult = false;
				}
			}else {
				sizeValidationResult = "No Image is Selected!";
				uploadResult = false;
			}
		} else {
			sizeValidationResult = "Something Wrong!";
			uploadResult = false;
		}
		return uploadResult;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImageInfo> getImages (String userName) {
		
		Transaction transaction = null;
		
		try (Session session = HibernateConnection.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
            // get a user object
            imageInfoList = session.createQuery("FROM ImageInfo U WHERE U.UserName = :userName")
            				.setParameter("userName", userName).getResultList();
            transaction.commit();
		 } catch (Exception e) {
			 if (transaction != null) {
	                transaction.rollback();
	            }
			 System.out.println("Failed while fetching");
	     }
		return imageInfoList;
	
	}
	

	@Override
	public boolean editImageInfo(int imageId, String newName) {
		Transaction transaction = null;
		
		try (Session session = HibernateConnection.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			ImageInfo image = (ImageInfo) session.get(ImageInfo.class, imageId);
			image.setName(newName);
			session.update(image);
            transaction.commit();
            
		 } catch (Exception e) {
			 if (transaction != null) {
				 transaction.rollback();
	         }
			 return false;
	     }
		return true;
		
	}

	@Override
	public boolean deleteImage(int imageId) {

		Transaction transaction = null;
		try (Session session = HibernateConnection.getSessionFactory().openSession()) {
			
			ImageInfo image = (ImageInfo) session.get(ImageInfo.class, imageId);
			transaction = session.beginTransaction();
			session.delete(image);
            transaction.commit();
            
		 } catch (Exception e) {
			 System.out.println("Error while deleting");
			 if (transaction != null) {
	                transaction.rollback();
	            }
			 return false;
	     }
		return true;
		
	}
	
	
	@SuppressWarnings("unchecked")
	private boolean validateSize(String userName, long sizeOfImage) {
		Transaction transaction = null;
		Optional<Double> totalSize;
		boolean validationResult;

		try (Session session = HibernateConnection.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction(); 

			totalSize = (Optional<Double>) session.createQuery("SELECT SUM(size) FROM ImageInfo WHERE UserName = : userName")
					.setParameter("userName", userName).uniqueResultOptional();   //Save the data

			// Validating size
			if (totalSize.isPresent()) {
				// Validating size of Image to be uploaded
				if (sizeOfImage <= IMUConstants.perImageSizeLimit) {
					// Validating total size
					if (totalSize.get() + sizeOfImage <= IMUConstants.totalImageSizeLimit) {
						validationResult = true;         			
					}else {
						sizeValidationResult = "Total Size of All Images will exceed 10 MB!";
						System.out.println("Total Size of All Images will exceed 10 MB!");
						validationResult = false;
					}

				}else {
					sizeValidationResult = "Image Size exceeds 1 MB!";
					System.out.println("Image Size exceeds 1 MB!");
					validationResult = false;
				}
			} else {
				validationResult = true;
			}

			session.getTransaction().commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Error in session");
			validationResult = false;
		}

		return validationResult;
	}
	
	/*
	 * Return Upload Status
	 */
	public String getValidationMessage() {
		return this.sizeValidationResult;
	}

}
