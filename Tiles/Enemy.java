package Tiles;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.MessageHandler;
import GameUtilities.Position;

import java.util.Random;

public abstract class Enemy extends Unit {
    private Player currentPlayer;

    public Enemy(char Character, Position position, String Name, Health hp, int atk, int def, Experience Exp, Player currentPlayer){
        super(Character,position,Name,hp,atk,def,Exp);
        this.currentPlayer=currentPlayer;
    }


    public Position getPlayerPosition(){return currentPlayer.getPosition();}

    public Player getCurrentPlayer(){return currentPlayer;}

    public abstract char MakeTurn();

    public char RandomMovement(){
        int randomChar=new Random().nextInt(5);
        char output='x';
        switch (randomChar){
            case 0: return 'a';
            case 1:return 'd';
            case 2:return 's';
            case 3:return 'w';
            case 4:return 'n'; //no movement
        }
        return output;
    }

    @Override
    public boolean accept(Visitor visitor){
        return visitor.visit(this);
    }

    @Override
    public boolean visit(Enemy e){
        return false;
    }

    @Override
    public boolean visit(Player p){

        // enemy has interacted a player
        SimulateCombat(this,p,getAtk(),p.getDef(),false);

        //player has died
        if(!p.isAlive()){
            p.setCharacter('X');
            MessageHandler.getMsgHandler().SendMessage(p.getName() + " has died");
        }
        return false;
    }


}
