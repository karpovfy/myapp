package jman.service;


import jman.domain.OrderType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class DoctypeServiceImpl implements DoctypeService{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<OrderType> getOrderTypes(){
        return sessionFactory.getCurrentSession().createQuery("FROM OrderType where activeCheck != 1 order by order_type_id").list();
    }

    @Override
    public List<OrderType> getAllOrderTypes(){
        return sessionFactory.getCurrentSession().createQuery("FROM OrderType order by order_type_id").list();
    }

    @Override
    public OrderType getOrderType(int id){
        return (OrderType) sessionFactory.getCurrentSession().createQuery("FROM OrderType where order_type_id = "+id).uniqueResult();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrderType(OrderType orderType){
        sessionFactory.getCurrentSession().saveOrUpdate(orderType);
    }
}
