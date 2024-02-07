package iwwwdnw.ui.impl.controller;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.gui.port.Controller;
import iwwwdnw.gui.port.View;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.storemanager.port.StoreManagement;
import iwwwdnw.turnmanager.port.TurnManagement;

import java.util.Scanner;

public class RollDiceController implements Controller {

    private View view;

    private TurnManagement turnManager;
    private StoreManagement storeManager;
    private StateMachine stateMachine;

    public RollDiceController(View view, LogicFactory logicFactory) {
        this.view = view;
        this.turnManager = logicFactory.managerPort().turnManagement();
        this.storeManager = logicFactory.managerPort().storeManagement();
        this.stateMachine = logicFactory.stateMachine();
    }

    @Override
    public void doit() {
        this.view.show("Drücke ENTER zum würfeln...");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int result = turnManager.rollDice();
        this.storeManager.setRollResult(result);

        this.view.show("Du hast eine " + result + " gewürfelt");

        if (result == 7 && turnManager.canMoveFromHomeField()) {
            System.out.println("MOVE FROM HOMEFIELD");
            stateMachine.setState(State.S.MoveFromHome);

        } else if (turnManager.canMove()){
            System.out.println("MOVE");
            stateMachine.setState(State.S.Move);
        } else {
            System.out.println("KANN NIX MACHEN");
            this.view.show(storeManager.getRetries() + " Versuche übrig");
            storeManager.playerTried();
        }

        if (storeManager.getRetries() == 0) {
            System.out.println("NEXT PLAYER");
            turnManager.nextPlayer();
        }
    }

    @Override
    public void update(State newState) {

    }
}
