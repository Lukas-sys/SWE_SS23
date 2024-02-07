package iwwwdnw.statemachine.port;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface State {

	public boolean isSubStateOf(State state);

	public boolean isSuperStateOf(State state);

	public enum S implements State {
		Register, StartGame, CanRollDice, RollDice(CanRollDice),
		MoveFromHome, Move,
		CanMove, CheckGameState, CanPlace,
		Turn(RollDice, CanMove, CheckGameState, CanPlace),
		Duel;

		private List<State> subStates;

		private S(State... subS) {
			this.subStates = new ArrayList<>(Arrays.asList(subS));
		}

		public boolean isSuperStateOf(State s) {
			boolean result = s == null || this == s; // self contained
			for (State state : this.subStates) // or
				result |= state.isSuperStateOf(s); // contained in a substate!
			return result;
		}

		public boolean isSubStateOf(State state) {
			return state == null ? false : state.isSuperStateOf(this);
		}
	}

}
