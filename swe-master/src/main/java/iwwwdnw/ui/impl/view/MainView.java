package iwwwdnw.ui.impl.view;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.gui.port.Controller;
import iwwwdnw.gui.port.View;
import iwwwdnw.statemachine.port.State;

public class MainView implements View {

    private LogicFactory logicFactory;
    private boolean running = true;

    private View currentView;

    public MainView(LogicFactory factory) {
        logicFactory = factory;
        logicFactory.subject().attach(this);
    }

    @Override
    public void update(State newState) {
//        System.out.println("MainView: observe update to: " + newState);
        this.updateCurrentView();
    }

    public void display() {
        currentView.display();
    }

    public void show(String string) {
        System.out.println(string);
    }

    void doit() {
        if (this.currentView != null) {
            this.currentView.getController().doit();
        }
    }

    @Override
    public void stop() {
        this.running = false;
    }

    @Override
    public Controller getController() {
        return null;
    }

    private void updateCurrentView() {
        // TODO: replace hard coded if statements with viewFactory

        State state = logicFactory.stateMachine().getState();

        if (state == State.S.Register) {
            this.currentView = new RegisterView(logicFactory);
        } else if (state == State.S.StartGame) {
            this.currentView = new StartGameView(logicFactory);
        } else if (state == State.S.RollDice) {
            this.currentView = new RollDiceView(logicFactory);
        } else if (state == State.S.MoveFromHome) {
            this.currentView = new MoveFromHomeView(logicFactory);
        } else if (state == State.S.Move) {
            this.currentView = new MoveView(logicFactory);
        }
    }

    @Override
    public void startEventLoop() {
        while (running) {
            this.display();
            this.doit();
        }
    }
}
