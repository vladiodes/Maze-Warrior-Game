package Tiles;

public interface Visitor {
    boolean visit(EmptyTile emptyTile);
    boolean visit(Wall wall);
    boolean visit(Enemy enemy);
    boolean visit(Player player);
}
