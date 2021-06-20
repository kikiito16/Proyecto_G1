package edu.upc.dsa.models;

import java.util.List;

public class Map {
    private int id;
    String line1;
    String line2;
    String line3;
    String line4;
    String line5;
    String line6;
    String line7;
    String line8;

    public Map() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMap(List<String> lines) {

        line1 = lines.get(0);
        line2 = lines.get(1);
        line3 = lines.get(2);
        line4 = lines.get(3);
        line5 = lines.get(4);
        line6 = lines.get(5);
        line7 = lines.get(6);
        line8 = lines.get(7);
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getLine3() {
        return line3;
    }

    public String getLine4() {
        return line4;
    }

    public String getLine5() {
        return line5;
    }

    public String getLine6() {
        return line6;
    }

    public String getLine7() {
        return line7;
    }

    public String getLine8() {
        return line8;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public void setLine4(String line4) {
        this.line4 = line4;
    }

    public void setLine5(String line5) {
        this.line5 = line5;
    }

    public void setLine6(String line6) {
        this.line6 = line6;
    }

    public void setLine7(String line7) {
        this.line7 = line7;
    }

    public void setLine8(String line8) {
        this.line8 = line8;
    }
}
