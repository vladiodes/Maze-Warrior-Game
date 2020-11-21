package EnemyCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Monster;
import Tiles.Player;

public class LannisterSolider extends Monster {
    public LannisterSolider(Position position, Player player) {
        super('s', position, "Lannister Soldier", new Health(80,80), 8, 3, new Experience(25,25),  player,3);
    }
}
