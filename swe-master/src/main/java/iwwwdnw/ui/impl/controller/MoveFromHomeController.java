package iwwwdnw.ui.impl.controller;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.gui.port.Controller;
import iwwwdnw.gui.port.View;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.storemanager.port.StoreManagement;
import iwwwdnw.turnmanager.port.TurnManagement;

import java.util.Scanner;

public class MoveFromHomeController implements Controller {

    View view;
    private TurnManagement turnManager;
    private StoreManagement storeManager;
    private StateMachine stateMachine;

    public MoveFromHomeController(View view, LogicFactory logicFactory) {
        this.view = view;
        turnManager = logicFactory.managerPort().turnManagement();
        storeManager = logicFactory.managerPort().storeManagement();
        stateMachine = logicFactory.stateMachine();
    }

    @Override
    public void doit() {
        this.turnManager.showgetStartTilesForEachFigureInHome();

        this.view.show("Syntax: Figur > Feld");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.matches("(" + storeManager.getCurrentPlayer().getName() + "-[0-9]+ > [0-9]+)")) {

            String result = turnManager.moveFigureFromHome(input);

            switch (result) {
                case "success":
                    this.view.show("Figur erfolgreich bewegt");
                    this.storeManager.nextPlayer();
                    this.stateMachine.setState(State.S.RollDice);
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

    @Override
    public void update(State newState) {
        System.out.println("MoveFromHomeController: update to: " + newState);
    }
}
