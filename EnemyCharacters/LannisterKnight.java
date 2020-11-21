package EnemyCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Monster;
import Tiles.Player;

public class LannisterKnight extends Monster {
    public LannisterKnight(Position position, Player player) {
        super('k', position, "Lannister Knight", new Health(200,200), 14, 8, new Experience(50,50),  player,4);
    }
}
