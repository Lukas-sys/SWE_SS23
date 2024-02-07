package iwwwdnw.ui.impl.view;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.gui.port.Controller;
import iwwwdnw.gui.port.View;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.storemanager.port.StoreManagement;
import iwwwdnw.turnmanager.impl.Player;
import iwwwdnw.turnmanager.port.TurnManagement;
import iwwwdnw.ui.impl.controller.RollDiceController;

public class RollDiceView implements View {

    private RollDiceController controller;
    private StoreManagement storeManager;
    private StateMachine stateMachine;
    private TurnManagement turnManager;

    public RollDiceView(LogicFactory logicFactory) {
        this.controller = new RollDiceController(this, logicFactory);
        this.stateMachine = logicFactory.stateMachine();
        this.storeManager = logicFactory.managerPort().storeManagement();
        this.turnManager = logicFactory.managerPort().turnManagement();
    }

    @Override
    public void display() {
        Player currentPlayer = storeManager.getCurrentPlayer();
        System.out.println(currentPlayer.getName() + " ist an der Reihe");

    }

    @Override
    public void show(String string) {
        System.out.println(string);
    }

    @Override
    public void startEventLoop() {

    }

    @Override
    public void stop() {

    }

    @Override
    public Controller getController() {
        return this.controller;
    }

    @Override
    public void update(State newState) {

    }
}
