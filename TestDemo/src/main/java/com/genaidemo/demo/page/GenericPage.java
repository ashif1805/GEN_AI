package com.genaidemo.demo.page;

import com.genaidemo.demo.utility.MockTestData;
import com.genaidemo.demo.utility.TestUtility;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.*;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public abstract class GenericPage {


    @Autowired
    protected WebDriver driver;
    @Autowired
    protected WebDriverWait wait;
    @Autowired
    private TestUtility testUtility;
    @Autowired
    protected MockTestData mockTestData;

    Set<String> allwindows;

    @PostConstruct
    private void init() {

        PageFactory.initElements(this.driver, this);
    }


    public void closeBrowser() {
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }


    public String getValueByElementId(String elementById) {
        return this.driver.findElement(By.id(elementById)).isDisplayed() ? this.driver.findElement(By.id(elementById)).getText() : null;
    }

    public String getValueByElementXpath(String elementXpath) throws InterruptedException {

        return this.driver.findElement(By.xpath(elementXpath)).getText();
    }

    //@author: Subhadeep Sen
    //To get the text value of an webelement
    public String getValue(WebElement element) throws InterruptedException {

        return element.getText();
    }


    public String getAttributeValueByElementXpath(String elementXpath, String attributeName) throws InterruptedException {
        return this.driver.findElement(By.xpath(elementXpath)).getAttribute(attributeName);
    }

    public String getAttributeValueByElementXpath(WebElement element, String attributeName) throws InterruptedException {
        return element.getAttribute(attributeName);
    }


    public WebElement getElementByXpath(String elementXpath) throws InterruptedException {
        return this.driver.findElement(By.xpath(elementXpath));
    }

    public List<WebElement> findElements(String elementXpath) throws InterruptedException {

        return this.driver.findElements(By.xpath(elementXpath));

    }

    public void clickTheElementByXpath(String elementXpath) throws InterruptedException {

        WebElement element = this.driver.findElement(By.xpath(elementXpath));
        if (this.wait.until((d) -> element.isDisplayed())) {
            element.click();
        }
    }

    //@author: Subhadeep Sen
    public void clickTheElementByXpath(WebElement element) throws InterruptedException {


        if (this.wait.until((d) -> element.isDisplayed())) {
            element.click();
        }


    }

    public void highlightElement(String elementXpath) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) this.driver;
        jsExecutor.executeScript("arguments[0].style.background='yellow'", this.driver.findElement(By.xpath(elementXpath)));
    }

    public void highlightElements(List<WebElement> elements) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) this.driver;
        for (int i = 0; i < elements.size(); i++)
            jsExecutor.executeScript("arguments[0].style.background='yellow'", elements.get(i));
    }

    public void highlightElement(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) this.driver;
        jsExecutor.executeScript("arguments[0].style.background='yellow'", element);
    }

    public void openCRMPanel(String elementXpath) throws InterruptedException {
        Thread.sleep(5000);
        WebElement elementPowerPanelButton = this.driver.findElement(By.xpath(elementXpath));
        if (this.wait.until((d) -> elementPowerPanelButton.isDisplayed())) {
            if ("Show Power Panel".equalsIgnoreCase(elementPowerPanelButton.getAttribute("title"))) {
                elementPowerPanelButton.click();
            }

        }
    }

    public boolean elementExists(String xPath) {
        try {
            this.driver.findElement(By.xpath(xPath));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void conditionalWait(String elementXpath, String waitingCondition) {

        if (waitingCondition.equalsIgnoreCase("presenceOfElementLocated")) {
            this.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
        } else if (waitingCondition.equalsIgnoreCase("elementToBeClickable")) {
            this.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
        } else if (waitingCondition.equalsIgnoreCase("elementToBeVisible")) {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
        } else if (waitingCondition.equalsIgnoreCase("elementToBeInVisible")) {
            this.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(elementXpath)));
        } else if (waitingCondition.equalsIgnoreCase("frameToBeAvailable")) {
            this.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(elementXpath));
        }
    }

    public void conditionalWait(String elementXpath, String waitingCondition, String textTobePresent) {
        if (waitingCondition.equalsIgnoreCase("presenceOfElementLocated")) {
            this.wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(elementXpath), textTobePresent));
        }

    }

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver ldriver) {
                return ((JavascriptExecutor) ldriver).executeScript("return document.readyState").equals("complete");
            }
        };
        this.wait.until(pageLoadCondition);
    }

    public void captureScreenShot(String docName, String screenCaption) throws Exception {
        testUtility.addingScreenShoot(docName, screenCaption, this.driver);
    }

    public void scrollWindow(String elementXpath) throws InterruptedException {
        WebElement webElement = this.getElementByXpath(elementXpath);
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        Thread.sleep(2000);
    }

    public void scrollWindow(WebElement element) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);

    }

    public void scrollWindowCoOrdinates(int xAxis, int yAxis) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(" + xAxis + "," + yAxis + ")");
        Thread.sleep(2000);

    }

    public void scrollWindowToTop() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
        Thread.sleep(2000);
    }

    public boolean presentElement(String elementXpath) throws Exception {
        boolean elementPresent;
        try {
            getElementByXpath(elementXpath);
            elementPresent = true;
        } catch (Exception e) {
            elementPresent = false;
        }
        return elementPresent;
    }

    public void clickSelectedOption(String elementXpath, String selection) throws InterruptedException {
        List<WebElement> dropdownList;
        String dropdownOption;
        Thread.sleep(1000);
        this.conditionalWait(elementXpath, "elementToBeClickable");
        dropdownList = this.findElements(elementXpath);
        for (int i = 0; i < dropdownList.size(); i++) {

            if (i > 2) {
                scrollWindow(dropdownList.get(i - 2));
            }
            dropdownList = this.findElements(elementXpath);
            dropdownOption = dropdownList.get(i).getText().trim();
            if (dropdownOption.toUpperCase().trim().equals(selection.toUpperCase().trim())) {

                highlightElement(dropdownList.get(i));
                dropdownList.get(i).click();
                break;
            }
        }
    }

    public void editText(String elementXpath, String value) throws InterruptedException {
        getElementByXpath(elementXpath).click();
        getElementByXpath(elementXpath).sendKeys(Keys.CONTROL + "A");
        getElementByXpath(elementXpath).sendKeys(Keys.BACK_SPACE);
        getElementByXpath(elementXpath).sendKeys(value);
    }

    //@author: Subhadeep Sen
    //To Switch the window
    public WebDriver switchToWindow(String title) {
        Set<String> allwindows = driver.getWindowHandles();
        for (String childwindow : allwindows) {
            driver.switchTo().window(childwindow);
            String ActualWindowTitle = driver.getTitle();
            if (ActualWindowTitle.contains(title)) {
                break;
            }
        }

        return driver;
    }

    //@author: Subhadeep Sen
    //To Switch the frame
    public void switchToFrame(String frameId) {
        moveToDefault();
        driver.switchTo().frame(frameId);
    }

    //@author: Subhadeep Sen
    //To Switch to default Content
    public void moveToDefault() {
        driver.switchTo().defaultContent();
    }


    //@author: Subhadeep Sen
    //To upload a document
    public void uploadDocument(String documentPath) throws AWTException {
        StringSelection docfile = new StringSelection(documentPath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(docfile, null);
        Robot robot = new Robot();
        robot.delay(8000);


        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    //@author: Subhadeep Sen
    //To get the file size in MB
    public String getFileSizeInMB(String documentPath) {

        File file = new File(documentPath);
        double fileSize = (double) file.length() / (1024 * 1024);
        BigDecimal bd = new BigDecimal(fileSize).setScale(2, RoundingMode.HALF_UP);

        return bd + "MB";

    }

    public WebElement selectOption(String elementXpath, String selection) throws InterruptedException {
        List<WebElement> dropdownList;
        WebElement selectedOption = null;
        String dropdownOption;
        dropdownList = this.findElements(elementXpath);
        for (int i = 0; i < dropdownList.size(); i++) {
            dropdownOption = dropdownList.get(i).getText().trim();
            if (dropdownOption.toUpperCase().trim().equals(selection.toUpperCase().trim())) {
                if (i > 5) {
                    scrollWindow(dropdownList.get(i - 5));
                }
                highlightElement(dropdownList.get(i));
                selectedOption = dropdownList.get(i);

                break;
            }
        }
        return selectedOption;
    }

    //@author: Seema Gnanender
    //To Check if element is enabled
    public boolean isElementEnabled(String xpath) {
        boolean elementEnabled = driver.findElement(By.xpath(xpath)).isEnabled();
        return elementEnabled;
    }

    //@author : Subhadeep Sen
    public void clearCache() {
        driver.manage().deleteAllCookies();
    }


    //@author : Subhadeep Sen
    //To get the mapped document Type
    public HashMap<String, String> mappedDocumentsName() {
        HashMap<String, String> mappedDocumentsName = new HashMap<String, String>();
        mappedDocumentsName.put("Application Data", "SSNB Application");
        mappedDocumentsName.put("Attorney Fee Structure Hold Harmless Agreement", "SSNB Hold Harmless Agreement");
        mappedDocumentsName.put("Commission Sharing Agreement", "SSNB Commission Sharing Agreement");
        mappedDocumentsName.put("Final Quote", "SSNB Quotes");
        mappedDocumentsName.put("Lockin Form", "SSNB Quotes");
        mappedDocumentsName.put("Minor's Compromise or Court Order", "SSNB Minors Compromise");
        mappedDocumentsName.put("Settlements Agreement Release", "SSNB Settlement Agreement");
        mappedDocumentsName.put("Uniform Qualified Assignment (UQA form)", "SSNB Qualified Assignment");
        mappedDocumentsName.put("Court Order appointing Guardian/Conservator/Trust", "SSNB Letters of Guardianship");
        mappedDocumentsName.put("Copy of the complete Trust Document", "SSNB Trust Documents");
        mappedDocumentsName.put("Proof of Age for Joint", "SSNB Proof of Birth Joint");
        mappedDocumentsName.put("Copy of Workers Comp/Order Stipulation", "SSNB Workers Compensation");
        mappedDocumentsName.put("Proof of Age for annuitant", "SSNB Proof of Birth");
        mappedDocumentsName.put("W-9", "SSNB W-9");
        mappedDocumentsName.put("W-4P", "SSNB W4P");
        mappedDocumentsName.put("Non-Qualified Assignment Agreement", "SSNB Qualified Assignment");
        mappedDocumentsName.put("Periodic Payment Agreement", "SSNB PPA");

        return mappedDocumentsName;
    }

    //@author : Abhishek Routh
    //change date format
    public String dateFormatter(Object date) throws ParseException {

        String formattedDate;
        Date inputDate;
        if (date == null) {
            formattedDate = "";
        } else if (date.toString().equalsIgnoreCase("") || date.toString().equalsIgnoreCase("null")) {
            formattedDate = "";
        } else {

            formattedDate = date.toString();
            if (formattedDate.contains(" ")) {
                DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                inputDate = inputDateFormat.parse(formattedDate);

            } else if (formattedDate.contains("/")) {
                DateFormat inputDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                inputDate = inputDateFormat.parse(formattedDate);
            } else {
                DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                inputDate = inputDateFormat.parse(formattedDate);
            }

            DateFormat outputDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String outputDateString = outputDateFormat.format(inputDate);
            formattedDate = outputDateString;
        }
        return formattedDate;
    }


    //@author : Abhishek Routh
    //change date format
    public String dateFormatterMMDDYYYY(Object date) throws ParseException {

        String formattedDate;
        Date inputDate;
        if (date == null) {
            formattedDate = "";
        } else {

            formattedDate = date.toString();
            if (formattedDate.contains(" ")) {
                DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
                inputDate = inputDateFormat.parse(formattedDate);

            } else if (formattedDate.contains("/")) {
                DateFormat inputDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                inputDate = inputDateFormat.parse(formattedDate);
            } else {
                DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                inputDate = inputDateFormat.parse(formattedDate);
            }

            DateFormat outputDateFormat = new SimpleDateFormat("MMM dd yyyy");
            String outputDateString = outputDateFormat.format(inputDate);
            formattedDate = outputDateString;

            if (Integer.parseInt(String.valueOf(formattedDate.charAt(4))) == 0) {
                formattedDate = formattedDate.substring(0, 3) + "  " + formattedDate.substring(5, formattedDate.length());
            }

        }
        return formattedDate;
    }


    //@author : Abhishek Routh
    //Put Value in text field
    public void putTextValues(String elementXpath, String value) throws InterruptedException {

        getElementByXpath(elementXpath).sendKeys(Keys.BACK_SPACE);
        getElementByXpath(elementXpath).sendKeys(value);

    }


    //@author : Abhishek Routh
    //Put Value in text field
    public void putTextValues(WebElement element, String value) throws InterruptedException {
        element.sendKeys(Keys.CONTROL + "A");
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(value);

    }

    //@author : Abhishek Routh
    //Generate random numbers between max & min val (inclusive)
    public int generateRandomNumber(int min, int max) {

        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;

    }

    //gets session id for each browser sessions
    public String getSessionId() {

        SessionId session = ((RemoteWebDriver) this.driver).getSessionId();
        return session.toString();
    }

    //@author : Subhadeep Sen
    //Generate random String between max & min val (inclusive)
    public String generateRandomAlphaneumericValue(String text, int min, int max) {
        int randomNumber = 0;
        if (min == max) {
            randomNumber = max;
        } else {
            randomNumber = generateRandomNumber(min, max);
        }
        String randomValue = "";
        for (int i = 0; i < randomNumber; i++) {
            int randomIndex = generateRandomNumber(0, text.length() - 1);
            randomValue = randomValue + text.charAt(randomIndex);
        }

        return randomValue;
    }

    public void clearTextByXpath(String xpath) {
        WebElement ele = driver.findElement(By.xpath(xpath));
        new Actions(driver).click(ele)
                .pause(200).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .pause(200).sendKeys(Keys.BACK_SPACE).perform();
    }

    public void clickJavascriptExecutor(String locatorType) throws InterruptedException {
        WebElement element = getElementByXpath(locatorType);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) this.driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void clickJavascriptExecutor(WebElement element) throws InterruptedException {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) this.driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    //Abhishek Routh
    //Appends any character after and before every character of a string
    public String appendChars(String input, String character) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            sb.append(character).append(input.charAt(i));
        }
        sb.append(character);
        return sb.toString();
    }

    //To validate window title and close the window (Resource Links)
    public WebDriver switchToWindowAndClose(String title) {
        Set<String> allwindows = driver.getWindowHandles();
        for (String childwindow : allwindows) {
            driver.switchTo().window(childwindow);
            String ActualWindowTitle = driver.getTitle();
            if (ActualWindowTitle.contains(title)) {
                waitForPageLoad();
                driver.close();
                break;
            }
        }
        return driver;
    }

    public void closeAllChildWindow(String title) {

        Set<String> allwindows = driver.getWindowHandles();
        for (String childwindow : allwindows) {
            driver.switchTo().window(childwindow);
            String ActualWindowTitle = driver.getTitle();
            if (!ActualWindowTitle.contains(title)) {
                waitForPageLoad();
                driver.close();
            }
        }
    }


    public String generateRandomDate(String minDate, String maxDate) {

        String[] minDateArray = minDate.split("/");
        String[] maxDateArray = maxDate.split("/");
        String randomValue = "";
        for (int i = 0; i < minDateArray.length; i++) {

            int randomNumber = 0;
            if (Integer.parseInt(minDateArray[i]) < Integer.parseInt(maxDateArray[i])) {
                randomNumber = generateRandomNumber(Integer.parseInt(minDateArray[i]), Integer.parseInt(maxDateArray[i]));
            } else if (Integer.parseInt(minDateArray[i]) == Integer.parseInt(maxDateArray[i])) {
                randomNumber = Integer.parseInt(minDateArray[i]);
            } else if (Integer.parseInt(minDateArray[i]) > Integer.parseInt(maxDateArray[i])) {
                randomNumber = generateRandomNumber(Integer.parseInt(maxDateArray[i]), Integer.parseInt(minDateArray[i]));
            }


            if (i == 0 || i == 1) {
                if (randomNumber < 10) {
                    randomValue = randomValue + "0" + randomNumber + "/";
                } else {
                    randomValue = randomValue + randomNumber + "/";
                }
            } else if (i == 2) {
                if (randomNumber < 10) {
                    randomValue = randomValue + "000" + randomNumber;
                } else if (randomNumber < 100) {
                    randomValue = randomValue + "00" + randomNumber;
                } else if (randomNumber < 1000) {
                    randomValue = randomValue + "0" + randomNumber;
                } else {
                    randomValue = randomValue + randomNumber;
                }
            }

        }


        return randomValue;
    }

    public void mouseHover(String xpath) {
        Actions action = new Actions(this.driver);
        WebElement element = driver.findElement(By.xpath(xpath));
        action.moveToElement(element).perform();
    }

    public void mouseHover(WebElement element) {
        Actions action = new Actions(this.driver);
        action.moveToElement(element).perform();
    }

}


