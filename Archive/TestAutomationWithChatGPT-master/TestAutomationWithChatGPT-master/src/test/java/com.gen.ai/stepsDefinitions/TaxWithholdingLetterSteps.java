package com.gen.ai.stepDefinations;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.SkipException;

import com.metlife.ace.automation.annotation.LazyAutowired;
import com.metlife.ace.automation.config.appconfig.CountryNameProperties;
import com.metlife.ace.automation.config.appconfig.TestQueryProperties;
import com.metlife.ace.automation.config.webelement.AnnuiatantPageElement;
import com.metlife.ace.automation.config.webelement.BasePageElement;
import com.metlife.ace.automation.model.AnnuitantDynamicDetails;
import com.metlife.ace.automation.model.AnnuitantDynamicMailingDetails;
import com.metlife.ace.automation.model.PersonProfileIIA;
import com.metlife.ace.automation.page.ACEBasePage;
import com.metlife.ace.automation.page.GenerateLetter;
import com.metlife.ace.automation.page.GenericPDFValidationPage;
import com.metlife.ace.automation.repository.AnnuitantDynamicDetailsRepository;
import com.metlife.ace.automation.repository.AnnuitantDynamicMailingDetailsRepository;
import com.metlife.ace.automation.repository.IIACaseActivationRepository;
import com.metlife.ace.automation.repository.PersonProfileRepository;
import com.metlife.ace.automation.utility.Constants;
import com.metlife.ace.automation.utility.TestUtility;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@SpringBootTest
public class TaxWithholdingLetterSteps extends ACEBasePage {

    @LazyAutowired
    private TestUtility fileUtility;
    @LazyAutowired
    private GenerateLetter generateletter;
    @LazyAutowired
    private PersonProfileRepository personProfileRepository;
    @LazyAutowired
    private TestQueryProperties testQueryProperties;
    @LazyAutowired
    private IIACaseActivationRepository iiaCaseActivationRepository;
    @LazyAutowired
    private BasePageElement basePageElement;
    @LazyAutowired
    private AnnuiatantPageElement annuiatantPageElement;
    @LazyAutowired
    private AnnuitantDynamicDetailsRepository annuitantDynamicDetailsRepository;
    @LazyAutowired
    private GenericPDFValidationPage genericPDF;
    @LazyAutowired
    private CountryNameProperties countryName;
    @LazyAutowired
    private AnnuitantDynamicMailingDetailsRepository annuitantDynamicMailingDetailsRepository;


    private Map<String, Map<String, String>> testCaseDataStoreMap = new ConcurrentHashMap<>();
    public static String IIACaseActivatedAltId;


