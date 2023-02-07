package streams;

import principal.ProiectPOO;
import streamers.Streamer;

import java.util.ArrayList;

public abstract class Stream {

    protected Integer streamType;
    protected Integer id;
    protected Integer streamGenre;
    protected Long noOfStreams;
    protected Integer streamerId;
    protected Long length;
    protected Long dateAdded;
    protected String name;

    public Integer getId() {
        return id;
    }

    public Integer getStreamType() {
        return streamType;
    }

    public void setNoOfStreams(Long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public Long getNoOfStreams() {
        return noOfStreams;
    }

    public Integer getStreamerId() {
        return streamerId;
    }

    public Long getLength() {
        return length;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public String getName() {
        return name;
    }

    protected Stream(Integer type) {
        this.streamType = type;
    }

    public void printStream(String streamerName) {
        System.out.print("{\"id\":\"" + this.getId() + "\",\"name\":\"" + this.getName()+"\",\"streamerName\":\"" + streamerName + "\",\"noOfListenings\":\"" + this.getNoOfStreams() + "\",\"length\":\""+ ProiectPOO.calculateLength(this.getLength())+"\",\"dateAdded\":\"" + ProiectPOO.calculateDate(this.getDateAdded()) + "\"}");
    }

    public static void printStreamList(ArrayList<Stream> recommendedStreamList, ProiectPOO project, int listSize) {

        System.out.print("[");
        int ok = 0;
        int i = 0;

        for (Stream stream : recommendedStreamList) {
            if (ok == 1)
                System.out.print(",");
            Streamer streamer = project.returnStreamer(stream.getStreamerId());
            stream.printStream(streamer.getName());
            ok = 1;
            i++;
            if (i == listSize)
                break;
        }
        System.out.println("]");
    }

}
