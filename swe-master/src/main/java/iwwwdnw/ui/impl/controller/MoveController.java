package iwwwdnw.ui.impl.controller;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.gui.port.Controller;
import iwwwdnw.gui.port.View;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.storemanager.port.StoreManagement;
import iwwwdnw.turnmanager.port.TurnManagement;

import java.util.Scanner;

public class MoveController implements Controller {

    private View view;
    private TurnManagement turnManager;
    private StoreManagement storeManager;
    private StateMachine stateMachine;

    public MoveController(View view, LogicFactory logicFactory) {
        this.view = view;
        turnManager = logicFactory.managerPort().turnManagement();
        storeManager = logicFactory.managerPort().storeManagement();
        stateMachine = logicFactory.stateMachine();
    }

    @Override
    public void doit() {
        System.out.println("Moved: " + storeManager.getMoved());
        System.out.println("RollResult: " + storeManager.getRollResult());

        while (storeManager.getMoved() < storeManager.getRollResult()) {
            this.view.show(storeManager.getRollResult() - storeManager.getMoved() + " Züge übrig");
            this.turnManager.showPossibleMoveTiles();

            this.view.show("Syntax: Figur > Feld");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.matches("(" + storeManager.getCurrentPlayer().getName() + "-[0-9]+ > [0-9]+)")) {

                String result = turnManager.moveFigure(input);

                switch (result) {
                    case "success":
                        this.view.show("Figur erfolgreich bewegt");
                        break;
                    case "DUELL!":
                        this.view.show(result);
                        this.storeManager.nextPlayer();
                        this.stateMachine.setState(State.S.RollDice);
                        break;
                    default:
                        this.view.show(result);
                        break;
                }

            } else {
                this.view.show("Ungültige Eingabe");
            }
        }

        if (storeManager.getMoved() == storeManager.getRollResult()) {
            this.storeManager.nextPlayer();
            this.stateMachine.setState(State.S.RollDice);
        }
    }

    @Override
    public void update(State newState) {
        System.out.println("MoveController: update to: " + newState);
    }
}
