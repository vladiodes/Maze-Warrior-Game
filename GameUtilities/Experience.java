package GameUtilities;

public class Experience {
    private int currentAmmount;
    private int capacity;

    public Experience(int currentAmmount, int capacity){
        this.currentAmmount=currentAmmount;
        this.capacity=capacity;
    }

    //sets the current amount of exp points and returns the numbers of levels that the player has leveled up
    public int setCurrentAmmount(int value){
        int counter=0;
        while (value>=capacity){
            value=value-capacity;
            capacity=capacity+50;
            counter++;
        }
        currentAmmount=value;
        return counter;
    }

    public int getCapacity() {
        return capacity;
    }

    public int addResource(int value){
        return setCurrentAmmount(currentAmmount+value);
    }

    public int getCurrentAmmount(){return currentAmmount;}

    @Override
    public String toString(){
        return getCurrentAmmount() + "/" + getCapacity();
    }

    public String EnemyString(){ return getCurrentAmmount() +"";}
}
