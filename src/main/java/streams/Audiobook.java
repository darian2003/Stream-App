package streams;

import streams.Stream;

public class Audiobook extends Stream {

    public Audiobook() {
        super(3);
    }

    public Audiobook(Integer id, Integer genre, Long noOfStreams, Integer streamerID, Long length, Long dateAdded, String name) {
        super(3);
        this.id = id;
        this.streamGenre = genre;
        this.noOfStreams = noOfStreams;
        this.streamerId = streamerID;
        this.length = length;
        this.dateAdded = dateAdded;
        this.name = name;
    }
}
