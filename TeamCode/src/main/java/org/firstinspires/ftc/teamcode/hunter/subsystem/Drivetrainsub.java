package org.firstinspires.ftc.teamcode.hunter.subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class Drivetrainsub implements Subsystem {
    public static final Drivetrainsub INSTANCE = new Drivetrainsub();
    private Drivetrainsub(){}

    public final MotorEx frontLeft = new MotorEx("fl").brakeMode().reversed();
    public final MotorEx frontRight = new MotorEx("fr").brakeMode();
    public final MotorEx backLeft = new MotorEx("bl").brakeMode().reversed();
    public final MotorEx backRight = new MotorEx("br").brakeMode();
