package iwwwdnw.ui.impl.controller;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.gui.port.Controller;
import iwwwdnw.gui.port.View;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.storemanager.impl.Color;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.storemanager.port.StoreManagement;

import java.util.Scanner;

public class RegisterController implements Controller {

    private View view;
    private StoreManagement storeManager;
    private StateMachine stateMachine;
    private int playerCount = 0;
    private int registeredPlayers = 0;

    public RegisterController(View view, LogicFactory logicFactory) {
        this.view = view;
        storeManager = logicFactory.managerPort().storeManagement();
        stateMachine = logicFactory.stateMachine();
    }

    @Override
    public void doit() {

        Scanner scanner = new Scanner(System.in);

        if (playerCount == 0) {
            // player count has not been set yet

            this.view.show("Anzahl der Spieler eingeben (2-6)");

            String input = scanner.nextLine();

            if (input.matches("[2-6]")) {
                playerCount = Integer.parseInt(input);
            } else {
                this.view.show("Ungültige Eingabe");
            }
        } else {
            // player count has been set
            // proceed with registration

            while (registeredPlayers < playerCount) {
                String name = "";
                int age = 0;
                Color color = null;

                while (name.isEmpty()) {
                    this.view.show("Spieler " + (registeredPlayers + 1) + ": Name eingeben");
                    String input = scanner.nextLine();
                    if (input.isEmpty()) {
                        this.view.show("Ungültige Eingabe");
                    } else {
                        name = input;
                    }
                }

                while (age == 0) {
                    this.view.show(name + ": Alter eingeben");
                    String input = scanner.nextLine();
                    if (!input.matches("[1-9]?[0-9]")) {
                        this.view.show("Ungültige Eingabe");
                    } else {
                        age = Integer.parseInt(input);
                    }
                }

                while (color == null) {
                    this.view.show(name + ": Farbe auswählen");
                    this.view.show("[1] Rot; [2] Gelb; [3] Grün; [4] Blau; [5] Schwarz; [6] Lila");
                    String input = scanner.nextLine();
                    // TODO: dont let already taken colors be selected
                    if (!input.matches("[1-6]")) {
                        this.view.show("Ungültige Eingabe");
                    } else {
                        switch (Integer.parseInt(input)) {
                            case 1:
                                color = Color.RED;
                                break;
                            case 2:
                                color = Color.YELLOW;
                                break;
                            case 3:
                                color = Color.GREEN;
                                break;
                            case 4:
                                color = Color.BLUE;
                                break;
                            case 5:
                                color = Color.BLACK;
                                break;
                            case 6:
                                color = Color.PURPLE;
                                break;
                        }
                    }
                }

                storeManager.storeNewPlayer(registeredPlayers, name, age, color);
                registeredPlayers++;
            }

            this.view.show("Spieler wurden registriert");
            stateMachine.setState(State.S.StartGame);
        }
    }

    @Override
    public void update(State newState) {

    }
}
