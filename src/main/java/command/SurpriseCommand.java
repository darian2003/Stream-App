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

    @Override
    public void executa(String[] line) {

        Integer userID = Integer.parseInt(line[0]);
        User user = this.project.returnUser(userID);
        Integer recommendedType;

        switch (line[2]) {
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
            return;
        }

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

        Collections.sort(unlistenedStreamersStreams, (o1, o2) -> {

            if (!ProiectPOO.calculateDate(o1.getDateAdded()).equals(ProiectPOO.calculateDate(o2.getDateAdded())))
                return o2.getDateAdded().compareTo(o1.getDateAdded());
            return o2.getNoOfStreams().compareTo(o1.getNoOfStreams());
        });

        // start printing
        System.out.print("[");
        int ok = 0;
        int i = 0;

        for (Stream stream : unlistenedStreamersStreams) {
            if (ok == 1)
                System.out.print(",");
            Streamer streamer = this.project.returnStreamer(stream.getStreamerId());
            stream.printStream(streamer.getName());
            ok = 1;
            i++;
            if (i == 3)
                break;
        }
        System.out.println("]");

    }
}
