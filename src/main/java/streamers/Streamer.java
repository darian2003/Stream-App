package streamers;

import principal.ProiectPOO;
import streams.Stream;

public abstract class Streamer {

    protected Integer type;
    protected Integer id;
    protected String name;

    public Integer getId() {
        return id;
    }

    public Streamer(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public void printStream(Stream stream) {
        System.out.print("{\"id\":\"" + stream.getId() + "\",\"name\":\"" + stream.getName()+"\",\"streamerName\":\"" + this.getName() + "\",\"noOfListenings\":\"" + stream.getNoOfStreams() + "\",\"length\":\""+ ProiectPOO.calculateLength(stream.getLength())+"\",\"dateAdded\":\"" + ProiectPOO.calculateDate(stream.getDateAdded()) + "\"}");
    }

}
