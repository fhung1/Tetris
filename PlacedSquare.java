public class PlacedSquare {
    private int[] coords;
    private String name;

    public PlacedSquare(int[] c, String n) {
        coords = c;
        name = n;
    }

    public int[] getCoords() {
        return coords;
    }

    public String getName() {
        return name;
    }

}
