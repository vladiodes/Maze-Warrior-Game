package Tiles;

import GameUtilities.*;

import java.util.ArrayList;
import java.util.List;

public class Rogue extends Player {

    private int abilityEnergyCost;
    private Resource Energy;
    private int specialAbilityRange=2;
    private String abilityName="Fan of Knives";

    public Rogue(char Character, Position position, String Name, Health hp, int atk, int def, Experience Exp, int abilityEnergyCost, Resource Energy) {
        super(Character, position, Name, hp, atk, def, Exp);
        this.abilityEnergyCost=abilityEnergyCost;
        this.Energy=Energy;
    }

    @Override
    public String LevelUp(int levelsGained) {
        StringBuilder builder=new StringBuilder();
        builder.append(super.LevelUp(levelsGained) + " In addition:");
        int atkAdded=0;
        for(int i=0;i<levelsGained;i++){
            atkAdded=atkAdded+3*(getLevel()-i);
        }
        Energy.setCurrentAmmount(Energy.getCapacity());
        setAtk(getAtk()+atkAdded);
        builder.append(" Attack +"+ atkAdded );
        return builder.toString();
    }

    public void OnGameTick(){
        Energy.addResource(10);
    }

    @Override
    public void AbilityCast(List<Enemy> units) {
        if (Energy.getCurrentAmmount() < abilityEnergyCost) {
            MessageHandler.getMsgHandler().SendMessage("You don't have enough energy to cast your ability");
            return;
        }
        Energy.setCurrentAmmount(Energy.getCurrentAmmount()-abilityEnergyCost);
        MessageHandler.getMsgHandler().SendMessage(getName() + " has used " + abilityName);
        for(Enemy u:generateUnitsWithingRange(units)){
            SimulateCombat(this,u,getAtk(),u.getDef(),true);
                if(!u.isAlive()) {
                    MessageHandler.getMsgHandler().SendMessage(u.getName() + " has died." + getName() + " has gained " + u.getExp().getCurrentAmmount() + " exp points from killing " + u.getName());
                    int levelsGained=getExp().addResource(u.getExp().getCurrentAmmount());
                    if(levelsGained>0)
                        MessageHandler.getMsgHandler().SendMessage(LevelUp(levelsGained));
                }

            }
        }

    private List<Enemy> generateUnitsWithingRange(List<Enemy> units){
        List<Enemy> withinRange=new ArrayList<Enemy>();
        for(Enemy u:units){
            if(u.getPosition().Range(getPosition())<specialAbilityRange)
                withinRange.add(u);
        }
        return withinRange;
    }

    @Override
    public String describe(){
        StringBuilder builder=new StringBuilder();
        builder.append(String.format("%-20s", "Experience: " + getExp().toString()));
        builder.append(String.format("%-20s","Energy: " + Energy.toString()));
        return super.describe()+builder.toString();
    }
}
