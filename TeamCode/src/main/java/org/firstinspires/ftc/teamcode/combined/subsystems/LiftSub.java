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

    // I am guessing on these
    // I need to grab an axon programmer to ensure that they are set up for 355 and are 0 at the point at which they are being used
    // I can grab a Axon servo Programmer if you need me too or we can look in Jacks old code
    private LiftSub() {}

    public final ServoEx leftA = new ServoEx("leftLift1");
    public final ServoEx leftB = new ServoEx("leftLift2").reversed();
    public final ServoEx rightA = new ServoEx("rightLift");
    public final ServoEx rightB = new ServoEx("rightLift2").reversed();

// I reversed the b ones which will have to be the ones that are flipped so I need to figure out what that is when I look at it next.
    public final Command up = new ParallelGroup(
            new SetPosition(leftA,extendUp),
        new SetPosition(leftB,extendUp),
        new SetPosition(rightA,extendUp),
        new SetPosition(rightB,extendUp)
).requires(this);
    public final Command down = new ParallelGroup(
            new SetPosition(leftA, extendDown),
            new SetPosition(leftB, extendDown),
            new SetPosition(rightA, extendDown),
            new SetPosition(rightB, extendDown)
    ).requires(this);

    @Override
    public void initialize(){
        leftA.setPosition(extendDown);
        leftB.setPosition(extendDown);
        rightA.setPosition(extendDown);
        rightB.setPosition(extendDown);

    }
}
