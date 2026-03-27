package org.firstinspires.ftc.teamcode.coach.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

public class RampSub implements Subsystem {
    public static final RampSub INSTANCE = new RampSub();
    private RampSub(){}

    private ServoEx servo = new ServoEx("flicker");

    public Command rampUp = new SetPosition(servo, .75).requires(this);
    public Command rampDown = new SetPosition(servo, .5).requires(this);
}
