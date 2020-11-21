package PlayerCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Warrior;

public class JonSnow extends Warrior {
    public JonSnow(Position position) {
        super('@',position,"Jon Snow",new Health(300,300),30,4,new Experience(0,50),3);
    }
}
