package in.school.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.school.entity.StateInfo;

public interface StateInfoRepo extends JpaRepository<StateInfo, Integer> {
	public List<StateInfo> findAllByCountyId(Integer cid) ;
}
