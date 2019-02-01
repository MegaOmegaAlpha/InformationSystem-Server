package protocol;

import model.Track;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class SimpleMessageForTraining implements Serializable {
    private Command command;
    private UUID trackId;

    public SimpleMessageForTraining(Command command, UUID trackId) {
        this.command = command;
        this.trackId = trackId;
    }

    public Command getCommand() {
        return command;
    }

    public UUID getTrackId() {
        return trackId;
    }
}

