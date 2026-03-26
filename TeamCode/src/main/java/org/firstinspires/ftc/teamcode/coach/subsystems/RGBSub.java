package org.firstinspires.ftc.teamcode.coach.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

public class RGBSub  implements Subsystem {
    public static final RGBSub INSTANCE = new RGBSub();
    private RGBSub(){}

    private ServoEx servo = new ServoEx("rgbpwm");

    public Command off = new SetPosition(servo, 0.0).requires(this);
    public Command red = new SetPosition(servo, 0.279).requires(this);
    public Command orange = new SetPosition(servo, 0.333).requires(this);
    public Command yellow = new SetPosition(servo, 0.388).requires(this);
    public Command sage = new SetPosition(servo, 0.444).requires(this);
    public Command green = new SetPosition(servo, 0.500).requires(this);
    public Command azure = new SetPosition(servo, 0.555).requires(this);
    public Command blue = new SetPosition(servo, 0.611).requires(this);
    public Command indigo = new SetPosition(servo, 0.666).requires(this);
    public Command violet = new SetPosition(servo, 0.722).requires(this);
    public Command white = new SetPosition(servo, 1.0).requires(this);

}
