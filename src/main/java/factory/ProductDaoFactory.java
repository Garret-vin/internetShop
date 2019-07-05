package factory;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;

public class ProductDaoFactory {

    private static ProductDao instance;

    private ProductDaoFactory() {
    }

    public static synchronized ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDaoImpl();
        }
        return instance;
    }
}
