package jman.report;

import jman.domain.ViewExDepts;
import jman.domainopt.ItemDeptView;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ReportDeptItems extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<ItemDeptView> items = (List<ItemDeptView>) model.get("deptItems");

        //create a worksheet
        HSSFSheet sheet = workbook.createSheet("Поручения УФССП РОССИИ по РБ");
        HSSFCellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setAlignment(CellStyle.ALIGN_CENTER);

        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("Тип документа");
        header.createCell(1).setCellValue("№ документа");
        header.createCell(2).setCellValue("№ пункта");
        header.createCell(3).setCellValue("Содержание поручения(пункта)");
        header.createCell(4).setCellValue("Срок исполнения");
        header.createCell(5).setCellValue("Отдел исполнитель");
        header.createCell(6).setCellValue("Контролирующий отдел");
        header.createCell(7).setCellValue("Статус");
        header.createCell(7).setCellValue("Дата загрузки");

        sheet.setColumnWidth(0,30000);
        sheet.setColumnWidth(1,6000);
        sheet.setColumnWidth(2,4000);
        sheet.setColumnWidth(3,30000);
        sheet.setColumnWidth(4,4000);
        sheet.setColumnWidth(5,30000);
        sheet.setColumnWidth(6,30000);
        sheet.setColumnWidth(6,4000);

        short ht = 540;
        header.setHeight(ht);
        for (int i = 0; i < 8; i++){ header.getCell(i).setCellStyle(style); }


        int rowNum = 1;
        short brd = CellStyle.BORDER_THIN;
        short fp = CellStyle.SOLID_FOREGROUND;
        HSSFCellStyle stSuccess = workbook.createCellStyle();
        HSSFCellStyle stFail = workbook.createCellStyle();
        HSSFCellStyle stComing = workbook.createCellStyle();

        //== WRAP CELL TEXT
        stSuccess.setWrapText(true);
        stFail.setWrapText(true);
        stComing.setWrapText(true);

        //== SET BGCOLOR
        stSuccess.setFillForegroundColor(IndexedColors.LIME.getIndex());
        stSuccess.setFillPattern(fp);

        stFail.setFillForegroundColor(IndexedColors.CORAL.getIndex());
        stFail.setFillPattern(fp);

        stComing.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        stComing.setFillPattern(fp);

        //== CELL TEXT VERTICAL ALIGN TOP
        stSuccess.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        stFail.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        stComing.setVerticalAlignment(CellStyle.VERTICAL_TOP);

        //== BORDER SET
        stSuccess.setBorderTop(brd); stSuccess.setBorderLeft(brd); stSuccess.setBorderBottom(brd); stSuccess.setBorderRight(brd);
        stFail.setBorderTop(brd); stFail.setBorderLeft(brd); stFail.setBorderBottom(brd); stFail.setBorderRight(brd);
        stComing.setBorderTop(brd); stComing.setBorderLeft(brd); stComing.setBorderBottom(brd); stComing.setBorderRight(brd);

        for (ItemDeptView entry : items) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd.MM.yyyy");
            String dateLoad = "";
            if(entry.getReportDate() == null){

            }
            HSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entry.getOrderTypeName());
            row.createCell(1).setCellValue(entry.getDocumentNum());
            row.createCell(2).setCellValue(entry.getItemNum());
            row.createCell(3).setCellValue(entry.getItemDsc());
            row.createCell(4).setCellValue(dateFormat.format(entry.getCheckTime()));
            row.createCell(5).setCellValue(entry.getExdeptName());
            row.createCell(6).setCellValue(entry.getDeptName());
            row.createCell(7).setCellValue(dateFormat2.format(entry.getReportDate()));


            if(entry.getStatus() == 1){
                row.createCell(7).setCellValue("1");
                for (int i = 0; i < 8; i++){ row.getCell(i).setCellStyle(stSuccess); }

            }else{
                if(entry.getCheckTime().before(entry.getMcurDate())){
                    row.createCell(7).setCellValue("0");
                    for (int i = 0; i < 8; i++){ row.getCell(i).setCellStyle(stFail); }
                }else{
                    row.createCell(7).setCellValue("2");
                    for (int i = 0; i < 8; i++){ row.getCell(i).setCellStyle(stComing); }
                }
            }


        }
    }
}
