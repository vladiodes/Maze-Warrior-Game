package Tiles;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.MessageHandler;
import GameUtilities.Position;

import java.util.Random;

public abstract class Unit extends Tile {
    private String Name;
    private Health hp;
    private int atk;
    private int def;
    private Experience Exp;

    public Unit(char Character, Position position, String Name, Health hp, int atk, int def, Experience Exp){
        super(Character,position);
        this.Name=Name;
        this.hp=hp;
        this.atk=atk;
        this.def=def;
        this.Exp=Exp;
    }

    public int getAtk(){return atk;}

    public int getDef() {
        return def;
    }

    public String getName() {
        return Name;
    }
    public String describe(){
        StringBuilder builder=new StringBuilder();
        builder.append(String.format("%-20s",getName() + ":"));
        builder.append(String.format("%-20s","Health: " + getHp().toString()));
        builder.append(String.format("%-20s", "Attack: " + getAtk()));
        builder.append(String.format("%-20s", "Defence: " + getDef()));
        return builder.toString();
    }


    @Override
    public abstract boolean visit(Player p);

    @Override
    public abstract boolean visit(Enemy e);

    @Override
    public boolean visit(Wall w){
        return false;
    }

    @Override
    public boolean visit(EmptyTile e){
        swapPositions(this,e);
        return true;
    }

    protected void swapPositions(Tile x, Tile y){
        Position temp=new Position(x.getPosition().getX(),x.getPosition().getY());
        x.getPosition().setPos(y.getPosition());
        y.getPosition().setPos(temp);
    }

    private int rollAmount(int value){
        Random rnd=new Random();
        return rnd.nextInt(value+1);
    }

    public boolean isAlive(){
        return hp.hasHp();
    }

    public Health getHp(){return hp;}

    protected void SimulateCombat(Unit attacker, Unit defender, int atkBound, int defBound, boolean isAbility){
       if(!defender.isAlive()) //if the defender is dead then do nothing - for edge cases
           return;
        if(!isAbility) {
            MessageHandler.getMsgHandler().SendMessage(attacker.getName() + " has engaged with combat with " + defender.getName());
            MessageHandler.getMsgHandler().SendMessage(attacker.describe());
            MessageHandler.getMsgHandler().SendMessage(defender.describe());
        }
        int atkDmg=atkBound;
        if(!isAbility) {
            atkDmg = rollAmount(atkBound);
            MessageHandler.getMsgHandler().SendMessage(attacker.getName() + " has rolled " + atkDmg + " attack points");
        }
        int defDmg=rollAmount(defBound);
        MessageHandler.getMsgHandler().SendMessage(defender.getName() + " has rolled " + defDmg + " defence points");

        if((atkDmg-defDmg)>0) {
            int dmgDealt=atkDmg-defDmg;
            defender.getHp().decreaseHP(dmgDealt);
            if (!isAbility)
                MessageHandler.getMsgHandler().SendMessage(attacker.getName() + " has attacked " + defender.getName() + "\n" + defender.getName() + " has lost " + dmgDealt + "health points");
            else
                MessageHandler.getMsgHandler().SendMessage(attacker.getName() + " hit " + defender.getName() + "  for " + dmgDealt + " ability damage");
        }
        else
            MessageHandler.getMsgHandler().SendMessage(defender.getName() + " has blocked successfully an attack from "+ attacker.getName());
    }

    public Experience getExp() {
        return Exp;
    }

    public void setAtk(int value){atk=value;}

    public void setDef(int value){def=value;}

    @Override
    public abstract boolean accept(Visitor visitor);


}
