package jman.report;

import jman.domain.ViewExDepts;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import org.odftoolkit.odfdom.OdfFileDom;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.office.OdfOfficeAutomaticStyles;

public class ReportDeptItemsOds {
    protected void buildOdsDocument(Map model, OdfSpreadsheetDocument odfSpreadsheetDocument,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<ViewExDepts> revenueData = (List<ViewExDepts>) model.get("revenueData");
        //create a DOM
        OdfFileDom contentDom; // the document object model for content.xml
        OdfFileDom stylesDom; // the document object model for styles.xml // the office:automatic-styles element in content.xml
        OdfOfficeAutomaticStyles contentAutoStyles;

        int rowNum = 1;
        for (ViewExDepts entry : revenueData) {
            //create the row data ++++

        }
    }
}
