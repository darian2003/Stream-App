package command;

import principal.ProiectPOO;
import principal.User;
import streamers.Streamer;
import streams.Stream;

import java.util.ArrayList;
import java.util.Collections;

public class SurpriseCommand extends Command {

    public SurpriseCommand(ProiectPOO project) {
        super(project);
    }

    // FACADE
    @Override
    public void executa(String[] line) {

        // Generate surprise list using the "secret & complex" algorithm
        ArrayList<Stream> surpriseStreamList = this.algorithmSurpriseList(line[0], line[2]);
        // Print the first 3 elements of the list
        Stream.printStreamList(surpriseStreamList, this.project, 3);

    }

    public ArrayList<Stream> algorithmSurpriseList(String id, String desiredType) {
        Integer userID = Integer.parseInt(id);
        User user = this.project.returnUser(userID);
        Integer recommendedType = SurpriseCommand.getRecommendedType(desiredType);
        ArrayList<Stream> surpriseStreamList = this.getSurpriseStreamList(user, recommendedType);
        Collections.sort(surpriseStreamList, (o1, o2) -> {

            if (!ProiectPOO.calculateDate(o1.getDateAdded()).equals(ProiectPOO.calculateDate(o2.getDateAdded())))
                return o2.getDateAdded().compareTo(o1.getDateAdded());
            return o2.getNoOfStreams().compareTo(o1.getNoOfStreams());
        });
        return surpriseStreamList;
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

    public ArrayList<Stream> getSurpriseStreamList(User user, Integer recommendedType) {

        ArrayList<Integer> listenedStreamersID = new ArrayList<>();
        for (Integer i : user.getStreams()) {
            Stream stream = this.project.returnStream(i);
            if (!stream.getStreamType().equals(recommendedType) && listenedStreamersID.contains(stream.getStreamerId()))
                continue;
            listenedStreamersID.add(stream.getStreamerId());
        }

        ArrayList<Integer> unlistenedStreamersID = new ArrayList<>();
        for (Streamer streamer : this.project.getStreamers()) {
            if (!listenedStreamersID.contains(streamer.getId()) && streamer.getType().equals(recommendedType))
                unlistenedStreamersID.add(streamer.getId());
        }

        ArrayList<Stream> unlistenedStreamersStreams = new ArrayList<>();
        for (Integer i : unlistenedStreamersID) {
            for (Stream stream : this.project.getStreams()) {
                if (stream.getStreamerId().equals(i))
                    unlistenedStreamersStreams.add(stream);
            }
        }
        return unlistenedStreamersStreams;
    }

}
