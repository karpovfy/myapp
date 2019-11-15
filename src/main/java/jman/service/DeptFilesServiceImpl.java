package jman.service;


import jman.domain.DeptFiles;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class DeptFilesServiceImpl implements DeptFilesService{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<DeptFiles> getDeptFiles(int itemDeptId){
        List<DeptFiles> list = sessionFactory.getCurrentSession().createQuery("FROM DeptFiles WHERE itemDeptId = :idpt").setInteger("idpt",itemDeptId).list();
        return list;
    }
}
