package jman.report;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ExcelRevenueReportView extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Map<String,String> revenueData = (Map<String,String>) model.get("revenueData");

        //create a worksheet
        HSSFSheet sheet = workbook.createSheet("Поручения отделу");
        HSSFRow header = sheet.createRow(0);
        sheet.setColumnWidth(0,200);
        sheet.setColumnWidth(1,200);
        sheet.setColumnWidth(2,200);
        sheet.setColumnWidth(3,200);
        sheet.setColumnWidth(4,200);

        header.createCell(0).setCellValue("Тип документа");
        header.createCell(1).setCellValue("№ документа");
        header.createCell(2).setCellValue("№ пункта");
        header.createCell(3).setCellValue("Содержание поручения(пункта)");
        header.createCell(4).setCellValue("Срок исполнения");

        int rowNum = 1;
        for (Map.Entry<String, String> entry : revenueData.entrySet()) {
            //create the row data
            HSSFRow row = sheet.createRow(rowNum++);
            HSSFCell cell = row.createCell(0);

            HSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.LIME.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            cell.setCellValue(entry.getKey());
            cell.setCellStyle(style);


            row.createCell(1).setCellValue(entry.getValue());
        }
    }
}
