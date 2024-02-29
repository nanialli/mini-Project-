package in.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.school.entity.User_info;

public interface UserInfoRepo extends JpaRepository<User_info, Integer> {
	public boolean findByUemail ( String email  ) ;
	
	public User_info findByUemailAndUpassword(String email  , String password ) ;
}
