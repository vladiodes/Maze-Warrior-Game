package GameUtilities;

public class Position {
    private int x;
    private int y;

    public Position(int x,int y){
        this.x=x;
        this.y=y;
    }
    public double Range(Position other){
        return Math.sqrt(Math.pow((x-other.x),2) + Math.pow((y-other.y),2));
    }

    public void setPos(Position other){
        this.x=other.x;
        this.y=other.y;
    }

    public int dx(Position other){
        return x-other.x;
    }
    public int dy(Position other){
        return y-other.y;
    }

    public int getX(){return x;}
    public int getY(){return y;}

    public String toString(){
        return "["+getX() + "," + getY() +"]";
    }
}
