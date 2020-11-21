package PlayerCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Warrior;

public class TheHound extends Warrior {
    public TheHound(Position position) {
        super('@', position, "The Hound", new Health(400, 400), 20, 6, new Experience(0, 50),  5);
    }
}
