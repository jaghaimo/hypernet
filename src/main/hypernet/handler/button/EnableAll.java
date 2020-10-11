package hypernet.handler.button;

import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;

import hypernet.HypernetBoard;
import hypernet.IntelQuery;
import hypernet.handler.ButtonHandler;

public class EnableAll implements ButtonHandler {

    @Override
    public void handle(List<IntelQuery> queries, IntelUIAPI ui) {
        for (IntelQuery query : queries) {
            query.enable();
        }
        HypernetBoard.refresh();
    }
}
