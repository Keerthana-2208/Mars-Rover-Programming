import java.util.Objects;

public class Position {
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
