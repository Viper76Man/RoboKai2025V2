package org.firstinspires.ftc.teamcode.combined.subsystems;

import java.util.Set;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

public class HoodSub implements Subsystem {
    public static final HoodSub INSTANCE = new HoodSub();
    private HoodSub(){}

    private ServoEx servo = new ServoEx("hood");

    public Command hoodZone1 = new SetPosition(servo, 0);
    public Command hoodZone2 = new SetPosition(servo, .175);
    public Command hoodZone3 = new SetPosition(servo, .35);

}
