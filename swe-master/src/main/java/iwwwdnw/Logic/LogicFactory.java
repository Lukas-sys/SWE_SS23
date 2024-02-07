package iwwwdnw.Logic;

import iwwwdnw.Logic.port.MVCPort;
import iwwwdnw.Logic.port.ManagerPort;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.statemachine.port.Subject;

public interface LogicFactory {

	LogicFactory FACTORY = new LogicFactoryImpl();

	public ManagerPort managerPort();
	
	public MVCPort mvcPort();

	Subject subject();

	StateMachine stateMachine();
}
