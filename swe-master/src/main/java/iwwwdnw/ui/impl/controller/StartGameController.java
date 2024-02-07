package iwwwdnw.ui.impl.controller;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.gui.port.Controller;
import iwwwdnw.gui.port.View;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.storemanager.port.StoreManagement;
import iwwwdnw.turnmanager.impl.Board;
import iwwwdnw.turnmanager.port.TurnManagement;

import java.util.Scanner;

public class StartGameController implements Controller {

    private View view;
    private TurnManagement turnManager;
    private StoreManagement storeManager;
    private StateMachine stateMachine;

    public StartGameController(View view, LogicFactory logicFactory) {
        this.view = view;
        turnManager = logicFactory.managerPort().turnManagement();
        storeManager = logicFactory.managerPort().storeManagement();
        stateMachine = logicFactory.stateMachine();
    }


    @Override
    public void update(State newState) {
        System.out.println("StartGameController: update to: " + newState);
    }

    @Override
    public void doit() {
        Board board = turnManager.initializeBoard(storeManager.getPlayers());
        storeManager.storeBoard(board);

        stateMachine.setState(State.S.RollDice);
    }
}
