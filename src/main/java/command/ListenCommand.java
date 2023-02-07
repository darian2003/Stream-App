package command;

import principal.ProiectPOO;
import principal.User;
import streams.Stream;

public class ListenCommand extends Command {

    public ListenCommand(ProiectPOO project) {
        super(project);
    }

    @Override
    public void executa(String[] line) {

        Integer userID = Integer.parseInt(line[0]);
        Integer streamID = Integer.parseInt(line[2]);

        for (Stream stream : this.project.getStreams()) {
            if (stream.getId().equals(streamID)) {
                stream.setNoOfStreams(stream.getNoOfStreams() + 1);
                break;
            }
        }

        User user = this.project.returnUser(userID);
        user.getStreams().add(streamID);
    }
}
