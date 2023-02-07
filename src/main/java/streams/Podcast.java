package streams;

import streams.Stream;

public class Podcast extends Stream {

    public Podcast() {
        super(2);
    }

    public Podcast(Integer id, Integer genre, Long noOfStreams, Integer streamerID, Long length, Long dateAdded, String name) {
        super(2);
        this.id = id;
        this.streamGenre = genre;
        this.noOfStreams = noOfStreams;
        this.streamerId = streamerID;
        this.length = length;
        this.dateAdded = dateAdded;
        this.name = name;
    }
}
