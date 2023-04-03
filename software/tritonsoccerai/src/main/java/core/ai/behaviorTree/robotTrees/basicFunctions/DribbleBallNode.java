package main.java.core.ai.behaviorTree.robotTrees.basicFunctions;

import main.java.core.ai.behaviorTree.nodes.NodeState;
import main.java.core.ai.behaviorTree.nodes.taskNodes.TaskNode;
import main.java.core.constants.ProgramConstants;
import main.java.core.util.Vector2d;
import main.java.core.search.implementation.PathfindGridGroup;
import static main.java.core.messaging.Exchange.AI_BIASED_ROBOT_COMMAND;
import main.java.core.ai.behaviorTree.robotTrees.basicFunctions.MoveToPositionNode;

import proto.simulation.SslSimulationRobotControl;
import static proto.triton.FilteredObject.Robot;

/**
 * Defines tasks to be performed to dribble ball
 */
public class DribbleBallNode extends TaskNode {

    private final Robot ally;
    PathfindGridGroup pathfindGridGroup;
    private final MoveToPositionNode moveToPositionNode;

    public DribbleBallNode(Robot ally) {
        super("Dribble Ball Node: " + ally, ally);
        this.ally = ally;
        this.moveToPositionNode = new MoveToPositionNode(ally);
    }

     /**
      * Move the kicker at speed that keeps the ball close to the robot.
      * Publish the robot command to execute dribble. 
      */
    @Override
    public NodeState execute() {
        // Set dribbler speed and publish the command
        SslSimulationRobotControl.RobotCommand.Builder robotCommand = SslSimulationRobotControl.RobotCommand.newBuilder();

        robotCommand.setId(ally.getId());
        robotCommand.setDribblerSpeed(1);
        robotCommand.setKickSpeed(0);

        ProgramConstants.aiModule.publish(AI_BIASED_ROBOT_COMMAND, robotCommand.build());

        return NodeState.SUCCESS;
    }

    public NodeState execute(Vector2d location) {
        // Set dribbler speed and publish the command
        SslSimulationRobotControl.RobotCommand.Builder robotCommand = SslSimulationRobotControl.RobotCommand.newBuilder();

        robotCommand.setId(ally.getId());
        robotCommand.setDribblerSpeed(1);
        robotCommand.setKickSpeed(0);

        ProgramConstants.aiModule.publish(AI_BIASED_ROBOT_COMMAND, robotCommand.build());

        this.moveToPositionNode.execute(location);
        
        return NodeState.SUCCESS;
    }

}