package EnemyCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Monster;
import Tiles.Player;

public class WhiteWalker extends Monster {
    public WhiteWalker(Position position, Player player) {
        super('w', position, "White Walker", new Health(2000,2000), 150, 50, new Experience(1000,1000),  player,6);
    }
}
