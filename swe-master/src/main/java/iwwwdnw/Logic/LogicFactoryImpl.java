package iwwwdnw.Logic;

import iwwwdnw.Logic.port.MVCPort;
import iwwwdnw.Logic.port.ManagerPort;
import iwwwdnw.Logic.port.SubjectPort;
import iwwwdnw.statemachine.StateMachineFactory;
import iwwwdnw.statemachine.port.StateMachine;
import iwwwdnw.statemachine.port.Subject;
import iwwwdnw.storemanager.StoreManagerFactory;
import iwwwdnw.storemanager.port.StoreManagement;
import iwwwdnw.storemanager.port.StoreManagerPort;
import iwwwdnw.turnmanager.TurnManagerFactory;
import iwwwdnw.turnmanager.port.TurnManagement;
import iwwwdnw.turnmanager.port.TurnManagerPort;

class LogicFactoryImpl implements LogicFactory, ManagerPort, MVCPort {

	private SubjectPort subjectPort = StateMachineFactory.FACTORY.subjectPort();
    private TurnManagerPort turnManagerPort = TurnManagerFactory.FACTORY.turnManagerPort();
    private StoreManagerPort storeManagerPort = StoreManagerFactory.FACTORY.storemanagerPort();
	//private SpielzugManagerPort spielzugManagerPort =
	
	@Override
	public Subject subject() {
		return this.subjectPort.subject();
	}

	@Override
	public StateMachine stateMachine() {
		return StateMachineFactory.FACTORY.stateMachine();
	}

	@Override
	public ManagerPort managerPort() {
		return this;
	}

	@Override
	public synchronized MVCPort mvcPort() {
		return this;
	}

//	@Override
//	public SpielzugManager spielzugManager() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public TurnManagement turnManagement() {
		return this.turnManagerPort.turnManagement();
	}

	public StoreManagement storeManagement() {
		return this.storeManagerPort.storeManagement();
	}

//	@Override
//	public synchronized SpielzugManager spielzugManager() {
//		return this.spielzugManagerPort.spielzugManager();
//	}

}
