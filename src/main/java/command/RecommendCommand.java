package command;

import principal.ProiectPOO;
import principal.User;
import streamers.Streamer;
import streams.Stream;

import java.util.ArrayList;
import java.util.Collections;

public class RecommendCommand extends Command {


    public RecommendCommand(ProiectPOO project) {
        super(project);
    }

    // FACADE
    // It decouples a client implementation from the complex subsystem
    @Override
    public void executa(String[] line) {

        // Generate recommended list using the "secret & complex" algorithm
        ArrayList<Stream> recommendedStreamList = this.algorithmRecommendedList(line[0], line[2]);
        // Print the first 5 elements of this list
        Stream.printStreamList(recommendedStreamList, this.project, 5);

    }

    public ArrayList<Stream> algorithmRecommendedList(String id, String desiredType) {

        Integer userID = Integer.parseInt(id);
        User user = this.project.returnUser(userID);
        Integer recommendedType = RecommendCommand.getRecommendedType(desiredType);
        ArrayList<Stream> recommendedStreamList = this.getRecommendedStreamList(user, recommendedType);
        Collections.sort(recommendedStreamList, (o1, o2) -> o1.getNoOfStreams().compareTo(o2.getNoOfStreams()));
        return recommendedStreamList;
    }

    private static Integer getRecommendedType(String type) {

        Integer recommendedType;
        switch (type) {
            case "SONG":
                recommendedType = 1;
                break;
            case "PODCAST":
                recommendedType = 2;
                break;
            case "AUDIOBOOK":
                recommendedType = 3;
                break;
            default:
                recommendedType = null;
        }

        if (recommendedType == null) {
            System.out.println("Error! Wrong genre");
        }
        return recommendedType;
    }

    private ArrayList<Stream> getRecommendedStreamList(User user, Integer recommendedType) {

        ArrayList<Integer> listenedStreamersID = new ArrayList<>();
        for (Integer i : user.getStreams()) {
            Stream stream = this.project.returnStream(i);
            if (!stream.getStreamType().equals(recommendedType) && listenedStreamersID.contains(stream.getStreamerId()))
                continue;
            listenedStreamersID.add(stream.getStreamerId());
        }

        ArrayList<Stream> unlistenedStreams = new ArrayList<>();

        for (Integer i : listenedStreamersID) {
            for (Stream stream : this.project.getStreams()) {
                if (user.getStreams().contains(stream.getId()))
                    continue;
                if (stream.getStreamerId().equals(i))
                    unlistenedStreams.add(stream);
            }
        }
        return unlistenedStreams;
    }

}
