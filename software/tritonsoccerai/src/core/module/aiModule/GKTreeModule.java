package core.module.aiModule;

import core.ai.behaviorTree.robotTrees.goalkeeper.GoalkeeperTree;
import core.module.Module;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeoutException;

public class GKTreeModule extends Module {

    // TODO
    private final GoalkeeperTree tree;

    public GKTreeModule(ScheduledThreadPoolExecutor executor) {
        super(executor);
        this.tree = new GoalkeeperTree();
    }

    @Override
    protected void prepare() {

    }

    @Override
    protected void declareConsumes() throws IOException, TimeoutException {

    }

}
