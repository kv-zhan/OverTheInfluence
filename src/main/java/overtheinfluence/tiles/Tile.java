package tiles;

import java.awt.image.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class represents individual tiles in the world map of the game.</p>
 *
 * <p>Work Allocation:<ul>
 *     <li>create Tile - Kevin Zhan</li>
 *     <li>tile artwork design - Alexander Peng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Tile {
    /**
     * the image of the tile
     */
    public BufferedImage image;
    /**
     * whether entities will collide with this tile
     */
    public boolean collision;
}
