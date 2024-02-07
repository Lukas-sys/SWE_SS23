package iwwwdnw.statemachine;

import iwwwdnw.Logic.port.SubjectPort;
import iwwwdnw.statemachine.impl.StateMachineImpl;
import iwwwdnw.statemachine.port.*;

public class StateMachineFacade implements StateMachineFactory, SubjectPort, Subject, StateMachine, StateMachinePort {

	private StateMachineImpl stateMachine;

	private void mkStateMachine() {
		if (this.stateMachine == null) {
			this.stateMachine = new StateMachineImpl();
		}
	}
	
	@Override
	public synchronized StateMachine stateMachine() {
		this.mkStateMachine();
		return this;
	}

	@Override
	public SubjectPort subjectPort() {
		return this;
	}

	@Override
	public StateMachinePort stateMachinePort() {
		return this;
	}

	@Override
	public synchronized Subject subject() {
		this.mkStateMachine();
		return this;
	}

	@Override
	public synchronized void attach(Observer obs) {
		this.stateMachine.attach(obs);
	}
	
	@Override
	public synchronized void detach(Observer obs) {
		this.stateMachine.detach(obs);
	}
	
	@Override
	public synchronized State getState() {
		return this.stateMachine.getState();
	}
	
	@Override
	public synchronized void setState(State newState) {
		this.stateMachine.setState(newState);
	}
}
