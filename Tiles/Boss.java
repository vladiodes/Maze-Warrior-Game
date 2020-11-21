package Tiles;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.MessageHandler;
import GameUtilities.Position;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Boss extends Enemy implements HeroicUnit {

    private int visionRange;
    private int abilityFreq;
    private int combatTicks=0;
    private String abilityName="Shooting";

    public Boss(char Character, Position position, String Name, Health hp, int atk, int def, Experience Exp, Player currentPlayer, int visionRange, int abilityFreq) {
        super(Character, position, Name, hp, atk, def, Exp, currentPlayer);
        this.visionRange=visionRange;
        this.abilityFreq=abilityFreq;
    }

    @Override
    public char MakeTurn() {

        //if the enemy died during the turn of the player
        if(!isAlive()){
            setCharacter('.');
            return 'X';
        }

        Position playerPos=getPlayerPosition();
        if(getPosition().Range(playerPos)<visionRange){
            if(combatTicks==abilityFreq){
                combatTicks=0;
                AbilityCast(null); //we know that there's no need in the list because there's only 1 player at the same time - this is just an interface constraint
                return 'n'; //no movement because we casted the ability
            }
            else {
                combatTicks++;
                int dx = getPosition().dx(playerPos);
                int dy = getPosition().dy(playerPos);
                if (Math.abs(dy) > Math.abs(dx))
                    return dy > 0 ? 'a' : 'd';
                else
                    return dx > 0 ? 'w' : 's';
            }
        }
        else {
            combatTicks=0;
            return RandomMovement();
        }

    }

    /* the function is public due to interface constraints - however it is never called outside of this class
    In addition, its argument is not necessary, however it's also an interface constraint.
    */
    @Override
    public void AbilityCast(List<Enemy> enemies) {
        //When invoked - we can be sure that the player is within range.
        MessageHandler.getMsgHandler().SendMessage(getName() + " uses " + abilityName);
        SimulateCombat(this,getCurrentPlayer(),getAtk(),getCurrentPlayer().getDef(),true);

        //player has died
        if(!getCurrentPlayer().isAlive()){
            getCurrentPlayer().setCharacter('X');
            MessageHandler.getMsgHandler().SendMessage(getCurrentPlayer().getName() + " has died");
        }
    }
}
