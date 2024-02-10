/*
package com.genaidemo.demo.page;


import com.genaidemo.demo.annotation.LazyAutowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public abstract class BasePage extends GenreicPage {
    @Autowired
    private BasePageElement basePageElement;
    @LazyAutowired
    private StatusCodesPageElements statuscodeElement;
    @LazyAutowired
    private AnnuiatantPageElement annuiatantPageElement;

    @LazyAutowired
    private BenefitsPageElement benefitsPageElement;
    @LazyAutowired

    private DeferredRepository DeferredRepo;
    @LazyAutowired
    private AnnuitantInPayBeneRepository Bene;
    @LazyAutowired
    private QuteInitiatedRepository QuoteInitiatedRepository;
    @LazyAutowired
    private AnnuitantInPayJointAnnuitantRepository JointAnnuitant;
    @LazyAutowired
    private PaymentPreviewReportLTRepository PaymentPreviewReportLT;
    @LazyAutowired
    private TestQueryProperties testQueryProperties;
    @LazyAutowired
    private CaseActivationRepository caseActivationRepository;

    @LazyAutowired
    private LifeAnnuityRepository Life;

    @LazyAutowired
    private ExtendedBenefitsPageElement benefitPageElementexd;
    @LazyAutowired
    private InPayJointAnnuitantRepository InPayJointAnnuitantRepository;
    @LazyAutowired
    private InPayAnnuitantBulkCheckRepository InPayAnnuitantBulkCheckRepository;
    @LazyAutowired
    private DeceasedLAAnnuitantRepository deceasedLAAnnuitantRepository;

    @LazyAutowired
    private BatchPageElement batchpage;
    @LazyAutowired
    private LivingLAAnnuitantRepository livingLAAnnuitantRepository;
    @LazyAutowired
    private DeceasedIIAAnnuitantRepository deceasedIIAAnnuitantRepository;

    @LazyAutowired
    private ContractCertsPageElement contractCertsPageElement;
    @LazyAutowired
    private DeathNotificationDetailsRepository deathNotificationDetailsRepository;
    @LazyAutowired
    private LivingSSAnnuitantRepository livingSSAnnuitantRepository;
    @LazyAutowired
    private InPayJointAnnuitantBulkRepository InPayJointAnnuitantBulkRepository;
    @LazyAutowired
    private QLACDeceasedAnnuitantRepository QLACDeceasedAnnuitantRepository;
    @LazyAutowired
    private AnnuitantBeneInPay1Repository AnnuitantBeneInPay1Repository;
    @LazyAutowired
    private AnnuitantBeneInPay1BulkRepository AnnuitantBeneInPay1BulkRepository;
    @LazyAutowired
    private DeferredBulkRepository DeferredBulkRepo;

    @PostConstruct
    private void init() {

        PageFactory.initElements(this.driver, this);
    }

    public String AltID;

    public void submitAceEntitySearch(String entityKeyType, String entityKeyValue) throws Exception {
        String searchKeyValue = null;
        waitForPageLoad();

        moveToDefault();
        if (presentElement(this.benefitsPageElement.getRoutingNoErrPopupOKBtn())) {
            clickElement(this.benefitsPageElement.getRoutingNoErrPopupOKBtn());
        }

        conditionalWait(this.basePageElement.getSearchFilter(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeClickable");
        this.clickElement(this.basePageElement.getSearchFilter());
        conditionalWait(this.basePageElement.getSearchFilter(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeClickable");
        this.fieldKeyInput(this.basePageElement.getSearchFilter(), Keys.BACK_SPACE);
        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeClickable");
        this.fieldKeyInput(this.basePageElement.getSearchFilter(), entityKeyType);
        this.fieldKeyInput(this.basePageElement.getSearchFilter(), Keys.ENTER);
        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeClickable");

        conditionalWait(this.basePageElement.getSearchLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getSearchLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getSearchLocator(), "elementToBeClickable");
        this.fieldKeyInput(this.basePageElement.getSearchLocator(), entityKeyValue);
        this.fieldKeyInput(this.basePageElement.getSearchLocator(), Keys.ENTER);
        waitForPageLoad();
        conditionalWait(this.basePageElement.getSearchLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getSearchLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeClickable");

//Added to select correct entity (Contract ID) among multiple options
        if (entityKeyType.equalsIgnoreCase("Contract")) {
            if (elementExists("//div[contains(@id,'v3-widget-SEARCH-v3page-window')]//div[contains(text(),'Search')]")){
                Thread.sleep(5000);
                String dynamicContractID="//div//span[text()='"+entityKeyValue+"']";
                //WebElement contractID = this.driver.findElement(By.xpath("//div//span[text()='"+entityKeyValue+"']"));

                if(this.findElements(dynamicContractID).size()>0) {
                    this.clickElement(dynamicContractID);
                }
                moveToDefault();
            }
        }
			Below Code is ramdomly picking any application as Xpath is not presice and unique

			if (presentElement(this.benefitsPageElement.getAceSearchedApplication())) {
				conditionalWait(this.benefitsPageElement.getAceSearchedApplication(), "presenceOfElementLocated");
				conditionalWait(this.benefitsPageElement.getAceSearchedApplication(), "elementToBeVisible");
				clickJavascriptExecutor(this.benefitsPageElement.getAceSearchedApplication());
			}

    }

    public boolean isAtBasePage() throws InterruptedException {
        waitForPageLoad();
        conditionalWait(this.basePageElement.getSearchLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getSearchLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getSearchLocator(), "elementToBeClickable");
        return elementExists(this.basePageElement.getSearchLocator());
    }

    public void listSelector(String frame, String locatorXpath) throws InterruptedException {
        switchToFrame(frame);
        conditionalWait(this.basePageElement.getListSelectorLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getListSelectorLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getListSelectorLocator(), "elementToBeClickable");
        this.clickElement(this.basePageElement.getListSelectorLocator());

        conditionalWait(locatorXpath, "presenceOfElementLocated");
        conditionalWait(locatorXpath, "elementToBeVisible");
        conditionalWait(locatorXpath, "elementToBeClickable");
        this.clickElement(locatorXpath);

        moveToDefault();
    }

    public void clickApply() throws InterruptedException {
        conditionalWait(this.basePageElement.getApplyButtonLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getApplyButtonLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getApplyButtonLocator(), "elementToBeClickable");
        this.clickElement(this.basePageElement.getApplyButtonLocator());
        conditionalWait(this.basePageElement.getApplyButtonLocator(), "elementToBeClickable");
    }

    public void closeNotification() throws InterruptedException {
        if (elementExists(this.basePageElement.getNotificationCloseLocator())) {
            getElementByXpath(this.basePageElement.getNotificationCloseLocator()).click();
        }
    }

    public void clickSave() throws InterruptedException {
        conditionalWait(this.basePageElement.getSaveButtonLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getSaveButtonLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getSaveButtonLocator(), "elementToBeClickable");
        this.clickElement(this.basePageElement.getSaveButtonLocator());
        conditionalWait(this.basePageElement.getSaveButtonLocator(), "elementToBeClickable");
        conditionalWait(this.basePageElement.getSaveButtonLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getSaveButtonLocator(), "elementToBeVisible");

    }

    public void clickStatusCode() throws InterruptedException {
        driver.switchTo().frame("tapestry");
        conditionalWait(this.statuscodeElement.getStatusCodesTab(), "presenceOfElementLocated");
        conditionalWait(this.statuscodeElement.getStatusCodesTab(), "elementToBeVisible");
        conditionalWait(this.statuscodeElement.getStatusCodesTab(), "elementToBeClickable");
        this.clickElement(this.statuscodeElement.getStatusCodesTab());
    }

    public void goToContractsandCerts() throws InterruptedException {
        conditionalWait(this.benefitsPageElement.getContractlink(), "presenceOfElementLocated");
        conditionalWait(this.benefitsPageElement.getContractlink(), "elementToBeVisible");
        conditionalWait(this.benefitsPageElement.getContractlink(), "elementToBeClickable");

        this.clickElement(this.benefitsPageElement.getContractlink());

    }

    public void aceBusinessSelection(String businessFlow) throws Exception {
        moveToDefault();
        waitForPageLoad();
        submitAceEntitySearch("Annuitant", "5092688107");
        waitForPageLoad();
        moveToDefault();
        conditionalWait(this.basePageElement.getPlusSignLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getPlusSignLocator(), "elementToBeVisible");
        this.clickElement(this.basePageElement.getPlusSignLocator());
        waitForPageLoad();

        if (businessFlow.contains("IIA")) {

            conditionalWait(this.basePageElement.getBusinessSelector(), "presenceOfElementLocated");
            conditionalWait(this.basePageElement.getBusinessSelector(), "elementToBeVisible");
            conditionalWait(this.basePageElement.getBusinessSelector(), "elementToBeClickable");
            selectOption(this.basePageElement.getBusinessSelector(), "IIA Organization");
        } else if (businessFlow.equalsIgnoreCase("Annuitant")) {
            conditionalWait(this.basePageElement.getBusinessSelector(), "presenceOfElementLocated");
            conditionalWait(this.basePageElement.getBusinessSelector(), "elementToBeVisible");
            conditionalWait(this.basePageElement.getBusinessSelector(), "elementToBeClickable");
            Thread.sleep(5000);
            selectOption(this.basePageElement.getBusinessSelector(), "Annuitant");
            Thread.sleep(3000);
        } else if (businessFlow.equalsIgnoreCase("CO")) {
            conditionalWait(this.basePageElement.getBusinessSelector(), "presenceOfElementLocated");
            conditionalWait(this.basePageElement.getBusinessSelector(), "elementToBeVisible");
            conditionalWait(this.basePageElement.getBusinessSelector(), "elementToBeClickable");
            Thread.sleep(5000);
            selectOption(this.basePageElement.getBusinessSelector(), "Plan Sponsor");
            Thread.sleep(3000);


        }
        waitForPageLoad();
        conditionalWait(this.basePageElement.getPlusSignLocator(), "elementToBeInVisible");
        waitForPageLoad();
        conditionalWait(this.basePageElement.getApplyButtonLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getApplyButtonLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getApplyButtonLocator(), "elementToBeClickable");

    }

    public void aceBusinessSelection(String businessFlow, String AltID) throws Exception {
        moveToDefault();
        waitForPageLoad();
        Thread.sleep(5000);
        submitAceEntitySearch(businessFlow, AltID);
        Thread.sleep(10000);
        waitForPageLoad();
        moveToDefault();
        waitForPageLoad();
    }

    public void clickEdit() throws InterruptedException {
        waitForPageLoad();
        conditionalWait(this.basePageElement.getEditButtonLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getEditButtonLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getEditButtonLocator(), "elementToBeClickable");
        this.clickElement(this.basePageElement.getEditButtonLocator());
        //conditionalWait(this.basePageElement.getEditButtonLocator(), "presenceOfElementLocated");

    }

    public void goToContractCertsPage() throws InterruptedException {
        conditionalWait(this.basePageElement.getContractsNCertsLinkLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getContractsNCertsLinkLocator(), "elementToBeVisible");
        this.clickElement(this.basePageElement.getContractsNCertsLinkLocator());
    }

    public void logout() {
        try {
            moveToDefault();
            waitForPageLoad();
            conditionalWait(this.basePageElement.getProfileLocator(), "presenceOfElementLocated");
            conditionalWait(this.basePageElement.getProfileLocator(), "elementToBeVisible");
            clickElement(this.basePageElement.getProfileLocator());

            conditionalWait(this.basePageElement.getLogoutLocator(), "presenceOfElementLocated");
            conditionalWait(this.basePageElement.getLogoutLocator(), "elementToBeVisible");
            clickElement(this.basePageElement.getLogoutLocator());

            waitForPageLoad();
            clearCache();
        } catch (ElementClickInterceptedException e) {
            log.error("exception on logout, closing the browser");
        }

    }

    public void hideCRMPanel() throws InterruptedException {
        try {
            moveToDefault();
            waitForPageLoad();
            if (elementExists(this.basePageElement.getHideCRMPanel())) {
                this.clickElement(this.basePageElement.getHideCRMPanel());
            } else {
                log.info("CRM Panel is hidden");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public void showCRMPanel() throws InterruptedException {
        try {
            moveToDefault();
            waitForPageLoad();
            if (elementExists(this.basePageElement.getShowCRMPanel())) {
                this.clickElement(this.basePageElement.getShowCRMPanel());
            } else {
                log.info("CRM Panel is hidden");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public void goToMenuPagefromACEPage() throws InterruptedException {
        waitForPageLoad();
        Thread.sleep(5000);
        conditionalWait(this.basePageElement.getAcePageMenu(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getAcePageMenu(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getAcePageMenu(), "elementToBeClickable");
        waitForPageLoad();
        clickElement(this.basePageElement.getAcePageMenu());
        Thread.sleep(5000);
        waitForPageLoad();

    }

    public void handleCRMPanel() throws Exception {
        conditionalWait(this.basePageElement.getDashBoardLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getDashBoardLocator(), "elementToBeVisible");
        if (!presentElement(this.basePageElement.getShowCRMPanel())) {
            conditionalWait(this.basePageElement.getDashBoardLocator(), "elementToBeClickable");
            clickElement(this.basePageElement.getHideCRMPanel());
        }
    }

    public String searchesforQuery(String testCaseNo, String query) {

        try {

            String queryDetails = testQueryProperties.getQuerySql().get(query);
            log.info("ACE Details : " + queryDetails);
            if (query.equalsIgnoreCase("Deferred")) {
                List<Deferred> DeferredList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                DeferredList = DeferredRepo.getDeferred(queryDetails);// this

                log.info("ACE Details : " + DeferredList);

                for (Deferred deferrediterator : DeferredList) {
                    AltID = deferrediterator.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }
            }

            if (query.equalsIgnoreCase("AnnuitantBeneficiary")) {

                List<AnnuitantInPaywithBene> BeneList = new ArrayList<>();

                BeneList = Bene.getAnnuitantInPayBene(queryDetails);// this

                log.info("ACE Details : " + BeneList);

                for (AnnuitantInPaywithBene Beneiterator : BeneList) {
                    AltID = Beneiterator.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }

            } else if (query.equalsIgnoreCase("AnnuitantJointAnnuitantInPay")) {
                List<AnnuitantInPaywithJointAnnuitant> JointAnnuitantList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                JointAnnuitantList = JointAnnuitant.getAnnuitantInPayJointAnnuitant(queryDetails);// this

                log.info("ACE Details : " + JointAnnuitantList);

                for (AnnuitantInPaywithJointAnnuitant JointAnnuitantiterator : JointAnnuitantList) {
                    AltID = JointAnnuitantiterator.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }

            } else if (query.equalsIgnoreCase("PaymentPreviewReportLT") || query.equalsIgnoreCase("PaymentPreviewReportOR")) {
                List<PaymentPreviewReportLT> paymentPreviewReportLTList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                paymentPreviewReportLTList = PaymentPreviewReportLT.getPaymentPreviewReportLT(queryDetails);// this

                log.info("ACE Details : " + paymentPreviewReportLTList);

                for (PaymentPreviewReportLT PaymentPreviewReportLTiterator : paymentPreviewReportLTList) {
                    AltID = PaymentPreviewReportLTiterator.getGAC();
                    log.info("Annuitant's Alt Id : " + AltID);

                }

            } else if (query.equalsIgnoreCase("CaseActivation")) {
                List<CaseActivation> caseActivationList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                caseActivationList = caseActivationRepository.getCaseActivation(queryDetails);// this

                log.info("ACE Details : " + caseActivationList);

                for (CaseActivation caseActivation : caseActivationList) {
                    AltID = caseActivation.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }
            } else if (query.equalsIgnoreCase("QuoteInitiated")) {
                List<QuoteInitiated> QuoteInitiatedList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                QuoteInitiatedList = QuoteInitiatedRepository.getQuoteInitiated(queryDetails);// this

                log.info("ACE Details : " + QuoteInitiatedList);

                for (QuoteInitiated QuoteInitiatediterator : QuoteInitiatedList) {
                    AltID = QuoteInitiatediterator.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }
            } else if (query.equalsIgnoreCase("LifeAnnuity")) {
                List<LifeAnnuity> LifeAnnuityList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                LifeAnnuityList = Life.getLifeAnnuity(queryDetails);// this

                log.info("ACE Details : " + LifeAnnuityList);

                for (LifeAnnuity LifeAnnuityiterator : LifeAnnuityList) {
                    AltID = LifeAnnuityiterator.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }
            }else if (query.equalsIgnoreCase("InPayJointAnnuitant")) {
                List<InPayJointAnnuitant> InPayJointAnnuitantList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                InPayJointAnnuitantList = InPayJointAnnuitantRepository.getInPayJointAnnuitant(queryDetails);// this

                log.info("ACE Details : " + InPayJointAnnuitantList);

                for (InPayJointAnnuitant InPayJointAnnuitant : InPayJointAnnuitantList) {
                    AltID = InPayJointAnnuitant.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }
            }else if (query.equalsIgnoreCase("InPayAnnuitantBulkCheck")) {
                List<InPayAnnuitantBulkCheck> InPayAnnuitantBulkCheckList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                InPayAnnuitantBulkCheckList = InPayAnnuitantBulkCheckRepository.getInPayAnnuitantBulkCheck(queryDetails);// this

                log.info("ACE Details : " + InPayAnnuitantBulkCheckList);

                for (InPayAnnuitantBulkCheck InPayAnnuitantBulkCheckiterator : InPayAnnuitantBulkCheckList) {
                    AltID = InPayAnnuitantBulkCheckiterator.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }
            }else if (query.equalsIgnoreCase("deceasedLAAnnuitant")) {
                List<DeceasedLAAnnuitantDetails> deceasedLAAnnuitantDetailsList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);

                deceasedLAAnnuitantDetailsList = deceasedLAAnnuitantRepository.getDeceasedLAAnnuitantDetails(queryDetails);

                log.info("ACE Details : " + deceasedLAAnnuitantDetailsList);

                for (DeceasedLAAnnuitantDetails deceasedLAAnnuitantDetails : deceasedLAAnnuitantDetailsList) {
                    AltID = deceasedLAAnnuitantDetails.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }

            } else if (query.equalsIgnoreCase("livingLAAnnuitant")) {
                List<LivingLAAnnuitantDetails> livingLAAnnuitantDetailsList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);

                livingLAAnnuitantDetailsList = livingLAAnnuitantRepository.getLivingLAAnnuitantDetails(queryDetails);

                log.info("ACE Details : " + livingLAAnnuitantDetailsList);

                for (LivingLAAnnuitantDetails livingLAAnnuitantDetails : livingLAAnnuitantDetailsList) {
                    AltID = livingLAAnnuitantDetails.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }

            } else if (query.equalsIgnoreCase("deceasedIIAAnnuitant")) {
                List<DeceasedIIAAnnuitantDetails> deceasedIIAAnnuitantDetailsList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);

                deceasedIIAAnnuitantDetailsList = deceasedIIAAnnuitantRepository.getDeceasedIIAAnnuitantDetails(queryDetails);

                log.info("ACE Details : " + deceasedIIAAnnuitantDetailsList);

                for (DeceasedIIAAnnuitantDetails deceasedIIAAnnuitantDetails : deceasedIIAAnnuitantDetailsList) {
                    AltID = deceasedIIAAnnuitantDetails.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }
            }else if (query.equalsIgnoreCase("deathNotificationDetails")) {
                List<DeathNotificationDetails> deathNotificationDetailsList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                deathNotificationDetailsList = deathNotificationDetailsRepository.getDeathNotificationDetails(queryDetails);// this

                log.info("ACE Details : " + deathNotificationDetailsList);

                for (DeathNotificationDetails deathNotificationDetails : deathNotificationDetailsList) {
                    AltID = deathNotificationDetails.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }

            } else if (query.equalsIgnoreCase("livingSSAnnuitant")) {
                List<LivingSSAnnuitantDetails> livingSSAnnuitantDetailsList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);

                livingSSAnnuitantDetailsList = livingSSAnnuitantRepository.getLivingSSAnnuitantDetails(queryDetails);

                log.info("ACE Details : " + livingSSAnnuitantDetailsList);

                for (LivingSSAnnuitantDetails livingSSAnnuitantDetails : livingSSAnnuitantDetailsList) {
                    AltID = livingSSAnnuitantDetails.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }

            }else if (query.equalsIgnoreCase("InPayJointAnnuitantBulk")) {
                List<InPayJointAnnuitantBulk> InPayJointAnnuitantBulkList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                InPayJointAnnuitantBulkList = InPayJointAnnuitantBulkRepository.getInPayJointAnnuitantBulk(queryDetails);// this

                log.info("ACE Details : " + InPayJointAnnuitantBulkList);

                for (InPayJointAnnuitantBulk InPayJointAnnuitantBulk : InPayJointAnnuitantBulkList) {
                    AltID = InPayJointAnnuitantBulk.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }
            }else if (query.equalsIgnoreCase("QLACDeceasedAnnuitant")) {
                List<QLACDeceasedAnnuitant> QLACDeceasedAnnuitantList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                QLACDeceasedAnnuitantList = QLACDeceasedAnnuitantRepository.getQLACDeceasedAnnuitant(queryDetails);// this

                log.info("ACE Details : " + QLACDeceasedAnnuitantList);

                for (QLACDeceasedAnnuitant QLACDeceasedAnnuitant : QLACDeceasedAnnuitantList) {
                    AltID = QLACDeceasedAnnuitant.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }
            }else if (query.equalsIgnoreCase("AnnuitantBeneInPay1")) {
                List<AnnuitantBeneInPay1> AnnuitantBeneInPay1List = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                AnnuitantBeneInPay1List = AnnuitantBeneInPay1Repository.getAnnuitantBeneInPay1(queryDetails);// this

                log.info("ACE Details : " + AnnuitantBeneInPay1List);

                for (AnnuitantBeneInPay1 AnnuitantBeneInPay1 : AnnuitantBeneInPay1List) {
                    AltID = AnnuitantBeneInPay1.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }
            }else if (query.equalsIgnoreCase("AnnuitantBeneInPay1Bulk")) {
                List<AnnuitantBeneInPay1Bulk> AnnuitantBeneInPay1BulkList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                AnnuitantBeneInPay1BulkList = AnnuitantBeneInPay1BulkRepository.getAnnuitantBeneInPay1Bulk(queryDetails);// this

                log.info("ACE Details : " + AnnuitantBeneInPay1BulkList);

                for (AnnuitantBeneInPay1Bulk AnnuitantBeneInPay1Bulk : AnnuitantBeneInPay1BulkList) {
                    AltID = AnnuitantBeneInPay1Bulk.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }
            }else if (query.equalsIgnoreCase("DeferredBulk")) {
                List<DeferredBulk> DeferredBulkList = new ArrayList<>();

                // List parametersList = new ArrayList();
                // parametersList.add(Altid);
                DeferredBulkList = DeferredBulkRepo.getDeferredBulk(queryDetails);// this

                log.info("ACE Details : " + DeferredBulkList);

                for (DeferredBulk deferredBulkiterator : DeferredBulkList) {
                    AltID = deferredBulkiterator.getPersonAltId();
                    log.info("Annuitant's Alt Id : " + AltID);

                }
            }






            return AltID;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }


    public void EditContactDetails() throws InterruptedException {

        try {

            if (presentElement(this.annuiatantPageElement.getContactgearicon())) {

                scrollWindow(this.annuiatantPageElement.getContactgearicon());
                Thread.sleep(3000);
                moveToDefault();
                String phone = getElementByXpath(this.annuiatantPageElement.getContactinfobanner()).getText();
                log.info("Phone number" + phone);
                this.frameToBeAvailableAndSwitchToIt(this.annuiatantPageElement.getAnnuitantProfileIframe());
                if (presentElement(this.annuiatantPageElement.getPhonenumber())) {


                    conditionalWait(this.annuiatantPageElement.getContactgearicon(), "presenceOfElementLocated");
                    conditionalWait(this.annuiatantPageElement.getContactgearicon(), "elementToBeVisible");
                    conditionalWait(this.annuiatantPageElement.getContactgearicon(), "elementToBeClickable");
                    this.clickElement(this.annuiatantPageElement.getContactgearicon());

                    conditionalWait(this.annuiatantPageElement.getAddcontactinfo(), "presenceOfElementLocated");
                    conditionalWait(this.annuiatantPageElement.getAddcontactinfo(), "elementToBeVisible");
                    conditionalWait(this.annuiatantPageElement.getAddcontactinfo(), "elementToBeClickable");
                    this.clickElement(this.annuiatantPageElement.getAddcontactinfo());
                    moveToDefault();
                    conditionalWait(this.annuiatantPageElement.getEditPhonenumber(), "presenceOfElementLocated");
                    conditionalWait(this.annuiatantPageElement.getEditPhonenumber(), "elementToBeVisible");
                    conditionalWait(this.annuiatantPageElement.getEditPhonenumber(), "elementToBeClickable");
                    getElementByXpath(this.annuiatantPageElement.getEditPhonenumber()).sendKeys(Keys.CONTROL + "A");
                    getElementByXpath(this.annuiatantPageElement.getEditPhonenumber()).sendKeys(Keys.BACK_SPACE);
                    getElementByXpath(this.annuiatantPageElement.getEditPhonenumber()).sendKeys("7655501233");

                    if (presentElement(this.annuiatantPageElement.getAlternatePhoneNumber()) || !(this.annuiatantPageElement.getAlternatePhoneNumber()).equals("")) {
                        conditionalWait(this.annuiatantPageElement.getAlternatePhoneNumber(), "presenceOfElementLocated");
                        conditionalWait(this.annuiatantPageElement.getAlternatePhoneNumber(), "elementToBeVisible");
                        conditionalWait(this.annuiatantPageElement.getAlternatePhoneNumber(), "elementToBeClickable");
                        getElementByXpath(this.annuiatantPageElement.getAlternatePhoneNumber()).sendKeys(Keys.CONTROL + "A");
                        getElementByXpath(this.annuiatantPageElement.getAlternatePhoneNumber()).sendKeys(Keys.BACK_SPACE);
                        getElementByXpath(this.annuiatantPageElement.getAlternatePhoneNumber()).sendKeys("7659991254");
                    }


                    conditionalWait(this.annuiatantPageElement.getContactpreference(), "presenceOfElementLocated");
                    conditionalWait(this.annuiatantPageElement.getContactpreference(), "elementToBeVisible");
                    conditionalWait(this.annuiatantPageElement.getContactpreference(), "elementToBeClickable");
                    getElementByXpath(this.annuiatantPageElement.getContactpreference()).sendKeys(Keys.CONTROL + "A");
                    getElementByXpath(this.annuiatantPageElement.getContactpreference()).sendKeys(Keys.BACK_SPACE);
                    getElementByXpath(this.annuiatantPageElement.getContactpreference()).sendKeys("Phone");

                    conditionalWait(this.annuiatantPageElement.getOkbuttoncontactinfo(), "presenceOfElementLocated");
                    conditionalWait(this.annuiatantPageElement.getOkbuttoncontactinfo(), "elementToBeVisible");
                    conditionalWait(this.annuiatantPageElement.getOkbuttoncontactinfo(), "elementToBeClickable");
                    this.clickElement(this.annuiatantPageElement.getOkbuttoncontactinfo());
                    moveToDefault();

                    conditionalWait(this.basePageElement.getApplyButtonLocator(), "presenceOfElementLocated");
                    conditionalWait(this.basePageElement.getApplyButtonLocator(), "elementToBeVisible");
                    conditionalWait(this.basePageElement.getApplyButtonLocator(), "elementToBeClickable");
                    this.clickElement(this.basePageElement.getApplyButtonLocator());
                    waitForPageLoad();
                    Thread.sleep(2000);
                    this.frameToBeAvailableAndSwitchToIt(this.annuiatantPageElement.getAnnuitantProfileIframe());
                    if (presentElement(this.benefitsPageElement.getAceUICaseADialogBoxOk())) {

                        conditionalWait(this.benefitsPageElement.getAceUICaseADialogBoxOk(), "presenceOfElementLocated");
                        conditionalWait(this.benefitsPageElement.getAceUICaseADialogBoxOk(), "elementToBeVisible");
                        conditionalWait(this.benefitsPageElement.getAceUICaseADialogBoxOk(), "elementToBeClickable");
                        this.clickElement(this.benefitsPageElement.getAceUICaseADialogBoxOk());
                    }
                    moveToDefault();

                }


                //scrollWindow(this.annuiatantPageElement.getContactgearicon());
                Thread.sleep(3000);
                moveToDefault();
                String email = getElementByXpath(this.annuiatantPageElement.getEmailcontactpreferencebanner()).getText();
                this.frameToBeAvailableAndSwitchToIt(this.annuiatantPageElement.getAnnuitantProfileIframe());

                if (presentElement(this.annuiatantPageElement.getEmailcontactpreference())) {
                    String ContactPreference = getElementByXpath(this.annuiatantPageElement.getEmailcontactpreference()).getText();
                    log.info("Primary Email Address and Contact Preference:" + email + " " + ContactPreference);
                }

                String ContactPreference = "Email";

                if ((email.contains(".com") || ContactPreference.contains("Email")) && phone.equals("")) {

                    log.info("No PHONE");
                    conditionalWait(this.annuiatantPageElement.getContactgearicon(), "presenceOfElementLocated");
                    conditionalWait(this.annuiatantPageElement.getContactgearicon(), "elementToBeVisible");
                    conditionalWait(this.annuiatantPageElement.getContactgearicon(), "elementToBeClickable");
                    this.clickElement(this.annuiatantPageElement.getContactgearicon());

                    conditionalWait(this.annuiatantPageElement.getAddcontactinfo(), "presenceOfElementLocated");
                    conditionalWait(this.annuiatantPageElement.getAddcontactinfo(), "elementToBeVisible");
                    conditionalWait(this.annuiatantPageElement.getAddcontactinfo(), "elementToBeClickable");
                    this.clickElement(this.annuiatantPageElement.getAddcontactinfo());
                    moveToDefault();
                    conditionalWait(this.annuiatantPageElement.getEditPhonenumber(), "presenceOfElementLocated");
                    conditionalWait(this.annuiatantPageElement.getEditPhonenumber(), "elementToBeVisible");
                    conditionalWait(this.annuiatantPageElement.getEditPhonenumber(), "elementToBeClickable");
                    getElementByXpath(this.annuiatantPageElement.getEditPhonenumber()).sendKeys(Keys.CONTROL + "A");
                    getElementByXpath(this.annuiatantPageElement.getEditPhonenumber()).sendKeys(Keys.BACK_SPACE);
                    getElementByXpath(this.annuiatantPageElement.getEditPhonenumber()).sendKeys("7655501233");

                    conditionalWait(this.annuiatantPageElement.getContactpreference(), "presenceOfElementLocated");
                    conditionalWait(this.annuiatantPageElement.getContactpreference(), "elementToBeVisible");
                    conditionalWait(this.annuiatantPageElement.getContactpreference(), "elementToBeClickable");
                    getElementByXpath(this.annuiatantPageElement.getContactpreference()).sendKeys(Keys.CONTROL + "A");
                    getElementByXpath(this.annuiatantPageElement.getContactpreference()).sendKeys(Keys.BACK_SPACE);
                    getElementByXpath(this.annuiatantPageElement.getContactpreference()).sendKeys("Phone");

                    conditionalWait(this.annuiatantPageElement.getOkbuttoncontactinfo(), "presenceOfElementLocated");
                    conditionalWait(this.annuiatantPageElement.getOkbuttoncontactinfo(), "elementToBeVisible");
                    conditionalWait(this.annuiatantPageElement.getOkbuttoncontactinfo(), "elementToBeClickable");
                    this.clickElement(this.annuiatantPageElement.getOkbuttoncontactinfo());

                    conditionalWait(this.basePageElement.getApplyButtonLocator(), "presenceOfElementLocated");
                    conditionalWait(this.basePageElement.getApplyButtonLocator(), "elementToBeVisible");
                    conditionalWait(this.basePageElement.getApplyButtonLocator(), "elementToBeClickable");
                    this.clickElement(this.basePageElement.getApplyButtonLocator());
                    waitForPageLoad();
                    Thread.sleep(2000);
                    moveToDefault();


                }


            } else {
                scrollWindow(this.annuiatantPageElement.getAddNocontactinfo());
                conditionalWait(this.annuiatantPageElement.getAddNocontactinfo(), "presenceOfElementLocated");
                conditionalWait(this.annuiatantPageElement.getAddNocontactinfo(), "elementToBeVisible");
                conditionalWait(this.annuiatantPageElement.getAddNocontactinfo(), "elementToBeClickable");
                this.clickElement(this.annuiatantPageElement.getAddNocontactinfo());
                moveToDefault();
                conditionalWait(this.annuiatantPageElement.getEditPhonenumber(), "presenceOfElementLocated");
                conditionalWait(this.annuiatantPageElement.getEditPhonenumber(), "elementToBeVisible");
                conditionalWait(this.annuiatantPageElement.getEditPhonenumber(), "elementToBeClickable");
                getElementByXpath(this.annuiatantPageElement.getEditPhonenumber()).sendKeys(Keys.CONTROL + "A");
                getElementByXpath(this.annuiatantPageElement.getEditPhonenumber()).sendKeys(Keys.BACK_SPACE);
                getElementByXpath(this.annuiatantPageElement.getEditPhonenumber()).sendKeys("7655501233");

                conditionalWait(this.annuiatantPageElement.getContactpreference(), "presenceOfElementLocated");
                conditionalWait(this.annuiatantPageElement.getContactpreference(), "elementToBeVisible");
                conditionalWait(this.annuiatantPageElement.getContactpreference(), "elementToBeClickable");
                getElementByXpath(this.annuiatantPageElement.getContactpreference()).sendKeys("Phone");

                conditionalWait(this.annuiatantPageElement.getOkbuttoncontactinfo(), "presenceOfElementLocated");
                conditionalWait(this.annuiatantPageElement.getOkbuttoncontactinfo(), "elementToBeVisible");
                conditionalWait(this.annuiatantPageElement.getOkbuttoncontactinfo(), "elementToBeClickable");
                this.clickElement(this.annuiatantPageElement.getOkbuttoncontactinfo());



                conditionalWait(this.basePageElement.getApplyButtonLocator(), "presenceOfElementLocated");
                conditionalWait(this.basePageElement.getApplyButtonLocator(), "elementToBeVisible");
                conditionalWait(this.basePageElement.getApplyButtonLocator(), "elementToBeClickable");
                this.clickElement(this.basePageElement.getApplyButtonLocator());


                waitForPageLoad();
                Thread.sleep(3000);
                moveToDefault();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    public void clickCancel() throws InterruptedException {
        moveToDefault();
        conditionalWait(this.basePageElement.getSaveButtonLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getSaveButtonLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getSaveButtonLocator(), "elementToBeClickable");
        conditionalWait(this.basePageElement.getCancelButtonLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getCancelButtonLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getCancelButtonLocator(), "elementToBeClickable");
        this.clickElement(this.basePageElement.getCancelButtonLocator());
        // switchToFrame(this.statusCodesPageElement.getIframeId());
        conditionalWait(this.basePageElement.getCancelOKButton(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getCancelOKButton(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getCancelOKButton(), "elementToBeClickable");
        this.clickElement(this.basePageElement.getCancelOKButton());

        waitForPageLoad();
        conditionalWait(this.basePageElement.getEditButtonLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getEditButtonLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getEditButtonLocator(), "elementToBeClickable");
    }

    public void navigateBatch(String testCaseNo) throws Exception {
        moveToDefault();
        conditionalWait(this.basePageElement.getHamburgerMenu(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getHamburgerMenu(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getHamburgerMenu(), "elementToBeClickable");
        this.clickElement(this.basePageElement.getHamburgerMenu());

        conditionalWait(this.basePageElement.getTools(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getTools(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getTools(), "elementToBeClickable");
        this.clickElement(this.basePageElement.getTools());

        conditionalWait(this.basePageElement.getBatchLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getBatchLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getBatchLocator(), "elementToBeClickable");
        captureScreenShoot(testCaseNo, "User clicks on Batch");
        this.clickElement(this.basePageElement.getBatchLocator());

    }

    public boolean validateNotice(String noticeTitle) throws Exception {
        boolean noticeVisibility;

        if (!getElementByXpath(this.basePageElement.getDocumentsLocator()).isDisplayed()) {
            clickTheElementByXpath(this.basePageElement.getDoubleArrowLocator());
            conditionalWait(this.basePageElement.getCrmLocator(), "presenceOfElementLocated");
            conditionalWait(this.basePageElement.getCrmLocator(), "elementToBeVisible");
            waitForPageLoad();
        }

        conditionalWait(this.basePageElement.getDocumentsLocator(), "elementToBeClickable");
        clickTheElementByXpath(this.basePageElement.getDocumentsLocator());

        if (elementExists(this.basePageElement.getNotificationCloseLocator())) {
            conditionalWait(this.basePageElement.getNoticeLocator(), "presenceOfElementLocated");
            conditionalWait(this.basePageElement.getNoticeLocator(), "elementToBeVisible");
            conditionalWait(this.basePageElement.getNoticeLocator(), "elementToBeClickable");
            noticeVisibility = true;
        } else {
            noticeVisibility = false;
        }

        return noticeVisibility;

    }


    public void search_Person(String personID) throws Exception {
        waitForPageLoad();
        conditionalWait(this.basePageElement.getSearchFilter(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeClickable");
        clickElement(this.basePageElement.getSearchFilter());

        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeClickable");
        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeClickable");

        sendInput(this.basePageElement.getSearchFilter(), Keys.CONTROL + "A");
        sendInput(this.basePageElement.getSearchFilter(), Keys.BACK_SPACE);
        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeClickable");


        Thread.sleep(1000);
        sendInput(this.basePageElement.getSearchFilter(), Keys.ENTER);

        Thread.sleep(2000);
        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeClickable");
        searchValue(personID);

    }

    public void searchValue(String val) throws Exception {
        conditionalWait(this.basePageElement.getSearchLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getSearchLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getSearchLocator(), "elementToBeClickable");
        sendInput(this.basePageElement.getSearchLocator(), val);
        Thread.sleep(1000);


        sendInput(this.basePageElement.getSearchLocator(), Keys.ENTER);
        Thread.sleep(1000);
        waitForPageLoad();
        conditionalWait(this.basePageElement.getSearchLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getSearchLocator(), "elementToBeVisible");
        waitForPageLoad();

        conditionalWait(this.basePageElement.getSearchFilter(), "elementToBeClickable");

    }

    public void navigateReports(String testCaseNo) throws Exception {
        conditionalWait(this.basePageElement.getHamburgerMenu(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getHamburgerMenu(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getHamburgerMenu(), "elementToBeClickable");
        this.clickElement(this.basePageElement.getHamburgerMenu());

        conditionalWait(this.basePageElement.getTools(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getTools(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getTools(), "elementToBeClickable");
        this.clickElement(this.basePageElement.getTools());

        conditionalWait(this.basePageElement.getReportsLocator(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getReportsLocator(), "elementToBeVisible");
        conditionalWait(this.basePageElement.getReportsLocator(), "elementToBeClickable");
        captureScreenShoot(testCaseNo, "User clicks on Reports");
        this.clickElement(this.basePageElement.getReportsLocator());
        log.info("User on ACE Reports page");

    }

    public void handleConfirmDialogBox() throws Exception {
        if (findElements(this.annuiatantPageElement.getConfirmDialogBox()).size() != 0) {
            log.info("Confirm Dialog Box found");
            conditionalWait(this.annuiatantPageElement.getConfirmOkButton(), "presenceOfElementLocated");
            conditionalWait(this.annuiatantPageElement.getConfirmOkButton(), "elementToBeVisible");
            conditionalWait(this.annuiatantPageElement.getConfirmOkButton(), "elementToBeClickable");
            this.clickElement(this.annuiatantPageElement.getConfirmOkButton());
            this.waitForPageLoad();
            Thread.sleep(2000);

        } else {
            log.info("Confirm Dialog Box not found");
        }

    }

    public void handleNRA(String testCaseNo) throws Exception {
        Thread.sleep(3000);
        moveToDefault();
        if ((this.findElements(this.benefitsPageElement.getErrorDialogBox()).size() > 0)) {
            log.info("Error message found ");
            conditionalWait(this.benefitPageElementexd.getErrormessage(), "presenceOfElementLocated");
            conditionalWait(this.benefitPageElementexd.getErrormessage(), "elementToBeVisible");
            conditionalWait(this.benefitPageElementexd.getErrormessage(), "elementToBeClickable");
            String errormessage = getElementByXpath(this.benefitPageElementexd.getErrormessage()).getText();
            if (errormessage.contains("Federal Tax withholding is not allowed for Non-Resident Alien (NRA)")) {
                log.info("Fed Tax found for a NRA data ");

                conditionalWait(this.benefitPageElementexd.getErrorok(), "presenceOfElementLocated");
                conditionalWait(this.benefitPageElementexd.getErrorok(), "elementToBeVisible");
                conditionalWait(this.benefitPageElementexd.getErrorok(), "elementToBeClickable");
                this.clickElement(this.benefitPageElementexd.getErrorok());
                popuphandler();

                this.waitForPageLoad();

                conditionalWait(this.basePageElement.getBenefitsLink(), "presenceOfElementLocated");
                conditionalWait(this.basePageElement.getBenefitsLink(), "elementToBeVisible");
                conditionalWait(this.basePageElement.getBenefitsLink(), "elementToBeClickable");
                this.clickElement(this.basePageElement.getBenefitsLink());

                waitForPageLoad();

                // Go to the Benefits tabs

                switchToFrame(this.benefitsPageElement.getIframeId());

                conditionalWait(this.benefitsPageElement.getPaymentSetupLocator(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getPaymentSetupLocator(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getPaymentSetupLocator(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getPaymentSetupLocator());

                waitForPageLoad();

                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "presenceOfElementLocated");
                scrollWindow(this.benefitsPageElement.getDeductionCogwheel());
                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getDeductionCogwheel());

                conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getPaymentDeductionEditDetail());

                moveToDefault();

                switchToFrame("DisbursementPages_DeductionTemplatePopupPage");


                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "elementToBeClickable");
                selectDDByVisibleText(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "Local Tax");


                conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getModifypaymentokbutton());

                moveToDefault();
                this.goToContractCertsPage();
                this.waitForPageLoad();
                this.clickElement(this.basePageElement.getBenefitsLink());
                this.waitForPageLoad();
                this.goToContractCertsPage();
                this.waitForPageLoad();
                this.clickStatusCode();
                this.waitForPageLoad();
                moveToDefault();
                this.clickSave();

            } else if (errormessage.contains("Non-Resident Alien (NRA) tax recipients who reside in a Treaty Country should have a NRA Tax Deduction")) {

                log.info("Fed Tax found for a NRA-Treaty data ");
                this.clickElement(this.benefitPageElementexd.getErrorok());
                popuphandler();

                this.waitForPageLoad();

                conditionalWait(this.basePageElement.getBenefitsLink(), "presenceOfElementLocated");
                conditionalWait(this.basePageElement.getBenefitsLink(), "elementToBeVisible");
                conditionalWait(this.basePageElement.getBenefitsLink(), "elementToBeClickable");
                this.clickElement(this.basePageElement.getBenefitsLink());

                waitForPageLoad();

                // Go to the Benefits tabs

                switchToFrame(this.benefitsPageElement.getIframeId());

                conditionalWait(this.benefitsPageElement.getPaymentSetupLocator(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getPaymentSetupLocator(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getPaymentSetupLocator(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getPaymentSetupLocator());

                waitForPageLoad();

                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "presenceOfElementLocated");
                scrollWindow(this.benefitsPageElement.getDeductionCogwheel());
                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getDeductionCogwheel());

                conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getPaymentDeductionEditDetail());

                moveToDefault();

                switchToFrame("DisbursementPages_DeductionTemplatePopupPage");


                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "elementToBeClickable");
                selectDDByVisibleText(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "Non-Resident Alien Tax");


                conditionalWait(this.benefitPageElementexd.getExpandlocator(), "presenceOfElementLocated");
                conditionalWait(this.benefitPageElementexd.getExpandlocator(), "elementToBeVisible");
                conditionalWait(this.benefitPageElementexd.getExpandlocator(), "elementToBeClickable");
                clickElement(this.benefitPageElementexd.getExpandlocator());

                conditionalWait(this.benefitPageElementexd.getErrormessagecountry(), "presenceOfElementLocated");
                conditionalWait(this.benefitPageElementexd.getErrormessagecountry(), "elementToBeVisible");
                conditionalWait(this.benefitPageElementexd.getErrormessagecountry(), "elementToBeClickable");
                selectDDByVisibleText(this.benefitPageElementexd.getErrormessagecountry(), "Canada");

                conditionalWait(this.benefitsPageElement.getAdditonalAmountValueDeduction(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getAdditonalAmountValueDeduction(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getAdditonalAmountValueDeduction(), "elementToBeClickable");
                getElementByXpath(this.benefitsPageElement.getAdditonalAmountValueDeduction()).sendKeys(Keys.CONTROL + "A");
                getElementByXpath(this.benefitsPageElement.getAdditonalAmountValueDeduction()).sendKeys(Keys.BACK_SPACE);

                conditionalWait(this.benefitsPageElement.getAdditonalPercentValueDeduction(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getAdditonalPercentValueDeduction(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getAdditonalPercentValueDeduction(), "elementToBeClickable");
                getElementByXpath(this.benefitsPageElement.getAdditonalPercentValueDeduction())
                        .sendKeys("0");


                if (isElementSelected(this.benefitsPageElement.getNewUseTable()) == false) {
                }
                clickElement(this.benefitsPageElement.getNewUseTable());


                conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getModifypaymentokbutton());

                moveToDefault();
                this.goToContractCertsPage();
                this.waitForPageLoad();
                this.clickElement(this.basePageElement.getBenefitsLink());
                this.waitForPageLoad();
                this.goToContractCertsPage();
                this.waitForPageLoad();
                this.clickStatusCode();
                this.waitForPageLoad();
                moveToDefault();
                this.clickSave();

            } else if (errormessage.contains("State Tax is required on Payment Stream with")) {
                log.info("Stat tax error found  ");
                {
                    this.clickElement(this.benefitPageElementexd.getErrorok());
                    popuphandler();

                    this.waitForPageLoad();

                    conditionalWait(this.basePageElement.getBenefitsLink(), "presenceOfElementLocated");
                    conditionalWait(this.basePageElement.getBenefitsLink(), "elementToBeVisible");
                    conditionalWait(this.basePageElement.getBenefitsLink(), "elementToBeClickable");
                    this.clickElement(this.basePageElement.getBenefitsLink());

                    waitForPageLoad();

                    // Go to the Benefits tabs

                    switchToFrame(this.benefitsPageElement.getIframeId());

                    conditionalWait(this.benefitsPageElement.getPaymentSetupLocator(), "presenceOfElementLocated");
                    conditionalWait(this.benefitsPageElement.getPaymentSetupLocator(), "elementToBeVisible");
                    conditionalWait(this.benefitsPageElement.getPaymentSetupLocator(), "elementToBeClickable");
                    clickElement(this.benefitsPageElement.getPaymentSetupLocator());

                    waitForPageLoad();

                    conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "presenceOfElementLocated");
                    scrollWindow(this.benefitsPageElement.getDeductionCogwheel());
                    conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeVisible");
                    conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeClickable");
                    clickElement(this.benefitsPageElement.getDeductionCogwheel());

                    conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "presenceOfElementLocated");
                    conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "elementToBeVisible");
                    conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "elementToBeClickable");
                    clickElement(this.benefitsPageElement.getPaymentDeductionEditDetail());

                    moveToDefault();

                    switchToFrame("DisbursementPages_DeductionTemplatePopupPage");
                    conditionalWait(this.benefitsPageElement.getEditDetails_cogwheel(), "presenceOfElementLocated");
                    conditionalWait(this.benefitsPageElement.getEditDetails_cogwheel(), "elementToBeVisible");
                    conditionalWait(this.benefitsPageElement.getEditDetails_cogwheel(), "elementToBeClickable");
                    clickElement(this.benefitsPageElement.getEditDetails_cogwheel());

                    conditionalWait(this.benefitsPageElement.getEditDetails_Add(), "presenceOfElementLocated");
                    conditionalWait(this.benefitsPageElement.getEditDetails_Add(), "elementToBeVisible");
                    conditionalWait(this.benefitsPageElement.getEditDetails_Add(), "elementToBeClickable");
                    clickElement(this.benefitsPageElement.getEditDetails_Add());


                    conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "presenceOfElementLocated");
                    conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "elementToBeVisible");
                    conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "elementToBeClickable");
                    selectDDByVisibleText(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "State Tax");

                    if (getElementByXpath(this.benefitsPageElement.getEditDetails_UseTable()).isSelected()) {
                        clickElement(this.benefitsPageElement.getEditDetails_UseTable());
                    }

                    conditionalWait(this.benefitsPageElement.getAdditonalPercentValueDeduction(), "presenceOfElementLocated");
                    conditionalWait(this.benefitsPageElement.getAdditonalPercentValueDeduction(), "elementToBeVisible");
                    conditionalWait(this.benefitsPageElement.getAdditonalPercentValueDeduction(), "elementToBeClickable");
                    getElementByXpath(this.benefitsPageElement.getAdditonalPercentValueDeduction())
                            .sendKeys("10");


                    conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "presenceOfElementLocated");
                    conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "elementToBeVisible");
                    conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "elementToBeClickable");
                    clickElement(this.benefitsPageElement.getModifypaymentokbutton());

                    moveToDefault();
                    this.goToContractCertsPage();
                    this.waitForPageLoad();
                    this.clickElement(this.basePageElement.getBenefitsLink());
                    this.waitForPageLoad();
                    this.goToContractCertsPage();
                    this.waitForPageLoad();
                    this.clickStatusCode();
                    this.waitForPageLoad();
                    moveToDefault();
                    this.clickSave();
                }
            }

            else if (errormessage.contains("Federal Tax is required and should be 30% on Payment Stream")) {

                log.info("Federal Tax is required  ");
                this.clickElement(this.benefitPageElementexd.getErrorok());
                popuphandler();

                this.waitForPageLoad();
                log.info("Federal Tax is required and should be 30% on Payment Stream ");


                conditionalWait(this.basePageElement.getBenefitsLink(), "presenceOfElementLocated");
                conditionalWait(this.basePageElement.getBenefitsLink(), "elementToBeVisible");
                conditionalWait(this.basePageElement.getBenefitsLink(), "elementToBeClickable");
                this.clickElement(this.basePageElement.getBenefitsLink());

                waitForPageLoad();

                // Go to the Benefits tabs

                switchToFrame(this.benefitsPageElement.getIframeId());

                conditionalWait(this.benefitsPageElement.getPaymentSetupLocator(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getPaymentSetupLocator(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getPaymentSetupLocator(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getPaymentSetupLocator());

                waitForPageLoad();


                conditionalWait(this.benefitsPageElement.getGrossAmount(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getGrossAmount(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getGrossAmount(), "elementToBeClickable");
                this.scrollWindow(this.benefitsPageElement.getGrossAmount());
                String Amount = this.getElementByXpath(benefitsPageElement.getGrossAmount()).getAttribute("value");

                log.info(Amount);
                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "presenceOfElementLocated");
                scrollWindow(this.benefitsPageElement.getDeductionCogwheel());
                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getDeductionCogwheel());

                conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getPaymentDeductionEditDetail());

                moveToDefault();

                switchToFrame("DisbursementPages_DeductionTemplatePopupPage");
                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "elementToBeClickable");


                List<WebElement> deductionTypes = findElements(this.benefitsPageElement.getEditDetails_DeductionTypeDD());
                List<WebElement> stopDates = findElements(this.basePageElement.getPaymentDedStopDate());
                List<WebElement> startDates = findElements(this.basePageElement.getPaymentDedStartDate());
                List<WebElement> deductionAmounts = findElements(this.benefitsPageElement.getDeduction_Additional_Amount_locator());
                List<WebElement> expandButton = findElements(this.basePageElement.getPaymentDedExpand());
                List<WebElement> taxPercentages = findElements(this.benefitsPageElement.getEditDetailsTaxPercentageLocator());
                List<WebElement> percentOf = findElements(this.basePageElement.getPaymentDedPercentOf());
                List<WebElement> filingstatus = findElements(this.basePageElement.getTaxfilingstatus());
                List<WebElement> usetable = findElements(this.benefitsPageElement.getEditDetails_UseTable());
                List<WebElement> exemption = findElements(this.basePageElement.getTaxexemption());


                for (int i = 0; i < deductionTypes.size(); i++) {
                    Select select = new Select(deductionTypes.get(i));
                    WebElement selectedOption = select.getFirstSelectedOption();
                    String deductionType = selectedOption.getText();
                    if (deductionType.contains("State")) {

                        System.out.println("Adding State Tax");
                        deductionAmounts.get(i).sendKeys(Keys.CONTROL + "A");
                        deductionAmounts.get(i).sendKeys(Keys.BACK_SPACE);
                        expandButton.get(i).click();
                        Thread.sleep(2000);
                        taxPercentages.get(i).sendKeys(Keys.CONTROL + "A");
                        taxPercentages.get(i).sendKeys(Keys.BACK_SPACE);
                        taxPercentages.get(i).sendKeys("7");
                        Select dd = new Select(percentOf.get(i));
                        dd.selectByVisibleText("Federal Withholding");
                        expandButton.get(i).click();

                    }
                    if (deductionType.contains("Federal")) {
                        String startDateText = startDates.get(i).getAttribute("value").replace("/", "");
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
                        LocalDate startDate = LocalDate.parse(startDateText, dtf);
                        LocalDate stopDate = startDate.plusDays(30);
                        String stopDateText = stopDate.format(dtf);
                        stopDates.get(i).sendKeys(Keys.CONTROL + "A");
                        stopDates.get(i).sendKeys(Keys.BACK_SPACE);
                        stopDates.get(i).sendKeys(stopDateText);
                        if (usetable.get(i).isSelected()) {
                            usetable.get(i).click();
                        }
                        taxPercentages.get(i).sendKeys(Keys.CONTROL + "A");
                        taxPercentages.get(i).sendKeys(Keys.BACK_SPACE);
                        taxPercentages.get(i).sendKeys("7");
                        exemption.get(i).sendKeys(Keys.CONTROL + "A");
                        exemption.get(i).sendKeys(Keys.BACK_SPACE);
                        Select dd = new Select(filingstatus.get(i));
                        dd.selectByVisibleText("");

                    }
                }
conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "presenceOfElementLocated");
        conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "elementToBeVisible");
        conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "elementToBeClickable");
        clickElement(this.benefitsPageElement.getModifypaymentokbutton());
        waitForPageLoad();
        moveToDefault();
        switchToFrame("tapestry");

        conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "presenceOfElementLocated");
        scrollWindow(this.benefitsPageElement.getDeductionCogwheel());
        conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeVisible");
        conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeClickable");
        clickElement(this.benefitsPageElement.getDeductionCogwheel());

        conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "presenceOfElementLocated");
        conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "elementToBeVisible");
        conditionalWait(this.benefitsPageElement.getPaymentDeductionEditDetail(), "elementToBeClickable");
        clickElement(this.benefitsPageElement.getPaymentDeductionEditDetail());
        waitForPageLoad();
        moveToDefault();
        switchToFrame("DisbursementPages_DeductionTemplatePopupPage");

                conditionalWait(this.benefitsPageElement.getEditDetails_cogwheel(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getEditDetails_cogwheel(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getEditDetails_cogwheel(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getEditDetails_cogwheel());

                conditionalWait(this.benefitsPageElement.getEditDetails_Add(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getEditDetails_Add(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getEditDetails_Add(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getEditDetails_Add());

                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "elementToBeClickable");
                selectDDByVisibleText(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "Federal Tax");
                conditionalWait(this.benefitsPageElement.getAdditonalPercentValueDeduction(), "presenceOfElementLocated");

                conditionalWait(this.benefitsPageElement.getAdditonalPercentValueDeduction(), "elementToBeVisible");

                conditionalWait(this.benefitsPageElement.getAdditonalPercentValueDeduction(), "elementToBeClickable");

                getElementByXpath(this.benefitsPageElement.getAdditonalPercentValueDeduction()).sendKeys("30");

                conditionalWait(this.basePageElement.getPaymentDedPercentOf(), "presenceOfElementLocated");
                conditionalWait(this.basePageElement.getPaymentDedPercentOf(), "elementToBeVisible");
                conditionalWait(this.basePageElement.getPaymentDedPercentOf(), "elementToBeClickable");
                selectDDByVisibleText(this.basePageElement.getPaymentDedPercentOf(), "Taxable Gross");

                conditionalWait(this.benefitsPageElement.getEditDetails_UseTable(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getEditDetails_UseTable(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getEditDetails_UseTable(), "elementToBeClickable");

                if (getElementByXpath(this.benefitsPageElement.getEditDetails_UseTable()).isSelected()) {
                    clickElement(this.benefitsPageElement.getEditDetails_UseTable());
                }

                conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getModifypaymentokbutton());
                waitForPageLoad();
                moveToDefault();
                switchToFrame("tapestry");

                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "presenceOfElementLocated");
                scrollWindow(this.benefitsPageElement.getDeductionCogwheel());
                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeClickable");

                moveToDefault();
                if (this.findElements(this.benefitsPageElement.getErrorDialogBox()).size() > 0) {

                } else {

                    clickApply();
                    waitForPageLoad();

                    Thread.sleep(3000);
                    this.frameToBeAvailableAndSwitchToIt(this.benefitsPageElement.getIframeId());
                    moveToDefault();

                    if (presentElement(this.batchpage.getPopupok())) {
                        clickElement(this.batchpage.getPopupok());
                    }

                    moveToDefault();
                    this.clickSave();
                    this.waitForPageLoad();

                    moveToDefault();
                    if (driver.findElements(By.xpath(this.basePageElement.getEditButtonLocator())).size() == 0) {
                        this.clickSave();
                        this.waitForPageLoad();
                        Thread.sleep(3000);
                    }

                    this.frameToBeAvailableAndSwitchToIt(this.benefitsPageElement.getIframeId());
                    moveToDefault();

                    if (presentElement(this.batchpage.getPopupok())) {
                        clickElement(this.batchpage.getPopupok());
                    }
                    moveToDefault();
                }


            }


        }
        else {
            moveToDefault();
            clickApply();
        }

    }

    public void nonUSAddressmodification(String testCaseNo) throws Exception {
        waitForPageLoad();
        moveToDefault();
        Thread.sleep(4000);
        this.clickEdit();
        waitForPageLoad();
        String[] mockNonUSAddress = this.getMockNonUSAddress();

        switchToFrame("tapestry");

        conditionalWait(this.batchpage.getMenupath(), "elementToBeVisible");
        conditionalWait(this.batchpage.getMenupath(), "presenceOfElementLocated");
        conditionalWait(this.batchpage.getMenupath(), "elementToBeClickable");
        this.getElementByXpath(this.batchpage.getMenupath()).click();
        this.scrollWindow(this.batchpage.getMenupath());
        conditionalWait(this.batchpage.getADDdeletexpath(), "elementToBeVisible");
        conditionalWait(this.batchpage.getADDdeletexpath(), "presenceOfElementLocated");
        conditionalWait(this.batchpage.getADDdeletexpath(), "elementToBeClickable");
        this.getElementByXpath(this.batchpage.getADDdeletexpath()).click();
        Thread.sleep(4000);
        this.driver.switchTo().parentFrame();

        this.getElementByXpath(this.batchpage.getYes_locator()).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        switchToFrame("tapestry");
        if (presentElement((this.batchpage.getMenupath()))) {
            conditionalWait(this.batchpage.getMenupath(), "elementToBeVisible");
            conditionalWait(this.batchpage.getMenupath(), "presenceOfElementLocated");
            conditionalWait(this.batchpage.getMenupath(), "elementToBeClickable");
            this.getElementByXpath(this.batchpage.getMenupath()).click();
            this.scrollWindow(this.batchpage.getMenupath());
            conditionalWait(this.batchpage.getADDdeletexpath(), "elementToBeVisible");
            conditionalWait(this.batchpage.getADDdeletexpath(), "presenceOfElementLocated");
            conditionalWait(this.batchpage.getADDdeletexpath(), "elementToBeClickable");
            this.getElementByXpath(this.batchpage.getADDdeletexpath()).click();
            this.driver.switchTo().parentFrame();
            Thread.sleep(4000);
            this.getElementByXpath(this.batchpage.getYes_locator()).sendKeys(Keys.ENTER);

            switchToFrame("tapestry");
        }



        conditionalWait(this.batchpage.getAddxpath(), "elementToBeVisible");
        conditionalWait(this.batchpage.getAddxpath(), "presenceOfElementLocated");
        conditionalWait(this.batchpage.getAddxpath(), "elementToBeClickable");
        this.getElementByXpath(this.batchpage.getAddxpath()).click();
        conditionalWait(this.batchpage.getAddresidentialxpath(), "elementToBeVisible");
        conditionalWait(this.batchpage.getAddresidentialxpath(), "presenceOfElementLocated");
        conditionalWait(this.batchpage.getAddresidentialxpath(), "elementToBeClickable");
        this.getElementByXpath(this.batchpage.getAddresidentialxpath()).click();

        moveToDefault();
        conditionalWait(this.batchpage.getADDCountry(), "elementToBeVisible");
        conditionalWait(this.batchpage.getADDCountry(), "presenceOfElementLocated");
        conditionalWait(this.batchpage.getADDCountry(), "elementToBeClickable");
        this.getElementByXpath(this.batchpage.getADDCountry()).sendKeys(Keys.CONTROL + "A");
        this.getElementByXpath(this.batchpage.getADDCountry()).sendKeys(Keys.BACK_SPACE);
        this.getElementByXpath(this.batchpage.getADDCountry()).sendKeys(mockNonUSAddress[2]);
        this.getElementByXpath(this.batchpage.getADDCountry()).sendKeys(Keys.ENTER);
        conditionalWait(this.batchpage.getPostalcode(), "elementToBeVisible");
        conditionalWait(this.batchpage.getPostalcode(), "presenceOfElementLocated");
        conditionalWait(this.batchpage.getPostalcode(), "elementToBeClickable");
        this.getElementByXpath(this.batchpage.getPostalcode()).sendKeys(mockNonUSAddress[3]);
        conditionalWait(this.batchpage.getAddress1xpath(), "elementToBeVisible");
        conditionalWait(this.batchpage.getAddress1xpath(), "presenceOfElementLocated");
        conditionalWait(this.batchpage.getAddress1xpath(), "elementToBeClickable");

        this.getElementByXpath(this.batchpage.getAddress1xpath()).sendKeys(mockNonUSAddress[0]);
        conditionalWait(this.batchpage.getCity(), "elementToBeVisible");
        conditionalWait(this.batchpage.getCity(), "presenceOfElementLocated");
        conditionalWait(this.batchpage.getCity(), "elementToBeClickable");
        this.getElementByXpath(this.batchpage.getCity()).sendKeys(mockNonUSAddress[1]);
        conditionalWait(this.batchpage.getOk_Locator(), "elementToBeVisible");
        conditionalWait(this.batchpage.getOk_Locator(), "presenceOfElementLocated");
        conditionalWait(this.batchpage.getOk_Locator(), "elementToBeClickable");
        this.getElementByXpath(this.batchpage.getOk_Locator()).click();
        this.captureScreenShoot(testCaseNo, "Address added is completed");
        moveToDefault();

        this.clickApply();
        this.waitForPageLoad();
        switchToFrame("tapestry");
        if (presentElement(this.batchpage.getPopupok())) {
            clickElement(this.batchpage.getPopupok());
        }
        moveToDefault();
        this.waitForPageLoad();
        this.clickSave();
        waitForPageLoad();
        switchToFrame("tapestry");
        if (presentElement(this.batchpage.getPopupok())) {
            clickElement(this.batchpage.getPopupok());
        }
        moveToDefault();
        waitForPageLoad();
    }
    public String[] getMockNonUSAddress() {
        String[] mockData;

        String[][] mockAddressSet = {{"Alsterkrugchaussee 14", "Schwabach", "Germany","91116"},
                {"Eschenweg 70", "Pobneck", "Germany","07372"},
                {"Rohrdamm 5", "Emmerthal", "Germany","31860"}};
        mockData=mockAddressSet[this.getRandomInteger(0,(mockAddressSet.length-1))];

        return mockData;
    }

    public int getRandomInteger(int min, int max) {
        Random ran=new Random();
        int number = min + (int)(ran.nextInt(max-min+1));
        return number;
    }

    public void ClickonProfile(){

        conditionalWait(this.annuiatantPageElement.getProfileXpath(), "presenceOfElementLocated");
        conditionalWait(this.annuiatantPageElement.getProfileXpath(), "elementToBeVisible");
        conditionalWait(this.annuiatantPageElement.getProfileXpath(), "elementToBeClickable");
        this.clickElement(this.annuiatantPageElement.getProfileXpath());

    }

    public String checkForErrorMessage() throws InterruptedException {
        this.waitForPageLoad();
        this.moveToDefault();

        String errorMessage = "";

        if (this.findElements(this.benefitsPageElement.getErrorDialogBox()).size() > 0) {
            conditionalWait(this.benefitPageElementexd.getErrormessage(), "elementToBeVisible");
            conditionalWait(this.benefitPageElementexd.getErrormessage(), "presenceOfElementLocated");
            errorMessage = this.getText(this.benefitPageElementexd.getErrormessage());

        }
        return errorMessage;
    }

    public void clickIPerrorhandler() throws Exception {
        moveToDefault();

        waitForPageLoad();
        if (presentElement(this.benefitPageElementexd.getIperrormessage())) {
            clickElement(this.benefitPageElementexd.getIperrormessage());
        }
        moveToDefault();
    }

    public Boolean goToBrokerProfile() throws InterruptedException {
        Boolean flowResult = true;

        waitForPageLoad();
        this.switchToFrame(contractCertsPageElement.getIframeId());
        conditionalWait(this.contractCertsPageElement.getBrokerWidgetHeader(), "presenceOfElementLocated");
        conditionalWait(this.contractCertsPageElement.getBrokerWidgetHeader(), "elementToBeVisible");
        scrollWindow(contractCertsPageElement.getBrokerWidgetHeader());
        waitForPageLoad();

        if(this.findElements(contractCertsPageElement.getBrokerName()).size()>0){

            conditionalWait(this.contractCertsPageElement.getBrokerName(), "presenceOfElementLocated");
            conditionalWait(this.contractCertsPageElement.getBrokerName(), "elementToBeVisible");
            conditionalWait(this.contractCertsPageElement.getBrokerName(), "elementToBeClickable");
            this.clickElement(this.contractCertsPageElement.getBrokerName());
            waitForPageLoad();


        } else{
            flowResult = false;
        }
        moveToDefault();
        return flowResult;
    }

    public String getLockin() throws InterruptedException {
        String lockin = "";

        waitForPageLoad();
        this.switchToFrame(contractCertsPageElement.getIframeId());
        conditionalWait(this.contractCertsPageElement.getLockin(), "presenceOfElementLocated");
        conditionalWait(this.contractCertsPageElement.getLockin(), "elementToBeVisible");
        scrollWindow(contractCertsPageElement.getLockin());
        lockin=this.getAttributeText(this.contractCertsPageElement.getLockin(),"value");

        moveToDefault();
        return lockin;
    }

    public void goToBenefitsPage() throws InterruptedException {
        conditionalWait(this.basePageElement.getBenefitsLink(), "presenceOfElementLocated");
        conditionalWait(this.basePageElement.getBenefitsLink(), "elementToBeVisible");
        this.clickElement(this.basePageElement.getBenefitsLink());
    }

    public String handleErrorMessage() throws InterruptedException {
        this.waitForPageLoad();
        this.moveToDefault();

        String errorMessage = "";

        if (this.findElements(this.benefitsPageElement.getErrorDialogBox()).size() > 0) {
            conditionalWait(this.benefitPageElementexd.getErrormessage(), "elementToBeVisible");
            conditionalWait(this.benefitPageElementexd.getErrormessage(), "presenceOfElementLocated");
            errorMessage = this.getText(this.benefitPageElementexd.getErrormessage());
            this.clickElement(this.benefitPageElementexd.getErrorok());
            this.waitForPageLoad();

        }
        return errorMessage;
    }

    public String handleTax(String testCaseNo) throws Exception {
        String errormessage="";
        Thread.sleep(3000);
        moveToDefault();
        if ((this.findElements(this.benefitsPageElement.getErrorDialogBox()).size() > 0)) {
            log.info("Error message found ");
            conditionalWait(this.benefitPageElementexd.getErrormessage(), "presenceOfElementLocated");
            conditionalWait(this.benefitPageElementexd.getErrormessage(), "elementToBeVisible");
            conditionalWait(this.benefitPageElementexd.getErrormessage(), "elementToBeClickable");
            errormessage = getElementByXpath(this.benefitPageElementexd.getErrormessage()).getText();

            if (errormessage.contains("Federal Tax is required and should be 30% on Payment Stream")) {

                log.info("Federal Tax is required  ");
                this.clickElement(this.benefitPageElementexd.getErrorok());
                popuphandler();

                this.waitForPageLoad();
                log.info("Federal Tax is required and should be 30% on Payment Stream ");



                List<WebElement> deductionTypes = findElements(this.benefitsPageElement.getEditDetails_DeductionTypeDD());
                List<WebElement> stopDates = findElements(this.basePageElement.getPaymentDedStopDate());
                List<WebElement> startDates = findElements(this.basePageElement.getPaymentDedStartDate());
                List<WebElement> deductionAmounts = findElements(this.benefitsPageElement.getDeduction_Additional_Amount_locator());
                List<WebElement> expandButton = findElements(this.basePageElement.getPaymentDedExpand());
                List<WebElement> taxPercentages = findElements(this.benefitsPageElement.getEditDetailsTaxPercentageLocator());
                List<WebElement> percentOf = findElements(this.basePageElement.getPaymentDedPercentOf());


                System.out.println(deductionTypes);
                for (int i = 0; i < deductionTypes.size(); i++) {
                    Select select = new Select(deductionTypes.get(i));
                    WebElement selectedOption = select.getFirstSelectedOption();
                    String deductionType = selectedOption.getText();
                    if (deductionType.contains("State")) {

                        System.out.println("Adding State Tax");
                        deductionAmounts.get(i).sendKeys(Keys.CONTROL + "A");
                        deductionAmounts.get(i).sendKeys(Keys.BACK_SPACE);
                        expandButton.get(i).click();
                        Thread.sleep(2000);
                        taxPercentages.get(i).sendKeys(Keys.CONTROL + "A");
                        taxPercentages.get(i).sendKeys(Keys.BACK_SPACE);
                        taxPercentages.get(i).sendKeys("7");
                        Select dd = new Select(percentOf.get(i));
                        dd.selectByVisibleText("Federal Withholding");
                        expandButton.get(i).click();

                    }
                    if (deductionType.contains("Federal")) {
                        System.out.println("Adding Federal Tax");
                        String startDateText = startDates.get(i).getAttribute("value").replace("/", "");
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy");
                        LocalDate startDate = LocalDate.parse(startDateText, dtf);
                        LocalDate stopDate = startDate.plusDays(30);
                        String stopDateText = stopDate.format(dtf);
                        stopDates.get(i).sendKeys(Keys.CONTROL + "A");
                        stopDates.get(i).sendKeys(Keys.BACK_SPACE);
                        stopDates.get(i).sendKeys(stopDateText);
                    }

                }
                conditionalWait(this.benefitsPageElement.getEditDetails_cogwheel(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getEditDetails_cogwheel(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getEditDetails_cogwheel(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getEditDetails_cogwheel());

                conditionalWait(this.benefitsPageElement.getEditDetails_Add(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getEditDetails_Add(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getEditDetails_Add(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getEditDetails_Add());
                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "elementToBeClickable");
                selectDDByVisibleText(this.benefitsPageElement.getEditDetails_DeductionTypeDD(), "Federal Tax");
                conditionalWait(this.benefitsPageElement.getAdditonalPercentValueDeduction(), "presenceOfElementLocated");

                conditionalWait(this.benefitsPageElement.getAdditonalPercentValueDeduction(), "elementToBeVisible");

                conditionalWait(this.benefitsPageElement.getAdditonalPercentValueDeduction(), "elementToBeClickable");

                getElementByXpath(this.benefitsPageElement.getAdditonalPercentValueDeduction()).sendKeys("30");

                conditionalWait(this.benefitsPageElement.getEditDetails_UseTable(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getEditDetails_UseTable(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getEditDetails_UseTable(), "elementToBeClickable");

                if (getElementByXpath(this.benefitsPageElement.getEditDetails_UseTable()).isSelected()) {
                    clickElement(this.benefitsPageElement.getEditDetails_UseTable());
                }

                conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "presenceOfElementLocated");
                conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getModifypaymentokbutton(), "elementToBeClickable");
                clickElement(this.benefitsPageElement.getModifypaymentokbutton());
                waitForPageLoad();
                moveToDefault();
                switchToFrame("tapestry");

                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "presenceOfElementLocated");
                scrollWindow(this.benefitsPageElement.getDeductionCogwheel());
                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeVisible");
                conditionalWait(this.benefitsPageElement.getDeductionCogwheel(), "elementToBeClickable");

                moveToDefault();
                if (this.findElements(this.benefitsPageElement.getErrorDialogBox()).size() > 0) {

                } else {

                    clickApply();
                    waitForPageLoad();

                    Thread.sleep(3000);
                    this.frameToBeAvailableAndSwitchToIt(this.benefitsPageElement.getIframeId());
                    moveToDefault();

                    if (presentElement(this.batchpage.getPopupok())) {
                        clickElement(this.batchpage.getPopupok());
                    }

                    moveToDefault();
                    this.clickSave();
                    this.waitForPageLoad();

                    moveToDefault();
                    if (driver.findElements(By.xpath(this.basePageElement.getEditButtonLocator())).size() == 0) {
                        this.clickSave();
                        this.waitForPageLoad();
                        Thread.sleep(3000);
                    }

                    this.frameToBeAvailableAndSwitchToIt(this.benefitsPageElement.getIframeId());
                    moveToDefault();

                    if (presentElement(this.batchpage.getPopupok())) {
                        clickElement(this.batchpage.getPopupok());
                    }
                    moveToDefault();
                }


            }


        }
        else {
            moveToDefault();
            clickApply();
        }

        return errormessage;
    }

    public void resetBatchFilter() throws Exception {
        waitForPageLoad();
        if (getElementByXpath(this.basePageElement.getBatchApplyFilterBtn()).isDisplayed()) {
            clickElement(this.basePageElement.getBatchClearFilterBtn());
            clickElement(this.basePageElement.getBatchApplyFilterBtn());
            waitForPageLoad();
        }
    }


}
*/
