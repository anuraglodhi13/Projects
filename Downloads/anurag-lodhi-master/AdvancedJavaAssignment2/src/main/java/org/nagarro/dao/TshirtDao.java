package org.nagarro.dao;

import org.hibernate.*;
import org.nagarro.model.Tshirt;

import org.nagarro.utilities.HibernateUtil;

import java.util.List;
import java.util.Set;

public class TshirtDao {

    public List<Tshirt> getTshirts() {
        List SearchedShirts = null;
        Transaction tx = null;
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Tshirt.class); // query added
            SearchedShirts = criteria.list();
        } catch (HibernateException exception) {
            if (tx != null)
                tx.rollback();

        } finally {
            session.close();
        }
        return SearchedShirts;
    }

    public void insertTshirtInDb(Set<Tshirt> tshirtData) {
        if (tshirtData.size() != 0) {
            try {
                //Get Session
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                //start transaction
                session.beginTransaction();
                Tshirt tshirt1 = null;
                for (Tshirt tshirt : tshirtData) {
                    if (searchTshirtInDb(tshirt.getId())) {
                        continue;
                    }
                    tshirt1 = new Tshirt();
//                           System.out.println(tshirt.getId());
                    tshirt1.setPrice(tshirt.getPrice());
                    tshirt1.setAvailability(tshirt.getAvailability());
                    tshirt1.setColor(tshirt.getColor());
                    tshirt1.setName(tshirt.getName());
                    tshirt1.setGenderRecommendation(tshirt.getGenderRecommendation());
                    tshirt1.setRating(tshirt.getRating());
                    tshirt1.setId(tshirt.getId());
                    tshirt1.setSize(tshirt.getSize());
                    session.save(tshirt1);
                }
                //Commit transaction
                session.getTransaction().commit();
            } catch (HibernateException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private boolean searchTshirtInDb(String tshirtId) {
        List<Tshirt> listOfShirts = getTshirts();
        if (listOfShirts != null) {
            for (Tshirt tshirt : listOfShirts) {
                if (tshirt.getId().equals(tshirtId)) {
                    return true;
                }
            }
        }
        return false;
    }
}
