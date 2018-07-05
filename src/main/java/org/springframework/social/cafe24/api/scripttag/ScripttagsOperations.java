package org.springframework.social.cafe24.api.scripttag;

public interface ScripttagsOperations {

	String getAll();
	
	String create(String data);
	
	void delete( String scriptNo);
	
	void update(String scriptNo, String data);
}
