package com.genaidemo.demo.config.webElement;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "demo.page.app.element")
@PropertySource(value = "classpath:pageelements/PageElements.properties")
@Data
public class PageElement {
    private String rISButtonXpath;
    private String lifetimeIncomeBtn;
    private String sponsRetirementPlanBtn;
    private String insightsButtonXpath;
    private String pensionRiskButton;
    private String structureSettlementButton;
    private String longevityReinsuranceButton;
    private String stableValueBtn;
    private String fundingSolutionsBtn;
    private String rISBannerXpath;
    private String metLifeInvestmentBanner;
    private String businessBanner;
    private String insuranceBannerXpath;
    private String solutionsDropMenuXpath;
    private String rISPageHeaderXpath;
    private String accessBenefitLink;
    private String rISInsight;
    private String rISStructuredSet;
    private String rISStableView;
    private String rISFundingSol;
    private String rISLifetimeIncome;
    private String rISPensionRisk;
    private String rISShowMoreBtn;
    private String rISSupport;
    private String quickLinksHeader;
    private String quickLinksPensionRiskSolutions;
    private String quickLinksMeetTheTeam;
    private String quickLinksMyRetirementBene;
    private String quickLinksVideoCenter;
    private String findADentistHome;
    private String openEntrollmentHome;
    private String myPetsLoginHome;
    private String contactUsHome;
    private String visionProviderFindHome;
    private String contactUsIndividual;
    private String contactUsBusinessBrokers;
    private String contactUsMediaPress;
    private String contactUsInvestors;
    private String conatctUsCareerSeek;
    private String contaxtUsGeneral;
    private String cusIndiReportCard;
    private String cusIndiReportCardContact;
    private String cusIndiItSecurityIssue;
    private String cusIndiItSecurityIssueContact;
    private String cusIndiFormerEmp;
    private String cusIndiFormerEmpContact;
    private String cusIndiAnnuitiesHeader;
    private String cusIndiAnnuitIndividualInsurance;
    private String cusIndiAnnuitIndividualInsuranceContact;
    private String cusIndiDisabilityIndividualInsurance;
    private String cusIndiDisabilityIndividualInsuranceContact;
    private String cusIndiHomeAutoIndividualInsurance;
    private String cusIndiEmployerGroup;
    private String cusIndiHomeAutoIndividualInsuranceContact;
    private String cusIndiEmployerGroupContact;
    private String cusIndiAnnuiEmployerRetirement;
    private String cusIndiAnnuiEmployerRetirementContact;
    private String cusIndiRetirementEmployerRetirement;
    private String cusIndiRetirementEmployerRetirementContact;
    private String cusIndiAnnuiEmployerRetirementService;
    private String cusIndiAnnuiEmployerRetirementServiceContact;
    private String cusIndiRetirementEmployerRetirementService;
    private String cusIndiRetirementEmployerRetirementServiceContact;
    private String cusIndiDentalInsurance;
    private String cusIndiDentalInsuranceContact;
    private String cusIndiDisEmployerSponsInsuranceContact;
    private String cusIndiDisEmployerSponsInsurance;
    private String cusIndiHomeEmployerSponsInsurance;
    private String cusIndiHomeEmployerSponsInsuranceContact;
    private String cusIndiGroupEmpSponsored;
    private String cusIndiGroupEmpSponsoredContact;
    private String cusIndiLifeIndividualLife;
    private String cusIndiLifeIndividualLifeContact;
    private String cusIndiLongEmpSponsored;
    private String cusIndiLongEmpSponsoredContact;
    private String cusIndiRetirementGroupAnnuit;
    private String cusIndiRetirementGroupAnnuitContact;
    private String cusIndiVisionInsurance;
    private String cusIndiVisionInsuranceContact;
    private String cusIndiBrighthouseFinancial;
    private String cusIndiBrighthouseFinancialContact;
    private String cusIndiStructureSettlement;
    private String cusIndiStructureSettlementContact;
    private String dentistPDP;
    private String dentistPDPsearchBoxInput;
    private String dentistFindAdentistButton;












}
