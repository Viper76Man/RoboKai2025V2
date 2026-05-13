package org.firstinspires.ftc.teamcode.combined.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.ftc.ActiveOpMode;

public class VisionSub implements Subsystem {
    public static final VisionSub INSTANCE = new VisionSub();
    private VisionSub(){}

    private Limelight3A limeLight;
    private double CAMERA_HEIGHT_CM = 40;
    private double CAMERA_ANGLE = 3.1;
    private double GOAL_HEIGHT = 74.95;
    private double distance = 0;

    @Override
    public void initialize(){
        limeLight = ActiveOpMode.hardwareMap().get(Limelight3A.class, "limelight");
        limeLight.pipelineSwitch(1);   //Obelisk 0, Blue 1, Red 2
        limeLight.start();
    }

    @Override
    public void periodic(){
        LLResult llResult = limeLight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            distance = getDistanceGoal(llResult.getTy());
        }
    }

    public double getDistanceGoal(double ty){
        double angleToTarget = CAMERA_ANGLE + ty;
        double heightDifference = GOAL_HEIGHT - CAMERA_HEIGHT_CM;
        return heightDifference / Math.tan(Math.toRadians(angleToTarget));
    }

    public double totalDistanceGoal(){
        return distance;
    }

}
