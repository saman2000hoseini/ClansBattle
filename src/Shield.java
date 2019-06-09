public class Shield {
    private short decrement;
    private short movementDelay;

    public Shield(short decrement, short movementDelay) {
        this.decrement = decrement;
        this.movementDelay = movementDelay;
    }

    public short getDecrement()
    {
        return decrement;
    }

    public short getMovementDelay()
    {
        return movementDelay;
    }
}
