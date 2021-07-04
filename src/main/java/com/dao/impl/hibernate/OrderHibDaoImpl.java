package com.dao.impl.hibernate;

import com.dao.OrderDao;
import com.model.Order;
import com.model.User;
import com.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class OrderHibDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderHibDaoImpl.class);

    @Override
    public void add(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            logger.info(order + " was added to DB");
        } catch (Exception e) {
            logger.error("Try to add order was failed!", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<Order> getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Order order = session.get(Order.class, id);
            return Optional.of(order);
        } catch (Exception e) {
            logger.error("Try to get order by id was failed!", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Order> getLastOrderForUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Order where user = :user order by id desc");
            query.setParameter("user", user);
            query.setMaxResults(1);
            Order order = (Order) query.uniqueResult();
            return Optional.of(order);
        } catch (Exception e) {
            logger.error("Try to get basket for user " + user + " was failed!", e);
        }
        return Optional.empty();
    }
}
