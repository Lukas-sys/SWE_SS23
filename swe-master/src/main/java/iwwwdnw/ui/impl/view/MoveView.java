package iwwwdnw.ui.impl.view;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.gui.port.Controller;
import iwwwdnw.gui.port.View;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.ui.impl.controller.MoveController;

public class MoveView implements View {

    private MoveController controller;
    private boolean running = true;

    public MoveView(LogicFactory logicFactory) {
        this.controller = new MoveController(this, logicFactory);
    }

    @Override
    public void display() {
        System.out.println("Figur zu Feld bewegen");
    }

    @Override
    public void show(String string) {
        System.out.println(string);
    }

    @Override
    public void startEventLoop() {
        while (running) {
            this.display();
            this.controller.doit();
        }
    }

    @Override
    public void stop() {
        this.running = false;
        System.out.println("Stop MoveView");
    }

    @Override
    public Controller getController() {
        return this.controller;
    }

    @Override
    public void update(State newState) {
        System.out.println("MoveView: update to: " + newState);
    }
}
