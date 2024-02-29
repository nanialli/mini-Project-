package in.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.school.entity.Country_info;

public interface CountryInfoRepo extends JpaRepository<Country_info, Integer>{

}
