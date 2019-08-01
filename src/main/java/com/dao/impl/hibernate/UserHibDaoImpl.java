package com.dao.impl.hibernate;

import com.dao.UserDao;
import com.model.User;
import com.utils.HashUtil;
import com.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserHibDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserHibDaoImpl.class);

    @Override
    public void add(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String saltedPassword = HashUtil.getSaltedPassword(user.getPassword(), user.getSalt());
            user.setPassword(saltedPassword);
            session.save(user);
            transaction.commit();
            logger.info(user + " was added to DB");
        } catch (Exception e) {
            logger.error("Try to add user was failed!", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void remove(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            logger.info(user + " was deleted from DB");
        } catch (Exception e) {
            logger.error("Try to remove user was failed!", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Long userId, User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String saltedPassword = HashUtil.getSaltedPassword(user.getPassword(), user.getSalt());
            User userFromDb = session.get(User.class, userId);
            userFromDb.setLogin(user.getLogin());
            userFromDb.setPassword(saltedPassword);
            userFromDb.setEmail(user.getEmail());
            userFromDb.setRole(user.getRole());
            session.update(userFromDb);
            transaction.commit();
            logger.info("User with id = " + userId + " was updated in DB");
        } catch (Exception e) {
            logger.error("Try to update user was failed!", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            return Optional.of(user);
        } catch (Exception e) {
            logger.error("Try to get user by id was failed!", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User where login = :login");
            query.setParameter("login", login);
            User user = (User) query.uniqueResult();
            return Optional.of(user);
        } catch (Exception e) {
            logger.error("Try to get user by login was failed!", e);
        }
        return Optional.empty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            userList = session.createQuery("FROM User").list();
        } catch (Exception e) {
            logger.error("Try to get all users was failed!", e);
        }
        return userList;
    }

    @Override
    public Optional<User> getByLoginOrEmail(String login, String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User where login = :login OR email = :email");
            query.setParameter("login", login);
            query.setParameter("email", email);
            query.setMaxResults(1);
            User user = (User) query.uniqueResult();
            return Optional.of(user);
        } catch (Exception e) {
            logger.error("Try to get user by login OR email was failed!", e);
        }
        return Optional.empty();
    }
}
