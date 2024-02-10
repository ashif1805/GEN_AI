package com.gen.ai.utility;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Configuration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@Slf4j
public class DocUtility {
    public void createNewDoc(String docName, String filePath) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
        Date date = new Date();
        docName=docName+"_"+dateFormat.format(date);

        String docFileName=filePath+"\\"+docName+".docx";
        log.info("docFileName: "+docFileName);
        log.info("inside createNewDoc");
        try {
            XWPFDocument testDoc=new XWPFDocument();
            FileOutputStream newDoc=new FileOutputStream(new File(docFileName));
            testDoc.write(newDoc);
            testDoc.close();
            newDoc.close();
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception();
        }
    }

    public  void addScrShot(String testDocPath, String caption, String TestCaseID, WebDriver driver) throws Exception {
        log.info("inside addScrShot");
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
            Date date = new Date();
            testDocPath=testDocPath+"_"+dateFormat.format(date);

            XWPFDocument testDoc=new XWPFDocument(OPCPackage.open(testDocPath+".docx"));
            XWPFRun run=testDoc.createParagraph().createRun();
            TakesScreenshot scrnShot=((TakesScreenshot)driver);
            File srcFile =scrnShot.getScreenshotAs(OutputType.FILE);
            File destFile=new File(testDocPath+".jpg");
            FileUtils.copyFile(srcFile,destFile);
            BufferedImage img= ImageIO.read(destFile);
            Dimension dim=new Dimension(img.getWidth(),img.getHeight());
            double imgWidth=dim.getWidth();
            double imgHeight=dim.getHeight();
            double scaling= 1.0;
            if(imgWidth > (72*6)) scaling=(72*6)/imgWidth;
            InputStream pic=new FileInputStream(testDocPath+".jpg");
            run.addBreak(BreakType.TEXT_WRAPPING);
            run.setText(caption);
            run.addPicture(pic, XWPFDocument.PICTURE_TYPE_JPEG, TestCaseID, Units.toEMU(imgWidth*scaling), Units.toEMU(imgHeight*scaling));
            pic.close();
            destFile.delete();
            FileOutputStream newDoc=new FileOutputStream(testDocPath+".docx", true);
            testDoc.write(newDoc);
            newDoc.close();
            testDoc.close();
        }catch(Exception e) {
            log.error("Exception in addScrShot: " +e);
            throw new Exception();
        }
    }
}
