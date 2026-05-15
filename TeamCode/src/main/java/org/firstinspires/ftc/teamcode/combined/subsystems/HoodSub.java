package org.firstinspires.ftc.teamcode.combined.subsystems;

import java.util.Set;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

public class   HoodSub implements Subsystem {
    public static final HoodSub INSTANCE = new HoodSub();
    private HoodSub(){}

    private ServoEx servo = new ServoEx("hood");

    public Command hoodZone1 = new SetPosition(servo, 0);
    public Command hoodZone2 = new SetPosition(servo,.100);
    // I believe that this shoould be a good range for that very short shot
    public Command hoodZone5 = new SetPosition(servo,.28);
    public Command hoodZone3 = new SetPosition(servo, .175);
    // half this should be a good combo for the mid-shot still in the short shot.
    // Max distance for this is 177.81
    public Command hoodZone4 = new SetPosition(servo, .35);

}
// For the shot on the corner that rpm and .175 in good
// though when you get to the very end of the first zone the .28 is good. The rest of the zone does good with the .100
