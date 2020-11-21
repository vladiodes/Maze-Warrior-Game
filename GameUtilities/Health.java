package GameUtilities;

public class Health {
    private int currentAmmount;
    private int capacity;

    public Health(int currentAmmount,int capacity){
        this.currentAmmount=currentAmmount;
        this.capacity=capacity;
    }

    public int getCurrentAmmount(){return currentAmmount;}

    public int getCapacity(){return capacity;}

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setCurrentAmmount(int currentAmmount){
        if(currentAmmount>capacity)
            this.currentAmmount=capacity;
        else if(currentAmmount<0)
            this.currentAmmount=0;
        else
            this.currentAmmount=currentAmmount;
    }

    public void addHP(int value){
        setCurrentAmmount(currentAmmount+value);
    }

    public void decreaseHP(int value){
        setCurrentAmmount(currentAmmount-value);
    }

    public boolean hasHp() {
        return currentAmmount > 0 ? true : false;
    }

    public void addCapacity(int value){setCapacity(capacity+value);}

    public void setMaxHP(){currentAmmount=capacity;}

    @Override
    public String toString(){
        return getCurrentAmmount() + "/" + getCapacity();
    }

}
