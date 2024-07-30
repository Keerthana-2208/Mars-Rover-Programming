import java.util.*;

// Command Pattern: Command interface
interface Command {
    void execute();
}

// Command Pattern: Move command implementation
class MoveCommand implements Command {
    private Rover rover;

    public MoveCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.move();
    }
}

// Command Pattern: Turn left command implementation
class TurnLeftCommand implements Command {
    private Rover rover;

    public TurnLeftCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.turnLeft();
    }
}

// Command Pattern: Turn right command implementation
class TurnRightCommand implements Command {
    private Rover rover;

    public TurnRightCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.turnRight();
    }
}

// Enum for direction with utility methods to turn left and right
enum Direction {
    N, E, S, W;

    private static Direction[] vals = values();

    public Direction left() {
        return vals[(this.ordinal() + 3) % 4];
    }

    public Direction right() {
        return vals[(this.ordinal() + 1) % 4];
    }
}

// Class representing position on the grid
class Position {
    int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Position move(Direction direction) {
        switch (direction) {
            case N: return new Position(x, y + 1);
            case E: return new Position(x + 1, y);
            case S: return new Position(x, y - 1);
            case W: return new Position(x - 1, y);
            default: throw new IllegalStateException("Unexpected value: " + direction);
        }
    }
}

// Composite Pattern: Grid class representing the grid and obstacles
class Grid {
    private int width, height;
    private Set<Position> obstacles;

    public Grid(int width, int height, Set<Position> obstacles) {
        this.width = width;
        this.height = height;
        this.obstacles = obstacles;
    }

    public boolean isWithinBounds(Position position) {
        return position.x >= 0 && position.x < width && position.y >= 0 && position.y < height;
    }

    public boolean isObstacle(Position position) {
        return obstacles.contains(position);
    }
}

// Rover class encapsulating its state and behavior
class Rover {
    private Position position;
    private Direction direction;
    private Grid grid;

    public Rover(Position position, Direction direction, Grid grid) {
        this.position = position;
        this.direction = direction;
        this.grid = grid;
    }

    public void move() {
        Position newPosition = position.move(direction);
        if (grid.isWithinBounds(newPosition) && !grid.isObstacle(newPosition)) {
            position = newPosition;
        }
    }

    public void turnLeft() {
        direction = direction.left();
    }

    public void turnRight() {
        direction = direction.right();
    }

    public String getStatus() {
        return String.format("Rover is at (%d, %d) facing %s. No Obstacles detected.", position.x, position.y, direction);
    }
}

// Main class
public class MarsRover {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking grid size input
        System.out.println("Enter grid size (width height): ");
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        // Taking starting position input
        System.out.println("Enter starting position (x y direction): ");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();
        Direction startDirection = Direction.valueOf(scanner.next().toUpperCase());

        // Taking obstacles input
        System.out.println("Enter number of obstacles: ");
        int obstacleCount = scanner.nextInt();
        Set<Position> obstacles = new HashSet<>();
        for (int i = 0; i < obstacleCount; i++) {
            System.out.println("Enter obstacle position (x y): ");
            int obstacleX = scanner.nextInt();
            int obstacleY = scanner.nextInt();
            obstacles.add(new Position(obstacleX, obstacleY));
        }

        // Initializing grid and rover
        Grid grid = new Grid(width, height, obstacles);
        Rover rover = new Rover(new Position(startX, startY), startDirection, grid);

        // Taking commands input
        System.out.println("Enter commands (M, L, R): ");
        String commandsString = scanner.next().toUpperCase();
        List<Command> commands = new ArrayList<>();
        for (char commandChar : commandsString.toCharArray()) {
            switch (commandChar) {
                case 'M':
                    commands.add(new MoveCommand(rover));
                    break;
                case 'L':
                    commands.add(new TurnLeftCommand(rover));
                    break;
                case 'R':
                    commands.add(new TurnRightCommand(rover));
                    break;
                default:
                    System.out.println("Invalid command: " + commandChar);
            }
        }

        // Executing commands
        commands.forEach(Command::execute);
        System.out.println(rover.getStatus());

        scanner.close();
    }
}
