package com.gen.ai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnnuitantDynamicDetails {
    private String personAltId;
    private String personSSN;
    private String personFirstName;
    private String personMiddleName;
    private String personLastName;
    private String personSuffix;
    private String personMaritalStatus;
    private String personPhoneNumber;
    private String personEmail;
    private String residentialAddress1;
    private String residentialAddress2;
    private String residentialAddress3;
    private String residentialCity;
    private String residentialState;
    private String residentialZip;
    private String residentialCountry;
    private String residentialPostal;
    private String groupNumber;
    private String groupName;
    private String legalBillingID;
    private String annuityNumber;

    private String payment_frequency;
    private String commencement_date;
    private String end_date;
    private String basic_amount;
    private String first_pay_date;
    private String employer_name;
    private String DOB;
    
    public AnnuitantDynamicDetails(Object[] dataColumns){
        this.personAltId=(String) dataColumns[0];
        
        if((BigDecimal) dataColumns[1]!=null) {
        this.personSSN= ((BigDecimal) dataColumns[1]).toString();
	    } else {
	    	this.personSSN=null;
	    }
        
        this.personFirstName=(String) dataColumns[2];
        this.personMiddleName=(String) dataColumns[3];
        this.personLastName= (String) dataColumns[4];
        this.personSuffix= (String) dataColumns[5];
        this.personMaritalStatus= (String) dataColumns[6];
        this.personPhoneNumber= (String) dataColumns[7];
        this.personEmail= (String) dataColumns[8];
        this.residentialAddress1= (String) dataColumns[9];
        this.residentialAddress2= (String) dataColumns[10];
        this.residentialAddress3= (String) dataColumns[11];
        this.residentialCity= (String) dataColumns[12];
        this.residentialState= (String) dataColumns[13];
        
        if((BigDecimal) dataColumns[14]!=null) {
        this.residentialZip= ((BigDecimal) dataColumns[14]).toString();
        } else {
        	this.residentialZip=null;
        }
        this.residentialCountry= (String) dataColumns[15];
        this.residentialPostal= (String) dataColumns[16];
        this.groupNumber = ((String) dataColumns[17]);
        this.groupName = ((String) dataColumns[18]);
        
        if((BigDecimal) dataColumns[19]!=null) {
            this.legalBillingID= ((BigDecimal) dataColumns[19]).toString();
    	    } else {
    	    	this.legalBillingID=null;
    	    }
        
        
        this.annuityNumber= ((String) dataColumns[20]);


        this.payment_frequency = ((String) dataColumns[21]);

        if((Timestamp) dataColumns[22]!=null) {
            this.commencement_date= ((Timestamp) dataColumns[22]).toString();
        } else {
            this.commencement_date=null;
        }

        if((Timestamp) dataColumns[23]!=null) {
            this.end_date= ((Timestamp) dataColumns[23]).toString();
        } else {
            this.end_date=null;
        }

        if((BigDecimal) dataColumns[24]!=null) {
            this.basic_amount= ((BigDecimal) dataColumns[24]).toString();
        } else {
            this.basic_amount=null;
        }

        if((Timestamp) dataColumns[25]!=null) {
            this.first_pay_date= ((Timestamp) dataColumns[25]).toString();
        } else {
            this.first_pay_date=null;
        }

        this.employer_name= (String) dataColumns[26];


        if((Timestamp) dataColumns[27]!=null) {
            this.DOB= ((Timestamp) dataColumns[27]).toString();
        } else {
            this.DOB=null;
        }
    }
}
