package iwwwdnw.spielzugmanager.impl;

import java.util.ArrayList;
import java.util.List;


public class Player {
	private int id;
	private String name;
	private String color;
	private int age;

	ArrayList<Figure> figuren;
	ArrayList<Tile> startFelder;
	ArrayList<Tile> heimFelder;


	public Player(int id, String name, int age, String color) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.age = age;

		this.figuren = new ArrayList<>();
		this.startFelder = new ArrayList<>();
		this.heimFelder = new ArrayList<>();

		for (int i=1; i<=5; i++) {
			figuren.add(new Figure(name+"-"+i, this));
		}
	}

	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}

	public String getColor() {
		return this.color;
	}

	public int getAge() {
		return this.age;
	}

	public ArrayList<Tile> getStartFelder() {
		return this.startFelder;
	}

	public List<? extends Tile> getHeimFelder() {
		return this.heimFelder;
	}

	public List<? extends Figure> getFiguren() {
		return this.figuren;
	}

	public void setStartFelder(ArrayList<Tile> startFelder) {
		this.startFelder = startFelder;
	}

	public void addStartTile(Tile tile) {
		this.startFelder.add(tile);
	}

	public void setHeimFelder(ArrayList<Tile> heimFelder) {
		this.heimFelder = heimFelder;
	}

}
