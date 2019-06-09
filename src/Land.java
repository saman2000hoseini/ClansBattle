public class Land {
    private int x,y;

    public Land(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Land && ((Land) obj).getX()==this.getX() && ((Land) obj).getY()==this.getY())
            return true;
        return false;
    }
}
