package iwwwdnw.ui.impl.view;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.gui.port.Controller;
import iwwwdnw.gui.port.View;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.ui.impl.controller.StartGameController;

public class StartGameView implements View {

    private StartGameController controller;
    private boolean running = true;

    public StartGameView(LogicFactory logicFactory) {
        this.controller = new StartGameController(this, logicFactory);
    }

    @Override
    public void update(State newState) {
        System.out.println("StartGameView: update to: " + newState);
    }

    @Override
    public void display() {
        System.out.println("Spielbrett wird vorbereitet...");
    }

    @Override
    public void show(String string) {
        System.out.println(string);
    }

    @Override
    public void stop() {
        this.running = false;
        System.out.println("Stop StartGameView");
    }

    @Override
    public Controller getController() {
        return this.controller;
    }

    @Override
    public void startEventLoop() {
        while (running) {
            this.display();
            this.controller.doit();
        }
    }
}
