package iwwwdnw.turnmanager.impl;

import iwwwdnw.storemanager.port.StoreManagement;
import iwwwdnw.turnmanager.port.TurnManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TurnManagerImpl implements TurnManagement {

    private StoreManagement storeManager;

    public TurnManagerImpl(StoreManagement storeManager) {
        this.storeManager = storeManager;
    }

    @Override
    public int rollDice() {
        int dice1 = (int) (Math.random() * 6) + 1;
        int dice2 = (int) (Math.random() * 6) + 1;

        return dice1 + dice2;
    }

    @Override
    public boolean canMove() {

        return storeManager.getOccupiedHomeTileCount() < 5;
    }

    @Override
    public boolean canMoveFromHomeField() {
        return storeManager.getOccupiedHomeTileCount() > 0 && storeManager.getOccupiedStartTileCount() < 2;
    }

    @Override
    public void nextPlayer() {
        storeManager.nextPlayer();
    }

    @Override
    public String moveFigureFromHome(String input) {
        String[] parts = input.split(">");

        String figureID = parts[0].replaceAll("\\s+$", "");
        int tileID = Integer.parseInt(parts[1].replaceAll("\\s", ""));

        Figure figure = storeManager.getFigureByID(figureID);
        Tile tile = storeManager.getBoard().getTileByID(tileID);

        if (figure == null || tile == null) {
            return "Ungültige Eingabe";
        }

        String result = this.moveFigureFromHome(figure, tile);

        return result;
    }

    @Override
    public void showPossibleMoveTiles() {
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

    @Override
    public String moveFigure(String input) {
        String[] parts = input.split(">");

        String figureID = parts[0].replaceAll("\\s+$", "");
        int tileID = Integer.parseInt(parts[1].replaceAll("\\s", ""));

        Figure figure = storeManager.getFigureByID(figureID);
        Tile tile = storeManager.getBoard().getTileByID(tileID);

        if (figure == null || tile == null) {
            return "Ungültige Eingabe";
        }

        String result = this.moveFigure(figure, tile);

        return result;
    }

    private String moveFigure(Figure figure, Tile toTile) {
        if (!checkFigure(figure)) {
//            System.out.println("Figure is not from active Player");
            return "Figure is not owned by active Player";
        }
        if (!checkMovement(figure, toTile)) {
//            System.out.println("Tile cannot be arrived by this figure.");
            return "Tile cannot be reached by this Figure.";
        }

        if (toTile.isOccupied()) {
            if (toTile.getFigure().getOwner() == figure.getOwner()) {
                return "The destination Tile is already occupied by an own figure.";
            } else {
                return "DUELL!";
            }

        } else {
            ArrayList<Tile> prevVisited = this.storeManager.getVisited(figure);

            if (checkBackTravelling(prevVisited, toTile)) {
//                System.out.println("Backtravel");
                return "Backtravel";
            }

            ArrayList<Tile> visited = new ArrayList<>();
            visited.add(figure.getCurrentTile());
            if (prevVisited != null) {
                visited.addAll(prevVisited);
            }
            this.storeManager.putVisited(figure, visited);

            this.storeManager.addMove();
            this.storeManager.getBoard().tiles.get(figure.getCurrentTile().getFieldID()).removeFigure();
            this.storeManager.getBoard().tiles.get(toTile.getFieldID()).addFigure(figure);
            figure.setTile(toTile);
        }

        return "success";
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

    private boolean checkBackTravelling(ArrayList<Tile> prevVisited, Tile toTile) {
        if (prevVisited != null) {
            for (Tile tile : prevVisited) {
                if (tile.getFieldID() == toTile.getFieldID()) {
                    //System.out.println("Backtravelling!");
                    //setState = MoveFigure
                    return true;
                }
            }
        }
        return false;
    }

    private Map<Figure, ArrayList<Tile>> getPossibleMoves() {
        Map<Figure, ArrayList<Tile>> ret = new HashMap<>();

        for (Figure figure : this.storeManager.getCurrentPlayer().getFiguren()) {
            ArrayList<Tile> possible = new ArrayList<>();
            ArrayList<Tile> visited = this.storeManager.getVisited(figure);

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

    private String moveFigureFromHome(Figure fig, Tile toTile) {
        if (!checkFigure(fig)) {
//            System.out.println("Figure is not from active Player");
            return "Figure is not owned by active Player";
        }
        if (!checkReleaseFromHome(fig)) {
//            System.out.println("Figure is not in a Home-Tile.");
            return "Figure is not on a Home-Tile.";
        }
        if (!checkCorrectStartTile(fig, toTile)) {
//            System.out.println("Tile is not a start Tile from player");
            return "Tile is not a start Tile owned by Player";
        }

        if (toTile.isOccupied()) {
            if (toTile.getFigure().getOwner() == this.storeManager.getCurrentPlayer()) {
//                System.out.println("The destination Tile is already occupied by an own figure.");
                return "The destination Tile is already occupied by an own figure.";
            } else {
                return "DUELL!";
            }

        } else {
            fig.getCurrentTile().removeFigure();
            fig.setTile(toTile);
            this.storeManager.getBoard().tiles.get(toTile.getFieldID()).addFigure(fig);
        }

        return "success";
    }

    private boolean checkFigure(Figure figure) {
        return figure.getOwner() == this.storeManager.getCurrentPlayer();
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

    @Override
    public Board initializeBoard(ArrayList<Player> players) {
        Board board = new Board(players.size());

        for (int i = 0; i < players.size(); i++) {
            players.get(i).setHeimFelder(board.createHomeTiles(players.get(i)));
        }

        int j = 0;
        for (Tile tile : board.tiles) {
            if (tile.isStartTile()) {
                players.get(j % players.size()).addStartTile(tile);
                j++;
            }
        }

        return board;
    }

    @Override
    public void showgetStartTilesForEachFigureInHome() {

        Player currentPlayer = storeManager.getCurrentPlayer();

        Map<Figure, ArrayList<Tile>> ret = getStartTilesForEachFigureInHome(currentPlayer);

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

    private Map<Figure, ArrayList<Tile>> getStartTilesForEachFigureInHome(Player player) {
        Map<Figure, ArrayList<Tile>> ret = new HashMap<>();
        ArrayList<Tile> tiles = player.getStartFelder();

        for (Figure figure : player.getFiguren()) {
            if (figure.getCurrentTile().getTileType().equals("H")) {
                ret.put(figure, tiles);
            }
        }
        return ret;
    }

}
