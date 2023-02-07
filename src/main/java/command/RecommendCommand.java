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

        ArrayList<Stream> unlistenedStreams = new ArrayList<>();

        for (Integer i : listenedStreamersID) {
            for (Stream stream : this.project.getStreams()) {
                if (user.getStreams().contains(stream.getId()))
                    continue;
                if (stream.getStreamerId().equals(i))
                    unlistenedStreams.add(stream);
            }
        }

        Collections.sort(unlistenedStreams, (o1, o2) -> o1.getNoOfStreams().compareTo(o2.getNoOfStreams()));

        if (unlistenedStreams.isEmpty())
            return;

        // start printing
        System.out.print("[");
        int ok = 0;
        int i = 0;

        for (Stream stream : unlistenedStreams) {
            if (ok == 1)
                System.out.print(",");
            Streamer streamer = this.project.returnStreamer(stream.getStreamerId());
            stream.printStream(streamer.getName());
            ok = 1;
            i++;
            if (i == 5)
                break;
        }
        System.out.println("]");

    }
}
