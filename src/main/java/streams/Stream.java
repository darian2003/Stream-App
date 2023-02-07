package streams;

import principal.ProiectPOO;

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

}
