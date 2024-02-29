package in.school.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User_info {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String uname;
	String uemail;
	String upassword;
	String phoneNo;
	String country;
	String state;
	String city;
	@CreatedDate
	String createdDate;
	@LastModifiedDate
	String updatedDate;
	String updatePassword;

}
