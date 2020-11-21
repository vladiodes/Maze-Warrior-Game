package Tiles;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.MessageHandler;
import GameUtilities.Position;

import java.util.Random;

public class Monster extends Enemy {
    private int vision;

    public Monster(char Character, Position position, String Name, Health hp, int atk, int def, Experience Exp, Player currentPlayer, int vision) {
        super(Character, position, Name, hp, atk, def, Exp, currentPlayer);
        this.vision=vision;
    }

    //returns what turn it does
    public char MakeTurn(){
        //if the enemy died during the turn of the player
        if(!isAlive()){
            setCharacter('.');
            return 'X';
        }

        Position playerPos=getPlayerPosition();
        if(getPosition().Range(playerPos)<vision){
            int dx=getPosition().dx(playerPos);
            int dy=getPosition().dy(playerPos);
            if(Math.abs(dy)>Math.abs(dx))
                return dy>0? 'a':'d';
            else
                return dx>0?'w':'s';
        }
        else { //not within range
            return RandomMovement();
        }
    }



    @Override
    public String describe(){
        StringBuilder builder=new StringBuilder();
        builder.append(super.describe());
        builder.append(String.format("%-20s", "Experience Value: " + getExp().EnemyString()));
        builder.append(String.format("%-20s", " Vision Range: " + vision ));
        return builder.toString();
    }
}
