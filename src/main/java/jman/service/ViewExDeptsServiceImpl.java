package jman.service;

import jman.domain.ViewExDepts;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ViewExDeptsServiceImpl implements ViewExDeptsService{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ViewExDepts getViewExDept(int id) {
        return (ViewExDepts) sessionFactory.getCurrentSession().get(ViewExDepts.class, id);
    }

    @Override
    public List<ViewExDepts> getList(String where, int frow, int rows) {
        return sessionFactory.getCurrentSession().createQuery("FROM ViewExDepts " + where).setFirstResult(frow).setMaxResults(rows).list();
    }

    @Override
    public Long getOrderCount(String query){
        return ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) FROM ViewExDepts "+query).iterate().next()).longValue();
    }

    @Override
    public List<ViewExDepts> getItemDepts(int id) {
        return sessionFactory.getCurrentSession().createQuery("FROM ViewExDepts where orderItemId = :nb").setInteger("nb",id).list();
    }
}
