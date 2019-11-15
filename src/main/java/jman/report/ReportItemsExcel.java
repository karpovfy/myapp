package jman.report;


import jman.domain.ItemView;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ReportItemsExcel extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<ItemView> revenueData = (List<ItemView>) model.get("revenueData");

        //create a worksheet
        HSSFSheet sheet = workbook.createSheet("Поручения отделу");
        HSSFRow header = sheet.createRow(0);

        HSSFCellStyle hStyle = workbook.createCellStyle();
        hStyle.setFillForegroundColor(HSSFColor.LIME.index);
        hStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


        header.createCell(0).setCellValue("№ пункта");
        header.createCell(1).setCellValue("Содержание поручения(пункта)");
        header.createCell(2).setCellValue("Срок исполнения");
        header.createCell(3).setCellValue("Тип документа");
        header.createCell(4).setCellValue("Контролирующий отдел");


        sheet.setColumnWidth(0,2400);
        sheet.setColumnWidth(1,30000);
        sheet.setColumnWidth(2,4000);
        sheet.setColumnWidth(3,30000);
        sheet.setColumnWidth(4,24000);


        header.setRowStyle(hStyle);

        HSSFCellStyle style = workbook.createCellStyle();
        //style.setFillForegroundColor(HSSFColor.LIME.index);
        //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setWrapText(true);
        //style.setVerticalAlignment(style.getVerticalAlignment());

        int rowNum = 1;
        for (ItemView entry : revenueData) {
            //create the row data
            HSSFRow row = sheet.createRow(rowNum++);

            HSSFCell cell = row.createCell(0);
            HSSFCell cell1= row.createCell(1);

            cell.setCellValue(entry.getItemNum());
            cell1.setCellStyle(style);
            cell1.setCellValue(entry.getItemDsc());
            //row.createCell(1).setCellValue(entry.getItemDsc());
            row.createCell(2).setCellValue(entry.getFcheckTime());
            row.createCell(3).setCellValue(entry.getOrderTypeName());
            row.createCell(4).setCellValue(entry.getContDeptName());
        }
    }
}
