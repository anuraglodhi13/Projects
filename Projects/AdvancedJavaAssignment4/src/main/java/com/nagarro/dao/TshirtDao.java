package com.nagarro.dao;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nagarro.model.TshirtData;
import com.nagarro.utilities.DifferenceUtil;
import com.nagarro.utilities.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TshirtDao {

	public List<TshirtData> getTshirts(final String colour, final String gender, final String size) {
        List searchedShirts = null;
        Transaction tx = null;
        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            String hql = "from TshirtData T where T.size = :size and T.genderRecommendation = :gender and T.color = :colour";
            Query query = session.createQuery(hql);
            query.setParameter("size", size);
            query.setParameter("gender", gender);
            query.setParameter("colour", colour);
            searchedShirts = query.list();
        } catch (HibernateException exception) {
            if (tx != null)
                tx.rollback();

        } finally {
            session.close();
        }
        return searchedShirts;
    }

    public void insertTshirtInDb(Set<TshirtData> tshirtData) throws IllegalAccessException {
        Transaction tx = null;
        Session session = null;
        if (tshirtData.size() != 0) {
            try {
                //Get Session
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                //start transaction
                session.beginTransaction();
                TshirtData tshirt1 = null;
                for (TshirtData tshirt : tshirtData) {
                	
                	ImmutablePair <TshirtData, Boolean> ansPair = searchTshirtInDb(tshirt);
                	TshirtData tshirtInDb = ansPair.getKey();
                	List<String> changedProperties = new ArrayList<>();
                	if(tshirtInDb != null && tshirtData != null) {
                		DifferenceUtil.difference(tshirt, tshirtInDb, changedProperties, null);
                	}
                	
                    if (ansPair.getValue()) {
                    	if(!changedProperties.isEmpty()) {
                    		session.merge(tshirt);
                    	}
                    	continue;
                    }
                    
                    tshirt1 = new TshirtData();
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
            } finally {
				session.close();
			}
        }
    }
    
    private ImmutablePair <TshirtData, Boolean> searchTshirtInDb(TshirtData tshirt) {
    	List searchedShirts = null;
        Transaction tx = null;
        Session session = null;
        String id = tshirt.getId();
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            String hql = "from TshirtData T where T.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            searchedShirts = query.list();
        } catch (HibernateException exception) {
            if (tx != null)
                tx.rollback();

        } finally {
            session.close();
        }
        
        TshirtData tshirtInDb = null;
        ImmutablePair <TshirtData, Boolean> ans = null;
        if (!searchedShirts.isEmpty()) {
        	 tshirtInDb = (TshirtData) searchedShirts.get(0);
             if (tshirtInDb.getId().equals(tshirt.getId())) {
                 	ans = new ImmutablePair<>(tshirtInDb,true);
                 	return ans;
             }
        }
        
        ans = new ImmutablePair <>(null,false);
    	return ans;
    }
}
