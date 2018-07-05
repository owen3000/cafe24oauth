package org.springframework.social.cafe24.api.scripttag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.social.cafe24.api.Cafe24;

public class ScripttagsTemplate implements ScripttagsOperations {

	private static final Logger logger = LoggerFactory.getLogger(ScripttagsTemplate.class);

    private Cafe24 cafe24;
    
	public ScripttagsTemplate(Cafe24 cafe24) {
		super();
		this.cafe24 = cafe24;
	}


	@Override
	public String getAll() {
        logger.info("getAllScripttags called...");
        
        String scripttag = cafe24.fetchScripttags(HttpMethod.GET, "scripttags", null);
        if (scripttag == null) {
            logger.info("getAllScripttags scripttags empty...");

        }
        return scripttag;
	}


	@Override
	public String create(String data) {
		logger.info("createScripttag called...");
		
		String scripttag = cafe24.fetchScripttags(HttpMethod.POST, "scripttags", data);
        if (scripttag == null) {
            logger.info("createScripttag scripttags empty...");

        }
		return scripttag;
	}


	@Override
	public void delete(String scriptNo) {
		logger.info("createScripttag called...");
		
		cafe24.fetchScripttags(HttpMethod.DELETE, "scripttags/" + scriptNo, null);
 
	}
	
	@Override
	public void update(String scriptNo, String data) {
		logger.info("updateScripttag called...");
		
		cafe24.fetchScripttags(HttpMethod.DELETE, "scripttags/" + scriptNo, data);
 
	}

}
