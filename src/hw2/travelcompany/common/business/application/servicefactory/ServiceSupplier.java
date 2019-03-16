package hw2.travelcompany.common.business.application.servicefactory;

import hw2.travelcompany.city.service.CityService;
import hw2.travelcompany.common.business.application.StorageType;
import hw2.travelcompany.country.service.CountryService;
import hw2.travelcompany.order.service.OrderService;
import hw2.travelcompany.user.service.UserService;

public class ServiceSupplier {
    private static volatile ServiceSupplier INSTANCE;
    private ServiceFactory serviceFactory;

    public static ServiceSupplier getInstance() {
        return INSTANCE;
    }

    public static ServiceSupplier newInstance(StorageType storageType) {
        if (INSTANCE == null) {
            synchronized (ServiceSupplier.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceSupplier(storageType);
                }
            }
        }
        return INSTANCE;
    }

    public ServiceSupplier(StorageType storageType) {
        initServiceFactory(storageType);
    }

    private void initServiceFactory(StorageType storageType) {
        switch (storageType) {
            case MEMORY_ARRAY: {
                serviceFactory = new MemoryArrayServiceFactory();
            }
            case MEMORY_COLLECTION: {
                serviceFactory = new MemoryCollectionServiceFactory();
            }
        }
    }

    public CountryService getCountryService() {
        return serviceFactory.getCountryService();
    }

    public CityService getCityService() {
        return serviceFactory.getCityService();
    }

    public OrderService getOrderService() {
        return serviceFactory.getOrderService();
    }

    public UserService getUserService() {
        return serviceFactory.getUserService();
    }
}
