package lifantou.com.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lifantou.com.entity.AccesApp;
import lifantou.com.entity.Ecole;
import lifantou.com.entity.Region;
import lifantou.com.service.AdminService;

@RestController
public class TestRestController {
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/getRegion")
	public List<Region> getRegion(){
		return adminService.getAllRegion();
	}
	
	@GetMapping("/listAccessApp")
	public List<AccesApp> getRegions(){
		return adminService.getAllAccessApp();
	}
	
	@GetMapping("/listEcoles")
	public List<Ecole> getAllEcoles(){
		return adminService.getEcoles();
	}
	

}
