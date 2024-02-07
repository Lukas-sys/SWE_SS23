package iwwwdnw.ui.impl.view;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.gui.port.Controller;
import iwwwdnw.gui.port.View;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.ui.impl.controller.MoveFromHomeController;

public class MoveFromHomeView implements View {

    private MoveFromHomeController controller;
    private boolean running = true;

    public MoveFromHomeView(LogicFactory logicFactory) {
        this.controller = new MoveFromHomeController(this, logicFactory);
    }

    @Override
    public void display() {
        System.out.println("Figur von Home-Feld zu Start-Feld bewegen");
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
        System.out.println("Stop MoveFromHomeView");
    }

    @Override
    public Controller getController() {
        return this.controller;
    }

    @Override
    public void update(State newState) {
        System.out.println("MoveFromHomeView: update to: " + newState);
    }
}
