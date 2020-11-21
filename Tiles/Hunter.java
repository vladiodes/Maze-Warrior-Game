package Tiles;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.MessageHandler;
import GameUtilities.Position;

import java.util.ArrayList;
import java.util.List;

public class Hunter extends Player {

    private String abilityName="Shoot";
    private int range;
    private int arrowsCount=10;
    private int ticksCount=0;
    public Hunter(char Character, Position position, String Name, Health hp, int atk, int def, Experience Exp,int range) {
        super(Character, position, Name, hp, atk, def, Exp);
        this.range=range;
    }

    @Override
    public void AbilityCast(List<Enemy> enemies) {
        if(arrowsCount==0) {
            MessageHandler.getMsgHandler().SendMessage("You don't have arrows to shoot");
            return;
        }

        List<Enemy> withinRange=getUnits(enemies);
        if(withinRange.size()==0)
            MessageHandler.getMsgHandler().SendMessage("No enemies within range");
        else {
            Unit closest=getClosest(withinRange);
            MessageHandler.getMsgHandler().SendMessage(getName() + " uses " + abilityName);
            arrowsCount--;
            SimulateCombat(this,closest,getAtk(),closest.getDef(),true);

            if(!closest.isAlive()) {
                MessageHandler.getMsgHandler().SendMessage(closest.getName() + " has died." + getName() + " has gained " + closest.getExp().getCurrentAmmount() + " exp points from killing " + closest.getName());
                int levelsGained=getExp().addResource(closest.getExp().getCurrentAmmount());
                if(levelsGained>0)
                    MessageHandler.getMsgHandler().SendMessage(LevelUp(levelsGained));
            }
        }
    }

    //returns the closest enemy withing given range
    private Enemy getClosest(List<Enemy> withinRange) {

        Enemy closest=withinRange.get(0);
        double minimalDistance=closest.getPosition().Range(getPosition());
        for(Enemy u: withinRange){
            if(u.getPosition().Range(getPosition())<minimalDistance){
                closest=u;
                minimalDistance=closest.getPosition().Range(getPosition());
            }
        }
        return closest;
    }

    @Override
    public String LevelUp(int levelsGained) {
        StringBuilder builder=new StringBuilder();
        builder.append(super.LevelUp(levelsGained) + " In addition:");
        int arrowsAdded=0;
        int atkAdded=0;
        int defAdded=0;
        for(int i=0;i<levelsGained;i++){
            arrowsAdded=arrowsAdded+10*(getLevel()-i);
            atkAdded=atkAdded+2*(getLevel()-i);
            defAdded=defAdded+(getLevel()-i);
        }
        arrowsCount=arrowsCount+arrowsAdded;
        setAtk(getAtk()+atkAdded);
        setDef(getDef()+defAdded);
        builder.append("Attack +" + atkAdded +", Defence +" +defAdded + ", Arrows +" + arrowsAdded);
        return builder.toString();
    }

    @Override
    public String describe(){
        StringBuilder builder=new StringBuilder();
        builder.append(String.format("%-20s", "Experience: " + getExp().toString()));
        builder.append(String.format("%-20s","Arrows: " + arrowsCount));
        builder.append(String.format("%-20s","Range: " + range));
        return super.describe()+builder.toString();
    }

    @Override
    public void OnGameTick() {
        if(ticksCount==10) {
            arrowsCount = arrowsCount + getLevel();
            ticksCount = 0;
        }
        else
            ticksCount++;
    }

    private List<Enemy> getUnits(List<Enemy> enemies){
        List<Enemy> withinRange=new ArrayList<Enemy>();
        for(Enemy u:enemies){
            if(u.getPosition().Range(getPosition())<=range)
                withinRange.add(u);
        }
        return withinRange;
    }
}
