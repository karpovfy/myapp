package jman.service;

        import jman.domain.AnalyticsDepts;
        import org.hibernate.SessionFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Propagation;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class AnalyticsDeptsServiceImpl implements AnalyticsDeptsService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<AnalyticsDepts> getList(){
        return sessionFactory.getCurrentSession().createQuery("FROM AnalyticsDepts").list();
    }

    public List<Object> getDeptCards(String startDate, String endDate, Short deptType){
        String sdeptType = " d.dept_type = 1 ";
        if(deptType == 2){
            sdeptType = " d.dept_type != 1 ";
        }

        return sessionFactory.getCurrentSession().createSQLQuery("select " +
                " d.dept_id, " +
                " d.dept_name, " +
                " d.dept_type, " +
                " (SELECT count(oi.order_item_id) FROM order_items oi where oi.control_dept_id = d.dept_id and oi.check_time between '"+startDate+"' and '"+endDate+"') on_control, "+
                " (SELECT count(id.item_dept_id) FROM item_depts id join order_items oi on oi.order_item_id = id.order_item_id where id.dept_id = d.dept_id and id.status = 3 and oi.check_time between '"+startDate+"' and '"+endDate+"') on_check, " +
                " (SELECT count(id.item_dept_id) FROM item_depts id join order_items oi on oi.order_item_id = id.order_item_id where id.dept_id = d.dept_id and id.status = 1 and oi.check_time between '"+startDate+"' and '"+endDate+"') on_checked, " +
                " (SELECT count(id.item_dept_id) FROM item_depts id join order_items oi on oi.order_item_id = id.order_item_id where id.dept_id = d.dept_id and id.status = 4 and oi.check_time between '"+startDate+"' and '"+endDate+"') fail_count " +
                " from depts d" +
                " where d.view_on = 0 and "+sdeptType+
                " order by d.dept_type ").list();
    }

}
