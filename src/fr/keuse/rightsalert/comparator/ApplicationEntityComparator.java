package fr.keuse.rightsalert.comparator;

import java.util.Comparator;

import fr.keuse.rightsalert.entity.ApplicationEntity;

public class ApplicationEntityComparator implements Comparator<ApplicationEntity> {
	public int compare(ApplicationEntity lae, ApplicationEntity rae) {
		return lae.getName().toLowerCase().compareTo(rae.getName().toLowerCase());
	}
}
