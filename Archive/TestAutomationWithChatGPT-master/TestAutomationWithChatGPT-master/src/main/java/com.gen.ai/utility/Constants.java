package com.gen.ai.utility;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class Constants {
	
	//any doc to upload should be stored in this directory
	public static String docDir = System.getProperty("user.dir")+ "/InboundFolder/";
	
	//thread safe nested map to execute parallel tests
	public static HashMap<String,HashMap<String,String>> testCaseDetails=new HashMap<String,HashMap<String,String>>();

	public static Map<String, String> importHeaderId =new LinkedHashMap<String, String>();
	public static Map<String, String> annuitantUpdatedSSN =new LinkedHashMap<String, String>();

	public static String batchDateTimeET;
	public static Map<String,ConcurrentHashMap<String, String>> ACEstagingTableValuesINPRS = new ConcurrentHashMap<>();
	public static Map<String,ConcurrentHashMap<String, String>> ACEstagingTableValuesFEDTHRIFT = new ConcurrentHashMap<>();
	public static Map<String, String> exportAddDetails =new LinkedHashMap<String, String>();
	public static Map<String, Set<String>> multipleBeneficiariesValueTable = new HashMap<>();
	public static Map<String, Set<String>> multipleBeneficiariesValueAce = new HashMap<>();

	public static String groupIdNumber;
	public static HashMap<String,String> ssnStore=new HashMap<>();
	
	public static HashMap<String, HashMap<String,String>> annuitantDynamicDetails = new HashMap<>();
	public static HashMap<String, String[]> annuitantPDFDetails = new HashMap<>();
	public static HashMap<String, String> fetchedAnnuitantAltIdDB = new HashMap<>();
	public static HashMap<String, HashMap<String,String>> paymentDetailsMetcheck = new HashMap<>();
	public static HashMap<String, HashMap<String,String>> paymentDetailsACH = new HashMap<>();
	public static HashMap<String, String> secondaryApplicationAltId = new HashMap<>();
	public static HashMap<String, String> secondaryBatchDate = new HashMap<>();

}
