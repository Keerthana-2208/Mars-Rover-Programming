public class CommandFactory {
    public static Command getCommand(char commandChar, Rover rover) {
        switch (commandChar) {
            case 'M':
                return new MoveCommand(rover);
            case 'L':
                return new TurnLeftCommand(rover);
            case 'R':
                return new TurnRightCommand(rover);
            default:
                throw new IllegalArgumentException("Invalid command: " + commandChar);
        }
    }
}
