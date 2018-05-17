package tina.game;

public class Game {

    boolean [] skeleton;
    String name;
    int dimension;

    Game (boolean [] skeleton, String name, int dimension){
        this.skeleton = skeleton;
        this.name = name;
        this.dimension = dimension;

    }

    public boolean[] getSkeleton() {
        return skeleton;
    }

    public void setSkeleton(boolean[] skeleton) {
        this.skeleton = skeleton;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
}
