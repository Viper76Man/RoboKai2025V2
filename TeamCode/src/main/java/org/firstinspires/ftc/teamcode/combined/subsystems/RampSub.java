package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

public class RampSub implements Subsystem {
    public static final RampSub INSTANCE = new RampSub();
    private RampSub() {}

    private ServoEx servo = new ServoEx("turretaxon");

    public Command position1 = new SetPosition(servo, .35).requires(this);
    public Command position2 = new SetPosition(servo, .65).requires(this);
    public Command position3 = new SetPosition(servo, .5).requires(this);


}
