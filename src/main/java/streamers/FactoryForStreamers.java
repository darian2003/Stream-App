package streamers;

public class FactoryForStreamers {

    public static FactoryForStreamers factory = null;

    private FactoryForStreamers(){}

    public static FactoryForStreamers getInstance() {
        if (factory == null) {
            return new FactoryForStreamers();
        }
        return factory;
    }

    public Streamer newStreamer(Integer type, Integer id, String name) {

        switch (type) {
            case  1:
                return new Musician(id, name);
            case 2:
                return new Host(id, name);
            case 3:
                return new AudiobookAuthor(id, name);
            default:
                return null;
        }

    }

}
