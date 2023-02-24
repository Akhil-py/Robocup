package core.ai.behaviorTree.robotTrees.fielder.offense;

import core.ai.GameInfo;
import core.ai.behaviorTree.nodes.NodeState;
import core.ai.behaviorTree.nodes.compositeNodes.CompositeNode;
import core.util.Vector2d;

import core.ai.behaviorTree.robotTrees.basicFunctions.DribbleBallNode;
import core.ai.behaviorTree.robotTrees.basicFunctions.CoordinatedPassNode;

import static proto.triton.FilteredObject.Robot;

import java.util.ArrayList;
import java.util.List;

/**
 * Define whether to dribble or pass the ball
 */
public class MakePlayNode extends CompositeNode {
    private final DribbleBallNode dribble;
    private final CoordinatedPassNode coordinatedPass;

    public MakePlayNode(Robot ally) {
        super("Make Play");
        this.dribble = new DribbleBallNode(ally);
        this.coordinatedPass = new CoordinatedPassNode(ally);
    }
    
    @Override
    public NodeState execute() {

        //If there is a space between robot and goal, dribble
        //otherwise, pass the ball to the other robots
       if(checkDribble()) {
        this.dribble.execute();
       }
       else{
        this.coordinatedPass.execute();
       }

        return NodeState.SUCCESS;
    }

    /**
     * Check if there is a space towards the goal
     */
    private boolean checkDribble(){
        boolean hasSpace = true;

        // TODO Get the field parameter
        SSL_GeometryFieldSize field = GameInfo.getField();

        // Get the goal parameters
        float goalX = field.getGoalWidth() / 2f;
        float goalY = field.getFieldLength() / 2f;

        // Define the objective point
        Vector2d objectPoint = new Vector2d((GameInfo.getAllyClosestToBall().getX() + goalX) / 2, 
                                            (GameInfo.getAllyClosestToBall().getY() + goalY) / 2); 


        // Check if there is a space towards the objective point

        ArrayList<Robot> foesList = new ArrayList<>(GameInfo.getFoes());
        ArrayList<Robot> allysList = new ArrayList<>(GameInfo.getFielders());
        List<Vector2d> obstaclePositions = new ArrayList<>();

        //remove the ally closest to the ball
        allysList.remove(GameInfo.getAllyClosestToBall());

        //add the other ally positions and foe positions to the obstaclesPositions list
        for(int i=0;i<allysList.size();i++) {
			obstaclePositions.add(getPos(allysList.get(i)));
            obstaclePositions.add(getPos(foesList.get(i)));
		}
    
        double slope = (objectPoint.y - GameInfo.getAllyClosestToBall().getY())
                         / (objectPoint.x - GameInfo.getAllyClosestToBall().getX());
        double yIntercept = objectPoint.y - slope * objectPoint.x;
        
        for (Vector2d obstacle : obstaclePositions) {
            if (obstacle.y == slope * obstacle.x + yIntercept) {
                hasSpace = false;
            }
        }

        return hasSpace;
    }

}
