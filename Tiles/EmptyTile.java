package Tiles;
import GameUtilities.Position;

public class EmptyTile extends Tile {
    public EmptyTile(Position position) {
        super('.', position);
    }

    @Override
    public boolean visit(EmptyTile emptyTile) {
        return false;
    }

    @Override
    public boolean visit(Wall wall) {
        return false;
    }

    @Override
    public boolean visit(Player player) {
        return false;
    }

    @Override
    public boolean visit(Enemy enemy) {
        return false;
    }

    @Override
    public boolean accept(Visitor visitor) {
        return visitor.visit(this);
    }
}

