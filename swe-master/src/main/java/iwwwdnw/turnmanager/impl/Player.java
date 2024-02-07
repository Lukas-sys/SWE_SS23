package iwwwdnw.turnmanager.impl;

import iwwwdnw.storemanager.impl.Color;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int id;
    private String name;
    private int age;
    private Color color;

    ArrayList<Figure> figuren = new ArrayList<>();
    ArrayList<Tile> startFelder = new ArrayList<>();
    ArrayList<Tile> heimFelder = new ArrayList<>();

    public Player(int id, String name, int age, Color color) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;

        for (int i = 1; i <= 5; i++) {
            figuren.add(new Figure(name + "-" + i, this));
        }
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public Color getColor() {
        return this.color;
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
