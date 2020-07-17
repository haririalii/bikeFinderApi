package com.police.bikeFinder.bikeFinderApi.repository.impl;

import com.police.bikeFinder.bikeFinderApi.entity.Case;
import com.police.bikeFinder.bikeFinderApi.entity.Officer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OfficerRepository implements com.police.bikeFinder.bikeFinderApi.repository.OfficerRepository {

    @Autowired
    SessionFactory factory;

    @Override
    public List<Officer> getOfficerList() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Officer") ;


        return query.list();
    }

    @Override
    public int addOfficer(Officer myCase) {
        return 0;
    }

    @Override
    public int delOfficer(int id) {
        return 0;
    }

    @Override
    public Officer getOfficer(int id) {
        return null;
    }

    @Override
    public int updateOfficer(Officer myCase) {
        Session session = factory.getCurrentSession();
        try {session.beginTransaction();
            session.update(myCase);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return 0;
    }

    @Override
    public Officer getBestOfficer() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("select min(lastMission) from Officer where  isAvailable = 1");
        long time =(long) query.list().get(0);
        query = session.createQuery("from Officer where lastMission = :lms");
        query.setParameter("lms",time);
        Officer officer = (Officer) query.list().get(0);
//        officer.setAvailable(false);

        return officer;
    }
}