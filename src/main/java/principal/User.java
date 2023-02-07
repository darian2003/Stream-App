package principal;

import streams.Stream;

import java.util.ArrayList;
import java.util.List;

public class User {

    protected Integer id;
    protected String name;
    protected List<Integer> streams = new ArrayList<>();

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public List<Integer> getStreams() {
        return streams;
    }

    public void printStream(Stream stream, String streamerName) {
        System.out.print("{\"id\":\"" + stream.getId() + "\",\"name\":\"" + stream.getName()+"\",\"streamerName\":\"" + streamerName + "\",\"noOfListenings\":\"" + stream.getNoOfStreams() + "\",\"length\":\""+ ProiectPOO.calculateLength(stream.getLength())+"\",\"dateAdded\":\"" + ProiectPOO.calculateDate(stream.getDateAdded()) + "\"}");
    }

}
