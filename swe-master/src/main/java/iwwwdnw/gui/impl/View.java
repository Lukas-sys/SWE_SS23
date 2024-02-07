package iwwwdnw.gui.impl;

import iwwwdnw.Logic.LogicFactory;
import iwwwdnw.statemachine.port.Observer;
import iwwwdnw.statemachine.port.State;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.statemachine.port.Subject;

public class View implements Observer {

	private Controller controller;
	private Subject subject;
	private StateMachine stateMachine;
	//private UseCase model;
	private boolean running = true;
	
	public View(LogicFactory factory) {
		//this.model = factory.useCase();
		this.stateMachine = factory.stateMachine();
		this.subject = factory.subject();
		this.subject.attach(this);
		this.controller = new Controller(this, factory);
	}

	@Override
	public void update(State newState) {
		// TODO: ???
	}
	
	void display() {
		// Situation gemäß Ihrer Vorlage darstellen.
	}
	
	void show(String text) {
		// Answeisung des Controllers darstellen.
		System.out.println(text);
	}
	
	void stop() {
		this.running = false;
	}
	
	public void startEventLoop() {
		while (running) {
			this.display();
			this.controller.doit();
		}
	}

}
