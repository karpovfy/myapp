package jman.service;

import jman.domain.OrderFile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class OrderFileServiceImpl implements OrderFileService{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public OrderFile getFile(int id) {
        return (OrderFile) sessionFactory.getCurrentSession().get(OrderFile.class, id);
    }

    @Override
    @Transactional(readOnly = false)
    public void addFile(OrderFile file) {
        sessionFactory.getCurrentSession().saveOrUpdate(file);
    }
    @Override
    public List<OrderFile> getUserFiles(int id) {
        return sessionFactory.getCurrentSession().createQuery("FROM OrderFile where creatorId = "+id).list();
    }
}
