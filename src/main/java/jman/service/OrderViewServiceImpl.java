package jman.service;

import jman.domain.ViewOrder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Expression;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class OrderViewServiceImpl implements OrderViewService{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ViewOrder getViewOrder(int id){
        return (ViewOrder) sessionFactory.getCurrentSession().get(ViewOrder.class, id);
    }

    @Override
    public List<ViewOrder> getOrders(String query, int rfirst, int rcnt){
        return sessionFactory.getCurrentSession().createQuery("FROM ViewOrder "+query).setFirstResult(rfirst).setMaxResults(rcnt).list();
    }

    @Override
    public Long getOrderCount(String query){
        return ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) FROM ViewOrder "+query).iterate().next()).longValue();
    }
}
