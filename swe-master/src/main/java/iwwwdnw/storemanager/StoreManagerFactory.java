package iwwwdnw.storemanager;

import iwwwdnw.storemanager.port.StoreManagerPort;

public interface StoreManagerFactory {

    StoreManagerFactory FACTORY = new StoreManagerFactoryImpl();

    StoreManagerPort storemanagerPort();

}
