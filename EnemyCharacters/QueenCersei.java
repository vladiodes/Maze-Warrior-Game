package EnemyCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Boss;
import Tiles.Monster;
import Tiles.Player;

public class QueenCersei extends Boss {
    public QueenCersei(Position position, Player player) {
        //frequency wasn't mentioned - so came up on my own
        super('C',position,"Queen Cersei",new Health(100,100),10,10,new Experience(1000,1000),player,1,3);
    }
}
