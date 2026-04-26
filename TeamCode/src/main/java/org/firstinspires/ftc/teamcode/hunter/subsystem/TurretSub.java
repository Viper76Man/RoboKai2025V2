package org.firstinspires.ftc.teamcode.hunter.subsystem;

import com.qualcomm.hardware.limelightvision.Limelight3A;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;

public class TurretSub implements Subsystem {

    public static final TurretSub INSTANCE = new TurretSub();
    private TurretSub() {}
    private Limelight3A limelight;
    public Double tx = 0;
    // This is the angle at which the limelight is off from the april tag
    public Double ty = 0;
    // This is the y angle of how much higher the robot is to the April Tag or lower.
    public Double ta = 0;
// area rough estimate length to target
    @Override
    public void initialization
    public final MotorEx Rotator = new MotorEx("AxonTurret");




















































































}
