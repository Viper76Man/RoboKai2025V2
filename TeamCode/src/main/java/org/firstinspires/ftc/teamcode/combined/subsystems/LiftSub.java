package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.groups.ParallelGroup;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

public class LiftSub implements Subsystem {
    public static final LiftSub INSTANCE = new LiftSub();
    public static final double extendUp = 1.0;
    public static final double extendDown = 0.0;
    private LiftSub() {}

    public final ServoEx lefta = new ServoEx("LiftA");
    public final ServoEx leftb = new ServoEx("LeftB").reversed();
    public final ServoEx rightA = new ServoEx("RightA");
    public final ServoEx rightB = new ServoEx("RightB").reversed();

// I reversed the b ones which will have to be the ones that are flipped so I need to figure out what that is when I look at it next.
    public final Command up = new ParallelGroup(
            new SetPosition(lefta,extendUp),
        new SetPosition(leftb,extendUp),
        new SetPosition(rightA,extendUp),
        new SetPosition(rightB,extendUp)
).requires(this);
    public final Command down = new ParallelGroup(
            new SetPosition(lefta, extendDown),
            new SetPosition(leftb, extendDown),
            new SetPosition(rightA, extendDown),
            new SetPosition(rightB, extendDown)
    ).requires(this);

}
