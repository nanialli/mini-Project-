package in.school.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.school.entity.CityInfo;

public interface CityInfoRepo extends JpaRepository<CityInfo, Integer> {

	public List<CityInfo> findAllByStateId(Integer stateId) ;
}
