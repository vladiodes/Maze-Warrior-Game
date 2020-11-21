package GameManagers;

import GameUtilities.MessageHandler;
import GameUtilities.Position;
import PlayerCharacters.*;
import Tiles.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class GameHandler {

    public static List<String> readAllLines(String path) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +
                    e.getStackTrace());
        }
        return lines;
    }

    public static void main(String[] args){
        Path path= Paths.get(args[0]);
        List<List<String>> levels=new ArrayList<>();

        File folder = new File(args[0]);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                levels.add(readAllLines(path.resolve(file.getName()).toString()));
            }
        }
        Player currentPlayer=null;
        Scanner myScanner = new Scanner(System.in);
        MessageHandler.getMsgHandler().SendMessage("Select player:");
        MessageHandler.getMsgHandler().SendMessage("1." + new JonSnow(null).describe());
        MessageHandler.getMsgHandler().SendMessage("2." + new TheHound(null).describe());
        MessageHandler.getMsgHandler().SendMessage("3." + new Melisandre(null).describe());
        MessageHandler.getMsgHandler().SendMessage("4." + new Thoros(null).describe());
        MessageHandler.getMsgHandler().SendMessage("5." + new Arya(null).describe());
        MessageHandler.getMsgHandler().SendMessage("6." + new Bronn(null).describe());
        MessageHandler.getMsgHandler().SendMessage(("7." + new Ygritte((null)).describe()));
        int input=myScanner.nextInt();
        switch (input){
            case 1: currentPlayer=new JonSnow(new Position(1,1));  break;
            case 2: currentPlayer=new TheHound(new Position(1,1)); break;
            case 3: currentPlayer=new Melisandre(new Position(1,1)); break;
            case 4: currentPlayer=new Thoros(new Position(1,1)); break;
            case 5: currentPlayer=new Arya(new Position(1,1)); break;
            case 6: currentPlayer=new Bronn(new Position(1,1)); break;
            case 7: currentPlayer=new Ygritte(new Position(1,1)); break;
        }
        MessageHandler.getMsgHandler().SendMessage("You have selected:" + "\n" + currentPlayer.getName());

        boolean hasLost=false;
        for(int i=0;i<levels.size() && !hasLost;i++){
            MessageHandler.getMsgHandler().SendMessage("Level " + i + " :");
            List<String> lines=levels.get(i);
            Board GameBoard=new Board(currentPlayer,lines);
            while (!GameBoard.isGameFinished())
            {
                char playerTurn=myScanner.next().charAt(0);
                GameBoard.GameTick(playerTurn);
            }
            hasLost=!GameBoard.hasWon();
            if(!hasLost)
                MessageHandler.getMsgHandler().SendMessage("Win");
            else
                MessageHandler.getMsgHandler().SendMessage("Lose");
        }
    }
}
