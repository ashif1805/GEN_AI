package com.genaidemo.demo.page;

import com.genaidemo.demo.annotation.LazyAutowired;
import com.genaidemo.demo.annotation.Page;
import com.genaidemo.demo.config.AppConfig;
import com.genaidemo.demo.config.webElement.PageElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

@Page
@Slf4j
public class LoginPage extends GenericPage {

    @LazyAutowired
    private AppConfig appConfig;
    @LazyAutowired
    private PageElement pageElement;


    public boolean isAt() throws InterruptedException {
        return true;
    }

    public void loginToPortal(String testCase) throws Exception {
        try {
            this.driver.get(this.appConfig.getUrl());
            waitForPageLoad();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToPage(String page, String testCase) throws InterruptedException {
        try {
            conditionalWait(this.pageElement.getSolutionsDropMenuXpath(), "presenceOfElementLocated");
            conditionalWait(this.pageElement.getSolutionsDropMenuXpath(), "elementToBeVisible");
            conditionalWait(this.pageElement.getSolutionsDropMenuXpath(), "elementToBeClickable");

            if (page.contains("RIS")) {
                conditionalWait(this.pageElement.getRISButtonXpath(), "presenceOfElementLocated");
                conditionalWait(this.pageElement.getRISButtonXpath(), "elementToBeVisible");
                conditionalWait(this.pageElement.getRISButtonXpath(), "elementToBeClickable");
                mouseHover(this.pageElement.getRISButtonXpath());
                highlightElement(this.pageElement.getRISButtonXpath());
                clickTheElementByXpath(this.pageElement.getRISButtonXpath());
            }
            if (page.contains("Access")) {
                conditionalWait(this.pageElement.getAccessBenefitLink(), "presenceOfElementLocated");
                conditionalWait(this.pageElement.getAccessBenefitLink(), "elementToBeVisible");
                conditionalWait(this.pageElement.getAccessBenefitLink(), "elementToBeClickable");
                mouseHover(this.pageElement.getAccessBenefitLink());
                highlightElement(this.pageElement.getAccessBenefitLink());
                clickTheElementByXpath(this.pageElement.getAccessBenefitLink());
            }
            if (page.contains("Find Dentist")) {
                conditionalWait(this.pageElement.getFindADentistHome(), "presenceOfElementLocated");
                conditionalWait(this.pageElement.getFindADentistHome(), "elementToBeVisible");
                conditionalWait(this.pageElement.getFindADentistHome(), "elementToBeClickable");
                mouseHover(this.pageElement.getFindADentistHome());
                highlightElement(this.pageElement.getFindADentistHome());
                clickTheElementByXpath(this.pageElement.getFindADentistHome());
                conditionalWait(this.pageElement.getDentistPDP(), "presenceOfElementLocated");
                conditionalWait(this.pageElement.getDentistPDP(), "elementToBeVisible");
                conditionalWait(this.pageElement.getDentistPDP(), "elementToBeClickable");
                mouseHover(this.pageElement.getDentistPDP());
//                highlightElement(this.pageElement.getDentistPDP());
                scrollWindow(this.pageElement.getDentistPDP());
                clickTheElementByXpath(this.pageElement.getDentistPDP());
                conditionalWait(this.pageElement.getDentistPDPsearchBoxInput(), "presenceOfElementLocated");
                conditionalWait(this.pageElement.getDentistPDPsearchBoxInput(), "elementToBeVisible");
                conditionalWait(this.pageElement.getDentistPDPsearchBoxInput(), "elementToBeClickable");
                mouseHover(this.pageElement.getDentistPDPsearchBoxInput());
                highlightElement(this.pageElement.getDentistPDPsearchBoxInput());
                scrollWindow(this.pageElement.getDentistPDPsearchBoxInput());
                getElementByXpath(this.pageElement.getDentistPDPsearchBoxInput()).sendKeys(this.mockTestData.getZipCode());
                conditionalWait(this.pageElement.getDentistFindAdentistButton(), "elementToBeClickable");
                mouseHover(this.pageElement.getDentistFindAdentistButton());
                highlightElement(this.pageElement.getDentistFindAdentistButton());
                scrollWindow(this.pageElement.getDentistFindAdentistButton());
                clickTheElementByXpath(this.pageElement.getDentistFindAdentistButton());

            }
            if (page.contains("Open Enrollment")) {
                conditionalWait(this.pageElement.getOpenEntrollmentHome(), "presenceOfElementLocated");
                conditionalWait(this.pageElement.getOpenEntrollmentHome(), "elementToBeVisible");
                conditionalWait(this.pageElement.getOpenEntrollmentHome(), "elementToBeClickable");
                mouseHover(this.pageElement.getOpenEntrollmentHome());
                highlightElement(this.pageElement.getOpenEntrollmentHome());
                clickTheElementByXpath(this.pageElement.getOpenEntrollmentHome());
            }
            if (page.contains("Mypets")) {
                conditionalWait(this.pageElement.getMyPetsLoginHome(), "presenceOfElementLocated");
                conditionalWait(this.pageElement.getMyPetsLoginHome(), "elementToBeVisible");
                conditionalWait(this.pageElement.getMyPetsLoginHome(), "elementToBeClickable");
                mouseHover(this.pageElement.getMyPetsLoginHome());
                highlightElement(this.pageElement.getMyPetsLoginHome());
                clickTheElementByXpath(this.pageElement.getMyPetsLoginHome());
            }
            if (page.contains("Vision provider")) {
                conditionalWait(this.pageElement.getVisionProviderFindHome(), "presenceOfElementLocated");
                conditionalWait(this.pageElement.getVisionProviderFindHome(), "elementToBeVisible");
                conditionalWait(this.pageElement.getVisionProviderFindHome(), "elementToBeClickable");
                mouseHover(this.pageElement.getVisionProviderFindHome());
                highlightElement(this.pageElement.getVisionProviderFindHome());
                clickTheElementByXpath(this.pageElement.getVisionProviderFindHome());
            }
            if (page.contains("Contact Us")) {
                conditionalWait(this.pageElement.getContactUsHome(), "presenceOfElementLocated");
                conditionalWait(this.pageElement.getContactUsHome(), "elementToBeVisible");
                conditionalWait(this.pageElement.getContactUsHome(), "elementToBeClickable");
                mouseHover(this.pageElement.getContactUsHome());
                highlightElement(this.pageElement.getContactUsHome());
                clickTheElementByXpath(this.pageElement.getContactUsHome());
            }
            log.info("Button click");
            waitForPageLoad();

            log.info("RIS Page Open for:" + testCase);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
