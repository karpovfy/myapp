package jman.service;

import jman.domain.AnalyticsDeptCard;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class AnalyticsDeptCardServiceImpl implements AnalyticsDeptCardService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<AnalyticsDeptCard> getCard(int id){
        return sessionFactory.getCurrentSession().createQuery("from AnalyticsDeptCard where deptId = "+id+" group by status").list();
    }
}
