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
    private double cameraHeightCm = 40;
    private double cameraAngle = 6.1;
    private double goalHeight = 74.95;
    private double distance = 0;
    private static final double zone0MxCm = 80;
//close
    private static final double zone1MaxCm = 100;
    // Middle of middle
    private static final double zone2MaxCm = 200;
    // Max shot that we can have 177.81
    private static final double zone3MaxCm = 400;

    public enum DetectedZone {
        ZONE0,
        ZONE1,
        ZONE2,
        ZONE3,
        UNKOWN
    }
public DetectedZone getDectectedZone(){
        if (distance <= 0) return DetectedZone.UNKOWN;
        if (distance <= zone0MxCm) return DetectedZone.ZONE0;
        if (distance <= zone1MaxCm) return DetectedZone.ZONE1;
        if (distance <= zone2MaxCm) return DetectedZone.ZONE2;
        if (distance <= zone3MaxCm) return DetectedZone.ZONE3;
        return DetectedZone.UNKOWN;
}

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
        double angleToTarget = cameraAngle + ty;
        double heightDifference = goalHeight - cameraHeightCm;
        return heightDifference / Math.tan(Math.toRadians(angleToTarget));
    }

    public double totalDistanceGoal(){
        return distance;
    }

}
