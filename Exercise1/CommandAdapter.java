public class CommandAdapter implements Command {
    private char commandChar;
    private Rover rover;

    public CommandAdapter(char commandChar, Rover rover) {
        this.commandChar = commandChar;
        this.rover = rover;
    }

    @Override
    public void execute() {
        Command command = CommandFactory.getCommand(commandChar, rover);
        command.execute();
    }
}
