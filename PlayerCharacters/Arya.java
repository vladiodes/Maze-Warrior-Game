package PlayerCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import GameUtilities.Resource;
import Tiles.Rogue;

public class Arya extends Rogue {
    public Arya(Position position) {
        super('@', position, "Arya Stark", new Health(150,150), 40, 2, new Experience(0,50),  20, new Resource(100,100));
    }
}
