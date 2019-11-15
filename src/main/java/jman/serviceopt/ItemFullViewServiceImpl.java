package jman.serviceopt;

import jman.domainopt.ItemDeptView;
import jman.domainopt.ItemFullView;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class ItemFullViewServiceImpl implements ItemFullViewService{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ItemFullView getItem(int id) {
        return (ItemFullView) sessionFactory.getCurrentSession().createQuery("from ItemFullView where orderItemId = "+id).uniqueResult();
        //(Module)session.createQuery("from Module mod where mod.moduleID = 1").uniqueResult();
    }

}
