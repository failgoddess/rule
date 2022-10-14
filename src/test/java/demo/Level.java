package demo;

public enum Level {
    L_10("10","甲"),
    L_20("20","乙"),
    L_30("30","丙")
    ;
    private String code;
    private String name;
    private Level(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
