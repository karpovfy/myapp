package jman.service;

import jman.domain.DeptReport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class DeptReportServiceImpl implements DeptReportService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public DeptReport getDeptReport(int id) {
        return (DeptReport) sessionFactory.getCurrentSession().get(DeptReport.class, id);
    }

    @Override
    public List<DeptReport> getReports(){
        return sessionFactory.getCurrentSession().createQuery("FROM DeptReport").list();
    }

}
