package GameUtilities;

public class Resource {
        private int currentAmmount;
        private int capacity;

        public Resource(int currentAmmount, int capacity){
            this.currentAmmount=currentAmmount;
            this.capacity=capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }
        public void setCurrentAmmount(int value){
            if(value>capacity)
                currentAmmount=capacity;
            else
                currentAmmount=value;
        }

        public int getCapacity() {
            return capacity;
        }

        public void addResource(int value){
            setCurrentAmmount(currentAmmount+value);
        }

        public int getCurrentAmmount(){return currentAmmount;}

        @Override
        public String toString(){
            return getCurrentAmmount() + "/" + getCapacity();
        }

}
