package iwwwdnw.gui.port;

import iwwwdnw.statemachine.port.Observer;

public interface View extends Observer {

    void display();

    void show(String string);

    void startEventLoop();

    void stop();

    Controller getController();

}
