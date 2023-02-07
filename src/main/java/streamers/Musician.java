package streamers;

import streamers.Streamer;

public class Musician extends Streamer {

    public Musician() {
        super(1);
    }

    public Musician(Integer id, String name) {
        super(1);
        this.id = id;
        this.name = name;
    }

}
