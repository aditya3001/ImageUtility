package com.assignment.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.assignment.hibernateutil.HibernateConnection;

@Entity
@Table(name = "ImageInfo")
public class ImageInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ImageId;
    
    @Column(name = "Name")
    private String Name;
    
    @Column(name = "Size")
    private float size;
    
    @Column(name = "UserName")
    private String UserName;
    
    @Lob
    private byte[] image;

	public int getImageId() {
		return ImageId;
	}

	public void setImageId(int imageId) {
		ImageId = imageId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public float getSize() {
		return (float) (Math.round((size/IMUConstants.bytesInMb) * 100.0) / 100.0);
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public ImageInfo(String name, float size, String userName, byte[] image) {
		super();
		Name = name;
		this.size = size;
		UserName = userName;
		this.image = image;
	}

	public ImageInfo() {
	}
	
	@SuppressWarnings("unchecked")
	public static List<ImageInfo> getImageList(String userName) {
			
		Transaction transaction = null;
		List<ImageInfo> imageInfoList = new ArrayList<>();
		
		try (Session session = HibernateConnection.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
	        // get a user object
	        imageInfoList = session.createQuery("FROM ImageInfo U WHERE U.UserName = :userName").setParameter("userName", userName).getResultList();
	        transaction.commit();
		 } catch (Exception e) {
			 if (transaction != null) {
	                transaction.rollback();
	            }
			 System.out.println("Failed while fetching");
	     }
		return imageInfoList;
	} 
}
