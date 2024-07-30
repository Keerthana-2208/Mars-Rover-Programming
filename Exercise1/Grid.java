import java.util.Set;

public class Grid {
    private static Grid instance = null;
    private int width, height;
    private Set<Position> obstacles;

    private Grid(int width, int height, Set<Position> obstacles) {
        this.width = width;
        this.height = height;
        this.obstacles = obstacles;
    }

    public static Grid getInstance(int width, int height, Set<Position> obstacles) {
        if (instance == null) {
            instance = new Grid(width, height, obstacles);
        }
        return instance;
    }

    public boolean isWithinBounds(Position position) {
        return position.x >= 0 && position.x < width && position.y >= 0 && position.y < height;
    }

    public boolean isObstacle(Position position) {
        return obstacles.contains(position);
    }
}