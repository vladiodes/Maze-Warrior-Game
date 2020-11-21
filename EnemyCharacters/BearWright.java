package EnemyCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Monster;
import Tiles.Player;

public class BearWright extends Monster {
    public BearWright(Position position, Player player) {
        super('b', position, "Bear Wright", new Health(1000,1000), 75, 30, new Experience(250,250), player,4);
    }
}
