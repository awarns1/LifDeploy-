package lifantou.com.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lifantou.com.dao.AccesAppRepository;
import lifantou.com.dao.RegionRepository;
import lifantou.com.dao.RoleRepository;
import lifantou.com.dao.UserRepository;
import lifantou.com.dao.ZoneRepository;
import lifantou.com.entity.AccesApp;
import lifantou.com.entity.Region;
import lifantou.com.entity.Role;
import lifantou.com.entity.Zone;
import lifantou.com.utils.UserForm;

@Service
public class LifantuServiceImpl implements LifantouService {
	
	@Autowired RoleRepository roleRipository;
	@Autowired ZoneRepository zoneRepository;
	@Autowired RegionRepository regionRepository;
	@Autowired UserRepository userRepository;
	@Autowired AccountService accountService;
	@Autowired AccesAppRepository accesAppRepository;
	
	@Override
	public void initAPP(HttpServletRequest request) {
		// required role saving
	   Role adminRole = new Role(null,"ADMIN",null);
	   Role ecoleRole = new Role(null,"ECOLE",null);
	   Role producteurRole = new Role(null,"PRODUCTEUR",null);
	   Role partenaireRole = new Role(null,"PARTENAIRE",null);
	   roleRipository.save(adminRole);
	   roleRipository.save(ecoleRole);
	   roleRipository.save(producteurRole);
	   roleRipository.save(partenaireRole);
	   
	   //required zone saving
	   Zone zone = zoneRepository.save(new Zone(null,"Dakar-Thies-StLouis",null));
	   
	   // region saving
	   Region favoriteRegion = regionRepository.save(new Region(null,"DAKAR","DK",zone,null));
	   regionRepository.save(new Region(null,"Thies","TH",zone,null));
	   regionRepository.save(new Region(null,"St Louis","STL",zone,null));
	   
	   // adding first admin user

		final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		UserForm userForm = new UserForm();
		userForm.setIdentifiant("12345");
		userForm.setUsername("Admin@admin");
		userForm.setPassword("admin@1");
		userForm.setTel("00221774687026");
		userForm.setAdresse("DAKAR BENTALLY");
		//userForm.setType("Ecole");
		userForm.setIdRegion(favoriteRegion.getId());
		//userForm.setNomEcole("Ecole 1");
		//userForm.setNomGest("GEST NOM");
		//userForm.setPrenomGest("GEST prenom");
		//userForm.setDepartement("dept");
		//userForm.setLatitudeEcole(0);
		//userForm.setLatitudeEcole(0);
		//userForm.setActiviteGest("ACtivite gest");
		//userForm.setBudgetAnnuel(100000);
		//userForm.setNbEleve(400);
		accountService.registration(userForm, appUrl);
	   
		// update stat access app
		/*
		 * AccesApp acap = accesAppRepository.getOne(Long.valueOf("1"));
		 * System.out.println(acap.getActived() +
		 * "-----------------------------------"); acap.setActived(1);
		 * accesAppRepository.save(acap);
		 */
	}

	@Override
	public void activeAllAccount() {
		//accesAppRepository.activeAccounts(1);
	}
}
