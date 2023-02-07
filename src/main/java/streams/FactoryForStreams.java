package streams;


public class FactoryForStreams {

    public static FactoryForStreams factory = null;

    private FactoryForStreams(){}

    public static FactoryForStreams getInstance() {
        if (factory == null) {
            return new FactoryForStreams();
        }
        return factory;
    }

    public Stream newStream(Integer type, Integer id, Integer genre, Long noOfStreams, Integer streamerID, Long length, Long dateAdded, String name) {

        switch (type) {
            case  1:
                return new Song(id, genre, noOfStreams, streamerID, length, dateAdded, name);
            case 2:
                return new Podcast(id, genre, noOfStreams, streamerID, length, dateAdded, name);
            case 3:
                return new Audiobook(id, genre, noOfStreams, streamerID, length, dateAdded, name);
            default:
                return null;
        }

    }


}
