package jman.service;

import jman.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)

public class UserServiceImpl implements UserService{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUser(int id) {
        return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> getUsers() {
        //return sessionFactory.getCurrentSession().createQuery("FROM User").setFirstResult(0).setMaxResults(700).list();

        return sessionFactory.getCurrentSession().createQuery("FROM User").setFirstResult(0).list();
    }


    @Override
    public List<User> findUsers(int currentPage, int numOfRecords) {
        List<User> usersonpage = new ArrayList<User>();
        usersonpage=sessionFactory.getCurrentSession().createQuery("FROM User").setFirstResult(currentPage).setMaxResults(numOfRecords).list();
        return usersonpage;
    }

    public int getCountUser(List<User> users)
    {
      /*  List<User> count = new ArrayList<User>();
        //Query query=sessionFactory.getCurrentSession().createQuery("COUNT(*) FROM users");
        Query query=sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) FROM `users`");
        count = (query.list());
        System.out.println(count.get(0));

        //String c= Integer.parseInt(count.get(0).toString());
        String c= (count.get(0).toString());*/
        return users.size();
    }

    @Override
    @Transactional(readOnly = false)
    public void saveUser(User user){
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public User getUserByLogin(String login) {
        List<User> userList = new ArrayList<User>();
        Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.username = :login and u.status = 1");
        query.setParameter("login", login);
        userList = query.list();
        if (userList.size() > 0){
            //System.out.println(userList.get(0).getUserId());
            return userList.get(0);
        }
        else{ return null; }
    }
}