    @Given("Fetch Case activated annuitant from DB with condition {string} for testcase {string}")
    public void fetchCaseActivatedAnnuitantFromDBWithConditionIIACaseActivatedAnnuitantLivingNonUSForTestCase(String queryName, String testCaseNo) {
        log.info("gettingTheCaseActivatedAnnuitantFromDBWithConditionIIACaseActivatedAnnuitantLivingNonUSForTestCase" + testCaseNo);

        try {
            String IIACaseActivatedAnnuitantAltId = this.generateletter.executeCaseActivationQuery(queryName);
            this.IIACaseActivatedAltId = IIACaseActivatedAnnuitantAltId;
            HashMap<String,String> annuitantDetails = new HashMap<>();
            annuitantDetails.put("ALTID", IIACaseActivatedAltId);
            Constants.annuitantDynamicDetails.put(testCaseNo,annuitantDetails);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Then("User navigates to World and to select a {string} Annuitant Alt ID Businessflow {string}")
    public void userNavigatesToWorldandToSelectAAnnuitantAltIDBusinessflow(String sheetName, String testCaseNo) {

        log.info("userNavigatesToWorldandToSelectAAnnuitantAltIDBusinessflow" + testCaseNo);
        Map<String, String> testDataSheet = null;
        try {
            fileUtility.createNewTestEvidence(testCaseNo);
            if (testCaseDataStoreMap.get(testCaseNo) != null) {
                testDataSheet = testCaseDataStoreMap.get(testCaseNo);
            } else {
                testDataSheet = this.fileUtility.getTestDataFromDataSheetNew(sheetName, testCaseNo);
            }

            testCaseDataStoreMap.put(testCaseNo, testDataSheet);

            this.generateletter.aceBusinessSelection("Annuitant", IIACaseActivatedAltId);
            this.frameToBeAvailableAndSwitchToIt(this.annuiatantPageElement.getAnnuitantProfileIframe());
            this.generateletter.EditContactDetails();
            moveToDefault();
            this.clickApply();
            waitForPageLoad();
            this.clickSave();


        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Given("Getting the IIA Case activated annuitant from DB for testcase {string}")
    public synchronized void GettingtheIIACaseactivatedannuitantfromDBfortestcase(String testCaseNo) throws Exception {

        log.info("GettingtheIIACaseactivatedannuitantfromDBfortestcase" + testCaseNo);
        String IIACaseActivatedAltId="";

        try {
            List<String> parametersList = new ArrayList<>();

            String IIACaseActivitaionQuery = testQueryProperties.getQuerySql().get("IIACaseActivatedAnnuitantLivingUSv2");

            List<PersonProfileIIA> queryresponselist = this.iiaCaseActivationRepository
                    .getIIACaseActivatedProfile(IIACaseActivitaionQuery, parametersList);

            if (queryresponselist != null) {

                int QueryResponseRandomValue = this.generateletter.randomNumberGenerator(1, queryresponselist.size());

                Object queryResponseObject = queryresponselist.get(QueryResponseRandomValue);

                String IIACaseActivatedPersonFirstName = ((PersonProfileIIA) queryResponseObject).getPersonFirstname();
                String IIACaseActivatedPersonLastName = ((PersonProfileIIA) queryResponseObject).getPersonLastName();
                String IIACaseActivatedAltID = ((PersonProfileIIA) queryResponseObject).getPersonAltId();

                IIACaseActivatedAltId = queryresponselist.get(QueryResponseRandomValue).getPersonAltId();

                log.info("Annuitant First Name is  : " + IIACaseActivatedPersonFirstName);
                log.info("Annuitant Last Name is  : " + IIACaseActivatedPersonLastName);
                log.info("Annuitant Alt ID is  : " + IIACaseActivatedAltID);

                HashMap<String,String> annuitantDetails = new HashMap<>();
                annuitantDetails.put("ALTID", IIACaseActivatedAltId);
                Constants.annuitantDynamicDetails.put(testCaseNo,annuitantDetails);

            }
            log.info("Query response size is : " + queryresponselist.size());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @And("User modify Tax details in Payment Setup Page for IIA testcase {string}")
    public void userModifyTaxDetailsInPaymentSetupPageForIIATestcase(String testCaseNo) {

        log.info("userModifyTaxDetailsInPaymentSetupPageForIIATestcase" + testCaseNo);

        try {
            this.generateletter.modifytaxAddition(testCaseNo);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Then("User running the {string} for {string} in IIA {string}")
    public void userRunningTheForInIIA(String batchname, String letter, String testCaseNo) {

        log.info("userRunningTheForInIIA" + testCaseNo);
        try {
            this.generateletter.executeBatch(batchname, letter, testCaseNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @And("User searches for the Annuitant for IIA testcase {string}")
    public void userSearchesForTheAnnuitantForIIATestcase(String testCaseNo)  throws Exception{

        log.info("userSearchesForTheAnnuitantForIIATestcase" + testCaseNo);

        try {
            this.generateletter.submitAceEntitySearch("Annuitant",
                    Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Then("Verify that User is able to generate the {string} for IIA testcase {string}")
    public void verifyThatUserIsAbleToGenerateTheForIIATestcase(String letterName, String testCaseNo) {

        log.info("verifyThatUserIsAbleToGenerateTheForIIATestcase" + testCaseNo);

        try {
            this.generateletter.LetterGenerationVerification(letterName, testCaseNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @And("User searches for the Annuitant {string}")
    public void userSearchesForTheAnnuitant(String testCaseNo) throws Exception {

        log.info("userSearchesForTheAnnuitant" + testCaseNo);

        try {

            //The below code is used in case if you want to retrive the Annuitant ID from the excel sheet
            // or can be used during dry run
            //this.generateletter.submitAceEntitySearch("Annuitant",
            //testCaseDataStoreMap.get(testCaseNo).get("Alt_ID"));

            this.generateletter.submitAceEntitySearch("Annuitant", Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID"));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Then("User navigates to World and to select a {string} Annuitant Alt ID Businessflows {string}")
    public void userNavigatesToWorldandToSelectAAnnuitantAltIDBusinessflows(String sheetName, String testCaseNo) {

        log.info("userNavigatesToWorldandToSelectAAnnuitantAltIDBusinessflows" + testCaseNo);
        Map<String, String> testDataSheet = null;
        try {
            fileUtility.createNewTestEvidence(testCaseNo);
//            if (testCaseDataStoreMap.get(testCaseNo) != null) {
//                testDataSheet = testCaseDataStoreMap.get(testCaseNo);
//            } else {
//                testDataSheet = this.fileUtility.getTestDataFromDataSheetNew(sheetName, testCaseNo);
//            }

            String AltID = Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID");
            this.generateletter.aceBusinessSelection("Annuitant", AltID);
            this.clickEdit();
            this.generateletter.EditContactDetails();
            moveToDefault();
            Thread.sleep(3000);
            this.clickApply();
            waitForPageLoad();
            this.clickSave();
            //testCaseDataStoreMap.put(testCaseNo, testDataSheet);

//            String AltID = Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID");
//            this.generateletter.aceBusinessSelection("Annuitant", AltID);

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("User modify Tax details in Payment Setup Page for testcase {string}")
    public void user_modify_tax_details_in_payment_setup_page_for_testcase(String testCaseNo) throws InterruptedException {
        String errorMessage="";
        log.info("userModifyTaxDetailsInPaymentSetupPageForTestcase" + testCaseNo);
        boolean flowResult=true;
        try {
            flowResult = this.generateletter.modifytaxAddition(testCaseNo);

        } catch (Exception e) {
            errorMessage=this.checkForErrorMessage();
            if(errorMessage!=""){
                flowResult=false;
            }
            throw new RuntimeException(e);
        } finally {
            errorMessage = this.checkForErrorMessage();
            if(flowResult==false){
                throw new SkipException("Appropriate Test Data could not be found for testcase "+testCaseNo+ "\n"
                        + "Error Message: " + errorMessage + "\n"
                        + "Annuitant Alternate ID: "+ Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID"));
            }
        }
    }

    @Then("User running the {string} for {string} in testcase {string}")
    public void user_running_the_for_in_testcase(String batchname, String letter, String testCaseNo) {

        log.info("userRunningTheForTestcase" + testCaseNo);
        try {
            this.generateletter.executeBatch(batchname, letter, testCaseNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("Verify that User is able to generate the {string} for testcase {string}")
    public void verify_that_user_is_able_to_generate_the_for_testcase(String letterName, String testCaseNo) {

        log.info("verifyThatUserIsAbleToGenerateTheForTestcase" + testCaseNo);

        try {
            this.generateletter.checkEditModeinBatchProcess();
            this.generateletter.LetterGenerationVerification(letterName, testCaseNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @And("User searches for the Annuitant for Tax Witholding Letter testcase {string}")
    public void userSearchesForTheAnnuitantForTestcase(String testCaseNo) throws Exception {
        log.info("userSearchesForTheAnnuitantForTestcase " + testCaseNo + " for alt ID: "+ Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID"));

        try {
            this.generateletter.submitAceEntitySearch("Annuitant", Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    @Then("User fetches the Annuitant Details for testcase {string}")
    public void user_fetches_the_annuitant_details_for_testcase(String testCaseNo) {
        log.info("User fetches the Annuitant Details for testcase " + testCaseNo);
        boolean flowResult=true;

        try {


            String annuitantDynamicDetailsQuery = testQueryProperties.getQuerySql().get("FetchAnnuitantDynamicDetails");
            log.info("Query for Annutiant Details of testcase " + testCaseNo + " : "+annuitantDynamicDetailsQuery );

            List<String> parametersList = new ArrayList<>();
            parametersList.add(Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID"));
            log.info("Parameters List for testcase " + testCaseNo + " : "+parametersList );

            List<AnnuitantDynamicDetails> queryresponselist = this.annuitantDynamicDetailsRepository
                    .getAnnuitantDyanmicDetails(annuitantDynamicDetailsQuery, parametersList);

            if (queryresponselist.size()!=0) {
                log.info("Annuitant Dynamic Details for testcase " + testCaseNo + " : "+queryresponselist );
                log.info(""+queryresponselist.get(0));
                Constants.annuitantDynamicDetails.get(testCaseNo).put("ALTID",queryresponselist.get(0).getPersonAltId());

                Constants.annuitantDynamicDetails.get(testCaseNo).put("SSN",queryresponselist.get(0).getPersonSSN());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("FirstName",queryresponselist.get(0).getPersonFirstName());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MiddleName",queryresponselist.get(0).getPersonMiddleName());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("LastName",queryresponselist.get(0).getPersonLastName());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("DOB",queryresponselist.get(0).getDOB());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("FirstNameUpperCase"
                        ,(queryresponselist.get(0).getPersonFirstName()!=null?queryresponselist.get(0).getPersonFirstName().toUpperCase():null));
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MiddleNameUpperCase"
                        ,(queryresponselist.get(0).getPersonMiddleName()!=null?queryresponselist.get(0).getPersonMiddleName().toUpperCase():null));
                Constants.annuitantDynamicDetails.get(testCaseNo).put("LastNameUpperCase"
                        ,(queryresponselist.get(0).getPersonLastName()!=null?queryresponselist.get(0).getPersonLastName().toUpperCase():null));


                Constants.annuitantDynamicDetails.get(testCaseNo).put("Suffix",queryresponselist.get(0).getPersonSuffix());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MaritalStatus",queryresponselist.get(0).getPersonMaritalStatus());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("PhoneNumber",queryresponselist.get(0).getPersonPhoneNumber());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("Email",queryresponselist.get(0).getPersonEmail());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialAddress1",queryresponselist.get(0).getResidentialAddress1());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialAddress2",queryresponselist.get(0).getResidentialAddress2());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialAddress3",queryresponselist.get(0).getResidentialAddress3());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialCity",queryresponselist.get(0).getResidentialCity());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialState",queryresponselist.get(0).getResidentialState());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialZip",queryresponselist.get(0).getResidentialZip());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialCountry",queryresponselist.get(0).getResidentialCountry());

                Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialAddress1UpperCase"
                        ,(queryresponselist.get(0).getResidentialAddress1()!=null?queryresponselist.get(0).getResidentialAddress1().toUpperCase():null));
                Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialAddress2UpperCase"
                        ,(queryresponselist.get(0).getResidentialAddress2()!=null?queryresponselist.get(0).getResidentialAddress2().toUpperCase():null));
                Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialAddress3UpperCase"
                        ,(queryresponselist.get(0).getResidentialAddress3()!=null?queryresponselist.get(0).getResidentialAddress3().toUpperCase():null));
                Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialCityUpperCase"
                        ,(queryresponselist.get(0).getResidentialCity()!=null?queryresponselist.get(0).getResidentialCity().toUpperCase():null));
                Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialStateUpperCase"
                        ,(queryresponselist.get(0).getResidentialState()!=null?queryresponselist.get(0).getResidentialState().toUpperCase():null));


                if(queryresponselist.get(0).getResidentialCountry()!=null) {
                    Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialCountryFullName", (this.countryName.getCountryFullName().get(queryresponselist.get(0).getResidentialCountry())).toUpperCase().trim());
                } else{
                    Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialCountryFullName", null);
                }
                Constants.annuitantDynamicDetails.get(testCaseNo).put("ResidentialPostal",queryresponselist.get(0).getResidentialPostal());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("GroupNumber",queryresponselist.get(0).getGroupNumber());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("GroupName",queryresponselist.get(0).getGroupName());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("LegalBillingID",queryresponselist.get(0).getLegalBillingID());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("FormattedFirstName",this.formatName(queryresponselist.get(0).getPersonFirstName()));
                Constants.annuitantDynamicDetails.get(testCaseNo).put("FormattedMiddleName",this.formatName(queryresponselist.get(0).getPersonMiddleName()));
                Constants.annuitantDynamicDetails.get(testCaseNo).put("FormattedLastName",this.formatName(queryresponselist.get(0).getPersonLastName()));
                Constants.annuitantDynamicDetails.get(testCaseNo).put("AnnuityNumber",queryresponselist.get(0).getAnnuityNumber());

                Constants.annuitantDynamicDetails.get(testCaseNo).put("EmployerName",queryresponselist.get(0).getEmployer_name());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("FirstPayDate",queryresponselist.get(0).getFirst_pay_date());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("PaymentFrequency",queryresponselist.get(0).getPayment_frequency());

                if(queryresponselist.get(0).getFirst_pay_date()!=null) {
                    Constants.annuitantDynamicDetails.get(testCaseNo).put("FormattedFirstPayDate", (this.convertDateFormat1(queryresponselist.get(0).getFirst_pay_date())));
                } else{
                    Constants.annuitantDynamicDetails.get(testCaseNo).put("FormattedFirstPayDate", null);
                }


            } else {
                log.info("Annuitant Dynamic Details is NULL for testcase " + testCaseNo);

            }



            String annuitantMailingAddressQuery = testQueryProperties.getQuerySql().get("AnnuitantDynamicDetailsMailingAddress");
            log.info("Query for Annutiant Mailing Address of testcase " + testCaseNo + " : "+annuitantMailingAddressQuery );


            List<AnnuitantDynamicMailingDetails> mailingaddressresponselist = this.annuitantDynamicMailingDetailsRepository
                    .getAnnuitantDyanmicDetails(annuitantMailingAddressQuery, parametersList);

            if (mailingaddressresponselist.size()!=0) {
                log.info("Annuitant Mailing Address Details for testcase " + testCaseNo + " : "+mailingaddressresponselist );
                log.info(""+mailingaddressresponselist.get(0));

                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingAddress1",mailingaddressresponselist.get(0).getMailingAddress1());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingAddress2",mailingaddressresponselist.get(0).getMailingAddress2());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingAddress3",mailingaddressresponselist.get(0).getMailingAddress3());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingCity",mailingaddressresponselist.get(0).getMailingCity());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingState",mailingaddressresponselist.get(0).getMailingState());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingZip",mailingaddressresponselist.get(0).getMailingZip());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingCountry",mailingaddressresponselist.get(0).getMailingCountry());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingCountryFullName",(this.countryName.getCountryFullName().get(mailingaddressresponselist.get(0).getMailingCountry())).toUpperCase());
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingPostal",mailingaddressresponselist.get(0).getMailingPostal());
            } else {
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingAddress1",null);
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingAddress2",null);
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingAddress3",null);
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingCity",null);
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingState",null);
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingZip",null);
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingCountry",null);
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingCountryFullName",null);
                Constants.annuitantDynamicDetails.get(testCaseNo).put("MailingPostal",null);
            }
            for(String key:Constants.annuitantDynamicDetails.get(testCaseNo).keySet()) {
                log.info(key + " : "+ Constants.annuitantDynamicDetails.get(testCaseNo).get(key));
            }

        } catch (Exception e) {
            flowResult=false;
            throw new RuntimeException(e);
        }
//            finally {
//        	if(flowResult==false){
//        		throw new SkipException("Appropriate Test Data could not be found for testcase "+testCaseNo);
//           }
//        }
    }

    @Given("User gets Case Activated Annuitant for testcase {string}")
    public void user_gets_case_activated_annuitant_for_testcase(String testCaseNo) {
        try {

            log.info("GettingtCaseactivatedannuitantfortestcase" + testCaseNo);
            String IIACaseActivatedAltId="";

            if(testCaseNo.equalsIgnoreCase("TaxWithholding_IIA_MGIP_NONUS_TC_02"))
                IIACaseActivatedAltId="6933289673";
            else if(testCaseNo.equalsIgnoreCase("INITIALDOCREQ_SSA_US_TC_01"))
                IIACaseActivatedAltId="7651111681";//2122831555 - Duplicate SSN , 4223613045 - Bad Broker, 7333767385- able to generate
            else if(testCaseNo.equalsIgnoreCase("BANKCHGSTARTDDC_SSA_NONUS_TC_04")){
                IIACaseActivatedAltId="8420895321"; //Routing Number is not valid.
            }
            HashMap<String,String> annuitantDetails = new HashMap<>();
            annuitantDetails.put("ALTID", IIACaseActivatedAltId);
            Constants.annuitantDynamicDetails.put(testCaseNo,annuitantDetails);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("User gets company name details from ACE for testcase {string}")
    public void user_gets_company_name_details_from_ace_for_testcase(String testCaseNo) {
        try {

            this.genericPDF.submitAceEntitySearch("Contract", Constants.annuitantDynamicDetails.get(testCaseNo).get("GroupNumber"));
            this.genericPDF.contractSelection(Constants.annuitantDynamicDetails.get(testCaseNo).get("GroupNumber"));
            Constants.annuitantDynamicDetails.get(testCaseNo).put("CompanyName",this.genericPDF.getCompany());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Given("Fetch Case activated annuitant from DB with prerequisite {string} for testcase {string}")
    public synchronized void fetch_case_activated_annuitant_from_db_with_prerequisite_for_testcase(String queryName, String testCaseNo) {
        log.info("gettingTheCaseActivatedAnnuitantFromDBForTestCase" + testCaseNo);
        String IIACaseActivatedAltId="";
        try {
            String IIACaseActivatedAnnuitantAltId = this.generateletter.executeCaseActivationQuery(queryName);
            IIACaseActivatedAltId = IIACaseActivatedAnnuitantAltId;
            HashMap<String,String> annuitantDetails = new HashMap<>();
            annuitantDetails.put("ALTID", IIACaseActivatedAltId);
            Constants.annuitantDynamicDetails.put(testCaseNo,annuitantDetails);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(IIACaseActivatedAltId ==null || IIACaseActivatedAltId=="") {
                throw new SkipException("Test Data could not be found in Database for testcase "+testCaseNo);
            }
        }
    }



    @Then("User navigates to World and to select a {string} Annuitant Alt ID for testcase {string}")
    public void user_navigates_to_world_and_to_select_a_annuitant_alt_id_for_testcase(String product, String testCaseNo) throws InterruptedException {
        log.info("userNavigatesToWorldandToSelectAAnnuitantAltIDBusinessflows" + testCaseNo);
        Map<String, String> testDataSheet = null;
        Boolean flowResult=true;
        String errorMessage="";
        try {

            String AltID = Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID");
            this.generateletter.aceBusinessSelection("Annuitant", AltID);
            this.waitForPageLoad();
            this.frameToBeAvailableAndSwitchToIt(this.annuiatantPageElement.getAnnuitantProfileIframe());
            String Country = Constants.annuitantDynamicDetails.get(testCaseNo).get("ResidentialCountryFullName");



            log.info("The country is "+Country);
            if (!Country.equals("BRAZIL")){
                this.generateletter.EditContactDetails();
            }
            moveToDefault();
            this.clickApply();



            this.clickIPerrorhandler();
            waitForPageLoad();
            this.clickSave();

            testCaseNo=testCaseNo.toUpperCase();

            if((testCaseNo.contains("TAXWITHHOLDING") || testCaseNo.contains("BANKCHGSTARTDDC") || testCaseNo.contains("BANKCHGSTOPDDC")|| testCaseNo.contains("CitizenshipDeclarationPackage")||testCaseNo.contains("FOREIGN_CITIZENSHIP")||testCaseNo.contains("OverPaymentFullfillment"))
                    &&testCaseNo.contains("NONUS")){

                this.nonUSAddressmodification(testCaseNo);
            }


        } catch (InterruptedException | IOException e) {
            errorMessage=this.checkForErrorMessage();
            if(errorMessage!=""){
                flowResult=false;
            }
            throw new RuntimeException(e);
        } catch (Exception e) {
            errorMessage=this.checkForErrorMessage();
            if(errorMessage!=""){
                flowResult=false;
            }
            throw new RuntimeException(e);
        } finally{
            if(!flowResult){
                throw new SkipException("Appropriate Test Data not found for " + testCaseNo + "\n"
                        + "Error Message: " + errorMessage + "\n"
                        + "Annuitant Alternate ID: "+ Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID"));
            }
        }
    }


    @And("User searches for the Annuitant for The Letter testcase {string}")
    public void user_searches_for_the_annuitant_for_the_letter_testcase(String testCaseNo) throws Exception {
        log.info("userSearchesForTheAnnuitantForTestcase " + testCaseNo + " for alt ID: "+ Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID"));

        try {
            this.generateletter.submitAceEntitySearch("Annuitant", Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Then("User navigates to World and selects a {string} Joint Annuitant Alt ID for testcase {string}")
    public void user_navigates_to_world_and_to_select_joint_annuitant_alt_id_for_testcase(String product, String testCaseNo) {

        log.info("userNavigatesToWorldandToSelectAAnnuitantAltIDBusinessflows" + testCaseNo);
        Map<String, String> testDataSheet = null;
        try {

            String AltID = Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID");
            this.generateletter.aceBusinessSelection("All", AltID);
            this.waitForPageLoad();
            ClickonProfile();
            this.frameToBeAvailableAndSwitchToIt(this.annuiatantPageElement.getAnnuitantProfileIframe());


            //String Country = Constants.annuitantDynamicDetails.get(testCaseNo).get("ResidentialCountryFullName");
            //log.info("The country is " + Country);
            //if (!Country.equals("BRAZIL")) {
            this.generateletter.EditContactDetails();
            //}
            moveToDefault();
            this.clickApply();
            waitForPageLoad();
            this.clickSave();

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Then("User updates the annuitant citizenship for testcase {string}")
    public void user_updates_the_annuitant_citizenship_for_testcase(String testCaseNo) throws InterruptedException {
        log.info("user_updates_the_annuitant_citizenship_for_testcase:" + testCaseNo);

        Boolean flowResult=true;
        String errorMessage="";
        try {

            String AltID = Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID");

            this.waitForPageLoad();
            this.clickEdit();
            this.waitForPageLoad();

            this.frameToBeAvailableAndSwitchToIt(this.annuiatantPageElement.getAnnuitantProfileIframe());
            this.waitForPageLoad();

            this.generateletter.editCitizenship("Germany");

            moveToDefault();
            this.clickApply();
            this.clickIPerrorhandler();
            waitForPageLoad();
            this.clickSave();


        } catch (InterruptedException | IOException e) {
            errorMessage=this.checkForErrorMessage();
            if(errorMessage!=""){
                flowResult=false;
            }
            throw new RuntimeException(e);
        } catch (Exception e) {
            errorMessage=this.checkForErrorMessage();
            if(errorMessage!=""){
                flowResult=false;
            }
            throw new RuntimeException(e);
        } finally{
            if(!flowResult){
                throw new SkipException("Appropriate Test Data not found for " + testCaseNo + "\n"
                        + "Error Message: " + errorMessage + "\n"
                        + "Annuitant Alternate ID: "+ Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID"));
            }
        }
    }

    @Then("User adds mailing address for testcase {string}")
    public void user_adds_mailing_address_for_testcase(String testCaseNo) throws InterruptedException {
        String errorMessage="";
        Boolean flowResult=true;
        try{
            this.nonUSMailingAddressmodification(testCaseNo);

        } catch (Exception e) {
            errorMessage=this.checkForErrorMessage();
            if(errorMessage!=""){
                flowResult=false;
            }
            throw new RuntimeException(e);
        } finally{
            if(!flowResult){
                throw new SkipException("Appropriate Test Data not found for " + testCaseNo + "\n"
                + "Error Message: " + errorMessage + "\n"
                + "Annuitant Alternate ID: "+ Constants.annuitantDynamicDetails.get(testCaseNo).get("ALTID"));
            }
        }
    }


}