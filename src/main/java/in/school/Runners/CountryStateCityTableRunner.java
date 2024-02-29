package in.school.Runners;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.school.entity.CityInfo;
import in.school.entity.Country_info;
import in.school.entity.StateInfo;
import in.school.repo.CityInfoRepo;
import in.school.repo.CountryInfoRepo;
import in.school.repo.StateInfoRepo;
@Component
public class CountryStateCityTableRunner implements ApplicationRunner {

	@Autowired
	CountryInfoRepo countryRepo ;
	@Autowired
	StateInfoRepo stateRepo ;
	@Autowired
	CityInfoRepo cityRepo ;
	@Override
	
	public void run(ApplicationArguments args) throws Exception {
		
		Country_info country1 = new Country_info ( 1 , "India") ;
		Country_info country2 = new Country_info ( 2 , "USA") ;
		countryRepo.saveAll(Arrays.asList(country1,country2));
		
		StateInfo s1 = new StateInfo(1,"AP", 1);
		StateInfo s2 = new StateInfo(2,"TS",1);
		StateInfo s3 = new StateInfo(3,"LA", 2) ;
		StateInfo s4 = new StateInfo ( 4, "Texces", 2) ;
		stateRepo.saveAll(Arrays.asList(s1,s2,s3,s4)) ;
		
		CityInfo c1 = new CityInfo(1,"RJY", 1) ;
		CityInfo c2 = new CityInfo(2, "KKD", 1) ;
		CityInfo c3 = new CityInfo(3, "HYD", 2) ;
		CityInfo c4 = new CityInfo(4, "WORANGAL" ,2) ;
		CityInfo c5 = new CityInfo(5,"LA1", 3) ;
		CityInfo c6 = new CityInfo(6, "LA2", 3) ;
		CityInfo c7 = new CityInfo(7, "TEXCES1", 4) ;
		CityInfo c8 = new CityInfo(8, "TEXCES-2" ,4) ;
		cityRepo.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8));
	}
}
