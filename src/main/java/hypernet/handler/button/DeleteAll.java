package hypernet.handler.button;

import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;

import hypernet.HypernetBoard;
import hypernet.IntelQuery;
import hypernet.handler.ButtonHandler;

public class DeleteAll implements ButtonHandler {

    @Override
    public void handle(List<IntelQuery> queries, IntelUIAPI ui) {
        for (IntelQuery query : queries) {
            query.disable();
        }
        queries.clear();
        HypernetBoard.refresh();
    }
}
