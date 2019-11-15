package jman.service;

import jman.domain.KipSetting;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class KipSettingServiceImpl implements KipSettingService{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public KipSetting getSetting() {
        int id = 1;
        return (KipSetting) sessionFactory.getCurrentSession().get(KipSetting.class, id);
    }
}
