package org.springframework.social.cafe24.api.scripttag;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scripttag {

	private static final Logger logger = LoggerFactory.getLogger(Scripttag.class);

	private Long shopNo;
	private String scriptNo;
	private String clientId;
	private String src;
	private List<String> displayLocation = new ArrayList<String>();
	private List<String> skinNo = new ArrayList<String>();
	private String createdDate;
	private String updatedDate;
	
	
	public Scripttag() {
		super();
	}

	public Scripttag(Long shopNo, String scriptNo, String clientId, String src, List<String> displayLocation,
			List<String> skinNo, String createdDate, String updatedDate) {
		logger.info("api.Scripttags class constructor called");

		this.shopNo = shopNo;
		this.scriptNo = scriptNo;
		this.clientId = clientId;
		this.src = src;
		this.displayLocation = displayLocation;
		this.skinNo = skinNo;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		logger.info("api.Scripttags class constructor end");
	}

	//@JSONField( name="shop_no")
	public Long getShopNo() {
		return shopNo;
	}

	//@JSONField( name="shop_no")
	public void setShopNo(Long shopNo) {
		this.shopNo = shopNo;
	}

	//@JSONField( name="script_no"  )
	public String getScriptNo() {
		return scriptNo;
	}

	//@JSONField( name="script_no"  )
	public void setScriptNo(String scriptNo) {
		this.scriptNo = scriptNo;
	}

	//@JSONField( name="client_id" )
	public String getClientId() {
		return clientId;
	}

	//@JSONField( name="client_id" )
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	//@JSONField( name="src")
	public String getSrc() {
		return src;
	}

	//@JSONField( name="src")
	public void setSrc(String src) {
		this.src = src;
	}

	//@JSONField( name="display_location" )
	public List<String> getDisplayLocation() {
		return displayLocation;
	}

	//@JSONField( name="display_location" )
	public void setDisplayLocation(List<String> displayLocation) {
		this.displayLocation = displayLocation;
	}

	//@JSONField( name="skin_no" )
	public List<String> getSkinNo() {
		return skinNo;
	}

	//@JSONField( name="skin_no" )
	public void setSkinNo(List<String> skinNo) {
		this.skinNo = skinNo;
	}

	//@JSONField( name="created_date" )
	public String getCreatedDate() {
		return createdDate;
	}

	//@JSONField( name="created_date" )
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	//@JSONField( name="updated_date" )
	public String getUpdatedDate() {
		return updatedDate;
	}

	//@JSONField( name="updated_date" )
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Override
	public String toString() {
		return "Scripttag [shopNo=" + shopNo + ", scriptNo=" + scriptNo + ", clientId=" + clientId + ", src=" + src
				+ ", displayLocation=" + displayLocation + ", skinNo=" + skinNo + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + "]";
	}

	
	
	
}
