package Tiles;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.MessageHandler;
import GameUtilities.Position;
import javafx.print.PageLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player {
    private int specialAbilityRange=3;
    private int coolDown;
    private int remainingCD;
    private String abilityName="Avenger's Shield";


    public Warrior(char Character, Position position, String Name, Health hp, int atk, int def, Experience Exp, int coolDown) {
        super(Character, position, Name, hp, atk, def, Exp);
        this.coolDown=coolDown;
        remainingCD=0;
    }

    public void OnGameTick(){
        setRemainingCD(getRemainingCD()-1);
    }

    @Override
    public String LevelUp(int levelsGained) {
        StringBuilder builder=new StringBuilder();
        builder.append(super.LevelUp(levelsGained) + ", In addition:");

        int hpAdded=0;
        int atkAdded=0;
        int defAdded=0;

        for(int i=0;i<levelsGained;i++){
            hpAdded=hpAdded+5*(getLevel()-i);
            atkAdded=atkAdded+2*(getLevel()-i);
            defAdded=defAdded+(getLevel()-i);
        }
        setRemainingCD(0);
        getHp().addCapacity(hpAdded);
        getHp().setMaxHP();
        setAtk(getAtk()+atkAdded);
        setDef(getDef()+defAdded);
        builder.append(" Cooldown=0" + " HP capacity +" + hpAdded + " Attack +"+ atkAdded + " Defence +" + defAdded);
        return builder.toString();
    }

    public void setRemainingCD(int value){
        if(value<0)
            remainingCD=0;
        else
            remainingCD=value;
    }

    public int getRemainingCD(){return remainingCD;}

    @Override
    public void AbilityCast(List<Enemy> units) {
        if (remainingCD > 0) {
            MessageHandler.getMsgHandler().SendMessage("You can't use this because you have to wait until cool down reset");
            return;
        }
        remainingCD = coolDown+1;
        int currentHp = getHp().getCurrentAmmount();
        int capacity = getHp().getCapacity();
        getHp().addHP(10*getDef());
        MessageHandler.getMsgHandler().SendMessage(getName() + " used " + abilityName + ", healing for " + 10*getDef());

        Unit enemyToHit = generateRandomUnit(units);
        if (enemyToHit != null) { //if found a random enemy, then hit it
            SimulateCombat(this,enemyToHit,capacity/10,enemyToHit.getDef(),true);
            if(!enemyToHit.isAlive()){
                MessageHandler.getMsgHandler().SendMessage(enemyToHit.getName() + " has died." + getName() + " has gained " + enemyToHit.getExp().getCurrentAmmount() + " exp points from killing " + enemyToHit.getName());
                int levelsGained=getExp().addResource(enemyToHit.getExp().getCurrentAmmount());
                if(levelsGained>0)
                    MessageHandler.getMsgHandler().SendMessage(LevelUp(levelsGained));
            }
        }
    }

    private Unit generateRandomUnit(List<Enemy> units){
       List<Enemy> withinRange=new ArrayList<Enemy>();
        for(Enemy u:units){
            if(u.getPosition().Range(getPosition())<specialAbilityRange)
                withinRange.add(u);
        }
        if(withinRange.isEmpty())
            return null;
        int size=withinRange.size();
        Random rnd=new Random();
        return withinRange.get(rnd.nextInt(size));
    }

    @Override
    public String describe(){
        StringBuilder builder=new StringBuilder();
        builder.append(String.format("%-20s", "Experience: " + getExp().toString()));
        builder.append(String.format("%-20s","Cooldown: " + remainingCD + "/" + coolDown));
        return super.describe()+builder.toString();
    }

}
