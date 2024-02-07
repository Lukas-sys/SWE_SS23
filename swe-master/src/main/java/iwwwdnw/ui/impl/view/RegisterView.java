package iwwwdnw.ui.impl.view;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.gui.port.Controller;
import iwwwdnw.gui.port.View;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.ui.impl.controller.RegisterController;

public class RegisterView implements View {

    private RegisterController controller;
    private boolean running = true;

    public RegisterView(LogicFactory logicFactory) {
        this.controller = new RegisterController(this, logicFactory);
    }

    @Override
    public void display() {

    }

    @Override
    public void show(String string) {
        System.out.println(string);
    }

    @Override
    public void startEventLoop() {
        while (running) {
            System.out.println("RegisterView EventLoop");
            this.display();
            this.controller.doit();
        }
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public Controller getController() {
        return this.controller;
    }

    @Override
    public void update(State newState) {

    }
}
