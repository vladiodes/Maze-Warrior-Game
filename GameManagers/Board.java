package GameManagers;

import EnemyCharacters.*;
import GameUtilities.MessageHandler;
import GameUtilities.Position;
import Tiles.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Board {
    private Tile[][] board;
    private Player player;
    private List<Enemy> enemies;
    private int width;
    private int length;

    public Board(Player player, List<String> lines){
        this.player=player;
        enemies=new ArrayList<Enemy>();
        length=lines.size();
        width=lines.get(0).length();
        buildGameBoard(lines);
    }

    private void buildGameBoard(List<String> lines){
        board=new Tile[length][width];
        for(int i=0;i<lines.size();i++){
            for (int j=0;j<lines.get(i).length();j++){
                Enemy e=null;
                switch (lines.get(i).charAt(j)) {
                    case '#':
                        board[i][j]=new Wall(new Position(i,j));
                        break;

                    case '.':
                        board[i][j]=new EmptyTile(new Position(i,j));
                        break;

                    case '@':
                        player.getPosition().setPos(new Position(i,j));
                        board[i][j] = player;
                        break;

                    case 's':
                        e = new LannisterSolider(new Position(i, j), player);
                        break;

                    case 'k':
                        e = new LannisterKnight(new Position(i, j), player);
                        break;
                    case 'q':
                        e = new QueenGuard(new Position(i, j), player);
                        break;

                    case 'z':
                        e = new Wright(new Position(i, j), player);
                        break;

                    case 'b':
                        e = new BearWright(new Position(i, j), player);
                        break;

                    case 'g':
                        e = new GiantWright(new Position(i, j), player);
                        break;

                    case 'w':
                        e = new WhiteWalker(new Position(i, j), player);
                        break;

                    case 'M':
                        e = new Mountain(new Position(i, j), player);
                        break;

                    case 'C':
                        e = new QueenCersei(new Position(i, j), player);
                        break;

                    case 'K':
                        e = new KnightsKing(new Position(i, j), player);
                        break;
                    case 'B':
                        e = new BonusTrap(new Position(i, j), player);
                        break;
                    case 'Q':
                        e = new QueensTrap(new Position(i, j), player);
                        break;
                    case 'D':
                        e = new DeathTrap(new Position(i, j), player);
                        break;
                }
                if(e!=null) {
                    enemies.add(e);
                    board[i][j]=e;
                }

            }
        }
        printBoard();
        MessageHandler.getMsgHandler().SendMessage(player.describe());
    }

    public boolean isGameFinished(){
        return !player.isAlive() || enemies.size()==0; //either player is dead or all enemies are dead
    }

    public boolean hasWon(){
        return player.isAlive() && enemies.size()==0;
    }

    public void GameTick(char input){
       int i=player.getPosition().getX();
       int j=player.getPosition().getY();
        switch (input) {
            case 'w':
                if(i-1<0)
                    MessageHandler.getMsgHandler().SendMessage("Can't move there");
                else {
                    if(player.interact(board[i-1][j])) //need to swap
                        swapTiles(i-1,j,i,j);
                }
                break;

            case 's':
                if(i+1==length)
                    MessageHandler.getMsgHandler().SendMessage("Can't move there");
                else {
                    if(player.interact(board[i+1][j]))
                        swapTiles(i+1,j,i,j);
                }
                break;

            case 'd':
                if(j+1==width)
                    MessageHandler.getMsgHandler().SendMessage("Can't move there");
                else {
                    if(player.interact(board[i][j+1]))
                        swapTiles(i,j+1,i,j);
                }
                break;

            case 'a':
                if(j-1<0)
                    MessageHandler.getMsgHandler().SendMessage("Can't move there");
                else {
                    if(player.interact(board[i][j-1]))
                        swapTiles(i,j-1,i,j);
                }
                break;
            case 'e':
                player.AbilityCast(enemies);
                break;
            case 'q':
                break; //do nothing
        }
        player.OnGameTick();


        //enemy turn
        EnemiesMakeTurn();

        printBoard();
        if(!isGameFinished())
            MessageHandler.getMsgHandler().SendMessage(player.describe());

    }

    private void swapTiles(int i, int j, int x, int y){
        Tile temp=board[i][j];
        board[i][j]=board[x][y];
        board[x][y]=temp;
    }

    private void EnemiesMakeTurn(){
        List<Enemy> deadEnemies=new LinkedList<Enemy>();
        for(Enemy u:enemies){
            int x=u.getPosition().getX();
            int y=u.getPosition().getY();
            switch (u.MakeTurn()){
                case 'X':
                    //enemy dead
                    board[x][y]=new EmptyTile(u.getPosition());
                    deadEnemies.add(u);
                    break;
                case 'n':
                    break;
                case 'a':
                    if(y-1>=0) {
                        if (u.interact(board[x][y-1])) //need to swap
                            swapTiles(x,y-1, x,y);
                    }
                    break;
                case 'd':
                    if(y+1<width) {
                        if (u.interact(board[x ][y+1]))//need to swap
                            swapTiles(x,y+1, x,y);
                    }
                    break;
                case 's':
                    if(x+1<length){
                        if(u.interact(board[x+1][y]))//need to swap
                            swapTiles(x+1,y,x,y);
                    }
                    break;
                case 'w':
                    if(x-1>=0){
                        if(u.interact(board[x-1][y]))// need to swap
                            swapTiles(x-1,y,x,y);
                    }
                    break;
            }

        }
        //Deleting dead enemies
        for(Enemy u: deadEnemies){
            enemies.remove(u);
        }
    }

    private void printBoard(){
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                builder.append(board[i][j].toString());
            }
            builder.append("\n");
        }
        MessageHandler.getMsgHandler().SendMessage(builder.toString());
    }
}
