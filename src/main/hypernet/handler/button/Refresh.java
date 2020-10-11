package hypernet.handler.button;

import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;

import hypernet.HypernetBoard;
import hypernet.IntelQuery;
import hypernet.handler.ButtonHandler;

public class Refresh implements ButtonHandler {

    int queryNumber;

    public Refresh(int q) {
        queryNumber = q;
    }

    @Override
    public void handle(List<IntelQuery> queries, IntelUIAPI ui) {
        queries.get(queryNumber).refresh();
        HypernetBoard.refresh();
    }
}
