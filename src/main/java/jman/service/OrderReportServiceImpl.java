package jman.service;

import jman.domain.OrderReport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class OrderReportServiceImpl implements OrderReportService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public OrderReport getFile(int id) {
        return (OrderReport) sessionFactory.getCurrentSession().get(OrderReport.class, id);
    }

    @Override
    public List<OrderReport> getOrderFiles(int id) {
        return sessionFactory.getCurrentSession().createQuery("FROM OrderReport WHERE orderID = "+id).list();
    }

    @Override
    public List<OrderReport> getUserFiles(int id) {
        return sessionFactory.getCurrentSession().createQuery("FROM OrderReport WHERE ownerID = "+id).list();
    }

    @Override
    @Transactional(readOnly = false)
    public void addFile(OrderReport file) {
        sessionFactory.getCurrentSession().saveOrUpdate(file);
    }
}
