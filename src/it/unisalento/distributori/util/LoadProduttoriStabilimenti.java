package it.unisalento.distributori.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class LoadProduttoriStabilimenti extends ActionSupport{

	private String language1;
	private String language2;

	Map languageMap;

	public String getLanguage1() {
		return language1;
	}

	public void setLanguage1(String language1) {
		this.language1 = language1;
	}

	public String getLanguage2() {
		return language2;
	}

	public void setLanguage2(String language2) {
		this.language2 = language2;
	}

	public Map getLanguageMap() {
		return languageMap;
	}

	public void setLanguageMap(Map languageMap) {
		this.languageMap = languageMap;
	}

	public LoadProduttoriStabilimenti(){
		languageMap =new HashMap();
		
		languageMap.put("Java", new ArrayList<String>(Arrays.asList("Spring", "Hibernate", "Struts 2")));
		languageMap.put(".Net", new ArrayList<String>(Arrays.asList("VB.Net", "C#")));
		languageMap.put("JavaScript", new ArrayList<String>(Arrays.asList("jQuery")));
	}

	public String execute() {
		return SUCCESS;
	}

	public String display() {
		return NONE;
	}
}
