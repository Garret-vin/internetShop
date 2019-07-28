package com.dao.impl.hibernate;

import com.dao.CodeDao;
import com.model.Code;
import com.model.User;
import com.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class CodeHibDaoImpl implements CodeDao {

    private static final Logger logger = Logger.getLogger(CodeHibDaoImpl.class);

    @Override
    public void add(Code code) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(code);

            transaction.commit();
            logger.info(code + " was added to DB");
        } catch (Exception e) {
            logger.error("Try to add code was failed!", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<Code> getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Code code = session.get(Code.class, id);
            return Optional.of(code);
        } catch (Exception e) {
            logger.error("Try to get code by id was failed!", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Code> getLastCodeForUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Code where user = :user order by id desc ");
            query.setParameter("user", user);
            query.setMaxResults(1);

            Code code = (Code) query.uniqueResult();
            return Optional.of(code);
        } catch (Exception e) {
            logger.error("Try to get last code for user" + user + " was failed!", e);
        }
        return Optional.empty();
    }
}
