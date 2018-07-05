package org.springframework.social.cafe24.api.scripttag;

import java.util.ArrayList;
import java.util.List;

public class Scripttags {

	private List<Scripttag> scripttags = new ArrayList<Scripttag>();
	
	public Scripttags() {
		super();
	}
	
	public Scripttags(List<Scripttag> scripttags) {
		super();
		this.scripttags = scripttags;
	}

	public List<Scripttag> getScripttags() {
		return scripttags;
	}
	public void setScripttags(List<Scripttag> scripttags) {
		this.scripttags = scripttags;
	}
	@Override
	public String toString() {
		return "Scripttags [scripttags=" + scripttags + "]";
	}

	
	
}
