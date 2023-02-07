package principal;

import command.*;
import streamers.FactoryForStreamers;
import streamers.Streamer;
import streams.FactoryForStreams;
import streams.Stream;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ProiectPOO {

    List<Streamer> streamers = new ArrayList<>();
    List<Stream> streams = new ArrayList<>();
    List<User> users = new ArrayList<>();

    public List<Stream> getStreams() {
        return streams;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Streamer> getStreamers() {
        return streamers;
    }

    private ProiectPOO () {}

    // SINGLETON
    private static ProiectPOO proiectPOO = null;
    public static ProiectPOO getInstance() {
        if (proiectPOO == null) {
            return new ProiectPOO();
        }
        return proiectPOO;
    }

    public static void main(String[] args) {

        if (args == null || args.length < 4) {
            System.out.println("Nothing to read here");
            return;
        }

        ProiectPOO project = proiectPOO.getInstance();

        String baseFolder = "src/main/resources/";
        String streamersFileName = args[0];
        String streamsFileName = args[1];
        String usersFileName = args[2];
        String commandsFileName = args[3];

        File streamersFile = new File(baseFolder + streamersFileName);
        File streamsFile = new File(baseFolder + streamsFileName);
        File usersFile = new File(baseFolder + usersFileName);
        File commandsFile = new File(baseFolder + commandsFileName);

        BufferedReader brStreamers;
        BufferedReader brStreams;
        BufferedReader brUsers;

        try {
            brStreamers = new BufferedReader(new FileReader(streamersFile));
            brStreams = new BufferedReader(new FileReader(streamsFile));
            brUsers = new BufferedReader(new FileReader(usersFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            project.transferStreamers(brStreamers);
            project.transferStreams(brStreams);
            project.transferUsers(brUsers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try (BufferedReader br = new BufferedReader(new FileReader(commandsFile))) {
            String line = br.readLine();

            while (line != null) {
                String[] strTok = line.split(" ", 0);

                if (strTok.length < 2) {
                    System.out.println("Error!");
                    return;
                }

                String command = strTok[1];

                switch (command) {
                    case "LIST":
                        Command listCommand = new ListCommand(project);
                        listCommand.executa(strTok);
                        break;
                    case "ADD":
                        Command addCommand = new AddCommand(project);
                        addCommand.executa(strTok);
                        break;
                    case "DELETE":
                        Command deleteCommand = new DeleteCommand(project);
                        deleteCommand.executa(strTok);
                        break;
                    case "LISTEN":
                        Command listenCommand = new ListenCommand(project);
                        listenCommand.executa(strTok);
                        break;
                    case "RECOMMEND":
                        Command recommendCommand = new RecommendCommand(project);
                        recommendCommand.executa(strTok);
                        break;
                    case "SURPRISE":
                        Command surpriseCommand = new SurpriseCommand(project);
                        surpriseCommand.executa(strTok);
                        break;
                    default:
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // add streamers from CSV to Collection
    public void transferStreamers(BufferedReader brStreamers) throws IOException {
        // prima linie nu contine date
        String line = brStreamers.readLine();
        line = brStreamers.readLine();

        while (line != null) {
            String[] strTok = line.split(",", 0);
            StringBuilder str = trimString(new StringBuilder(strTok[0]));
            Integer streamerType = Integer.parseInt(new String(str));

            str = trimString(new StringBuilder(strTok[1]));
            Integer streamerID = Integer.parseInt(new String(str));

            str = trimString((new StringBuilder(strTok[2])));
            String streamerName = new String(str);

            FactoryForStreamers factoryForStreamers = FactoryForStreamers.getInstance();
            Streamer newStreamer = factoryForStreamers.newStreamer(streamerType, streamerID, streamerName);
            this.streamers.add(newStreamer);
            line = brStreamers.readLine();
        }

    }

    // add streams from CSV to Collection
    public void transferStreams(BufferedReader brStreams) throws IOException {
        // prima linie nu contine date
        String line = brStreams.readLine();
        line = brStreams.readLine();

        while (line != null) {
            String[] strTok = line.split(",", 0);
            StringBuilder str = trimString(new StringBuilder(strTok[0]));
            Integer streamType = Integer.parseInt(new String(str));

            str = trimString(new StringBuilder(strTok[1]));
            Integer streamID = Integer.parseInt(new String(str));

            str = trimString(new StringBuilder(strTok[2]));
            Integer streamGenre = Integer.parseInt(new String(str));

            str = trimString(new StringBuilder(strTok[3]));
            Long noOfStreams = Long.parseLong(new String(str));

            str = trimString(new StringBuilder(strTok[4]));
            Integer streamerId = Integer.parseInt(new String(str));

            str = trimString(new StringBuilder(strTok[5]));
            Long length = Long.parseLong(new String(str));

            str = trimString(new StringBuilder(strTok[6]));
            Long dateAdded = Long.parseLong(new String(str));

            if (strTok[7].charAt(strTok[7].length()-1) != '"') {
                strTok[7] = strTok[7] + "," + strTok[8];
            }
            str = trimString(new StringBuilder(strTok[7]));
            String name = new String(str);

            FactoryForStreams factoryForStreams = FactoryForStreams.getInstance();
            Stream newStream = factoryForStreams.newStream(streamType, streamID, streamGenre, noOfStreams, streamerId, length, dateAdded, name);
            this.streams.add(newStream);

            line = brStreams.readLine();

        }
    }

    // add users from CSV to Collection
    public void transferUsers(BufferedReader brUsers) throws IOException {
        // prima linie nu contine date
        String line = brUsers.readLine();
        line = brUsers.readLine();

        while (line != null) {
            String[] strTok = line.split(",", 0);
            StringBuilder str = trimString(new StringBuilder(strTok[0]));
            Integer userID = Integer.parseInt(new String(str));

            str = trimString(new StringBuilder(strTok[1]));
            String userName = new String(str);

            User newUser = new User(userID, userName);

            // adaugam noului utilizator stream-urile sale in ArrayList
            str = trimString(new StringBuilder(strTok[2]));
            String listOfStreams = new String(str);

            // impartim stream-urile in functie de spatiu
            String[] arrayOfStreams = listOfStreams.split(" ", 0);
            for (String stream : arrayOfStreams) {
                Integer streamID = Integer.parseInt(stream);
                newUser.streams.add(streamID);
            }

            this.users.add(newUser);

            line = brUsers.readLine();
        }
    }

    // returns streamer by ID
    public Streamer returnStreamer(Integer id) {
        for (Streamer streamer : this.streamers) {
            if (streamer.getId().equals(id))
                return streamer;
        }
        return null;

    }

    // returns stream by ID
    public User returnUser(Integer id) {
        for (User user : this.users) {
            if (user.getId().equals(id))
                return user;
        }
        return null;

    }

    // returns user by ID
    public Stream returnStream(Integer id) {
        for (Stream stream : this.streams) {
            if (stream.getId().equals(id))
                return stream;
        }
        return null;

    }

    // transforms fields from CSV file to refference format
    public static String calculateDate(long dateAdded) {
        Date date = new Date(dateAdded * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        return sdf.format(date);
    }

    // transforms fields from CSV file to refference format
    public static String calculateLength(long length) {
        long hours = length / 3600;
        long minutes = (length % 3600) / 60;
        long seconds = length % 60;
        if (hours == 0) {
            return String.format("%02d:%02d", minutes, seconds);
        }
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    // trims the first and last chars of a string
    // mainly used to get rid of ""
    public StringBuilder trimString(StringBuilder str) {
        str.deleteCharAt(0);
        str.deleteCharAt(str.length() - 1);
        return str;
    }

}

