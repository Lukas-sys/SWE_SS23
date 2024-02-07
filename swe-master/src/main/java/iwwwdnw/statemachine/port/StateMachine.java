package iwwwdnw.statemachine.port;

public interface StateMachine {
	public State getState();
	public void setState(State state);
}
