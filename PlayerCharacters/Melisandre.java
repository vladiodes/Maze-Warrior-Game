package PlayerCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import GameUtilities.Resource;
import Tiles.Mage;

public class Melisandre extends Mage {

    public Melisandre(Position position) {
        super('@', position, "Melisandre", new Health(100,100), 5, 1, new Experience(0,50),  6, new Resource(300/4,300), 30, 15, 5);
    }
}
