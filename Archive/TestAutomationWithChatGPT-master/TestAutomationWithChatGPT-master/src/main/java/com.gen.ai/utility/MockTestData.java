package com.gen.ai.utility;


import com.metlife.ace.automation.annotation.LazyAutowired;
import com.metlife.ace.automation.config.appconfig.EnvironmentProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
@Slf4j
public class MockTestData {
    @LazyAutowired
    private TestUtility testUtility;
    @LazyAutowired
    private EnvironmentProperties environmentProperties;

    public  Map<String, String> getMockAddress() {
        List<Map<String, String>> mockAddressList = new ArrayList<>();
        Random rand = new Random();
        Map<String, String> mockAddress = new HashMap<>();

        Map<String, String> mockAddress1 = new HashMap<>();
        mockAddress1.put("address1", "94 Bear Hill Street");
        mockAddress1.put("city", "Harrisonburg");
        mockAddress1.put("state", "Virginia");
        mockAddress1.put("zipcode", "22801");
        mockAddress1.put("country", "United States");

        Map<String, String> mockAddress2 = new HashMap<>();
        mockAddress2.put("address1", "8345 Broad St");
        mockAddress2.put("city", "Richmond Hill");
        mockAddress2.put("state", "New York");
        mockAddress2.put("zipcode", "11418");
        mockAddress2.put("country", "United States");

        Map<String, String> mockAddress3 = new HashMap<>();
        mockAddress3.put("address1", "7016 E Ridgewood Ave.");
        mockAddress3.put("city", "Youngstown");
        mockAddress3.put("state", "Ohio");
        mockAddress3.put("zipcode", "44512");
        mockAddress3.put("country", "United States");

        Map<String, String> mockAddress4 = new HashMap<>();
        mockAddress4.put("address1", "1016 E Chatam Ave.");
        mockAddress4.put("city", "Cary");
        mockAddress4.put("state", "North Carolina");
        mockAddress4.put("zipcode", "27519");
        mockAddress4.put("country", "United States");

        Map<String, String> mockAddress5 = new HashMap<>();
        mockAddress5.put("address1", "63 Brewery Lane");
        mockAddress5.put("city", "Wilmington");
        mockAddress5.put("state", "Delaware");
        mockAddress5.put("zipcode", "19807");
        mockAddress5.put("country", "United States");

        mockAddressList.add(mockAddress1);
        mockAddressList.add(mockAddress2);
        mockAddressList.add(mockAddress3);
        mockAddressList.add(mockAddress4);
        mockAddressList.add(mockAddress5);

        int numberOfElements = 1;
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(mockAddressList.size());
            mockAddress = mockAddressList.get(randomIndex);
        }
        return mockAddress;

    }

    public  int randomNumberGenerator(int lowerRange, int upperRange) {
        int number = (int) (Math.random() * (upperRange - lowerRange + 1) + lowerRange);
        return number;
    }
  

    public String generateAlphabeticString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public String getGender() {
        Random rand = new Random();
        String personGender = "Male";
        int numberOfElements = 1;
        //List<String> genderList = Arrays.asList("Male", "Female", "Unknown", "Transgender");
        List<String> genderList = Arrays.asList("Male", "Female");
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(genderList.size());
            personGender = genderList.get(randomIndex);
        }
        return personGender;
    }

    public String getCitizenship() {
        Random rand = new Random();
        String country = "Male";
        int numberOfElements = 1;
        // List<String> genderList = Arrays.asList("United States", "Costa Rica", "India");
        List<String> genderList = Arrays.asList("United States");
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(genderList.size());
            country = genderList.get(randomIndex);
        }
        return country;
    }

    public String getMaritalStatus() {
        Random rand = new Random();
        String marital = "Married";
        int numberOfElements = 1;
        //List<String> genderList = Arrays.asList("Married", "Single", "Divorced", "Unknown", "Widowed");
        List<String> maritalList = Arrays.asList("Married", "Single");
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(maritalList.size());
            marital = maritalList.get(randomIndex);
        }
        return marital;
    }

    public String getFirstName() {
        Random rand = new Random();
        String firstName = "Jodie";
        int numberOfElements = 1;
        List<String> firstNameList = Arrays.asList("Jessie", "Marion", "Alva", "Ollie", "Kerry", "Jodie", "Cleo");

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(firstNameList.size());
            firstName = firstNameList.get(randomIndex);
        }
        return firstName;
    }

    public String getLastName() {
        Random rand = new Random();
        String lastName = "Johnson";
        int numberOfElements = 1;
        List<String> lastNameList = Arrays.asList("Smith", "Johnson", "Williams", "Davis", "Jones", "Brown", "Garcia");

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(lastNameList.size());
            lastName = lastNameList.get(randomIndex);
        }
        return lastName;
    }

    public String getForeignTaxIndicator() {
        Random rand = new Random();
        String panProduct = "Fixed Unassigned - MLIC";
        int numberOfElements = 1;
        List<String> panProductList = Arrays.asList("Fixed Unassigned - MLIC");
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(panProductList.size());
            panProduct = panProductList.get(randomIndex);
        }
        return panProduct;
    }

    public String getGrossPremium() {
        Random rand = new Random();
        String grossPremium = "50000";
        int numberOfElements = 1;
        List<String> grossPremiumList = Arrays.asList("50000");
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(grossPremiumList.size());
            grossPremium = grossPremiumList.get(randomIndex);
        }
        return grossPremium;
    }

    public String getSSPlanProductName() {
        Random rand = new Random();
        String ssPlanProduct = "Fixed Unassigned - MLIC";
        int numberOfElements = 1;
        List<String> ssPlanProductList = Arrays.asList("Fixed Unassigned - MLIC");
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(ssPlanProductList.size());
            ssPlanProduct = ssPlanProductList.get(randomIndex);
        }
        return ssPlanProduct;
    }

    public String getContractIssueDate() {
        Random rand = new Random();
        String contractIssueDate = "01012017";
        int numberOfElements = 1;
        List<String> contractIssueDateList = Arrays.asList("01012017", "01022018", "09012019", "09062020");
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(contractIssueDateList.size());
            contractIssueDate = contractIssueDateList.get(randomIndex);
        }
        return contractIssueDate;
    }

    public String getCommenceDate() {
        Random rand = new Random();
        String commenceDate = "01012017";
        int numberOfElements = 1;
        List<String> commenceDateList = Arrays.asList("01012017", "01022018", "09012019", "09062020");
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(commenceDateList.size());
            commenceDate = commenceDateList.get(randomIndex);
        }
        return commenceDate;
    }

    public String getPremiumTaxState() {
        Random rand = new Random();
        String premiumTaxState = "Delaware";
        int numberOfElements = 1;
        //List<String> premiumTaxStateList = Arrays.asList("NEW YORK", "Virginia", "Ohio","North Carolina","Delaware");
        List<String> premiumTaxStateList = Arrays.asList("Delaware");
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(premiumTaxStateList.size());
            premiumTaxState = premiumTaxStateList.get(randomIndex);
        }
        return premiumTaxState;
    }

    public String getContractPaymentType() {
        Random rand = new Random();
        String contractPaymentType = "Direct";
        int numberOfElements = 1;
        //List<String> contractPaymentTypeList = Arrays.asList("Direct", "Bulk");
        List<String> contractPaymentTypeList = Arrays.asList("Direct");
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(contractPaymentTypeList.size());
            contractPaymentType = contractPaymentTypeList.get(randomIndex);
        }
        return contractPaymentType;
    }

    public String getTaxableFlag() {
        Random rand = new Random();
        String taxableFlag = "Y";
        int numberOfElements = 1;
        // List<String> taxableFlagList = Arrays.asList("Y","N");
        List<String> taxableFlagList = Arrays.asList("Y");
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(taxableFlagList.size());
            taxableFlag = taxableFlagList.get(randomIndex);
        }
        return taxableFlag;
    }

    public String getFoa() {
        Random rand = new Random();
        String foa = "Life";
        int numberOfElements = 1;
        // List<String> foaList = Arrays.asList("Life","Term Certain");
        List<String> foaList = Arrays.asList("Life");
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(foaList.size());
            foa = foaList.get(randomIndex);
        }
        return foa;
    }

    public Map<String, String> getMockProfile(String dataType) {
        Map<String, String> mockProfile = new HashMap<>();
        if ("Annuitant".equalsIgnoreCase(dataType)) {
            mockProfile.put("firstname", this.getFirstName());
            mockProfile.put("lastname", this.getLastName());
            mockProfile.put("gender", this.getGender());
            mockProfile.put("dob", "01011965");
            mockProfile.put("ssn", getSSN());
            mockProfile.put("citizenship", getCitizenship());
            mockProfile.put("maritalstatus", getMaritalStatus());
            if (!mockProfile.get("citizenship").equalsIgnoreCase("United States")) {
                mockProfile.put("foreigntaxindicator", getForeignTaxIndicator());
                mockProfile.put("foreigntaxindicatorDate", "01011983");
            } else {
                mockProfile.put("foreigntaxindicator", "");
                mockProfile.put("foreigntaxindicatorDate", "");
            }

        }
        return mockProfile;
    }

    public Map<String, String> getMockProfileOfJoint(String dataType) {
        Map<String, String> mockProfile = new HashMap<>();
        if ("Joint".equalsIgnoreCase(dataType)) {
            mockProfile.put("firstname", this.getFirstName());
            mockProfile.put("lastname", this.getLastName());
            mockProfile.put("gender", this.getGender());
            mockProfile.put("dob", "01011965");
            mockProfile.put("ssn", getSSN());
            mockProfile.put("citizenship", getCitizenship());
            mockProfile.put("maritalstatus", getMaritalStatus());
            if (!mockProfile.get("citizenship").equalsIgnoreCase("United States")) {
                mockProfile.put("foreigntaxindicator", getForeignTaxIndicator());
                mockProfile.put("foreigntaxindicatorDate", "01011983");
            } else {
                mockProfile.put("foreigntaxindicator", "");
                mockProfile.put("foreigntaxindicatorDate", "");
            }

        }
        return mockProfile;
    }
    public Map<String, String> getMockContractCertData() {
        Map<String, String> mockPlanData = new HashMap<>();
        mockPlanData.put("plan", this.getSSPlanProductName());
        mockPlanData.put("issuedate", this.getContractIssueDate());
        mockPlanData.put("grosspremium", this.getGrossPremium());
        mockPlanData.put("premiumtaxstate", this.getPremiumTaxState());
        mockPlanData.put("paymenttype", this.getContractPaymentType());
        mockPlanData.put("product", this.getSSPlanProductName());
        mockPlanData.put("contractsandcertsbranchsubbranch", "");

        return mockPlanData;
    }

    public List<Map<String, String>> getMockBeneficiary() {
        List<Map<String, String>> beneList = new ArrayList<>();
        Map<String, String> beneficiary1 = new HashMap<>();
        beneficiary1.put("effectivedate", this.getContractIssueDate());
        beneficiary1.put("category", "Individual");
        beneficiary1.put("type", "Primary Beneficiary");
        beneficiary1.put("percent", "50");
        beneficiary1.put("firstname", this.getFirstName());
        beneficiary1.put("lastname", this.getLastName());
        beneficiary1.put("ssn", getSSN());
        beneficiary1.put("dob", "01011966");
        beneficiary1.put("gender", this.getGender());
        beneficiary1.put("livestatus", "Living");
        beneficiary1.put("residentialaddressflag", "Y");
        beneficiary1.put("residentialaddressdefaultflag", "Y");
        beneList.add(beneficiary1);
        Map<String, String> beneficiary2 = new HashMap<>();
        beneficiary2.put("effectivedate", this.getContractIssueDate());
        beneficiary2.put("category", "Individual");
        beneficiary2.put("type", "Primary Beneficiary");
        beneficiary2.put("percent", "50");
        beneficiary2.put("firstname", this.getFirstName());
        beneficiary2.put("lastname", this.getLastName());
        beneficiary2.put("ssn", getSSN());
        beneficiary2.put("dob", "01011976");
        beneficiary2.put("gender", this.getGender());
        beneficiary2.put("livestatus", "Living");
        beneficiary2.put("residentialaddressflag", "Y");
        beneficiary2.put("residentialaddressdefaultflag", "Y");
        beneList.add(beneficiary2);
        return beneList;
    }
    public List<Map<String, String>> getMockforSingleBeneficiary() {
        List<Map<String, String>> beneList = new ArrayList<>();
        Map<String, String> beneficiary1 = new HashMap<>();
        beneficiary1.put("effectivedate", this.getContractIssueDate());
        beneficiary1.put("category", "Individual");
        beneficiary1.put("type", "Primary Beneficiary");
        beneficiary1.put("percent", "100");
        beneficiary1.put("firstname", this.getFirstName());
        beneficiary1.put("lastname", this.getLastName());
        beneficiary1.put("ssn", getSSN());
        beneficiary1.put("dob", "01011966");
        beneficiary1.put("gender", this.getGender());
        beneficiary1.put("livestatus", "Living");
        beneficiary1.put("residentialaddressflag", "Y");
        beneficiary1.put("residentialaddressdefaultflag", "Y");
        beneList.add(beneficiary1);
        return beneList;
    }

    public List<Map<String, String>> getMockBeneficiarymultiple() {
        List<Map<String, String>> beneList = new ArrayList<>();
        Map<String, String> beneficiary1 = new HashMap<>();
        beneficiary1.put("effectivedate", this.getContractIssueDate());
        beneficiary1.put("category", "Individual");
        beneficiary1.put("type", "Primary Beneficiary");
        beneficiary1.put("percent", "50");
        beneficiary1.put("firstname", this.getFirstName());
        beneficiary1.put("lastname", this.getLastName());
        beneficiary1.put("ssn", getSSN());
        beneficiary1.put("dob", "01011966");
        beneficiary1.put("gender", this.getGender());
        beneficiary1.put("livestatus", "Living");
        beneficiary1.put("residentialaddressflag", "Y");
        beneficiary1.put("residentialaddressdefaultflag", "Y");
        beneficiary1.put("Marital Status",this.getMaritalStatus());
        beneList.add(beneficiary1);
        Map<String, String> beneficiary2 = new HashMap<>();
        beneficiary2.put("effectivedate", this.getContractIssueDate());
        beneficiary2.put("category", "Individual");
        beneficiary2.put("type", "Primary Beneficiary");
        beneficiary2.put("percent", "50");
        beneficiary2.put("firstname", this.getFirstName());
        beneficiary2.put("lastname", this.getLastName());
        beneficiary2.put("ssn", getSSN());
        beneficiary2.put("dob", "01011976");
        beneficiary2.put("gender", this.getGender());
        beneficiary2.put("livestatus", "Living");
        beneficiary2.put("residentialaddressflag", "Y");
        beneficiary2.put("residentialaddressdefaultflag", "Y");
        beneficiary2.put("Marital Status",this.getMaritalStatus());
        beneList.add(beneficiary2);
        return beneList;

    }



    public List<Map<String, String>> getMockBroker() {
        List<Map<String, String>> brokerList = new ArrayList<>();
        Map<String, String> broker1 = new HashMap<>();
        broker1.put("brokerid", "20S0001");
        broker1.put("commissionplit", "100");
        broker1.put("productionsplit", "100");
        brokerList.add(broker1);
        return brokerList;

    }

    public List<Map<String, String>> getMockBenefit() {
        List<Map<String, String>> benefitList = new ArrayList<>();
        Map<String, String> benefit1 = new HashMap<>();
        benefit1.put("BenefitsContractName", "GEICO General Insurance Company/SSA_CONTRACT_01");
        benefit1.put("CommencementDate", getCommenceDate());
        benefit1.put("Foa", getFoa());
        benefit1.put("FOAStatReserve", "100");
        benefit1.put("QIBReserve", "123");
        benefit1.put("OwnerState", "New York");
        benefit1.put("FOAPremiumAmt", getGrossPremium());
        benefit1.put("SSAPlanStructuredProduct", "Fixed Unassigned - MLIC");
        benefit1.put("CommutationRequiredFlag", "");
        benefit1.put("CommutationPercentage", "");
        benefit1.put("DeathCommutation", "");
        benefit1.put("CommutationEliminationOfLiability", "");
        benefit1.put("CommutationEliminationType", "");
        benefit1.put("CommutationRemarriage", "");
        benefit1.put("GrossPremium", this.getGrossPremium());
        benefit1.put("TaxableFlag", this.getTaxableFlag());
        benefitList.add(benefit1);
        return benefitList;

    }

    public List<Map<String, String>> getMockBenefitSegmentDetail() {
        List<Map<String, String>> benefitList = new ArrayList<>();
        Map<String, String> benefit1 = new HashMap<>();
        benefit1.put("BenefitsContractName", "GEICO General Insurance Company/SSA_CONTRACT_01");
        benefit1.put("CommencementDate", getContractIssueDate());
        benefit1.put("FOAAnnuityOptionTypeName", "");
        benefit1.put("FOASegmentName", "Life");
        benefit1.put("Foa", getFoa());
        benefit1.put("FOAPlanCode", "");
        benefit1.put("FOABaseForm", getFoa());
        benefit1.put("FOABasicAmount", "1000");
        benefit1.put("FOAFrequency", "Monthly");
        benefit1.put("FOAEveryXthMonthOrWeek", "1");
        benefit1.put("FOADay", "1st");
        benefit1.put("FOADay1", "");
        benefit1.put("FOADay2", "");
        benefit1.put("FOAEveryXthYear", "");
        benefit1.put("FOAPercentRemainAtDeath", "");
        benefit1.put("RDBFlag", "");
        benefit1.put("RDBTrigger", "");
        benefit1.put("RDBTriggerMod", "");
        benefit1.put("RDBDate", "");
        benefit1.put("RDBType", "");
        benefit1.put("RDBInitialAmount", "");
        benefit1.put("RDBRemainingBalance", "");
        benefit1.put("RDBReducedBenefitAmount", "");
        benefit1.put("FOACertainPeriod", "");
        benefit1.put("FOACertainPeriodEndDate", "");
        benefit1.put("FOAOtherFeatureTrigger", "");
        benefit1.put("FOAJointInfoTrigger", "");
        benefit1.put("FOATemporaryPeriod", "");
        benefit1.put("FOATemporaryPeriodEndDate", "");
        benefit1.put("FOAEditDeath_ChangeType", "");
        benefit1.put("FOAEditDeathChangeAmount", "");
        benefit1.put("FOAEditDeathChangeDate", "");
        benefit1.put("FOAEditDeathChangeTo_Amount", "");
        benefit1.put("FOAEditDeathChangeEvent", "");
        benefit1.put("FOAEditDeathChangePercent", "");
        benefit1.put("ExcludableRatio", "");
        benefit1.put("ExclusionType", "");
        benefit1.put("TotalCostBasisAmount", "");
        benefit1.put("TotalCapitalGainsAmount", "");
        benefit1.put("EquivalencyRatio", "");
        benefit1.put("CostBasisGainsFlag", "");
        benefit1.put("RolloverAmount", "");
        benefit1.put("RDBLumpSumAmount", "");
        benefit1.put("LumpSumAmountType", "");
        benefit1.put("RDBCheckBoxFlag", "");
        benefit1.put("FOAPayeeName", "");
        benefit1.put("CommutationRequiredFlag", "");
        benefit1.put("DeathCommutation", "");
        benefit1.put("CommutationPercentage", "");
        benefit1.put("CommutationEliminationOfLiability", "");
        benefit1.put("CommutationEliminationType", "");
        benefit1.put("CommutationRemarriage", "");
        benefit1.put("CommutationNextDue", "");
        benefit1.put("CommutationAmount", "");
        benefit1.put("FDBFlag", "");
        benefit1.put("FDBCheckboxFlag", "");
        benefit1.put("FDB_Trigger", "");
        benefit1.put("FOAStatReserve", "100");
        benefit1.put("QIBReserve", "123");
        benefit1.put("OwnerState", "Delaware");
        benefit1.put("FOAPremiumAmt", getGrossPremium());
        benefit1.put("SSAPlanStructuredProduct", "Fixed Unassigned - MLIC");
        benefit1.put("GrossPremium", this.getGrossPremium());
        benefit1.put("TaxableFlag", this.getTaxableFlag());
        benefitList.add(benefit1);
        return benefitList;

    }

    public Map<String, String> getMockLoginDetail(String userType) {
        Map<String, String> loginDetails = new HashMap<>();
        if (userType.equalsIgnoreCase("User")) {
            loginDetails.put("userID", this.environmentProperties.getUserId());
            loginDetails.put("userPassword", this.environmentProperties.getUserPass());
        }
        if (userType.equalsIgnoreCase("Approver")) {
            loginDetails.put("userID", this.environmentProperties.getApproverId());
            loginDetails.put("userPassword", this.environmentProperties.getApproverPass());
        }
        if (userType.equalsIgnoreCase("FCU-User")) {
            loginDetails.put("userID", this.environmentProperties.getFCUUserId());
            loginDetails.put("userPassword", this.environmentProperties.getFCUuserPass());
        }
        if (userType.equalsIgnoreCase("FCU-Approver")) {
            loginDetails.put("userID", this.environmentProperties.getFCUApproverID());
            loginDetails.put("userPassword", this.environmentProperties.getFCUApproverpass());
        }
        if (userType.equalsIgnoreCase("NonApprover")) {
        	loginDetails.put("userID", this.environmentProperties.getNonApproverId());
            loginDetails.put("userPassword", this.environmentProperties.getNonApproverPass());
        }
        return loginDetails;
    }

    public String getSSN() {
        boolean doNotMatch = true;
        String ssn = null;
        while (doNotMatch) {
            ssn = Integer.toString(randomNumberGenerator(100000020, 899999999));
            String regex = "^(?!000|666)[0-8][0-9]{2}(?!00)[0-9]{2}(?!0000)[0-9]{4}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(ssn);
            if (matcher.matches()) {
                doNotMatch = false;
            }
        }
        return ssn;
    }

    public HashMap<String, String> provisional() {
        HashMap<String, String> provisionalright = new HashMap<>();
        provisionalright.put("Normal_Type", "Normal Package 1");
        provisionalright.put("GEP_Trait", "General Eligibility Parameters Package 1 - Participant");
        provisionalright.put("Purchase_Form", "Purchased Form Package 2 - Purchased Form");
        provisionalright.put("Delayed_Type", "Delayed Package 1");
        provisionalright.put("Early_Type", "Early Package 1 - Participant (3E)");
        
        return provisionalright;
    }
    
    public HashMap<String,String> mockContractsCertsDetails() {
    	HashMap<String, String> ContractsCertsDetails = new HashMap<>();
    	ContractsCertsDetails.put("Plan_Structured_Product", "Plan01");
    	ContractsCertsDetails.put("Payment_Status_At_Issue", "Active");
    	ContractsCertsDetails.put("Benefit_Details_Type", "Basic");
    	ContractsCertsDetails.put("Benefit_Details_Basic_Amount", "1000");
    	ContractsCertsDetails.put("Benefit_Details_Basic_Frequency", "Monthly");
    	
    	
    	
    	return ContractsCertsDetails;
    }
    public Map<String, String> getMockIIABenefitData() {
        List<Map<String, String>> mockBenefitData = new ArrayList<>();
        Random rand = new Random();
        Map<String, String> mockIIABenefitData = new HashMap<>();
        mockIIABenefitData.put("Commencement_Date","01012019");
        mockIIABenefitData.put("costAmount","");
        mockIIABenefitData.put("capitalAmount", "");
        mockIIABenefitData.put("purchasedRateSeries", "DEF");
        mockIIABenefitData.put("taxability", "Y");
        mockIIABenefitData.put("childLienNetworkVerifiedDate", "03232023");
        mockIIABenefitData.put("costBasisGainsFlag", "N");
        mockIIABenefitData.put("Activate_Case_Workflow_Generate_Flag","Y");
        mockIIABenefitData.put("Commutation_Required_Flag", "N");
        mockIIABenefitData.put("Death_Commutation", "N");
        mockIIABenefitData.put("Commutation_Percentage", "N");
        mockIIABenefitData.put("Commutation_Elimination_of_Liability", "N");
        mockIIABenefitData.put("Commutation_Elimination_type", "N");
        mockIIABenefitData.put("Commutation_Percentage", "N");
        mockIIABenefitData.put("Commutation_Remarriage", "N");
        //FOA Detals
        mockIIABenefitData.put("FOA_Segment_Name", "Life");
        mockIIABenefitData.put("FOA_premium_Amt", "50000");
        mockIIABenefitData.put("FOA_Stat_Reserve", "100");
        mockIIABenefitData.put("FOA", "Life Annuity");
        mockIIABenefitData.put("FOA_Base_Form", "Life");
        mockIIABenefitData.put("FOA_Basic_Amt", "1000");
        //Premium Movement
        mockIIABenefitData.put("Premium_Movement_Payment_Type", "Check");
        mockIIABenefitData.put("Premium_Movement_Check_Number", "12345");
        mockIIABenefitData.put("Premium_Movement_Bank_Account_Number","");
        mockIIABenefitData.put("Premium_Movement_Payment_Amount", "50000");
        mockIIABenefitData.put("Premium_Movement_Batch_Number", "");
        mockIIABenefitData.put("Premium_Movement_Premium_Received_Date", "3232023");
        mockIIABenefitData.put("Premium_Movement_Batch_Date", "");
        mockIIABenefitData.put("Product", "MGIP Non-Qual - DB - MLIC");
        //Workflow
        mockIIABenefitData.put("Activate_Case_Workflow_Generate_Flag", "Y");
        
        //Payment Method
        
        mockIIABenefitData.put("Total_Number_of_Payment_Methods", "1"); //Same as ACD
        mockIIABenefitData.put("Payment_Stream_Payment_Method_1_Start_Date", "01012018"); //Same as ACD
        mockIIABenefitData.put("Payment_Stream_Payment_Method_1_Stop_Date", "");
        mockIIABenefitData.put("Payment_Stream_Payment_Method_1_Bank_Name","BOA");
        mockIIABenefitData.put("Payment_Stream_Payment_Method_1_Routing_No", "021000322");
        mockIIABenefitData.put("Payment_Stream_Payment_Method_1_Account_type", "Checking");
        mockIIABenefitData.put("Payment_Stream_Payment_Method_1_Account_number", "798343123");
        mockIIABenefitData.put("Payment_Stream_Payment_Method_1_Amount_Split", "");
        mockIIABenefitData.put("Payment_Stream_Payment_Method_1_Payment_Type", "EFT");
        mockIIABenefitData.put("Payment_Stream_Payment_Method_1_Routing_type", "");
        mockIIABenefitData.put("Payment_Stream_Payment_Method_1_Percent_Split", "60");
        
      
        return mockIIABenefitData;
        
    }
    public String getsystemdateforINPRSFT(){
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String trailerdate = currentDate.format(formatter);
		return trailerdate;

	}

    public List<Map<String, String>> getMockJA() {
        List<Map<String, String>> jaList = new ArrayList<>();
        Map<String, String> jointAnnuitant1 = new HashMap<>();
        jointAnnuitant1.put("effectivedate", this.getContractIssueDate());
        jointAnnuitant1.put("category", "Individual");
        jointAnnuitant1.put("type", "Spouse");
        jointAnnuitant1.put("percent", "100");
        jointAnnuitant1.put("firstname", this.getFirstName());
        jointAnnuitant1.put("lastname", this.getLastName());
        jointAnnuitant1.put("ssn", getSSN());
        jointAnnuitant1.put("dob", "01011958");
        jointAnnuitant1.put("gender", "Female");
        jointAnnuitant1.put("livestatus", "Living");
        jointAnnuitant1.put("residentialaddressflag", "Y");
        jointAnnuitant1.put("residentialaddressdefaultflag", "Y");
        jaList.add(jointAnnuitant1);
        return jaList;
    }

    public List<Map<String, String>> getMockJointAnnuitant() {
        List<Map<String, String>> jointAnnuitantList = new ArrayList<>();
        Map<String, String> jointAnnuitant1 = new HashMap<>();
        jointAnnuitant1.put("effectivedate", this.getContractIssueDate());
        jointAnnuitant1.put("category", "Individual");
        jointAnnuitant1.put("type", "Spouse");
        jointAnnuitant1.put("Marital Status", "Married");
        jointAnnuitant1.put("percent", "100");
        jointAnnuitant1.put("firstname", this.getFirstName());
        jointAnnuitant1.put("lastname", this.getLastName());
        jointAnnuitant1.put("ssn", getSSN());
        jointAnnuitant1.put("dob", "01011958");
        jointAnnuitant1.put("gender", this.getGender());
        jointAnnuitant1.put("residentialaddressflag", "Y");
        jointAnnuitant1.put("residentialaddressdefaultflag", "Y");
        jointAnnuitantList.add(jointAnnuitant1);
        return jointAnnuitantList;
    }
    public HashMap<String,String> mockBenefitDetails() {
        HashMap<String, String> BenefitDetails = new HashMap<>();
        BenefitDetails.put("Date_Option", "Date Specific");
        BenefitDetails.put("Age", "65/00");
        BenefitDetails.put("Date", "02012017");

        return BenefitDetails;
    }
    public HashMap<String, String> deductionDetails() {
        HashMap<String, String> addingDeduction = new HashMap<>();
        addingDeduction.put("dType","Federal Tax");
        addingDeduction.put("dAmount","20");


        return addingDeduction;
    }

    public HashMap<String, String> annuitantDeathDetails() {
        HashMap<String, String> annuitantDeathDetails = new HashMap<>();
        annuitantDeathDetails.put("Annuitant_DOD","01012023");
        annuitantDeathDetails.put("Annuitant_Death_Source_Notification","Other");
        annuitantDeathDetails.put("Annuitant_DOD_Notification","01012023");


        return annuitantDeathDetails;
    }


}
