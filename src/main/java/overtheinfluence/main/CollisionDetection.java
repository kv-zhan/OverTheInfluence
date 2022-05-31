package main;

import entity.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class is used for detection collision between entities, namely the player, and the tiles being moved over.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Collision Detection - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class CollisionDetection {

    /**
     * the level using the collision detection
     */
    Level lvl;

    /**
     * constructor for CollisionDetection
     *
     * @param level the level using collision detection
     */
    public CollisionDetection(Level level) {
        lvl = level;
    }

    /**
     * checks if an entity is colliding with a tile
     *
     * @param e the entity to check
     */
    public void tileCollide(Entity e) {
        int worldXLeft = e.worldX + e.area.x;
        int worldXRight = e.worldX + e.area.x + e.area.width;
        int worldYTop = e.worldY + e.area.y;
        int worldYBottom = e.worldY + e.area.y + e.area.height;

        int leftCol = worldXLeft / lvl.tileSize;
        int rightCol = worldXRight / lvl.tileSize;

        int tileUp1, tileUp2, tileDown1, tileDown2, tileLeft1, tileLeft2, tileRight1, tileRight2;

        int topRow = (worldYTop - e.speed) / lvl.tileSize;
        tileUp1 = lvl.tm.tileMap[leftCol][topRow];
        tileUp2 = lvl.tm.tileMap[rightCol][topRow];
        if (lvl.tm.tile[tileUp1].collision || lvl.tm.tile[tileUp2].collision) {
            e.collidingT = true;
        }
        topRow = worldYTop / lvl.tileSize;

        int bottomRow = (worldYBottom + e.speed) / lvl.tileSize;
        tileDown1 = lvl.tm.tileMap[leftCol][bottomRow];
        tileDown2 = lvl.tm.tileMap[rightCol][bottomRow];
        if (lvl.tm.tile[tileDown1].collision || lvl.tm.tile[tileDown2].collision) {
            e.collidingB = true;
        }
        bottomRow = worldYBottom / lvl.tileSize;

        leftCol = (worldXLeft - e.speed) / lvl.tileSize;
        tileLeft1 = lvl.tm.tileMap[leftCol][topRow];
        tileLeft2 = lvl.tm.tileMap[leftCol][bottomRow];
        if (lvl.tm.tile[tileLeft1].collision || lvl.tm.tile[tileLeft2].collision) {
            e.collidingL = true;
        }

        rightCol = (worldXRight + e.speed) / lvl.tileSize;
        tileRight1 = lvl.tm.tileMap[rightCol][topRow];
        tileRight2 = lvl.tm.tileMap[rightCol][bottomRow];
        if (lvl.tm.tile[tileRight1].collision || lvl.tm.tile[tileRight2].collision) {
            e.collidingR = true;
        }
    }

    /**
     * checks if an entity is colliding with any object
     *
     * @param e        the entity to check
     * @param isPlayer whether the entity is the player
     * @return whether the entity is colliding with an object
     */
    public int objectCollide(Entity e, boolean isPlayer) {
        int index = -1;
        for (int i = 0; i < lvl.objects.size(); i++) {
            e.area.x = e.worldX + e.area.x;
            e.area.y = e.worldY + e.area.y;
            lvl.objects.get(i).area.x = lvl.objects.get(i).worldX + lvl.objects.get(i).area.x;
            lvl.objects.get(i).area.y = lvl.objects.get(i).worldY + lvl.objects.get(i).area.y;

            if (isPlayer) {
                if (((Player) e).keyIn.left) {
                    e.area.x -= e.speed;
                    if (e.area.intersects(lvl.objects.get(i).area)) {
                        e.collidingL = true;
                        index = i;
                    }
                    e.area.x = e.areaDefaultX + e.worldX;
                    e.area.y = e.areaDefaultY + e.worldY;
                }
                if (((Player) e).keyIn.right) {
                    e.area.x += e.speed;
                    if (e.area.intersects(lvl.objects.get(i).area)) {
                        e.collidingR = true;
                        index = i;
                    }
                    e.area.x = e.areaDefaultX + e.worldX;
                    e.area.y = e.areaDefaultY + e.worldY;
                }
                if (((Player) e).keyIn.up) {
                    e.area.y -= e.speed;
                    if (e.area.intersects(lvl.objects.get(i).area)) {
                        e.collidingT = true;
                        index = i;
                    }
                    e.area.x = e.areaDefaultX + e.worldX;
                    e.area.y = e.areaDefaultY + e.worldY;
                }
                if (((Player) e).keyIn.down) {
                    e.area.y += e.speed;
                    if (e.area.intersects(lvl.objects.get(i).area)) {
                        e.collidingB = true;
                        index = i;
                    }
                }
            } else {
                switch (e.direction) {
                    case "up":
                        e.area.y -= e.speed;
                        if (e.area.intersects(lvl.objects.get(i).area)) {
                            e.collidingL = true;
                        }
                        break;
                    case "down":
                        e.area.y += e.speed;
                        if (e.area.intersects(lvl.objects.get(i).area)) {
                            e.collidingL = true;
                        }
                        break;
                    case "left":
                        e.area.x -= e.speed;
                        if (e.area.intersects(lvl.objects.get(i).area)) {
                            e.collidingL = true;
                        }
                        break;
                    case "right":
                        e.area.x += e.speed;
                        if (e.area.intersects(lvl.objects.get(i).area)) {
                            e.collidingL = true;
                        }
                        break;
                }
            }
            e.area.x = e.areaDefaultX;
            e.area.y = e.areaDefaultY;
            lvl.objects.get(i).area.x = lvl.objects.get(i).areaDefaultX;
            lvl.objects.get(i).area.y = lvl.objects.get(i).areaDefaultY;
        }
        return index;
    }
}