package hypernet.handler;

import java.util.List;

import com.fs.starfarer.api.ui.IntelUIAPI;

import hypernet.IntelQuery;

public interface ButtonHandler {

    public void handle(List<IntelQuery> queries, IntelUIAPI ui);
}
