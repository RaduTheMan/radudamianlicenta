package com.org.example;

public class SteamGame extends GamePrototype implements Comparable<SteamGame>{

    private int appid;

    public SteamGame() {
        super();
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SteamGame steamGame = (SteamGame) o;

        return appid == steamGame.appid;
    }

    @Override
    public int compareTo(SteamGame other) {
        return Integer.compare(this.getName().length(), other.getName().length());
    }
}
