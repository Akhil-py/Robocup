package core.ai.behaviorTree.robotTrees.basicFunctions;

import core.ai.behaviorTree.nodes.NodeState;
import core.ai.behaviorTree.nodes.taskNodes.TaskNode;

import static proto.triton.FilteredObject.Robot;

/**
 * Defines task of catching ball in motion
 */
public class CatchBallNode extends TaskNode {

    public CatchBallNode(Robot ally) {
        super("Catch Ball Node: " + ally, ally);
    }

    @Override
    public NodeState execute() {
        /* 
        // Calculating path from robot to ball
        Ball ball = GameInfo.getBall();
        Vector2d ballPos = getPos(ball);
        Vector2d ballToActor = getPos(ally).sub(ballPos);
        Vector2d offset = ballToActor.project(ball.getVel());
        Vector2d targetPos = ballPos.add(offset);


        // Move to ball
        MoveToPositionNode MoveToPosition = new MoveToPositionNode(ally);
        MoveToPosition.execute(targetPos);

        // Dribble It
        Dribble dribbleBall = new Dribble(ally);
        dribbleBall.execute();
        */

        return NodeState.SUCCESS;
    }

}