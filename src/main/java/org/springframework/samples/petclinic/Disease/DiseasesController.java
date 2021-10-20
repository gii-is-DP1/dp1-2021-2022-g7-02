package org.springframework.samples.petclinic.Disease;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.vet.Vets;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiseasesController {
	
	
	private final DiseasesService diseasesService;
	
	@Autowired
	public DiseasesController(DiseasesService diseasesService, UserService userService, AuthoritiesService authoritiesService) {
		this.diseasesService = diseasesService;
	}
	
	@GetMapping(value="/diseases")
	public String showDiseasesList(Map<String, Object> model) {
		Diseases diseases = new Diseases();
		diseases.getDiseaseList().addAll(this.diseasesService.findDiseases());
		model.put("diseases", diseases);
		return "diseases/diseaseList";
	}
	
	
}
