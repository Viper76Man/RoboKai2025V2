package org.firstinspires.ftc.teamcode.coach.subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class MecanumDriveSub implements Subsystem {
    public static final MecanumDriveSub INSTANCE = new MecanumDriveSub();
    private MecanumDriveSub(){}

    public final MotorEx frontLeft = new MotorEx("fl").brakeMode().reversed();
    public final MotorEx frontRight = new MotorEx("fr").brakeMode();
    public final MotorEx backLeft = new MotorEx("bl").brakeMode().reversed();
    public final MotorEx backRight = new MotorEx("br").brakeMode();

}
