package main.java.core.ai.behaviorTree.robotTrees.basicFunctions;

import main.java.core.ai.GameInfo;
import main.java.core.ai.behaviorTree.nodes.NodeState;
import main.java.core.ai.behaviorTree.nodes.taskNodes.TaskNode;
import static proto.triton.FilteredObject.Robot;

/**
 * Defines task of chasing ball
 */
public class ChaseBallNode extends TaskNode {

    private final MoveToObjectNode moveToObjectNode;

    public ChaseBallNode(Robot ally) {
        super("Chase Ball Node: " + ally, ally);
        this.moveToObjectNode = new MoveToObjectNode(ally);
    }

    /**
     * Moves robot to move in direction of ball
     */
    @Override
    public NodeState execute() {
        System.out.println("Ally " + ally.getId() + " chasing ball");
        this.moveToObjectNode.execute(GameInfo.getBall());
        return NodeState.SUCCESS;
    }
}
