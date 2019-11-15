package jman.service;

import jman.domain.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class OrderServiceImpl implements OrderService{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order getOrder(int id){
        return (Order) sessionFactory.getCurrentSession().get(Order.class, id);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrder(Order order){
        sessionFactory.getCurrentSession().saveOrUpdate(order);
    }

}
