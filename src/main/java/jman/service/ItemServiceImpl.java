package jman.service;

import jman.domain.Item;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ItemServiceImpl implements ItemService{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Item getItem(int id){
        return (Item) sessionFactory.getCurrentSession().get(Item.class, id);
    }

    @Override
    public List<Item> getOrderItems(int id){
        return sessionFactory.getCurrentSession().createQuery("FROM Item where orderId=:id").setInteger("id",id).list();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveItem(Item item){
        sessionFactory.getCurrentSession().saveOrUpdate(item);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteItem(Item item){
        sessionFactory.getCurrentSession().delete(item);
    }

}
