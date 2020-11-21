package EnemyCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Monster;
import Tiles.Player;

public class GiantWright extends Monster {
    public GiantWright(Position position, Player player) {
        super('g', position, "Giant Wright", new Health(1500,1500), 100, 40, new Experience(500,500),  player,5);
    }
}
