package com.dao.impl.hibernate;

import com.dao.ProductDao;
import com.model.Product;
import com.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductHibDaoImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductHibDaoImpl.class);

    @Override
    public void add(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            logger.info(product + " was added to DB");
        } catch (Exception e) {
            logger.error("Try to add product was failed!", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void remove(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
            logger.info(product + " was deleted from DB");
        } catch (Exception e) {
            logger.error("Try to remove product was failed!", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Long productId, Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product productFromDb = session.get(Product.class, productId);
            productFromDb.setName(product.getName());
            productFromDb.setDescription(product.getDescription());
            productFromDb.setPrice(product.getPrice());
            session.update(productFromDb);
            transaction.commit();
            logger.info("Product with id = " + productId + " was updated in DB");
        } catch (Exception e) {
            logger.error("Try to update user was failed!", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            productList = session.createQuery("FROM Product").list();
        } catch (Exception e) {
            logger.error("Try to get all products was failed!", e);
        }
        return productList;
    }

    @Override
    public Optional<Product> getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Product product = session.get(Product.class, id);
            return Optional.of(product);
        } catch (Exception e) {
            logger.error("Try to get product by id was failed!", e);
        }
        return Optional.empty();
    }
}
