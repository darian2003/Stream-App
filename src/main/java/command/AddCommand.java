package command;

import principal.ProiectPOO;
import streams.FactoryForStreams;
import streams.Stream;

import java.time.LocalDate;
import java.time.ZoneOffset;

public class AddCommand extends Command {

    public AddCommand(ProiectPOO project) {
        super(project);
    }

    @Override
    public void executa(String[] line) {

        Integer streamerID = Integer.parseInt(line[0]);
        Integer streamType = Integer.parseInt(line[2]);
        Integer id = Integer.parseInt(line[3]);
        Integer streamGenre = Integer.parseInt(line[4]);
        Long length = Long.parseLong(line[5]);
        Long noOfStreams = 0L;

        // name of the stream can have spaces
        StringBuilder name = new StringBuilder(line[6]);
        int i = 7;
        while (i < line.length) {
            name.append(" ").append(line[i]);
            i++;
        }

        // Compute date
        // Using default date as: 13/01/2023 because this is in the refference

        LocalDate date = LocalDate.of(2023, 1, 13);  // given in reference file
        long dateFormat = date.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        FactoryForStreams factoryForStreams = FactoryForStreams.getInstance();
        Stream newStream = factoryForStreams.newStream(streamType, id, streamGenre, noOfStreams, streamerID, length, dateFormat, new String(name));
        this.project.getStreams().add(newStream);

    }


}
