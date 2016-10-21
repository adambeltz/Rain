package com.adb.rain.level;

import com.adb.rain.entity.Entity;
import com.adb.rain.entity.particle.Particle;
import com.adb.rain.entity.projectile.Projectile;
import com.adb.rain.graphics.Screen;
import com.adb.rain.level.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Level {


    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;


    private List<Entity> entities = new ArrayList<Entity>();
    private List<Projectile> projectiles = new ArrayList<Projectile>();
    private List<Particle> particles = new ArrayList<Particle>();


    public static Level spawn = new SpawnLevel("/levels/spawn.png");

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }

    public Level (String path){
        loadLevel(path);
        generateLevel();
    }


    protected void generateLevel() {
//Cherno has somestuff here!!!!
    }

    protected void loadLevel(String path){

    }

    public void update() {
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update();
        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
        }
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }


        remove();
    }

    private void remove(){
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved()) entities.remove(i);
        }

        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) projectiles.remove(i);
        }

        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isRemoved()) particles.remove(i);
        }
    }



    public List<Projectile> getProjectiles(){
        return projectiles;
    }

    private void time(){

    }

    public boolean tileCollision(int x, int y, int size) {
        boolean solid = false;
        //checks all four corners to see if a tile is solid
        for (int c = 0; c < 4; c++) {
            //these 2 lines control the area that is collidable->>4 is same as divide by 16
            int xt = (x - c % 2 * size) >> 4;
            int yt = (y - c / 2 * size) >> 4;

            if (getTile(xt, yt).solid()) solid = true;
        }
        return solid;
    }


    public void render (int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        // added 4 corner pins to define the area we want to render
        int x0 = xScroll >> 4;  // going from pixel to tile precision to render the tiles that will be on the screen
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        // this code cycles through every number that is on teh screen---between the 4 corner pins
        for(int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
               getTile(x,y).render(x,y,screen);
            }
        }

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(screen);
        }

        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).render(screen);
        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).render(screen);
        }
    }

    public void add(Entity e) {
        e.init(this);
        if (e instanceof Particle) {
            particles.add((Particle) e);
        }else if (e instanceof Projectile){
            projectiles.add((Projectile)e);


        }else{
            entities.add(e);

        }


    }





    public Tile getTile(int x, int y){
        if ( x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        if (tiles[x+y*width] == Tile.col_spawn_floor) return Tile.spawn_floor;
        if (tiles[x+y*width] == Tile.col_spawn_grass) return Tile.spawn_grass;
        if (tiles[x+y*width] == Tile.col_spawn_hedge) return Tile.spawn_hedge;
        if (tiles[x+y*width] == Tile.col_spawn_wall1) return Tile.spawn_wall1;
        if (tiles[x+y*width] == Tile.col_spawn_wall2) return Tile.spawn_wall2;
        if (tiles[x+y*width] == Tile.col_spawn_water) return Tile.spawn_water;
        return Tile.voidTile;

    }


}
