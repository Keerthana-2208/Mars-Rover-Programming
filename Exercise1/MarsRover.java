import java.util.*;

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
        Grid grid = Grid.getInstance(width, height, obstacles);
        Rover rover = new Rover(new Position(startX, startY), startDirection, grid);

        // Taking commands input
        System.out.println("Enter commands (M, L, R): ");
        String commandsString = scanner.next().toUpperCase();
        List<Command> commands = new ArrayList<>();
        for (char commandChar : commandsString.toCharArray()) {
            commands.add(new CommandAdapter(commandChar, rover));
        }

        // Executing commands
        commands.forEach(Command::execute);
        System.out.println(rover.getStatus());

        scanner.close();
    }
}
