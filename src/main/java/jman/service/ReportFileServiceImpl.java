package jman.service;

import jman.domain.ReportFile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ReportFileServiceImpl implements ReportFileService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ReportFile getReportFile(int id) {
        return (ReportFile) sessionFactory.getCurrentSession().get(ReportFile.class, id);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveReport(ReportFile reportFile) {
        sessionFactory.getCurrentSession().saveOrUpdate(reportFile);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteReport(ReportFile reportFile) {
        sessionFactory.getCurrentSession().delete(reportFile);
    }

    @Override
    public List<ReportFile> getDeptItemReports(int id) {
        return sessionFactory.getCurrentSession().createQuery("FROM ReportFile where itemDeptId = "+id).list();
    }
}