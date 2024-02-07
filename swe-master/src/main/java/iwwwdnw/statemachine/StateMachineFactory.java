package iwwwdnw.statemachine;

import iwwwdnw.Logic.port.SubjectPort;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.statemachine.port.StateMachinePort;
import iwwwdnw.statemachine.port.Subject;

public interface StateMachineFactory {

	StateMachineFactory FACTORY = new StateMachineFacade();
	public Subject subject();
	public SubjectPort subjectPort();
	public StateMachine stateMachine();
	public StateMachinePort stateMachinePort();
}
