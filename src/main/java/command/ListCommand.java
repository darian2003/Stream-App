package command;

import principal.ProiectPOO;
import principal.User;
import streamers.Streamer;
import streams.Stream;

public class ListCommand extends Command {

    @Override
    public void executa(String[] line) {
        Integer id = Integer.parseInt(line[0]);
        Streamer streamer = this.project.returnStreamer(id);
        if (streamer != null) {
            this.printStreamerList(streamer);
            return;
        }

        User user = this.project.returnUser(id);
        if (user != null) {
            this.printUserList(user);
            return;
        }

        System.out.println("Error! Invalid id");

    }

    public void printUserList(User user) {
        int ok = 0;
        System.out.print("[");
        for (Integer streamID : user.getStreams()) {
            for (Stream stream : this.project.getStreams()) {
                if (streamID.equals(stream.getId())) {
                    if (ok == 1)
                        System.out.print(",");
                    String streamerName = this.project.returnStreamer(stream.getStreamerId()).getName();
                    user.printStream(stream, streamerName);
                    ok = 1;
                }
            }
        }
        System.out.println("]");
    }

    public void printStreamerList(Streamer streamer) {
        int ok = 0;
        System.out.print("[");
        for (Stream stream : this.project.getStreams()) {
            if (stream.getStreamerId().equals(streamer.getId())) {
                if (ok == 1)
                    System.out.print(",");
                streamer.printStream(stream);
                ok = 1;
            }
        }
        System.out.println("]");

    }

    public ListCommand(ProiectPOO project) {
        super(project);
    }
}
