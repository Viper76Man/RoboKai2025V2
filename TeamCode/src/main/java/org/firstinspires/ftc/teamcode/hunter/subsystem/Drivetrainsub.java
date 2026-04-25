package org.firstinspires.ftc.teamcode.hunter.subsystem;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;

public class Drivetrainsub implements Subsystem {
    public static final Drivetrainsub INSTANCE = new Drivetrainsub();
    private Drivetrainsub(){}

    public final MotorEx frontLeft = new MotorEx("fl").brakeMode().reversed();
    public final MotorEx frontRight = new MotorEx("fr").brakeMode();
    public final MotorEx backLeft = new MotorEx("bl").brakeMode().reversed();
    public final MotorEx backRight = new MotorEx("br").brakeMode();


