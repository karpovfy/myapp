package jman.service;

import jman.domain.Message;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)

public class MessageServiceImpl implements MessageService{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Message getMessage(int id) {
        return (Message) sessionFactory.getCurrentSession().get(Message.class, id);
    }

    @Override
    public List<Message> getMessages(){
        return sessionFactory.getCurrentSession().createQuery("FROM Message").list();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveMessage(Message message){
        sessionFactory.getCurrentSession().saveOrUpdate(message);
    }

}
