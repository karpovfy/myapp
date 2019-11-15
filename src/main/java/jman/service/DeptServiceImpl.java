package jman.service;

import jman.domain.DayStatusCard;
import jman.domain.DayStatusCardOut;
import jman.domain.Dept;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class DeptServiceImpl implements DeptService{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Dept getDept(int id) {
        return (Dept) sessionFactory.getCurrentSession().get(Dept.class, id);
    }

    @Override
    public List<Dept> getAllDepts(){
        return sessionFactory.getCurrentSession().createQuery("FROM Dept order by deptType").list();
    }

    @Override
    public List<Dept> getDepts(){
        return sessionFactory.getCurrentSession().createQuery("FROM Dept where viewOn != 1 order by deptType").list();
    }


    @Override
    @Transactional(readOnly = false)
    public void saveDept(Dept dept){
        sessionFactory.getCurrentSession().saveOrUpdate(dept);
    }


    @Override
    public DayStatusCard getDayStatusCard(int id){
        return (DayStatusCard) sessionFactory.getCurrentSession().createQuery("FROM DayStatusCard where deptId = :id").setParameter("id",id).uniqueResult();
    }

    @Override
    public DayStatusCardOut getDayStatusCardOut(int id){
        return (DayStatusCardOut) sessionFactory.getCurrentSession().createQuery("FROM DayStatusCardOut where deptId = :id").setParameter("id",id).uniqueResult();
    }

}
