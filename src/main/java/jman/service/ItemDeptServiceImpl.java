package jman.service;

import jman.domain.ItemDept;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ItemDeptServiceImpl implements ItemDeptService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ItemDept getItemDept(int id){
        return (ItemDept) sessionFactory.getCurrentSession().get(ItemDept.class, id);
    }

    @Override
    public List<ItemDept> getItemDepts(int id){
        return sessionFactory.getCurrentSession().createQuery("FROM ItemDept where orderItemId=:id").setInteger("id",id).list();
    }
    @Override
    @Transactional(readOnly = false)
    public void saveItemDept(ItemDept itemDept){
        sessionFactory.getCurrentSession().saveOrUpdate(itemDept);
    }
    @Override
    @Transactional(readOnly = false)
    public void deleteItemDept(ItemDept itemDept){
        sessionFactory.getCurrentSession().delete(itemDept);
    }
}
