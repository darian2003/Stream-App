package command;

import principal.ProiectPOO;
import principal.User;
import streamers.Streamer;
import streams.Stream;

public class DeleteCommand extends Command {

    public DeleteCommand(ProiectPOO project) {
        super(project);
    }

    @Override
    public void executa(String[] line) {

        Integer streamerID = Integer.parseInt(line[0]);
        Integer streamID = Integer.parseInt(line[2]);

        // delete stream from ArrayList<Stream>
        for (Stream stream : this.project.getStreams()) {
            if (stream.getId().equals(streamID) && stream.getStreamerId().equals(streamerID)) {
                this.project.getStreams().remove(stream);
                break;
            }
        }

        // delete stream from user's ArrayList
        for (User user : this.project.getUsers()) {
            for (Integer i : user.getStreams()) {
                if (streamID.equals(i)) {
                    user.getStreams().remove(i);
                    break;
                }
            }
        }

        Streamer streamer = this.project.returnStreamer(streamerID);


    }
}
