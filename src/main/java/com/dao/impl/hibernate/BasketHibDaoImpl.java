package com.dao.impl.hibernate;

import com.dao.BasketDao;
import com.model.Basket;
import com.model.Product;
import com.model.User;
import com.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class BasketHibDaoImpl implements BasketDao {

    private static final Logger logger = Logger.getLogger(BasketHibDaoImpl.class);

    @Override
    public void add(Basket basket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(basket);

            transaction.commit();
            logger.info(basket + " was added to DB");
        } catch (Exception e) {
            logger.error("Try to add basket was failed!", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void addProduct(Basket basket, Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            basket.getProductList().add(product);
            session.update(basket);

            transaction.commit();
            logger.info("Added " + product + " in basket " + basket);
        } catch (Exception e) {
            logger.error("Try to add " + product + " in basket was failed", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public int size(Basket basket) {
        return basket.getProductList().size();
    }

    @Override
    public Optional<Basket> getBasketByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Basket where user = :user order by id desc");
            query.setParameter("user", user);
            query.setMaxResults(1);

            Basket basket = (Basket) query.uniqueResult();
            return Optional.of(basket);
        } catch (Exception e) {
            logger.error("Try to get basket for user " + user + " was failed!", e);
        }
        return Optional.empty();
    }
}
