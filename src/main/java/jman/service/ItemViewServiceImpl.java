package jman.service;

import jman.domain.ItemView;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ItemViewServiceImpl implements ItemViewService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ItemView> getItems(int deptId) {
        return sessionFactory.getCurrentSession().createQuery("FROM ItemView where creatorDeptId = "+deptId+" order by checkTime desc").list();
    }

    @Override
    public Long getItemCount(int deptId){
        return ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) FROM ItemView where creatorDeptId = "+deptId).iterate().next()).longValue();
    }

    @Override
    public List<ItemView> getItemsWhere(String where, int frow, int rows) {
        return sessionFactory.getCurrentSession().createQuery("FROM ItemView "+where+" order by checkTime desc").setFirstResult(frow).setMaxResults(rows).list();
    }

    @Override
    public Long getItemCountWhere(String where){
        return ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) FROM ItemView "+where).iterate().next()).longValue();
    }
}
