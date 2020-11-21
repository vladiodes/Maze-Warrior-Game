package Tiles;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.MessageHandler;
import GameUtilities.Position;

import java.util.List;

public abstract class Player extends Unit implements HeroicUnit {

    private int level=1;


    public Player(char Character, Position position, String Name, Health hp, int atk, int def, Experience Exp){
        super(Character,position,Name,hp,atk,def,new Experience(0,50));
    }

    public String LevelUp(int levelsGained) {
        StringBuilder builder=new StringBuilder();
        int hpAdded=0;
        int atkAdded=0;
        int defAdded=0;
        for(int i=1;i<=levelsGained;i++){
            setLevel(getLevel()+1);
            hpAdded=hpAdded+10*getLevel();
            atkAdded=atkAdded+(4*getLevel());
            defAdded=defAdded+getLevel();
        }
        getHp().addCapacity(hpAdded);
        getHp().setMaxHP();
        setAtk(getAtk()+atkAdded);
        setDef(getDef()+defAdded);
        builder.append(getName() + " has reached level " + getLevel() + " HP Capacity +" + hpAdded + ", Attack +" + atkAdded + ", Defence +" + defAdded );
        return builder.toString();
    }

    @Override
    public abstract void AbilityCast(List<Enemy> units);

    public abstract void OnGameTick();

    @Override
    public boolean visit(Enemy e){
        //player has interacted with enemy
        SimulateCombat(this,e,getAtk(),e.getDef(),false);

        //enemy was killed by the player
        if(!e.isAlive()){
            MessageHandler.getMsgHandler().SendMessage(e.getName() + " has died. " + getName() + " has gained " + e.getExp().getCurrentAmmount() + " exp points from killing " + e.getName());
            int levelsGained=getExp().addResource(e.getExp().getCurrentAmmount());
            if(levelsGained>0)
                MessageHandler.getMsgHandler().SendMessage(LevelUp(levelsGained));
            swapPositions(this,e);
            return true;
        }
        return false;
    }

    @Override
    public boolean visit(Player p){
        return false;
    }

    @Override
    public boolean accept(Visitor v){
        return v.visit(this);
    }

    public void setLevel(int value){level=value;}

    public int getLevel(){return level;}

    public String describe(){
        StringBuilder builder=new StringBuilder();
        builder.append(String.format("%-20s", "Level: " + getLevel()));
        return super.describe()+builder.toString();
    }



}
