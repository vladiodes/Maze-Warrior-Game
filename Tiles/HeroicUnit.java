package Tiles;

import java.util.List;

public interface HeroicUnit {
    //even though boss class which is an enemy implements this method - it holds an instance of the player, and therefore doesn't use the argument - so it's mainly for the player characters.
    public void AbilityCast(List<Enemy> enemies);
}
