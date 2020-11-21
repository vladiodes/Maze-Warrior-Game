package Tiles;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.MessageHandler;
import GameUtilities.Position;

import java.util.Random;

public class Trap extends Enemy {

    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Trap(char Character, Position position, String Name, Health hp, int atk, int def, Experience Exp, Player currentPlayer, int visibilityTime, int invisibilityTime) {
        super(Character, position, Name, hp, atk, def, Exp, currentPlayer);
        this.visibilityTime=visibilityTime;
        this.invisibilityTime=invisibilityTime;
        this.ticksCount=0;
        this.visible=ticksCount < visibilityTime;
    }

    //returns X if died
    //else returns n = no movement
    @Override
    public char MakeTurn() {
        if (!isAlive()) {
            setCharacter('.');
            return 'X';
        }
        visible = ticksCount < visibilityTime;
        if (ticksCount == (visibilityTime + invisibilityTime))
            ticksCount = 0;
        else
            ticksCount++;
        if (getPosition().Range(getPlayerPosition()) < 2) { //attack player if within range
            interact(getCurrentPlayer());
        }

        return 'n';

    }

    @Override
    public String toString(){
        if(visible)
            return super.toString();
        return ".";
    }

    @Override
    public String describe(){
        StringBuilder builder=new StringBuilder();
        builder.append(super.describe());
        builder.append(String.format("%-20s", "Experience Value:" + getExp().EnemyString()));
        return builder.toString();
    }

}
