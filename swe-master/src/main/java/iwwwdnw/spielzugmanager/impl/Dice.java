package iwwwdnw.spielzugmanager.impl;


public class Dice {

	private int dice1;
	private int dice2;


	public void rollDice() {
		this.dice1 = (int)(Math.random() * 6) + 1;
		this.dice2 = (int)(Math.random() * 6) + 1;
	}


	public int getDice1() {
		return this.dice1;
	}


	public int getDice2() {
		return this.dice2;
	}


	public int getSumOfDice() {
		return this.dice1 + this.dice2;
	}

}
