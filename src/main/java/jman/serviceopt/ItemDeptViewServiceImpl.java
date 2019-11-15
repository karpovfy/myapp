package jman.serviceopt;

import jman.domain.ViewExDepts;
import jman.domainopt.ItemDeptView;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ItemDeptViewServiceImpl implements ItemDeptViewService{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ItemDeptView> getList(String where, int frow, int rows) {
        return sessionFactory.getCurrentSession().createQuery("FROM ItemDeptView " + where).setFirstResult(frow).setMaxResults(rows).list();
    }

    @Override
    public List<ItemDeptView> getListItem(int orderItemId) {
        return sessionFactory.getCurrentSession().createQuery("FROM ItemDeptView where orderItemId = " + orderItemId).list();
    }

    @Override
    public List<ItemDeptView> getListItemOrder(int orderItemId) {
        return sessionFactory.getCurrentSession().createQuery("FROM ItemDeptView where orderItemId = " + orderItemId +" order by viewed,reportDate").list();
    }

    @Override
    public Long getCount(String query){
        return ((Long) sessionFactory.getCurrentSession().createQuery("select count(itemDeptId) FROM ItemDeptView "+query).iterate().next()).longValue();
    }

}
