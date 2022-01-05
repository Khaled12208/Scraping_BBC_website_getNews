package com.bbc;

import com.bbc.framework.testconfiguration.*;
import com.bbc.framework.webdriverfactory.DriverFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import javax.swing.text.Style;
import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SeleniumTask {

    private final TestConfiguration config = new TestConfigurationBuilder()
            .setBrowser(BrowserType.CHROME)
            .setExecutionMode(ExecutionMode.HEADFULL)
            .Build();
    private WebDriver driver;
    private String newsFileName;

    @Test
    public void Task() throws IOException {
        Browser_Init("https://www.bbc.com/");
        List<String> newsLinks = homePage_findAllNewsLinks();
        List<String> newsTitls = homePage_findAllNewsTitls();
        List<String> desc = getShortDesc(newsLinks);
        storeLinksAndTitlsIntoFile(newsLinks,newsTitls);
        fetchDetailsToFile(desc);

    }


    private List<String> homePage_findAllNewsLinks() {
        List<String> linksTexts = new ArrayList<>();
        List<WebElement> linksElement = driver.findElements(By.xpath("//a[@class='media__link'][@href]"));


        for (WebElement element : linksElement) {
            linksTexts.add(element.getAttribute("href"));
        }
        return linksTexts;

    }

    private List<String> homePage_findAllNewsTitls() {
        List<String> linksTexts = new ArrayList<>();

        List<WebElement> linksElement = driver.findElements(By.xpath("//a[@class='media__link'][@href]"));
        for (WebElement element : linksElement) {
            linksTexts.add(element.getText());
        }
        return linksTexts;

    }


    private void storeLinksAndTitlsIntoFile(List<String> linksList, List<String> titls) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("News Links");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        Cell cell2 = row.createCell(1);
        cell.setCellValue("BBC news links");
        cell2.setCellValue("Titles");
        CellStyle mainHeaderCellStyle = workbook.createCellStyle();
        mainHeaderCellStyle.setWrapText(true);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        mainHeaderCellStyle.setFont(font);
        cell.setCellStyle(mainHeaderCellStyle);
        cell2.setCellStyle(mainHeaderCellStyle);

        CellStyle regularCellStyle = workbook.createCellStyle();
        regularCellStyle.setWrapText(true);
        regularCellStyle.setAlignment(HorizontalAlignment.LEFT);
        regularCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);


        for (int i = 0; i < linksList.size(); i++) {
            Row rowLink = sheet.createRow(i + 1);
            Cell cellLink = rowLink.createCell(0);
            Cell cellTitle = rowLink.createCell(1);
            cellLink.setCellValue(linksList.get(i));
            cellLink.setCellStyle(regularCellStyle);
            cellTitle.setCellValue(titls.get(i));
            cellTitle.setCellStyle(regularCellStyle);

        }
        sheet.setColumnWidth(0, 25 * 400);
        sheet.autoSizeColumn(1);


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm");
        LocalDateTime now = LocalDateTime.now();
        newsFileName = dtf.format(now) + "_NewsLinks.xlsx";
        OutputStream initialStream = new FileOutputStream(new File(System.getProperty("user.dir"), newsFileName));
        workbook.write(initialStream);
    }

    private  List<String> getShortDesc(List<String> Links) {
        List<String> dec = new LinkedList<>();
        driver.navigate().to(Links.get(0));
        if (driver.findElement(By.xpath("/html/body/div[4]")).getAttribute("style") != null) {
            driver.findElement(By.xpath("/html/body/div[3]/div/button")).click();
        }

        for (String url : Links) {
            driver.navigate().to(url);
            try
            {
                dec.add(driver.findElement(By.xpath("//div[@data-component=\"text-block\"][1]//b")).getText());

            }catch (Exception e)
            {
                try {
                    dec.add(driver.findElement(By.xpath("//p[@class=\"qa-introduction gel-pica-bold\"]//span")).getText());

                }catch (Exception x)
                {
                    try {
                        dec.add(driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[5]/div/div[1]/article/div[2]/div/p/b")).getText());


                    }catch (Exception y)
                    {
                        try {
                            dec.add(driver.findElement(By.xpath("//article//p")).getText());

                        }catch (Exception z)
                        {
                            dec.add("skipp");

                        }
                    }

                }


            }

        }
        return dec;
    }


    private void Browser_Init(String url) {
        try {
            driver = new DriverFactory(config).OpenBrowser();

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(url);

    }
    public void fetchDetailsToFile(List<String> descList ) throws IOException { FileInputStream inputStream=null;
        try {
            inputStream= new FileInputStream(new File(System.getProperty("user.dir"), newsFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheet("News Links");
        Row row = sheet.getRow(0);
        Cell cell = row.createCell(3);
        cell.setCellValue("details");
        CellStyle mainHeaderCellStyle = workbook.createCellStyle();
        mainHeaderCellStyle.setWrapText(true);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        mainHeaderCellStyle.setFont(font);
        cell.setCellStyle(mainHeaderCellStyle);

        CellStyle regularCellStyle = workbook.createCellStyle();
        regularCellStyle.setWrapText(true);
        regularCellStyle.setAlignment(HorizontalAlignment.LEFT);
        regularCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        for (int i = 0; i < descList.size(); i++) {
            Row rowLink = sheet.getRow(i + 1);
            Cell cellDesc = rowLink.createCell(3);
            cellDesc.setCellValue(descList.get(i));
            cellDesc.setCellStyle(regularCellStyle);

        }
        sheet.autoSizeColumn(3);


        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream outputStream = new FileOutputStream(newsFileName);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();


    }



}
