package jman.web;

import jman.domain.*;
import jman.domainGrid.ItemDeptViewGrid;
import jman.domainGrid.ItemDeptViewRow;
import jman.domainopt.ItemDeptView;
import jman.domainopt.ItemFullView;
import jman.service.*;
import jman.serviceopt.ItemDeptViewService;
import jman.serviceopt.ItemFullViewService;
import jman.testing.Testmodel;
import jman.testing.TestmodelService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.tiles.ListAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    //=== ok list
    @Autowired
    private UserService userService;

    //=== check list
    @Autowired
    private OrderViewService orderViewService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private DoctypeService doctypeService;
    @Autowired
    private ItemService itemService;
    //@Autowired
    //private TestmodelService testmodelService;
    @Autowired
    private ItemDeptService itemDeptService;
    @Autowired
    private ViewExDeptsService viewExDeptsService;
    @Autowired
    private OrderFileService orderFileService;
    @Autowired
    private ReportFileService reportFileService;
    @Autowired
    private DeptFilesService deptFilesService;
    @Autowired
    private ItemViewService itemViewService;
    @Autowired
    private AnalyticsDeptsService analyticsDeptsService;

    @Autowired
    private KipSettingService kipSettingService;

    //=== new optimized-services
    @Autowired
    private ItemDeptViewService itemDeptViewService;
    @Autowired
    private ItemFullViewService itemFullViewService;
    @Autowired
    private MessageService messageService;

    //private String rPath = "C:/apache-tomcat-6.0.35/webapps/ROOT/files";

    //=== ABOUT
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String aboutSys(Model model) {
        jman.domain.User userBD = populateCurrentUser();
        model.addAttribute("username", userBD.getFullname());
        model.addAttribute("user", userBD);
        return "about";
    }

    //=== INBOX
    @RequestMapping(value = "/inbox", method = RequestMethod.GET)
    public String inBox(Model model) {
        jman.domain.User userBD = populateCurrentUser();
        model.addAttribute("username", userBD.getFullname()+"");
        model.addAttribute("user", userBD);
        model.addAttribute("card", deptService.getDayStatusCard(userBD.getDeptId()));
        model.addAttribute("message",messageService.getMessage(1));
        return "inboxopt"; //return "inbox";
    }

    //=== ALLBOX
    @RequestMapping(value = "/allbox", method = RequestMethod.GET)
    public String allBox(Model model) {
        jman.domain.User userBD = populateCurrentUser();
        model.addAttribute("username", userBD.getFullname());
        model.addAttribute("user", userBD);
        return "allbox";
    }

    //=== OUTBOX
    @RequestMapping(value = "/outbox", method = RequestMethod.GET)
    public String outBox(Model model) {
        jman.domain.User userBD = populateCurrentUser();
        System.out.println(userBD.getUserId());
        Dept dept = deptService.getDept(userBD.getDeptId());
        model.addAttribute("username", userBD.getFullname());
        model.addAttribute("user", userBD);
        model.addAttribute("udept", dept);
        model.addAttribute("card", deptService.getDayStatusCardOut(userBD.getDeptId()));
        model.addAttribute("message",messageService.getMessage(1));
        return "outbox";
    }

    //=== ANALYTICS
    @RequestMapping(value = "/analytics", method = RequestMethod.GET)
    public String analytics(Model model,
                            @RequestParam(value = "start_date", required = false, defaultValue = "") String startDate,
                            @RequestParam(value = "end_date", required = false, defaultValue = "") String endDate,
                            @RequestParam(value = "dept_type", required = false, defaultValue = "1") Short deptType){
        jman.domain.User userBD = populateCurrentUser();
        System.out.println(userBD.getUserId());
        List<AnalyticsDepts> depts = analyticsDeptsService.getList();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dateFormat2= new SimpleDateFormat("dd.MM.yyyy");

        try {
            model.addAttribute("cards", analyticsDeptsService.getDeptCards(new SimpleDateFormat("yyyy-MM-dd").format(dateFormat.parse(startDate)),new SimpleDateFormat("yyyy-MM-dd").format(dateFormat.parse(endDate)),deptType));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        model.addAttribute("user", userBD);
        model.addAttribute("depts", depts);
        model.addAttribute("stDate",startDate);
        model.addAttribute("enDate",endDate);
        model.addAttribute("deptType",deptType);

        return "analytics";
    }

    //=== RESOLUTIONS OKO
    @RequestMapping(value = "/resolutions", method = RequestMethod.GET)
    public String resolutions(Model model){
        jman.domain.User userBD = populateCurrentUser();
        model.addAttribute("username", userBD.getFullname());
        model.addAttribute("user", userBD);
        return "resolutions";
    }

    @RequestMapping(value = "/analytics/{id}", method = RequestMethod.GET)
    public String analyticsDept(@PathVariable("id") int id, Model model){
        jman.domain.User userBD = populateCurrentUser();
        System.out.println(userBD.getUserId());
        List<AnalyticsDepts> depts = analyticsDeptsService.getList();

        model.addAttribute("user", userBD);
        model.addAttribute("depts", depts);
        return "analytics";
    }

    //=== DOCUMENTS LIST
    @RequestMapping(value = "/cabinet")
    public String cabinet(Model model){
        jman.domain.User userBD = populateCurrentUser();
        System.out.println(userBD.getUserId());

        model.addAttribute("user", userBD);
        return "cabinet";
    }

    //=== ORDER ITEM INF AND EXECUTOR LIST
    @RequestMapping(value = "/order/{orderId}/item/{itemId}", method = RequestMethod.GET)
    public String orderItem(@PathVariable("orderId") int id, @PathVariable("itemId") int itemId, Model model) {
        jman.domain.User userBD = populateCurrentUser();
        List<ItemDeptView> itemDeptViews = itemDeptViewService.getListItemOrder(itemId);
        ItemFullView itemFullView = itemFullViewService.getItem(itemId);

        //no cashing
        System.out.println(userBD.getUserId());
        System.out.println(itemFullView.getOrderItemId());
        for(ItemDeptView d : itemDeptViews){ System.out.println(d.getItemDeptId()); break;}

        model.addAttribute("itemdepts", itemDeptViews);
        model.addAttribute("it", itemFullView);
        model.addAttribute("user", userBD);
        return "orderitem";
    }

    @RequestMapping(value = "/order/{orderId}/item/{itemId}/{itemDeptId}", method = RequestMethod.GET)
    public String orderItemDept(@PathVariable("orderId") int id,
                                @PathVariable("itemId") int itemId,
                                @PathVariable("itemDeptId") int itemDeptId,
                                Model model) {
        ItemDept cdept = itemDeptService.getItemDept(itemDeptId);
        jman.domain.User userBD = populateCurrentUser();
        ItemFullView itemFullView = itemFullViewService.getItem(itemId);
        Dept dept = deptService.getDept(cdept.getDeptId());
        List<DeptFiles> deptFileses = deptFilesService.getDeptFiles(itemDeptId);


        String ssss = "123456";
        for (DeptFiles d : deptFileses){
            ssss+= d.getFilename();
        }

        System.out.println(userBD.getUserId());
        System.out.println(itemFullView.getOrderItemId());
        System.out.println(ssss.substring(1,2));


        model.addAttribute("user", userBD);
        model.addAttribute("itdept", cdept);
        model.addAttribute("it", itemFullView);
        model.addAttribute("dept", dept);
        model.addAttribute("files", deptFileses);
        return "orderitemdept";
    }

    @RequestMapping(value = "/order/{orderId}/item/{itemId}/{itemDeptId}", method = RequestMethod.POST)
    public String orderItemDeptFile(
                @RequestParam("orderfile") MultipartFile file,
                @PathVariable("orderId") int id,
                @PathVariable("itemId") int itemId,
                @PathVariable("itemDeptId") int itemDeptId) throws IOException {
        if(!file.isEmpty() && (file.getSize()/1024>30000)){        //model.addAttribute("order",orderViewService.getViewOrder(id));  model.addAttribute("dept", deptService.getDept(user.getDeptId()));            model.addAttribute("user", user);   model.addAttribute("doctypes", doctypeService.getOrderTypes());            model.addAttribute("depts", deptService.getDepts());    model.addAttribute("item", itemId); model.addAttribute("cdept", viewExDeptsService.getViewExDept(itemId));
            jman.domain.User user = populateCurrentUser();
            System.out.println(user.getUserId());

            ReportFile report = new ReportFile();
            report.setStatusId(3);
            String fln = file.getOriginalFilename();
            String ext = FilenameUtils.getExtension(fln).toLowerCase();
            String genflile = itemDeptId+"_rep."+ext;

            report.setFilename(file.getOriginalFilename());
            report.setGenFileName(genflile);
            report.setFpath("/depts/"+user.getDeptId()+"/" + genflile);
            report.setCreatorId(user.getUserId());
            report.setItemDeptId(itemDeptId);

            reportFileService.saveReport(report);
            genflile = itemDeptId+"_"+report.getResponseFileId()+"_rep."+ext;
            report.setGenFileName(genflile);
            report.setFpath("/depts/"+user.getDeptId()+"/" + genflile);
            report.setStatusId(1);
            reportFileService.saveReport(report);

            KipSetting st = kipSettingService.getSetting();

            String destination = st.getPathString() + "/depts/"+user.getDeptId()+"/" + genflile;
            File fl = new File(destination);
            if(!fl.exists()) {
                fl.mkdirs();
            }
            file.transferTo(fl);
            //=== refresh cash
            List<DeptFiles> fls = deptFilesService.getDeptFiles(itemDeptId);
            String buf = "";
            for(DeptFiles d : fls){ buf = ""+d.getResponseFileId(); break; }
            List<ItemDeptView> vws = itemDeptViewService.getListItem(itemId);
            for(ItemDeptView d: vws){ buf = ""+d.getItemDeptId(); break; }
        }
        return "redirect:/order/"+id+"/item/"+itemId+"/"+itemDeptId;
    }

    @RequestMapping(value = "/add-order", method = RequestMethod.GET)
    public String addOrder(Model model) {
        jman.domain.User user = populateCurrentUser();
        System.out.println(user.getUserId());

        Dept dept = deptService.getDept(user.getDeptId());
        List<Dept> depts = deptService.getDepts();
        List<OrderType> orderTypes = doctypeService.getOrderTypes();

        model.addAttribute("dept", dept);
        model.addAttribute("user", user);
        model.addAttribute("doctypes", orderTypes);
        model.addAttribute("depts", depts);
        return "addorder";
    }

    @RequestMapping(value = "/new-resolution", method = RequestMethod.GET)
    public String newResolution(Model model) {
        jman.domain.User user = populateCurrentUser();

        Dept dept = deptService.getDept(user.getDeptId());
        List<Dept> depts = deptService.getDepts();
        List<OrderType> orderTypes = doctypeService.getOrderTypes();

        model.addAttribute("dept", dept);
        model.addAttribute("username", user.getFullname());
        model.addAttribute("user", user);
        model.addAttribute("doctypes", orderTypes);
        model.addAttribute("depts", depts);
        return "new-resolution";
    }

    @RequestMapping(value = "/add-order", method = RequestMethod.POST)
    public String saveOrder(Model model,
                            @RequestParam("orderfile") MultipartFile file,
                            @RequestParam(value = "docname", required = false, defaultValue = "") String docname,
                            @RequestParam(value = "ordertype", required = false, defaultValue = "0") int orderType,
                            @RequestParam(value = "docnum", required = false, defaultValue = "") String docnum,
                            @RequestParam(value = "docdate", required = false, defaultValue = "") String docdate
    ) throws IOException {
        jman.domain.User user = populateCurrentUser();
        String flname = file.getOriginalFilename();
        boolean check = false;

        if(docname.equals("")){
            check = true;
        }
        if(orderType==0){
            check = true;
        }
        if(docnum.equals("")){
            check = true;
        }
        if(docdate.equals("")){
            check = true;
        }

        if(check){
            return "redirect:/add-order";
        }else{
            SimpleDateFormat odf = new SimpleDateFormat("dd.MM.yyyy");
            Date dt = null;
            try{
                dt = odf.parse(docdate);
            }catch (Exception e){ }

            String ext = FilenameUtils.getExtension(flname);
            Order o = new Order();
            o.setCreatorId(user.getUserId());
            o.setCreatorDeptId(user.getDeptId());
            o.setOrderName(docname);
            o.setDocumentType(orderType);
            o.setDocumentNum(docnum);
            o.setDocumentDate(dt);
            orderService.saveOrder(o);

            OrderFile fl = new OrderFile();
            String sName = o.getOrderId()+"_order."+ext;

            fl.setFilename(flname);
            fl.setGenFileName(sName);

            KipSetting st = kipSettingService.getSetting();

            String destination = st.getPathString() + "/owner/"  + sName;
            File tfl = new File(destination);
            if(!tfl.exists()) {
                tfl.mkdirs();
            }
            file.transferTo(tfl);
            fl.setFpath("/files/owner/"+sName);
            fl.setCreatorId(user.getUserId());

            orderFileService.addFile(fl);
            o.setOrderFileId(fl.getOrderFileId());
            orderService.saveOrder(o);
            // Testmodel t = new Testmodel(); t.setTestText(flname); testmodelService.saveTest(t);
            return "redirect:/order/"+o.getOrderId()+"/items";
        }
    }

    @RequestMapping(value = "/order/{orderId}/items", method = RequestMethod.GET)
    public String orderItems(@PathVariable("orderId") int id, Model model) {
        jman.domain.User user = populateCurrentUser();
        model.addAttribute("order",orderViewService.getViewOrder(id));
        model.addAttribute("dept", deptService.getDept(user.getDeptId()));
        model.addAttribute("user", user);
        model.addAttribute("doctypes", doctypeService.getOrderTypes());
        model.addAttribute("depts", deptService.getDepts());
        model.addAttribute("items",itemService.getOrderItems(id));
        return "orderitems";
    }

    @RequestMapping(value = "/order/{orderId}/additem", method = RequestMethod.GET)
    public String addItems(@PathVariable("orderId") int id, Model model) {
        jman.domain.User user = populateCurrentUser();
        model.addAttribute("order",id);
        model.addAttribute("dept", deptService.getDept(user.getDeptId()));
        model.addAttribute("user", user);
        model.addAttribute("doctypes", doctypeService.getOrderTypes());
        model.addAttribute("depts", deptService.getDepts());
        return "additem";
    }

    @RequestMapping(value = "/order/{orderId}/additem", method = RequestMethod.POST)
    public String saveItems(
            @PathVariable("orderId") int id,
            @RequestParam(value = "itemnum", required = false, defaultValue = "") String itemnum,
            @RequestParam(value = "itemdate", required = false, defaultValue = "0") String itemdate,
            @RequestParam(value = "itemdsc", required = false, defaultValue = "") String itemdsc,
            @RequestParam(value = "contdept", required = false, defaultValue = "0") int contdept,
            @RequestParam(value = "depts", required = false, defaultValue = "0") int[] depts
            ) {
        jman.domain.User user = populateCurrentUser();
        SimpleDateFormat odf = new SimpleDateFormat("dd.MM.yyyy");

        Item i = new Item();
        i.setOrderId(id);
        i.setItemNum(itemnum);
        i.setItemDsc(itemdsc);
        i.setControlDeptId(contdept);
        i.setCreatorId(user.getUserId());

        Date dt = null;
        try{
            dt = odf.parse(itemdate);
        }catch (Exception e){

        }
        i.setCheckTime(dt);
        itemService.saveItem(i);

        for(int d: depts){
            ItemDept dp = new ItemDept();
            dp.setDeptId(d);
            dp.setOrderItemId(i.getOrderItemId());
            itemDeptService.saveItemDept(dp);
        }
        return "redirect:/order/"+id+"/items";
    }

    @RequestMapping(value = "/cabinet/list")
    public @ResponseBody JqGridResponse orderList(@RequestParam(value = "tdoc", required = false, defaultValue = "0") int tdoc,
                                                  @RequestParam(value = "dsdate", required = false, defaultValue = "") String dsdt,
                                                  @RequestParam(value = "dedate", required = false, defaultValue = "") String dedt,
                                                  @RequestParam(value = "csdate", required = false, defaultValue = "") String csdt,
                                                  @RequestParam(value = "cedate", required = false, defaultValue = "") String cedt,
                                                  @RequestParam(value = "sidx", required = false, defaultValue = "id") String sidx,
                                                  @RequestParam(value = "sord", required = false, defaultValue = "desc") String sord,
                                                  @RequestParam(value = "docname", required = false, defaultValue = "") String docname,
                                                  @RequestParam(value = "docnumber", required = false, defaultValue = "") String docnumber,
                                                  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                  @RequestParam(value = "rows", required = false, defaultValue = "25") int rows,
                                                  Model model){

        SimpleDateFormat odf = new SimpleDateFormat("dd.MM.yyyy");
        JqGridResponse response = new JqGridResponse();
        List<JqGridRow> orders = new ArrayList<JqGridRow>();
        Date date = null;

        StringBuilder query = new StringBuilder("");
        List<String> whereQuery = new ArrayList<String>();
        String whereStr = "";

        if(tdoc>0){
            whereQuery.add("documentType = " + tdoc);
        }
        if(!docname.equals("")){
            whereQuery.add("orderName like '%" + docname + "%'");
        }
        if(!docnumber.equals("")){
            whereQuery.add("documentNum like '%" + docnumber +"%'");
        }
        if(!dsdt.equals("")){
            try {
                date = odf.parse(dsdt);
            }catch(Exception e) {
                date = null;
            }
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            whereQuery.add("orderDate >= '" + sqlDate + "'");
        }

        if(!dedt.equals("")){
            try {
                date = odf.parse(dedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("orderDate <= '" + sqlDate + "'");
            }
        }
        if(!csdt.equals("")){
                try {
                    date = odf.parse(csdt);
                }catch(Exception e) {
                    date = null;
                }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate >= '" + sqlDate + "'");
            }
        }
        if(!cedt.equals("")){
            try {
                date = odf.parse(cedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate <= '" + sqlDate + "'");
            }
        }

        if(whereQuery.size()>0){
            whereStr = " WHERE ";
            for (String s : whereQuery){
                whereStr += s + " AND ";
            }
            whereStr = whereStr.substring(0,whereStr.length()-4);
        }

        if(!sidx.equals("")){
            whereStr += " order by " + sidx + " " +sord;
        }

        Long cntRec  = orderViewService.getOrderCount(whereStr);
        Long pages = cntRec/rows;

        int rowFirst = 0;
        if(cntRec/rows>0){
            pages++;
        }
        if(page > 1){
            rowFirst = page*rows;
        }
        List<ViewOrder> ordersView = orderViewService.getOrders(whereStr,rowFirst,rows);


        for (ViewOrder o: ordersView){
            JqGridRow row = new JqGridRow();
            row.setId(o.getOrderId());
            row.setCell(o);
            orders.add(row);
        }
        response.setTotal(pages);
        response.setPage(page);
        response.setRecords(cntRec);
        response.setRows(orders);
        return response;
    }

    @RequestMapping(value = "/cabinet/itemslist")
    public @ResponseBody ExdeptsGrid exdeptsList( @RequestParam(value = "tdoc", required = false, defaultValue = "0") int tdoc,
                                                  @RequestParam(value = "dsdate", required = false, defaultValue = "") String dsdt,
                                                  @RequestParam(value = "dedate", required = false, defaultValue = "") String dedt,
                                                  @RequestParam(value = "csdate", required = false, defaultValue = "") String csdt,
                                                  @RequestParam(value = "cedate", required = false, defaultValue = "") String cedt,
                                                  @RequestParam(value = "sidx", required = false, defaultValue = "id") String sidx,
                                                  @RequestParam(value = "sord", required = false, defaultValue = "desc") String sord,
                                                  @RequestParam(value = "docname", required = false, defaultValue = "") String docname,
                                                  @RequestParam(value = "docnumber", required = false, defaultValue = "") String docnumber,
                                                  @RequestParam(value = "docarchive", required = false, defaultValue = "0") int docarchive,
                                                  @RequestParam(value = "dept", required = false, defaultValue = "0") int dept,
                                                  @RequestParam(value = "period", required = false, defaultValue = "0") int period,
                                                  @RequestParam(value = "creator_dept", required = false, defaultValue = "0") int creatorDept,
                                                  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                  @RequestParam(value = "rows", required = false, defaultValue = "25") int rows,
                                                  Model model){

        jman.domain.User user = populateCurrentUser();
        SimpleDateFormat odf = new SimpleDateFormat("dd.MM.yyyy");
        ExdeptsGrid response = new ExdeptsGrid();
        List<ExdeptRow> orders = new ArrayList<ExdeptRow>();
        Date date = null;

        StringBuilder query = new StringBuilder("");
        List<String> whereQuery = new ArrayList<String>();
        String whereStr = "";

        whereQuery.add("exDeptId = " + user.getDeptId());
        if(period>0){
            if(period == 1){
                whereQuery.add("dayLeft <= 7");
                whereQuery.add("dayLeft >= 0");
            }else if(period == 2){
                whereQuery.add("dayLeft <=14");
                whereQuery.add("dayLeft >= 0");
            }else if(period == 3){
                whereQuery.add("month(checkTime) = 4");
            }else if(period == 4){
                whereQuery.add("dayLeft < 0");
            }else if(period == 5){
                whereQuery.add("dayLeft < 0");
                whereQuery.add("respFile is null");
            }else if(period == 6){
                whereQuery.add("dayLeft < 0");
                whereQuery.add("respFile is not null");
            }else{

            }
        }

        if(tdoc>0){
            whereQuery.add("orderType = " + tdoc);
        }
        if(docarchive>0){
            whereQuery.add("respFile is not null ");
        }

        /*
        if(user.getUserRole().equals("ROLE_OKO")){
            if(dept>0){
                whereQuery.add("exDeptId = " + dept);
            }
        }else{
            if(creatorDept==0){
                whereQuery.add("exDeptId = " + user.getDeptId());
            }
        }
        */

        if(!docname.equals("")){
            whereQuery.add("itemDsc like '%" + docname + "%'");
        }

        if(!dsdt.equals("")){
            try {
                date = odf.parse(dsdt);
            }catch(Exception e) {
                date = null;
            }
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            whereQuery.add("checkTime >= '" + sqlDate + "'");
        }

        if(!dedt.equals("")){
            try {
                date = odf.parse(dedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("checkTime <= '" + sqlDate + "'");
            }
        }

        if(!csdt.equals("")){
            try {
                date = odf.parse(csdt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate >= '" + sqlDate + "'");
            }
        }
        if(!cedt.equals("")){
            try {
                date = odf.parse(cedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate <= '" + sqlDate + "'");
            }
        }

        if(creatorDept>0){
            whereQuery.add("creatorDeptId = "+creatorDept);
        }

        if(!docnumber.equals("")){
            whereQuery.add("documentNum like '%" + docnumber +"%'");
        }

        if(whereQuery.size()>0){
            whereStr = " WHERE ";
            for (String s : whereQuery){
                whereStr += s + " AND ";
            }
            whereStr = whereStr.substring(0,whereStr.length()-4);
        }

        if(!sidx.equals("")){
            whereStr += " order by " + sidx + " " +sord;
        }

        Long cntRec  = viewExDeptsService.getOrderCount(whereStr);
        Long pages = cntRec/rows;

        int rowFirst = 0;
        if(cntRec/rows>0){
            pages++;
        }
        if(page > 1){
            rowFirst = (page-1)*rows;
        }

        List<ViewExDepts> exDeptLst = viewExDeptsService.getList(whereStr,rowFirst,rows);
        for (ViewExDepts o: exDeptLst){
            ExdeptRow row = new ExdeptRow();
            row.setId(o.getItemDeptId());
            row.setCell(o);
            orders.add(row);
        }
        response.setTotal(pages);
        response.setPage(page);
        response.setRecords(cntRec);
        response.setRows(orders);
        return response;
    }

    @RequestMapping(value = "/cabinet/outitems")
    public @ResponseBody ItemViewGrid outItems(   @RequestParam(value = "tdoc", required = false, defaultValue = "0") int tdoc,
                                                  @RequestParam(value = "dsdate", required = false, defaultValue = "") String dsdt,
                                                  @RequestParam(value = "dedate", required = false, defaultValue = "") String dedt,
                                                  @RequestParam(value = "csdate", required = false, defaultValue = "") String csdt,
                                                  @RequestParam(value = "cedate", required = false, defaultValue = "") String cedt,
                                                  @RequestParam(value = "sidx", required = false, defaultValue = "id") String sidx,
                                                  @RequestParam(value = "sord", required = false, defaultValue = "desc") String sord,
                                                  @RequestParam(value = "docname", required = false, defaultValue = "") String docname,
                                                  @RequestParam(value = "docnumber", required = false, defaultValue = "") String docnumber,
                                                  @RequestParam(value = "docarchive", required = false, defaultValue = "0") int docarchive,
                                                  @RequestParam(value = "dept", required = false, defaultValue = "0") int dept,
                                                  @RequestParam(value = "period", required = false, defaultValue = "0") int period,
                                                  @RequestParam(value = "creator_dept", required = false, defaultValue = "0") int creatorDept,
                                                  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                  @RequestParam(value = "rows", required = false, defaultValue = "25") int rows,
                                                  Model model){

        jman.domain.User user = populateCurrentUser();
        SimpleDateFormat odf = new SimpleDateFormat("dd.MM.yyyy");
        ItemViewGrid response = new ItemViewGrid();
        List<ItemViewRow> orders = new ArrayList<ItemViewRow>();
        Date date = null;

        StringBuilder query = new StringBuilder("");
        List<String> whereQuery = new ArrayList<String>();
        String whereStr = "";

        if(period>0){
            if(period == 1){
                whereQuery.add("dayLeft <= 7");
                whereQuery.add("dayLeft >= 0");
            }else if(period == 2){
                whereQuery.add("dayLeft <=14");
                whereQuery.add("dayLeft >= 0");
            }else if(period == 3){
                whereQuery.add("month(checkTime) = 4");
            }else if(period == 4){
                whereQuery.add("dayLeft < 0");
            }else if(period == 5){
                whereQuery.add("dayLeft < 0");
                whereQuery.add("respFile is null");
            }else if(period == 6){
                whereQuery.add("dayLeft < 0");
                whereQuery.add("respFile is not null");
            }else{

            }
        }

        if(tdoc>0){
            whereQuery.add("orderType = " + tdoc);
        }

        whereQuery.add("(creatorDeptId = " + user.getDeptId() + " or controlDeptId = " + user.getDeptId() + ")");

        //whereQuery.add("controlDeptId = " + user.getDeptId());

        if(!docname.equals("")){
            whereQuery.add("itemDsc like '%" + docname + "%'");
        }

        if(!dsdt.equals("")){
            try {
                date = odf.parse(dsdt);
            }catch(Exception e) {
                date = null;
            }
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            whereQuery.add("checkTime >= '" + sqlDate + "'");
        }

        if(!dedt.equals("")){
            try {
                date = odf.parse(dedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("checkTime <= '" + sqlDate + "'");
            }
        }

        if(!csdt.equals("")){
            try {
                date = odf.parse(csdt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate >= '" + sqlDate + "'");
            }
        }
        if(!cedt.equals("")){
            try {
                date = odf.parse(cedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate <= '" + sqlDate + "'");
            }
        }

        if(!docnumber.equals("")){
            whereQuery.add("itemNum like '%" + docnumber +"%'");
        }

        if(whereQuery.size()>0){
            whereStr = " WHERE ";
            for (String s : whereQuery){
                whereStr += s + " AND ";
            }
            whereStr = whereStr.substring(0,whereStr.length()-4);
        }

        if(!sidx.equals("")){
            whereStr += " order by " + sidx + " " +sord;
        }

        Long cntRec  = itemViewService.getItemCountWhere(whereStr);
        Long pages = cntRec/rows;

        int rowFirst = 0;
        if(cntRec/rows>0){
            pages++;
        }
        if(page > 1){
            rowFirst = (page-1)*rows;
        }

        List<ItemView> exDeptLst = itemViewService.getItemsWhere(whereStr,rowFirst,rows);
        for (ItemView o: exDeptLst){
            ItemViewRow row = new ItemViewRow();
            row.setId(o.getOrderItemId());
            row.setCell(o);
            orders.add(row);
        }
        response.setTotal(pages);
        response.setPage(page);
        response.setRecords(cntRec);
        response.setRows(orders);
        return response;
    }

    @RequestMapping(value = "/cabinet/report")
    public @ResponseBody OrderReportList OrderReportList(@RequestParam(value = "id", required = false, defaultValue = "0") int id){
        OrderReportList orderReportList = new OrderReportList();
        orderReportList.setOrderId(id);    //orderReportList.setOrderReportList(orderReportService.getOrderFiles(id));
        return orderReportList;
    }

    @RequestMapping(value = "/analytics/report")
    public @ResponseBody HashMap AnalyticsReport(@RequestParam(value = "id", required = false, defaultValue = "0") int id){
        HashMap hm = new HashMap();
        //hm.put("depts",deptReportService.getReports());
        return hm;
    }

    @RequestMapping(value = "/files")
    public String MyFiles(Model model){
        jman.domain.User user = populateCurrentUser();
        model.addAttribute("user", user);
        //model.addAttribute("files", orderFileService.getUserFiles(user.getUserId()));
        //model.addAttribute("reports", orderReportService.getUserFiles(user.getUserId()));
        return "files";
    }






    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String UploadForm(Model model) {
        jman.domain.User user = populateCurrentUser();
        model.addAttribute("user", user);
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String FileUpload(@RequestParam("name") String name,
                             @RequestParam("file") MultipartFile file,
                             Model model){

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                model.addAttribute("name",file.getOriginalFilename()+" "+file.getContentType());
                model.addAttribute("size",Math.round(file.getSize()/1024.0*100.0)/100.0);
                return "upload";
            } catch (Exception e) {
                return "upload";
            }
        }else {
            return "upload";
        }
    }

    /*
    @RequestMapping(value = "/upload/test", method = RequestMethod.POST)
    public @ResponseBody HashMap uploadTest(@RequestParam("file") MultipartFile file) throws IOException {
        String destination = rPath + "/owner/"  + file.getOriginalFilename();
        File fl = new File(destination);
        if(!fl.exists()) {
            fl.mkdirs();
        }
        file.transferTo(fl);

        OrderFile orderFile = new OrderFile();
        orderFile.setFilename(file.getOriginalFilename());
        orderFile.setFiletext("test "+file.getOriginalFilename());
        orderFile.setFpath("/owner/" +file.getOriginalFilename());
        orderFile.setOwnerID(populateCurrentUser().getUserId());

        //orderFileService.addFile(orderFile);

        HashMap jsonResponse = new HashMap();
        jsonResponse.put("status","created");
        jsonResponse.put("filename",file.getOriginalFilename());
        jsonResponse.put("id",orderFile.getId());
        return jsonResponse;
    }
    */

    @RequestMapping(value = "/upload/test2", method = RequestMethod.POST)
    public @ResponseBody HashMap uploadTest2(@RequestParam("file") MultipartFile file,
                                             @RequestParam("order") int id
    ) throws IOException {
        KipSetting st = kipSettingService.getSetting();
        String destination = st.getPathString() + "/reports/"  + file.getOriginalFilename();
        File fl = new File(destination);
        if(!fl.exists()) {
            fl.mkdirs();
        }
        file.transferTo(fl);

        OrderReport report = new OrderReport();
        report.setFilename(file.getOriginalFilename());
        report.setFiletext("test "+file.getOriginalFilename());
        report.setFpath("/reports/" +file.getOriginalFilename());
        report.setOwnerID(populateCurrentUser().getUserId());
        report.setOrderID(id);

        //orderReportService.addFile(report);

        Order order = orderService.getOrder(id);
        //orderService.saveOrder(order);

        HashMap jsonResponse = new HashMap();
        jsonResponse.put("status","created");
        jsonResponse.put("filename",file.getOriginalFilename());
        jsonResponse.put("id",report.getId());
        return jsonResponse;
    }

    //=== Authorization section ===//
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signin() {
        return "login";
    }
    @RequestMapping(value = "/signin-failure", method = RequestMethod.GET)
    public String signinFailure(Model model) {
        model.addAttribute("rp","NO");
        return "login";
    }
    //=== END authorization section ===//

    //=== Validate report files , method = RequestMethod.GET ===//
    @RequestMapping(value = "/item/file/{fileId}/{statusId}")
    public String fileOk(
            @PathVariable("fileId") int id,
            @PathVariable("statusId") int statusId,
            @RequestParam(value = "reason", required = false) String reason) {
        jman.domain.User user = populateCurrentUser();
        ReportFile rp = reportFileService.getReportFile(id);
        ItemDept itemDept = itemDeptService.getItemDept(rp.getItemDeptId());
        Item item = itemService.getItem(itemDept.getOrderItemId());
        Date date= new Date();
        Timestamp curTime = new Timestamp(date.getTime());
        rp.setStatusId(statusId);
        rp.setChangerId(user.getUserId());
        rp.setStatusDate(curTime);

        if(statusId == 2){
            if(!reason.equals("")){
                rp.setStatusReason(reason);
                reportFileService.saveReport(rp);
                itemDept.setStatus(new Short("4"));
            }
        }else{
            //itemDept.setStatus(new Short("3"));
            reportFileService.saveReport(rp);
        }

        return "redirect:/order/"+item.getOrderId()+"/item/"+item.getOrderItemId()+"/"+itemDept.getItemDeptId();
    }

    //=== DYNAMIC DICT JS ===//
    @RequestMapping(value = "/app/dict", method = RequestMethod.GET)
    public String jsDict(Model model) {
        model.addAttribute("doctypes", doctypeService.getOrderTypes());
        model.addAttribute("depts", deptService.getDepts());
        return "jsdict";
    }

    //=== GEN REPORT FILES ==//
    @RequestMapping(value = "/list/excel", method = RequestMethod.GET)
    public ModelAndView attachExcel(Model model) {
        return new ModelAndView("ReportItemsExcel","revenueData",itemViewService.getItems(4));
    }

    @RequestMapping(value = "/list/deptitems", method = RequestMethod.GET)
    public ModelAndView attachDeptItems(@RequestParam(value = "tdoc", required = false, defaultValue = "0") int tdoc,
                                        @RequestParam(value = "dsdate", required = false, defaultValue = "") String dsdt,
                                        @RequestParam(value = "dedate", required = false, defaultValue = "") String dedt,
                                        @RequestParam(value = "csdate", required = false, defaultValue = "") String csdt,
                                        @RequestParam(value = "cedate", required = false, defaultValue = "") String cedt,
                                        @RequestParam(value = "sidx", required = false, defaultValue = "id") String sidx,
                                        @RequestParam(value = "sord", required = false, defaultValue = "desc") String sord,
                                        @RequestParam(value = "docname", required = false, defaultValue = "") String docname,
                                        @RequestParam(value = "docnumber", required = false, defaultValue = "") String docnumber,
                                        @RequestParam(value = "docarchive", required = false, defaultValue = "0") int docarchive,
                                        @RequestParam(value = "dept", required = false, defaultValue = "0") int dept,
                                        @RequestParam(value = "period", required = false, defaultValue = "0") int period,
                                        @RequestParam(value = "creator_dept", required = false, defaultValue = "0") int creatorDept,
                                        @RequestParam(value = "control_dept", required = false, defaultValue = "0") int controlDept,
                                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(value = "rows", required = false, defaultValue = "25") int rows,
                                        Model model) {

        jman.domain.User user = populateCurrentUser();
        SimpleDateFormat odf = new SimpleDateFormat("dd.MM.yyyy");
        ExdeptsGrid response = new ExdeptsGrid();
        List<ExdeptRow> orders = new ArrayList<ExdeptRow>();
        Date date = null;

        StringBuilder query = new StringBuilder("");
        List<String> whereQuery = new ArrayList<String>();
        String whereStr = "";

        if(period>0){
            if(period == 1){
                whereQuery.add("dayLeft <= 7");
                whereQuery.add("dayLeft >= 0");
            }else if(period == 2){
                whereQuery.add("dayLeft <=14");
                whereQuery.add("dayLeft >= 0");
            }else if(period == 3){
                whereQuery.add("month(checkTime) = 4");
            }else if(period == 4){
                whereQuery.add("dayLeft < 0");
            }else if(period == 5){
                whereQuery.add("status = 0");
                whereQuery.add("checkTime < current_date()");
            }else if(period == 6){
                whereQuery.add("status = 1");
            }else{

            }
        }

        if(tdoc>0){
            whereQuery.add("orderType = " + tdoc);
        }

        if(docarchive>0){
            if(docarchive==1){
                whereQuery.add("status = 1");
            }else{
                whereQuery.add("status = 0");
            }
        }
        /*
        if(docarchive>0){
            whereQuery.add("respFile is not null ");
        }
        */


        if(dept>0){
            if(dept == 777){
                    whereQuery.add("exdeptType = 1");
            }else if(dept == 555){
                    whereQuery.add("exdeptType in (2,3,4)");
            }else{
                    whereQuery.add("deptId = " + dept);
            }
        }

        if(creatorDept>0){
            whereQuery.add("orderCreatorDeptID = " + creatorDept);
        }

        if(controlDept>0){
            whereQuery.add("controlDeptId = " + controlDept);
        }

        //whereQuery.add("exdeptType = 1");

        if(!docname.equals("")){
            whereQuery.add("itemDsc like '%" + docname + "%'");
        }

        if(!dsdt.equals("")){
            try {
                date = odf.parse(dsdt);
            }catch(Exception e) {
                date = null;
            }
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            whereQuery.add("checkTime >= '" + sqlDate + "'");
        }

        if(!dedt.equals("")){
            try {
                date = odf.parse(dedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("checkTime <= '" + sqlDate + "'");
            }
        }

        if(!csdt.equals("")){
            try {
                date = odf.parse(csdt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate >= '" + sqlDate + "'");
            }
        }
        if(!cedt.equals("")){
            try {
                date = odf.parse(cedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate <= '" + sqlDate + "'");
            }
        }

        if(creatorDept>0){
            whereQuery.add("orderCreatorDeptID = "+creatorDept);
        }

        /*

        if(!docnumber.equals("")){
            whereQuery.add("documentNum like '%" + docnumber +"%'");
        }

        */

        if(whereQuery.size()>0){
            whereStr = " WHERE ";
            for (String s : whereQuery){
                whereStr += s + " AND ";
            }
            whereStr = whereStr.substring(0,whereStr.length()-4);
        }

        if(!sidx.equals("")){
            whereStr += " order by " + sidx + " " +sord;
        }

        return new ModelAndView("ReportDeptItems","deptItems", itemDeptViewService.getList(whereStr,0,50000));
                                                               //itemDeptViewService
                //viewExDeptsService.getList(where,0,5000));
                //itemDeptViewService
    }

    @RequestMapping(value = "/list/deptods", method = RequestMethod.GET)
    public ModelAndView attachDeptOds(Model model) {
        jman.domain.User user = populateCurrentUser();
        String where = "";
        where  = "where exDeptId = " + user.getDeptId();
        return new ModelAndView("ReportDeptItemsOds","revenueData",viewExDeptsService.getList(where,0,5000));
    }


    public int getWeekDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        return ((day == 1) ? 7 : --day);
    }

    @RequestMapping(value = "/cabinet/allitemslist")
    public @ResponseBody ExdeptsGrid allBox( @RequestParam(value = "tdoc", required = false, defaultValue = "0") int tdoc,
                                                  @RequestParam(value = "dsdate", required = false, defaultValue = "") String dsdt,
                                                  @RequestParam(value = "dedate", required = false, defaultValue = "") String dedt,
                                                  @RequestParam(value = "csdate", required = false, defaultValue = "") String csdt,
                                                  @RequestParam(value = "cedate", required = false, defaultValue = "") String cedt,
                                                  @RequestParam(value = "sidx", required = false, defaultValue = "id") String sidx,
                                                  @RequestParam(value = "sord", required = false, defaultValue = "desc") String sord,
                                                  @RequestParam(value = "docname", required = false, defaultValue = "") String docname,
                                                  @RequestParam(value = "docnumber", required = false, defaultValue = "") String docnumber,
                                                  @RequestParam(value = "docarchive", required = false, defaultValue = "0") int docarchive,
                                                  @RequestParam(value = "dept", required = false, defaultValue = "0") int dept,
                                                  @RequestParam(value = "period", required = false, defaultValue = "0") int period,
                                                  @RequestParam(value = "creator_dept", required = false, defaultValue = "0") int creatorDept,
                                                  @RequestParam(value = "control_dept", required = false, defaultValue = "0") int controlDept,
                                                  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                  @RequestParam(value = "rows", required = false, defaultValue = "25") int rows,
                                                  Model model){

        jman.domain.User user = populateCurrentUser();
        SimpleDateFormat odf = new SimpleDateFormat("dd.MM.yyyy");
        ExdeptsGrid response = new ExdeptsGrid();
        List<ExdeptRow> orders = new ArrayList<ExdeptRow>();
        Date date = null;

        StringBuilder query = new StringBuilder("");
        List<String> whereQuery = new ArrayList<String>();
        String whereStr = "";

        if(period>0){
            if(period == 1){
                whereQuery.add("dayLeft <= 7");
                whereQuery.add("dayLeft >= 0");
            }else if(period == 2){
                whereQuery.add("dayLeft <=14");
                whereQuery.add("dayLeft >= 0");
            }else if(period == 3){
                whereQuery.add("month(checkTime) = 4");
            }else if(period == 4){
                whereQuery.add("dayLeft < 0");
            }else if(period == 5){
                whereQuery.add("dayLeft < 0");
                whereQuery.add("respFile is null");
            }else if(period == 6){
                whereQuery.add("dayLeft < 0");
                whereQuery.add("respFile is not null");
            }else{

            }
        }

        if(tdoc>0){
            whereQuery.add("orderType = " + tdoc);
        }
        if(docarchive>0){
            whereQuery.add("respFile is not null ");
        }

        if(dept>0){
            whereQuery.add("exDeptId = " + dept);
        }

        if(creatorDept>0){
            whereQuery.add("creatorDeptId = " + creatorDept);
        }

        if(controlDept>0){
            whereQuery.add("contDeptId = " + controlDept);
        }

        //whereQuery.add("exDeptId = " + user.getDeptId());

        if(!docname.equals("")){
            whereQuery.add("itemDsc like '%" + docname + "%'");
        }

        if(!dsdt.equals("")){
            try {
                date = odf.parse(dsdt);
            }catch(Exception e) {
                date = null;
            }
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            whereQuery.add("checkTime >= '" + sqlDate + "'");
        }

        if(!dedt.equals("")){
            try {
                date = odf.parse(dedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("checkTime <= '" + sqlDate + "'");
            }
        }

        if(!csdt.equals("")){
            try {
                date = odf.parse(csdt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate >= '" + sqlDate + "'");
            }
        }
        if(!cedt.equals("")){
            try {
                date = odf.parse(cedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate <= '" + sqlDate + "'");
            }
        }

        if(creatorDept>0){
            whereQuery.add("creatorDeptId = "+creatorDept);
        }

        /*

        if(!docnumber.equals("")){
            whereQuery.add("documentNum like '%" + docnumber +"%'");
        }

        */

        if(whereQuery.size()>0){
            whereStr = " WHERE ";
            for (String s : whereQuery){
                whereStr += s + " AND ";
            }
            whereStr = whereStr.substring(0,whereStr.length()-4);
        }

        if(!sidx.equals("")){
            whereStr += " order by " + sidx + " " +sord;
        }

        Long cntRec  = viewExDeptsService.getOrderCount(whereStr);
        Long pages = cntRec/rows;

        int rowFirst = 0;
        if(cntRec/rows>0){
            pages++;
        }
        if(page > 1){
            rowFirst = (page-1)*rows;
        }

        List<ViewExDepts> exDeptLst = viewExDeptsService.getList(whereStr,rowFirst,rows);
        for (ViewExDepts o: exDeptLst){
            ExdeptRow row = new ExdeptRow();
            row.setId(o.getItemDeptId());
            row.setCell(o);
            orders.add(row);
        }
        response.setTotal(pages);
        response.setPage(page);
        response.setRecords(cntRec);
        response.setRows(orders);
        return response;
    }


    /* NEW OPTIMIZED SELECT */
    @RequestMapping(value = "/inboxopt/list")
    public @ResponseBody ItemDeptViewGrid inboxoptList( @RequestParam(value = "tdoc", required = false, defaultValue = "0") int tdoc,
                                                  @RequestParam(value = "dsdate", required = false, defaultValue = "") String dsdt,
                                                  @RequestParam(value = "dedate", required = false, defaultValue = "") String dedt,
                                                  @RequestParam(value = "csdate", required = false, defaultValue = "") String csdt,
                                                  @RequestParam(value = "cedate", required = false, defaultValue = "") String cedt,
                                                  @RequestParam(value = "sidx", required = false, defaultValue = "id") String sidx,
                                                  @RequestParam(value = "sord", required = false, defaultValue = "desc") String sord,
                                                  @RequestParam(value = "docname", required = false, defaultValue = "") String docname,
                                                  @RequestParam(value = "docnumber", required = false, defaultValue = "") String docnumber,
                                                  @RequestParam(value = "docarchive", required = false, defaultValue = "0") int docarchive,
                                                  @RequestParam(value = "dept", required = false, defaultValue = "0") int dept,
                                                  @RequestParam(value = "period", required = false, defaultValue = "0") int period,
                                                  @RequestParam(value = "creator_dept", required = false, defaultValue = "0") int creatorDept,
                                                  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                  @RequestParam(value = "rows", required = false, defaultValue = "35") int rows){

        jman.domain.User user = populateCurrentUser();
        SimpleDateFormat odf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;

        ItemDeptViewGrid response = new ItemDeptViewGrid();
        List<ItemDeptViewRow> orders = new ArrayList<ItemDeptViewRow>();

        StringBuilder query = new StringBuilder("");
        List<String> whereQuery = new ArrayList<String>();
        String whereStr = "";

        /*=== WHERE PARAMS ===*/
        whereQuery.add("deptId = " + user.getDeptId());

        if(tdoc>0){
            whereQuery.add("orderType = " + tdoc);
        }

        if(period>0){
            if(period == 1){
                whereQuery.add("checkTime >= current_date()");
                whereQuery.add("checkTime < (current_date() + 8)");
            }else if(period == 2){
                whereQuery.add("checkTime >= current_date()");
                whereQuery.add("checkTime < (current_date() + 15)");
            }else if(period == 3){
                whereQuery.add("month(checkTime) = month(current_date())");
            }else if(period == 4){
                whereQuery.add("checkTime < current_date()");
            }else if(period == 5){
                whereQuery.add("status = 0");
                whereQuery.add("checkTime < current_date()");
            }else if(period == 6){
                whereQuery.add("status = 1");
                //whereQuery.add("checkTime < current_date()");
            }else{

            }
        }

        if(!docname.equals("")){
            whereQuery.add("itemDsc like '%" + docname + "%'");
        }

        if(docarchive>0){
            whereQuery.add("status = 1");
        }

        if(!dsdt.equals("")){
            try {
                date = odf.parse(dsdt);
            }catch(Exception e) {
                date = null;
            }
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            whereQuery.add("checkTime >= '" + sqlDate + "'");
        }

        if(!dedt.equals("")){
            try {
                date = odf.parse(dedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("checkTime <= '" + sqlDate + "'");
            }
        }

        if(!csdt.equals("")){
            try {
                date = odf.parse(csdt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate >= '" + sqlDate + "'");
            }
        }
        if(!cedt.equals("")){
            try {
                date = odf.parse(cedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate <= '" + sqlDate + "'");
            }
        }


        if(!docnumber.equals("")){
            whereQuery.add("documentNum like '%" + docnumber +"%'");
        }
        if(whereQuery.size()>0){
            whereStr = " WHERE ";
            for (String s : whereQuery){
                whereStr += s + " AND ";
            }
            whereStr = whereStr.substring(0,whereStr.length()-4);
        }

        if(!sidx.equals("")){
            whereStr += " order by " + sidx + " " +sord;
        }

        Long cntRec  = itemDeptViewService.getCount(whereStr);
        Long pages = cntRec/rows;

        int rowFirst = 0;
        if(cntRec/rows>0){
            pages++;
        }
        if(page > 1){
            rowFirst = (page-1)*rows;
        }

        List<ItemDeptView> exDeptLst = itemDeptViewService.getList(whereStr,rowFirst,rows);

        for (ItemDeptView o: exDeptLst){
            ItemDeptViewRow row = new ItemDeptViewRow();
            row.setId(o.getItemDeptId());
            row.setCell(o);
            orders.add(row);
        }
        response.setTotal(pages);
        response.setPage(page);
        response.setRecords(cntRec);
        response.setRows(orders);
        return response;
    }

    @RequestMapping(value = "/allboxopt/list")
    public @ResponseBody ItemDeptViewGrid allboxoptList( @RequestParam(value = "tdoc", required = false, defaultValue = "0") int tdoc,
                                                        @RequestParam(value = "dsdate", required = false, defaultValue = "") String dsdt,
                                                        @RequestParam(value = "dedate", required = false, defaultValue = "") String dedt,
                                                        @RequestParam(value = "csdate", required = false, defaultValue = "") String csdt,
                                                        @RequestParam(value = "cedate", required = false, defaultValue = "") String cedt,
                                                        @RequestParam(value = "sidx", required = false, defaultValue = "id") String sidx,
                                                        @RequestParam(value = "sord", required = false, defaultValue = "desc") String sord,
                                                        @RequestParam(value = "docname", required = false, defaultValue = "") String docname,
                                                        @RequestParam(value = "docnumber", required = false, defaultValue = "") String docnumber,
                                                        @RequestParam(value = "docarchive", required = false, defaultValue = "0") int docarchive,
                                                        @RequestParam(value = "dept", required = false, defaultValue = "0") int dept,
                                                        @RequestParam(value = "period", required = false, defaultValue = "0") int period,
                                                        @RequestParam(value = "creator_dept", required = false, defaultValue = "0") int creatorDept,
                                                        @RequestParam(value = "control_dept", required = false, defaultValue = "0") int controlDept,
                                                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                        @RequestParam(value = "rows", required = false, defaultValue = "25") int rows,
                                                        @RequestParam(value = "mode", required = false, defaultValue = "0") int modeOko){

        jman.domain.User user = populateCurrentUser();
        SimpleDateFormat odf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;

        ItemDeptViewGrid response = new ItemDeptViewGrid();
        List<ItemDeptViewRow> orders = new ArrayList<ItemDeptViewRow>();

        StringBuilder query = new StringBuilder("");
        List<String> whereQuery = new ArrayList<String>();
        String whereStr = "";

        /*=== WHERE PARAMS ===*/
        if(tdoc>0){
            whereQuery.add("orderType = " + tdoc);
        }

        if(dept>0){
            if(dept == 777){
                whereQuery.add("exdeptType = 1");
            }else if(dept == 555){
                whereQuery.add("exdeptType in (2,3,4)");
            }else{
                whereQuery.add("deptId = " + dept);
            }
        }

        if(creatorDept>0){
            whereQuery.add("orderCreatorDeptID = "+creatorDept);
        }

        if(controlDept>0){
            whereQuery.add("controlDeptId = " + controlDept);
        }

        if(period>0){
            if(period == 1){
                whereQuery.add("checkTime >= current_date()");
                whereQuery.add("checkTime < (current_date() + 8)");
            }else if(period == 2){
                whereQuery.add("checkTime >= current_date()");
                whereQuery.add("checkTime < (current_date() + 15)");
            }else if(period == 3){
                whereQuery.add("month(checkTime) = month(current_date())");
            }else if(period == 4){
                whereQuery.add("checkTime < current_date()");
            }else if(period == 5){
                whereQuery.add("status = 0");
                whereQuery.add("checkTime < current_date()");
            }else if(period == 6){
                whereQuery.add("status = 1");
                //whereQuery.add("checkTime < current_date()");
            }else{

            }
        }

        if(modeOko == 1){
            whereQuery.add("orderCategory = 1");
        }

        if(!docname.equals("")){
            whereQuery.add("(itemDsc like '%" + docname + "%' or documentNum like '%" + docname + "%' )");
        }

        if(docarchive>0){
            if(docarchive==1){
                whereQuery.add("status = 1");
            }else{
                whereQuery.add("status = 0");
            }
        }

        if(!dsdt.equals("")){
            try {
                date = odf.parse(dsdt);
            }catch(Exception e) {
                date = null;
            }
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            whereQuery.add("checkTime >= '" + sqlDate + "'");
        }

        if(!dedt.equals("")){
            try {
                date = odf.parse(dedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("checkTime <= '" + sqlDate + "'");
            }
        }

        if(!csdt.equals("")){
            try {
                date = odf.parse(csdt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate >= '" + sqlDate + "'");
            }
        }
        if(!cedt.equals("")){
            try {
                date = odf.parse(cedt);
            }catch(Exception e) {
                date = null;
            }
            if(date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                whereQuery.add("documentDate <= '" + sqlDate + "'");
            }
        }


        if(!docnumber.equals("")){
            whereQuery.add("documentNum like '%" + docnumber +"%'");
        }
        if(whereQuery.size()>0){
            whereStr = " WHERE ";
            for (String s : whereQuery){
                whereStr += s + " AND ";
            }
            whereStr = whereStr.substring(0,whereStr.length()-4);
        }

        if(!sidx.equals("")){
            whereStr += " order by " + sidx + " " +sord;
        }

        Long cntRec  = itemDeptViewService.getCount(whereStr);
        Long pages = cntRec/rows;

        int rowFirst = 0;
        if(cntRec/rows>0){
            pages++;
        }
        if(page > 1){
            rowFirst = (page-1)*rows;
        }

        List<ItemDeptView> exDeptLst = itemDeptViewService.getList(whereStr,rowFirst,rows);

        for (ItemDeptView o: exDeptLst){
            ItemDeptViewRow row = new ItemDeptViewRow();
            row.setId(o.getItemDeptId());
            row.setCell(o);
            orders.add(row);
        }
        response.setTotal(pages);
        response.setPage(page);
        response.setRecords(cntRec);
        response.setRows(orders);
        return response;
    }


    //=======FOR ADMINS
    @RequestMapping(value = "/edit/order/{orderId}", method = RequestMethod.GET)
    public String editOrder(@PathVariable("orderId") int id, Model model) {
        model.addAttribute("order",orderViewService.getViewOrder(id));
        model.addAttribute("user", populateCurrentUser());
        return "editOrder";
    }

    @RequestMapping(value = "/edit/order/{orderId}", method = RequestMethod.POST)
    public String saveOrder(
            @PathVariable("orderId") int id,
            Model model,
            @RequestParam("orderfile") MultipartFile file,
            @RequestParam(value = "docname", required = false, defaultValue = "") String docname,
            @RequestParam(value = "ordertype", required = false, defaultValue = "0") int orderType,
            @RequestParam(value = "docnum", required = false, defaultValue = "") String docnum,
            @RequestParam(value = "docdate", required = false, defaultValue = "") String docdate
    ) throws IOException {

        SimpleDateFormat odf = new SimpleDateFormat("dd.MM.yyyy");
        jman.domain.User user = populateCurrentUser();

        Date dt = null;
        try{
            dt = odf.parse(docdate);
        }catch (Exception e){

        }

        Order o = orderService.getOrder(id);
        o.setOrderName(docname);
        o.setDocumentType(orderType);
        o.setDocumentNum(docnum);
        o.setDocumentDate(dt);

        if(!file.isEmpty()){
            String flname = file.getOriginalFilename();
            String ext = FilenameUtils.getExtension(flname);
            OrderFile fl = new OrderFile();
            String sName = o.getOrderId()+"_"+fl.getOrderFileId()+"."+ext;

            fl.setFilename(flname);
            fl.setGenFileName(sName);
            KipSetting st = kipSettingService.getSetting();
            String destination = st.getPathString() + "/owner/"  + sName;
            File tfl = new File(destination);
            if(!tfl.exists()) {
                tfl.mkdirs();
            }
            file.transferTo(tfl);
            fl.setFpath("/files/owner/"+sName);
            fl.setCreatorId(user.getUserId());

            orderFileService.addFile(fl);
            o.setOrderFileId(fl.getOrderFileId());
        }
        orderService.saveOrder(o);
        return "redirect:/edit/order/"+id;
    }

    @RequestMapping(value = "/edit/item/{itemId}", method = RequestMethod.GET)
    public String editItem(@PathVariable("itemId") int id, Model model) {
        model.addAttribute("item",itemService.getItem(id));
        model.addAttribute("itemdepts", itemDeptViewService.getListItem(id));
        model.addAttribute("user", populateCurrentUser());
        return "editItem";
    }

    @RequestMapping(value = "/edit/item/{itemId}", method = RequestMethod.POST)
    public String saveItem(
            @PathVariable("itemId") int id,
            Model model,
            @RequestParam(value = "itemnum", required = false, defaultValue = "") String itemnum,
            @RequestParam(value = "itemdate", required = false, defaultValue = "0") String itemdate,
            @RequestParam(value = "itemdsc", required = false, defaultValue = "") String itemdsc,
            @RequestParam(value = "contdept", required = false, defaultValue = "0") int contdept
    ){
        SimpleDateFormat odf = new SimpleDateFormat("dd.MM.yyyy");
        jman.domain.User user = populateCurrentUser();
        Date dt = null;
        try{
            dt = odf.parse(itemdate);
        }catch (Exception e){

        }

        Item i = itemService.getItem(id); // new Item();
        i.setItemNum(itemnum);
        i.setItemDsc(itemdsc);
        i.setControlDeptId(contdept);
        i.setCheckTime(dt);
        itemService.saveItem(i);
        return "redirect:/order/"+i.getOrderId()+"/item/"+i.getOrderItemId();
    }

    @RequestMapping(value = "/edit/additem/{itemId}", method = RequestMethod.POST)
    public String addExDept(
            @PathVariable("itemId") int id,
            @RequestParam(value = "exdept", required = false, defaultValue = "0") int exdept
    ){
        jman.domain.User user = populateCurrentUser();
        Item i = itemService.getItem(id);
        ItemDept dp = new ItemDept();
        dp.setDeptId(exdept);
        dp.setOrderItemId(id);
        itemDeptService.saveItemDept(dp);
        return "redirect:/order/"+i.getOrderId()+"/item/"+i.getOrderItemId();
    }

    @RequestMapping(value = "/edit/delete/itemdept/{id}", method = RequestMethod.GET)
    public String deleteItemDept(@PathVariable("id") int id, Model model) {
        List<ReportFile> reportFiles = reportFileService.getDeptItemReports(id);
        ItemDept dp = itemDeptService.getItemDept(id);
        Item i = itemService.getItem(dp.getOrderItemId());

        for(ReportFile r: reportFiles){
            reportFileService.deleteReport(r);
        }
        itemDeptService.deleteItemDept(dp);

        return "redirect:/order/"+i.getOrderId()+"/item/"+i.getOrderItemId();
    }

    @RequestMapping(value = "/edit/item/{itemId}/file/{fileId}", method = RequestMethod.GET)
    public String editFileDate(@PathVariable("itemId") int id, @PathVariable("fileId") int fileId, Model model) {
        Item i = itemService.getItem(id);
        ReportFile fl = reportFileService.getReportFile(fileId);
        fl.setCreateDate(new Timestamp(i.getCheckTime().getTime()));
        reportFileService.saveReport(fl);
        return "redirect:/order/"+i.getOrderId()+"/item/"+i.getOrderItemId();
    }

    @RequestMapping(value = "/edit/delete/file/{fileId}", method = RequestMethod.GET)
    public String deleteFile(@PathVariable("fileId") int fileId, Model model) {
        ReportFile reportFile = reportFileService.getReportFile(fileId);
        ItemDept dp = itemDeptService.getItemDept(reportFile.getItemDeptId());
        Item i = itemService.getItem(dp.getOrderItemId());
        reportFileService.deleteReport(reportFile);
        return "redirect:/order/"+i.getOrderId()+"/item/"+i.getOrderItemId()+"/"+dp.getItemDeptId();
    }

    @RequestMapping(value = "/edit/item/dept/{id}", method = RequestMethod.GET)
    public String confirmDeleteItemDept(@PathVariable("id") int deptId, Model model) {
        ItemDept dp = itemDeptService.getItemDept(deptId);
        Item i = itemService.getItem(dp.getOrderItemId());
        Dept dept = deptService.getDept(dp.getDeptId());
        model.addAttribute("user", populateCurrentUser());
        model.addAttribute("dept", dept);
        model.addAttribute("itemDept", dp);
        model.addAttribute("item", i);
        return "confirmDeleteDept";
    }

    //=======SETTINGS //=======SETTINGS
    @RequestMapping(value = "/settings")
    public String settingsPage(Model model) {
        KipSetting st = kipSettingService.getSetting();
        model.addAttribute("user", populateCurrentUser());
        model.addAttribute("path", st.getPathString());
        model.addAttribute("message",messageService.getMessage(1));
        return "settingsPage";
    }

    @RequestMapping(value = "/settings/users/{start}")
    public String settingsUsers(Model model,@PathVariable ("start")  int start) {
        //List<jman.domain.User> usersList = userService.getUsers();
        List<jman.domain.User> allUsersList = userService.getUsers();
        int allcount=allUsersList.size();
        int recordsPerPage=50;
        int pager=  ((allcount/recordsPerPage));
        List<jman.domain.User> usersList = userService.findUsers(start,50);
        int counter=userService.getCountUser(usersList);

        model.addAttribute("user", populateCurrentUser());
        model.addAttribute("users", usersList);
        model.addAttribute("count",counter);
        model.addAttribute("allcount",allcount);
        model.addAttribute("pager",pager);
        model.addAttribute("recordsPerPage",recordsPerPage);
        return "usersPage";
    }




    @RequestMapping(value = "/settings/users/delete/{id}", method = RequestMethod.GET)
    public String userDelete(@PathVariable("id") int userId)
    {
        jman.domain.User idUser = userService.getUser(userId);
        userService.deleteUser(idUser);
        return "/settings/";
    }








    @RequestMapping(value = "/settings/users/edit/{id}", method = RequestMethod.GET)
    public String userEdit(@PathVariable("id") int userId, Model model) {
        model.addAttribute("user", populateCurrentUser());
        model.addAttribute("userEdit", userService.getUser(userId));
        return "userEditPage";
    }

    @RequestMapping(value = "/settings/users/edit/{id}", method = RequestMethod.POST)
    public String userEditSave(@PathVariable("id") int userId, Model model,
                               @RequestParam(value = "fullname", required = false, defaultValue = "") String fullname,
                               @RequestParam(value = "dept", required = false, defaultValue = "0") int deptId,
                               @RequestParam(value = "pass", required = false, defaultValue = "0") String pass,
                               @RequestParam(value = "passrp", required = false, defaultValue = "0") String passrp,
                               @RequestParam(value = "role", required = false, defaultValue = "0") int userRole
    ) {
        jman.domain.User edUser = userService.getUser(userId);
        edUser.setFullname(fullname);
        edUser.setDeptId(deptId);
        edUser.setUserpass(pass);
        String rl = "ROLE_ADMIN";
        if(userRole == 2){
            rl = "ROLE_OKO";
        }else if(userRole == 3){
            rl = "ROLE_KIP";
        }
        edUser.setUserRole(rl);
        userService.saveUser(edUser);
        return "redirect:/settings/users/edit/"+userId;
    }

    @RequestMapping(value = "/settings/users/add", method = RequestMethod.GET)
    public String userAdd(Model model) {
        model.addAttribute("user", populateCurrentUser());
        return "userAdd";
    }
    @RequestMapping(value = "/settings/users/add", method = RequestMethod.POST)
    public String userAddSave(@RequestParam(value = "fullname", required = false, defaultValue = "") String fullname,
                              @RequestParam(value = "dept", required = false, defaultValue = "0") int deptId,
                              @RequestParam(value = "username", required = false, defaultValue = "") String username,
                              @RequestParam(value = "pass", required = false, defaultValue = "0") String pass,
                              @RequestParam(value = "passrp", required = false, defaultValue = "0") String passrp,
                              @RequestParam(value = "role", required = false, defaultValue = "0") int userRole,
                              Model model) {

        jman.domain.User checkUser = userService.getUserByLogin(username);
        if(checkUser == null){

            jman.domain.User u = new jman.domain.User();
            u.setFullname(fullname);
            u.setDeptId(deptId);
            u.setUsername(username);
            u.setUserpass(pass);
            String rl = "ROLE_ADMIN";

            if(userRole == 2){
                rl = "ROLE_OKO";
            }else if(userRole == 3){
                rl = "ROLE_KIP";
            }
            u.setStatus(1);
            u.setUserRole(rl);
            userService.saveUser(u);

            return "redirect:/settings/users/edit/"+u.getUserId();
        }
        model.addAttribute("error","1");
        return "userAdd";
    }

    @RequestMapping(value = "/settings/depts")
    public String settingsDepts(Model model) {
        List<Dept> depts = deptService.getAllDepts();
        for(Dept o : depts){
            System.out.println(o.getDeptName());
            break;
        }
        model.addAttribute("user", populateCurrentUser());
        model.addAttribute("depts", depts);
        return "deptsPage";
    }
    @RequestMapping(value = "/settings/depts/edit/{id}", method = RequestMethod.GET)
    public String deptEdit(@PathVariable("id") int deptId, Model model) {
        Dept dept = deptService.getDept(deptId);
        jman.domain.User user = populateCurrentUser();
        String str = dept.getDeptName() + user.getFullname();
        model.addAttribute("user", user);
        model.addAttribute("rec", dept);
        return "deptEdit";
    }

    @RequestMapping(value = "/settings/depts/add", method = RequestMethod.GET)
    public String deptEdit(Model model) {
        Dept dept = new Dept();
        jman.domain.User user = populateCurrentUser();

        dept.setDeptName("");
        dept.setViewOn(0);

        String str = dept.getDeptName() + user.getFullname();
        model.addAttribute("user", user);
        model.addAttribute("rec", dept);
        return "deptEdit";
    }

    @RequestMapping(value = "/settings/depts/edit/{id}", method = RequestMethod.POST)
    public String deptEditSave(@PathVariable("id") int deptId,
                               @RequestParam(value = "fullname", required = false, defaultValue = "") String fullname,
                               @RequestParam(value = "status", required = false, defaultValue = "0") int activeCheck,
                               @RequestParam(value = "dtype", required = false, defaultValue = "1") int dtype) {
        Dept dept = null;
        if(!fullname.equals("")){
            if(deptId == 0){
                dept = new Dept();
            }else{
                dept = deptService.getDept(deptId);
            }

            dept.setDeptName(fullname);
            dept.setViewOn(activeCheck);
            dept.setDeptType(dtype);
            deptService.saveDept(dept);
        }
        return "redirect:/settings/depts/edit/"+dept.getDeptId();
    }

    @RequestMapping(value = "/settings/ordertypes")
    public String settingsOrdertypes(Model model) {
        model.addAttribute("user", populateCurrentUser());
        List<OrderType> types = doctypeService.getAllOrderTypes();
        for(OrderType o : types){
            System.out.println(o.getOrderTypeId());
            break;
        }
        model.addAttribute("ordertypes", types);
        return "ordertypesPage";
    }
    @RequestMapping(value = "/settings/ordertypes/edit/{id}", method = RequestMethod.GET)
    public String ordertypeEdit(@PathVariable("id") int orderTypeId,
                                Model model) {
        OrderType orderType = doctypeService.getOrderType(orderTypeId);
        String str = orderType.getOrderTypeName();
        model.addAttribute("user", populateCurrentUser());
        model.addAttribute("rec", orderType);
        return "typeEdit";
    }
    @RequestMapping(value = "/settings/ordertypes/edit/{id}", method = RequestMethod.POST)
    public String ordertypeEditSave(@PathVariable("id") int orderTypeId,
                                @RequestParam(value = "fullname", required = false, defaultValue = "") String fullname,
                                @RequestParam(value = "sortorder", required = false, defaultValue = "0") int sortOrder,
                                @RequestParam(value = "status", required = false, defaultValue = "0") int activeCheck,
                                @RequestParam(value = "catstatus", required = false, defaultValue = "0") int catStatus
    ) {
        OrderType orderType = null;

        if(!fullname.equals("")){
            if(orderTypeId == 0){
                orderType = new OrderType();
            }else{
                orderType = doctypeService.getOrderType(orderTypeId);
            }
            orderType.setOrderTypeName(fullname);
            orderType.setSortOrder(sortOrder);
            orderType.setActiveCheck(activeCheck);
            orderType.setOrderCategory(catStatus);
            doctypeService.saveOrderType(orderType);
        }
        return "redirect:/settings/ordertypes/edit/"+orderType.getOrderTypeId();
    }

    @RequestMapping(value = "/settings/ordertypes/add", method = RequestMethod.GET)
    public String ordertypeAdd(Model model) {
        OrderType orderType = new OrderType();
        orderType.setOrderTypeName("");
        orderType.setSortOrder(1);
        orderType.setActiveCheck(0);
        orderType.setOrderCategory(0);
        model.addAttribute("user", populateCurrentUser());
        model.addAttribute("rec", orderType);
        return "typeEdit";
    }


    @RequestMapping(value = "/settings/message", method = RequestMethod.POST)
    public String messageEditSave(@RequestParam(value = "msg_text", required = false, defaultValue = "") String msgText    ) {
        Message message = messageService.getMessage(1);
        message.setMsgText(msgText);
        messageService.saveMessage(message);
        return "redirect:/settings";
    }

    private jman.domain.User populateCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        jman.domain.User user2 = userService.getUserByLogin(user.getUsername());
        return user2;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String editFileDate(Model model) {
        model.addAttribute("user", populateCurrentUser());
        return "my403";
    }
}