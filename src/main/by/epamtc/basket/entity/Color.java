package by.epamtc.basket.entity;

public enum Color {
    WHITE("White", 0xFFFFFF),
    BLACK("Black", 0x000000),
    YELLOW("Yellow", 0xFFFF00),
    BLUE("Blue", 0x0000FF),
    RED("Red", 0xFF0000),
    GREEN("Green", 0x00FF00);

    private final int colorCode;
    private final String name;

    Color(String name, int colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }

    public int getColorCode() {
        return colorCode;
    }

    public String getName() {
        return name;
    }
}
