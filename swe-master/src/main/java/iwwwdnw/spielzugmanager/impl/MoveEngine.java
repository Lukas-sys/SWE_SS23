package iwwwdnw.spielzugmanager.impl;

import java.util.*;

public class MoveEngine {
    private Board board;
    private Storage storage;
    private Dice dice;


    public static void main(String[] args) {
        int amount = 6;
        MoveEngine engine = new MoveEngine(amount);
    }

    MoveEngine(int players) {
        ArrayList<Player> playersList = this.initPlayers(players);
        storage = new Storage(playersList);
        this.initBoard(players);
        dice = new Dice();
        //-------------
        dice.rollDice();
        //this.show();

        this.showgetStartTilesForEachFigureInHome(getStartTilesForEachFigureInHome());

        this.moveFigureFromHome(this.storage.getCurrentPlayer().figuren.get(0), this.board.tiles.get(0));
        this.showpossibleMoveTiles();

        this.showgetStartTilesForEachFigureInHome(getStartTilesForEachFigureInHome());
        this.moveFigure(this.storage.getCurrentPlayer().figuren.get(0), this.board.tiles.get(1));

        try {
            this.moveFigure(this.storage.getPlayerByName("Philipp").figuren.get(0), this.board.tiles.get(1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.showpossibleMoveTiles();
        this.showVisitedTilesForFigure(this.storage.getCurrentPlayer().figuren.get(0));
        System.out.println("Try to move Figure 1 to Field 0 again.");
        this.moveFigure(this.storage.getCurrentPlayer().figuren.get(0), this.board.tiles.get(0));
        this.showpossibleMoveTiles();


        this.moveFigureFromHome(this.storage.getCurrentPlayer().figuren.get(1), this.board.tiles.get(60));
        this.showpossibleMoveTiles();
        this.moveFigure(this.storage.getCurrentPlayer().figuren.get(1), this.board.tiles.get(61));

        this.showgetStartTilesForEachFigureInHome(getStartTilesForEachFigureInHome());
        this.showpossibleMoveTiles();
    }

    public void initBoard(int amountOfPlayers) {
        board = new Board(amountOfPlayers);
        for (int i = 0; i < this.storage.getPlayers().size(); i++) {
            this.storage.getPlayers().get(i).setHeimFelder(board.createHomeTiles(this.storage.getPlayers().get(i)));
        }

        int j = 0;
        for (Tile tile : board.tiles) {
            if (tile.isStartTile()) {
                this.storage.getPlayers().get(j % this.storage.getPlayers().size()).addStartTile(tile);
                j++;
            }
        }
    }

    public ArrayList<Player> initPlayers(int amount) {
        String[] colors = {"red", "yellow", "green", "blue", "black", "purple"};
        String[] names = {"Ava", "Philipp", "Julius", "Marina", "Hartwig", "Ellen"};
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            players.add(new Player(i, names[i], 20 + i, colors[i]));
        }
        return players;
    }

    public void rollDice() {
        this.dice.rollDice();
        //setState = DiceRolled
        int dice = this.dice.getSumOfDice();
        int occupiedHomeTiles = figAtHome();
        int occupiedStartTiles = figAtStart();

        if (dice == 7 && occupiedHomeTiles > 0 && occupiedStartTiles < 2) {
            //setState = MoveFigureFromHome
        } else {
            if (occupiedHomeTiles < 5) {
                //setState = MoveFigure
            } else {
                this.storage.playerTried();
                if (this.storage.getRetries() > 0) {
                    // setState = RollDiceAgain
                } else {
                    this.storage.nextPlayer();
                    // setState = RollDice
                }
            }
        }
    }

    public void moveFigure(Figure fig, Tile toTile) {
        if (!checkFigure(fig)) {
            System.out.println("Figure is not from active Player");
            return;
        }
        if (!checkMovement(fig, toTile)) {
            System.out.println("Tile cannot be arrived by this figure.");
            return;
        }

        if (toTile.isOccupied()) {
            if (toTile.getFigure().getOwner() == fig.getOwner()) {
                throw new UnsupportedOperationException();
            }
            //setState = Collision
            this.storage.nextPlayer();
            //setState = RollDice
        } else {
            ArrayList<Tile> prevVisited = this.storage.getVisited(fig);

            if (checkBackTravelling(prevVisited, toTile)) {
                System.out.println("Backtravel");
                //setState = MoveFigure
                return;
            }

            ArrayList<Tile> visited = new ArrayList<>();
            visited.add(fig.getCurrentTile());
            if (prevVisited != null) {
                visited.addAll(prevVisited);
            }
            this.storage.putVisited(fig, visited);

            this.storage.addMove();
            this.board.tiles.get(fig.getCurrentTile().getFieldID()).removeFigure();
            this.board.tiles.get(toTile.getFieldID()).addFigure(fig);
            fig.setTile(toTile);
            if (this.getRemainingMoves() > 0) {
                //setState = MoveFigure
            } else {
                this.storage.nextPlayer();
                //setState = RollDice
            }

        }
    }

    public void moveFigureFromHome(Figure fig, Tile toTile) {
        if (!checkFigure(fig)) {
            System.out.println("Figure is not from active Player");
            return;
        }
        if (!checkReleaseFromHome(fig)) {
            System.out.println("Figure is not in a Home-Tile.");
            return;
        }
        if (!checkCorrectStartTile(fig, toTile)) {
            System.out.println("Tile is not a start Tile from player");
            return;
        }

        if (toTile.isOccupied()) {
            if (toTile.getFigure().getOwner() == this.storage.getCurrentPlayer()) {
                System.out.println("The destination Tile is already occupied by an own figure.");
                return;
            }
            //setState = Collision
            this.storage.nextPlayer();
            //setState = RollDice
        } else {
            fig.getCurrentTile().removeFigure();
            fig.setTile(toTile);
            this.board.tiles.get(toTile.getFieldID()).addFigure(fig);
            //this.store.nextPlayer();
            //setState = RollDice
        }
    }

    public Map<Figure, ArrayList<Tile>> getStartTilesForEachFigureInHome() {
        Map<Figure, ArrayList<Tile>> ret = new HashMap<>();
        ArrayList<Tile> tiles = this.storage.getCurrentPlayer().getStartFelder();

        for (Figure figure : this.storage.getCurrentPlayer().getFiguren()) {
            if (figure.getCurrentTile().getTileType().equals("H")) {
                ret.put(figure, tiles);
            }
        }
        return ret;
    }


    private int getRemainingMoves() {
        return this.dice.getSumOfDice() - this.storage.getMoved();
    }

    private int figAtHome() {
        int ret = 0;
        for (Tile tile : this.storage.getCurrentPlayer().getHeimFelder()) {
            if (tile.isOccupied()) {
                ret++;
            }
        }
        return ret;
    }

    private int figAtStart() {
        int ret = 0;
        for (Tile tile : this.storage.getCurrentPlayer().getStartFelder()) {
            if (tile.isOccupied()) {
                ret++;
            }
        }
        return ret;
    }

    private Map<Figure, ArrayList<Tile>> getPossibleMoves() {
        Map<Figure, ArrayList<Tile>> ret = new HashMap<>();

        for (Figure figure : this.storage.getCurrentPlayer().getFiguren()) {
            ArrayList<Tile> possible = new ArrayList<>();
            ArrayList<Tile> visited = this.storage.getVisited(figure);

            if (!figure.getCurrentTile().getTileType().equals("H")) {
                possible.add(figure.getCurrentTile().getNext());
                possible.add(figure.getCurrentTile().getPrev());
                if (figure.getCurrentTile().getConnector() != null) {
                    possible.add(figure.getCurrentTile().getConnector());
                }
                if (visited != null) {
                    possible.removeAll(visited);
                }
                ret.put(figure, possible);
            }

        }
        return ret;
    }

    private boolean checkFigure(Figure figure) {
        return figure.getOwner() == this.storage.getCurrentPlayer();
    }

    private boolean checkMovement(Figure figure, Tile toTile) {
        Map<Figure, ArrayList<Tile>> check = this.getPossibleMoves();
        ArrayList<Tile> possibleTiles = check.get(figure);
        if (possibleTiles == null) return false;
        for (Tile tile : possibleTiles) {
            if (toTile.getFieldID() == tile.getFieldID()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkReleaseFromHome(Figure figure) {
        return figure.getCurrentTile().getTileType().equals("H");
    }

    private boolean checkCorrectStartTile(Figure figure, Tile toTile) {
        ArrayList<Tile> startingTilesFromFigure = figure.getOwner().getStartFelder();
        for (Tile tile : startingTilesFromFigure) {
            if (tile.getFieldID() == toTile.getFieldID()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkBackTravelling(ArrayList<Tile> prevVisited, Tile toTile) {
        if (prevVisited != null) {
            for (Tile tile:prevVisited) {
                if (tile.getFieldID() == toTile.getFieldID()) {
                    //System.out.println("Backtravelling!");
                    //setState = MoveFigure
                    return true;
                }
            }
        }
        return false;
    }
    private void show() {
        this.showPlayers();
        this.showPlayersHomeTiles();
        this.showPlayersStartTiles();
        this.showFigureTiles();
    }

    private void showPlayers() {
        System.out.println(" ");
        System.out.println("Players");
        for (Player player : this.storage.getPlayers()) {
            System.out.println(player.getName() + " " + player.getAge() + " " + player.getColor());
        }
    }

    private void showPlayersHomeTiles() {
        System.out.println(" ");
        System.out.println("Player Home Tiles");
        for (Player player : this.storage.getPlayers()) {
            System.out.print(player.getName());
            for (Tile tile : player.getHeimFelder()) {
                System.out.print(" " + tile.getFieldID() + "(" + tile.getFigure().getFigureID() + ")");
            }
            System.out.println(" ");
        }
    }

    private void showPlayersStartTiles() {
        System.out.println(" ");
        System.out.println("Player start Tiles");
        for (Player player : this.storage.getPlayers()) {
            System.out.print(player.getName());
            for (Tile tile : player.getStartFelder()) {
                System.out.print(" " + tile.getFieldID());
            }
            System.out.println(" ");
        }
    }

    private void showFigureTiles() {
        System.out.println(" ");
        System.out.println("Figure Tiles");
        for (Player player : this.storage.getPlayers()) {
            for (Figure fig : player.getFiguren()) {
                System.out.print(" " + fig.getFigureID() + "(" + fig.getCurrentTile().getFieldID() + ")");
            }
            System.out.println(" ");
        }
    }

    private void showgetStartTilesForEachFigureInHome(Map<Figure, ArrayList<Tile>> ret) {
        System.out.println(" ");
        System.out.println("getStartTilesForEachFigureInHome();");
        for (Figure figure : ret.keySet()) {
            System.out.print(figure.getFigureID() + "(" + figure.getCurrentTile().getFieldID() + ") : ");
            for (Tile tile : ret.get(figure)) {
                System.out.print(tile.getFieldID() + " ");
            }
            System.out.println(" ");
        }
    }

    private void showmoveFigureFromHome() {
        System.out.println(" ");
        System.out.println("Figure aus Heimatsfeld entfernt");
        for (Tile tile : this.storage.getCurrentPlayer().heimFelder) {
            System.out.print(" " + tile.getFieldID() + "(" + tile.getTileType() + ") " + tile.isOccupied());
        }
        System.out.println(" ");
        for (Tile tile : this.board.tiles) {
            if (tile.isStartTile()) {
                if (tile.getFigure() != null) {
                    System.out.print(tile.getFieldID() + "(" + tile.getFigure().getFigureID() + ")");
                } else {
                    System.out.print(" " + tile.getFieldID());
                }

            }
        }
        System.out.println(" ");
    }

    private void showmoveFigure() {
        System.out.println(" ");
        System.out.println("showmoveFigure");
        for (Tile tile : this.board.tiles) {
            if (tile.isOccupied()) {
                System.out.println(" " + tile.getFieldID() + "(" + tile.getFigure().getFigureID() + ")");
            }
        }
    }

    private void showpossibleMoveTiles() {
        System.out.println(" ");
        System.out.println("showpossibleMoveTiles");
        Map<Figure, ArrayList<Tile>> possi = this.getPossibleMoves();
        for (Figure figure : possi.keySet()) {
            System.out.print("Für " + figure.getFigureID() + "(" + figure.getCurrentTile().getFieldID() + ")" + ":");
            for (Tile tile : possi.get(figure)) {
                System.out.print(" " + tile.getFieldID() + "(" + tile.getTileType() + ")");
            }
            System.out.println("");
        }
    }

    private void showVisitedTilesForFigure(Figure figure) {
        System.out.print("Visited tiles for " + figure.getFigureID() + ": ");
        ArrayList<Tile> visited = this.storage.getVisited(figure);
        if (visited != null) {
            for (Tile tile: visited) {
                System.out.print(" " + tile.getFieldID());
            }
        } else {
            System.out.println("The figure did not visit any tile yet.");
        }
        System.out.println("");
    }
}
