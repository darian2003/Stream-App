package command;

import principal.ProiectPOO;

public abstract class Command {

    ProiectPOO project;

    public Command(ProiectPOO project) {
        this.project = project;
    }

    public abstract void executa(String[] line);

}
