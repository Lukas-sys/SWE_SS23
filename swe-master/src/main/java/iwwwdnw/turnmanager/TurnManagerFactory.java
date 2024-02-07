package iwwwdnw.turnmanager;

import iwwwdnw.turnmanager.port.TurnManagerPort;

public interface TurnManagerFactory {

    TurnManagerFactory FACTORY = new TurnManagerFactoryImpl();

    TurnManagerPort turnManagerPort();
}
