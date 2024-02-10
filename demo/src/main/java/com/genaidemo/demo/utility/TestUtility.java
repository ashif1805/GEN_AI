package com.genaidemo.demo.utility;

import com.genaidemo.demo.annotation.LazyAutowired;
import com.genaidemo.demo.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.InputStreamEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import com.genaidemo.demo.config.AppConfig;
import com.genaidemo.demo.config.EnvironmentProperties;

@Configuration
@Slf4j
public class TestUtility {
    @LazyAutowired
    private EnvironmentProperties environmentProperties;
    @LazyAutowired
    private AppConfig AppConfig;
    @LazyAutowired
    private DocUtility docUtility;
    private XSSFSheet dataSheet;

    public Map<String, List<String>> getTestDataFromDataSheet(String sheetName) throws IOException {

        Map<String, List<String>> testDataSheet = new HashMap<>();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream fileInputStream = classLoader.getResourceAsStream(this.environmentProperties.getTestDataSheet());
        XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
        XSSFSheet dataSheet = workBook.getSheet(sheetName);

        Iterator<Row> rowIterator = dataSheet.iterator();
        int colNum;
        String testNum = null;

        while (rowIterator.hasNext()) {

            List<String> colDataList = new ArrayList<>();
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            Iterator<Cell> cellIterator = row.cellIterator();
            colNum = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (colNum == 0) {
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case NUMERIC:
                            testNum = NumberToTextConverter.toText(cell.getNumericCellValue());
                            break;
                        case STRING:
                            testNum = cell.getStringCellValue();
                            break;
                    }
                    // testNum = cell.getStringCellValue();
                } else {
                    CellType cellType = cell.getCellType();

                    switch (cellType) {
                        case NUMERIC:
                            colDataList.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                            break;
                        case STRING:
                            colDataList.add(cell.getStringCellValue());
                            break;
                    }
                }

                colNum++;
            }
            testDataSheet.put(testNum, colDataList);
        }
        fileInputStream.close();
        return testDataSheet;
    }

    public Map getServiceConfiguration(String serviceName) {
        Map<String, Map<String, String>> allServicePropertyMap = environmentProperties.getService();
        Map<String, String> serviceProperty = allServicePropertyMap.get(serviceName);
        return serviceProperty;
    }

    /*public int callService(Map<String, String> serviceProperty, String annuataintSSN) throws IOException {
        String requestFilePath = serviceProperty.get("Request");
        String serviceEndPoint = serviceProperty.get("Endpoint");
        log.info("requestFilePath:" + requestFilePath);
        log.info("serviceEndPoint:" + serviceEndPoint);

        File file = new File(String.valueOf(ResourceUtils.getFile("classpath:" + requestFilePath)));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String fileContentXML = FileUtils.readFileToString(file, StandardCharsets.UTF_8).replace("SSN", annuataintSSN);
        log.info("Request :" + fileContentXML);
        InputStream inputStreamXML = new ByteArrayInputStream(fileContentXML.getBytes(StandardCharsets.UTF_8));

        HttpPost request = new HttpPost(serviceEndPoint);
        request.addHeader("Content-Type", "text/xml");
        request.setEntity(new InputStreamEntity(inputStreamXML));
        CloseableHttpResponse response = httpClient.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        log.info("Response :" + EntityUtils.toString(response.getEntity()));
        return statusCode;
    }*/

    public void createNewTestEvidence(String docName) throws Exception {
        if (this.environmentProperties.isEnableTestEvidence()) {
            String docFilePath = System.getProperty("user.dir") + AppConfig.getTestEvidenceFolder();
            docUtility.createNewDoc(docName, docFilePath);
        }

    }

    public Map<String, String> getTestDataFromDataSheetNew(String sheetName, String testCaseNo) throws IOException {
        HashMap<String, String> sheetDetails = new HashMap<String, String>();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream fileInputStream = classLoader.getResourceAsStream(this.environmentProperties.getTestDataSheet());
        XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
        XSSFSheet dataSheet = workBook.getSheet(sheetName);
        int noOfRows = dataSheet.getPhysicalNumberOfRows();
        for (int i = 1; i < noOfRows; i++) {
            HashMap<String, String> Details = new HashMap<String, String>();
            Details = getRowDetails(dataSheet, i);

            if (Details.get("TestCase_Name").equals(testCaseNo)) {
                sheetDetails = Details;
                break;
            }
        }
        fileInputStream.close();
        return sheetDetails;
    }

    public void addingScreenShoot(String docName, String screenShootCaption, WebDriver webDriver) throws Exception {
        if (this.environmentProperties.isEnableTestEvidence()) {
            log.info("addingScreenShoot" + environmentProperties.isEnableTestEvidence());
            String docFilePath = System.getProperty("user.dir") + AppConfig.getTestEvidenceFolder() + "\\" + docName;
            docUtility.addScrShot(docFilePath, screenShootCaption, docName, webDriver);
        }
    }

    public HashMap<String, String> getRowDetails(XSSFSheet dataSheet, int rowNum) {
        int noOfCols = dataSheet.getRow(2).getPhysicalNumberOfCells();
        HashMap<String, String> testDataDetails = new HashMap<String, String>();
        for (int i = 0; i < noOfCols; i++) {
            testDataDetails.put(excelRead(dataSheet, 2, i).trim(), excelRead(dataSheet, rowNum, i).trim());
        }
        return testDataDetails;
    }

    public String excelRead(XSSFSheet dataSheet, int rownum, int cellnum) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(dataSheet.getRow(rownum).getCell(cellnum)).trim();
    }

}

