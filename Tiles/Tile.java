package Tiles;

import GameUtilities.Position;

public abstract class Tile implements Visited,Visitor {
    private char Character;
    private Position position;

    public Tile(char Character, Position position){
        this.Character=Character;
        this.position=position;
    }

    @Override
    public String toString() {
        return String.valueOf(Character);
    }

    public void setCharacter(char Character){
        this.Character=Character;
    }

    public Position getPosition(){return position;}

    public boolean interact(Tile tile){
        return tile.accept(this);
    }

    @Override
    public abstract boolean accept(Visitor visitor);


    @Override
    public abstract boolean visit(Player player);
    @Override
    public abstract boolean visit(Enemy enemy);

    @Override
    public abstract boolean visit(Wall wall);

    @Override
    public abstract boolean visit(EmptyTile emptyTile);
}
