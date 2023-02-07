package streams;

import streams.Stream;

public class Song extends Stream {


    public Song() {
        super(1);
    }

    public Song(Integer id, Integer genre, Long noOfStreams, Integer streamerID, Long length, Long dateAdded, String name) {
        super(1);
        this.id = id;
        this.streamGenre = genre;
        this.noOfStreams = noOfStreams;
        this.streamerId = streamerID;
        this.length = length;
        this.dateAdded = dateAdded;
        this.name = name;
    }

}
