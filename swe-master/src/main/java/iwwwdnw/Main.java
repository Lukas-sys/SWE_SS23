package iwwwdnw;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.statemachine.port.Observer;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.ui.impl.view.MainView;

public class Main {

	public static void main(String[] args) {

//		Subject sub = StateMachineFactory.FACTORY.subject();
//		sub.attach(new Obs());
//		StateMachine sm = StateMachineFactory.FACTORY.stateMachine();
//		sm.setState(S.DarfWuerfeln);
//		sm.setState(S.DarfPlatzieren);

//		(new View(LogicFactory.FACTORY)).startEventLoop();


		System.out.println("Spiel wurde gestartet");

		(new MainView(LogicFactory.FACTORY)).startEventLoop();
	}
	
	public static class Obs implements Observer {
		public void update(State newState) {
			System.out.println(""+newState);
		}
	}

}


