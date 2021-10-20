package org.springframework.samples.petclinic.Disease;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;


public class Diseases {

	private List<Disease> diseases;

	@XmlElement
	public List<Disease> getDiseaseList() {
		if (diseases == null) {
			diseases = new ArrayList<>();
		}
		return diseases;
	}
}
