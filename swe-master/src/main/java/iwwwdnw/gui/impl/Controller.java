package iwwwdnw.gui.impl;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.statemachine.port.Observer;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.statemachine.port.Subject;

import java.util.Scanner;

public class Controller implements Observer {

    private View myView;
    private Subject subject;
    //private UseCase myModel;

    Controller(View view, LogicFactory factory) {
        this.myView = view;
//		this.myModel = factory.useCase();
        this.subject = factory.subject();
        this.subject.attach(this);
    }

    @Override
    public void update(State newState) {
        // TODO Auto-generated method stub

    }

    void doit() {
        this.myView.show("gib was ein"); //Eingabeaufforderung
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {//Eingabe
            case "x":
//				this.myModel.sysOp();
                break;

        }
    }

}
