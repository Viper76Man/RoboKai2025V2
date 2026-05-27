package org.firstinspires.ftc.teamcode.combined.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.ftc.ActiveOpMode;

public class VisionRedSub implements Subsystem {
    public static final VisionRedSub INSTANCE = new VisionRedSub();
    private VisionRedSub(){}

    private Limelight3A limeLight;
    private double cameraHeightCm = 40;
    private double cameraAngle = 6.1;
    private double goalHeight = 74.95;
    private double distance = 0;
    private double tx = 0;
    private boolean hastarget = false;
    private static final double zone4MxCm = 80;

    private static final double zone5MaxCm = 100;
    // Max shot that we can have 177.81
    private static final double zone6MaxCm = 200;
    private static final double zone7MaxCm =400;

    public enum DetectedZone {
        ZONE4,
        ZONE5,
        ZONE6,
        ZONE7,
        UNKOWN
    }
    public DetectedZone getDectectedZone(){
        if (distance <= 0) return DetectedZone.UNKOWN;
        if (distance <= zone4MxCm) return DetectedZone.ZONE4;
        if (distance <= zone5MaxCm) return DetectedZone.ZONE5;
        if (distance <= zone6MaxCm) return DetectedZone.ZONE6;
        if(distance <= zone7MaxCm) return DetectedZone.ZONE7;
        return DetectedZone.UNKOWN;
    }

    @Override
    public void initialize(){
        limeLight = ActiveOpMode.hardwareMap().get(Limelight3A.class, "limelight");
        limeLight.pipelineSwitch(2);   //Obelisk 0, Blue 1, Red 2
        limeLight.start();
    }

    @Override
    public void periodic(){
        LLResult llResult = limeLight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            tx = llResult.getTx();
            distance = getDistanceGoal(llResult.getTy());
            hastarget = true;
        }
        else {
            hastarget = false;
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
    public double getTx(){
        return tx;
    }
    public boolean hastarget(){
        return hastarget;
    }
}
