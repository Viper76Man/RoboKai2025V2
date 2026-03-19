package org.firstinspires.ftc.teamcode.coach.subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

//Update class to use implements Subsystem
public class WorkbenchSub implements Subsystem {
    //Always use these 2 statements to create a singleton
    public static final WorkbenchSub INSTANCE = new WorkbenchSub();
    private WorkbenchSub(){}

    //Example of MotorEx instead of DcMotorEx
    public final MotorEx frontLeft = new MotorEx("fl").brakeMode().reversed();
    public final MotorEx frontRight = new MotorEx("fr").brakeMode();
    public final MotorEx backLeft = new MotorEx("bl").brakeMode().reversed();
    public final MotorEx backRight = new MotorEx("br").brakeMode();

}
