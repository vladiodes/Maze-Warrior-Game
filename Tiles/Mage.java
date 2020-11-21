package Tiles;

import GameUtilities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Mage extends Player {

    private int specialAbilityRange;
    private Resource Mana;
    private int abilityManaCost;
    private int spellPower;
    private int hitsCount;
    private String abilityName="Blizzard";

    public Mage(char Character, Position position, String Name, Health hp, int atk, int def, Experience Exp, int specialAbilityRange, Resource Mana, int abilityManaCost, int spellPower, int hitsCount) {
        super(Character, position, Name, hp, atk, def, Exp);
        this.specialAbilityRange=specialAbilityRange;
        this.Mana=Mana;
        this.abilityManaCost=abilityManaCost;
        this.spellPower=spellPower;
        this.hitsCount=hitsCount;
    }

    @Override
    public String LevelUp(int levelsGained) {
        StringBuilder builder=new StringBuilder();
        builder.append(super.LevelUp(levelsGained) + " In addition:");
        int manaAdded=0;
        int spellAdded=0;
        for(int i=levelsGained-1;i>=0;i--){
            manaAdded=manaAdded+25*(getLevel()-i);
            Mana.setCapacity(Mana.getCapacity()+25*(getLevel()-i));
            Mana.addResource(Mana.getCapacity()/4);
            spellAdded=spellAdded+10*(getLevel()-i);
        }

        spellPower=spellPower+spellAdded;
        builder.append(" Mana capacity +" + manaAdded + " Spell Power +" + spellAdded);
        return builder.toString();
    }

    public void OnGameTick(){
        Mana.addResource(1);
    }

    @Override
    public void AbilityCast(List<Enemy> units) {
        if(Mana.getCurrentAmmount()<abilityManaCost) {
            MessageHandler.getMsgHandler().SendMessage("You don't have enough mana to cast your ability");
            return;
        }
        MessageHandler.getMsgHandler().SendMessage(getName() + " uses " + abilityName);
        Mana.setCurrentAmmount(Mana.getCurrentAmmount()-abilityManaCost);
        int numOfHits=0;
        List<Enemy> withinRange=getUnits(units);
        while (numOfHits<hitsCount && withinRange.size()>0) {
            Enemy enemyToHit = selectRandom(withinRange);
            SimulateCombat(this,enemyToHit,spellPower,enemyToHit.getDef(),true);
            if(!enemyToHit.isAlive()) {
                MessageHandler.getMsgHandler().SendMessage(enemyToHit.getName() + " has died." + getName() + " has gained " + enemyToHit.getExp().getCurrentAmmount() + " exp points from killing " + enemyToHit.getName());
                int levelsGained=getExp().addResource(enemyToHit.getExp().getCurrentAmmount());
                if(levelsGained>0)
                    MessageHandler.getMsgHandler().SendMessage(LevelUp(levelsGained));
                withinRange.remove(enemyToHit);
            }
            numOfHits++;
        }
    }

    private List<Enemy> getUnits(List<Enemy> units){
        List<Enemy> withinRange=new ArrayList<Enemy>();
        for(Enemy u:units){
            if(u.getPosition().Range(getPosition())<specialAbilityRange)
                withinRange.add(u);
        }
        return withinRange;
    }

    private Enemy selectRandom(List<Enemy> units){
        Random rnd=new Random();
        return units.get(rnd.nextInt(units.size()));
    }

    @Override
    public String describe(){
        StringBuilder builder=new StringBuilder();
        builder.append(String.format("%-20s", "Experience: " + getExp().toString()));
        builder.append(String.format("%-20s","Mana:" + Mana.toString()));
        builder.append(String.format("%-20s","Spell Power: " + spellPower));
        return super.describe()+builder.toString();
    }
}
