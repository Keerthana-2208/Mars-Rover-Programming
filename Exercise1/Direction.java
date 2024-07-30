public enum Direction {
    N, E, S, W;

    private static Direction[] vals = values();

    public Direction left() {
        return vals[(this.ordinal() + 3) % 4];
    }

    public Direction right() {
        return vals[(this.ordinal() + 1) % 4];
    }
}