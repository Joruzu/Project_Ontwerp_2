package com.example.sarnamibasicalphav1;

public class Translation {
    private int id;
    private String ned;
    private String sar;

    public Translation(int id, String ned, String sar) {
        this.id = id;
        this.ned = ned;
        this.sar = sar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNed() {
        return ned;
    }

    public void setNed(String ned) {
        this.ned = ned;
    }

    public String getSar() {
        return sar;
    }

    public void setSar(String sar) {
        this.sar = sar;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "id=" + id +
                ", ned='" + ned + '\'' +
                ", sar='" + sar + '\'' +
                '}';
    }
}
