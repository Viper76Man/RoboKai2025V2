package org.firstinspires.ftc.teamcode.combined.subsystems;


import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.FeedbackCRServoEx;

public class LiftSub2 implements Subsystem {
    public static final LiftSub2 Instance = new LiftSub2();
    private LiftSub2(){}

    // Pair each servo with its encoder
    private final FeedbackCRServoEx leftLift1 = new FeedbackCRServoEx("leftenc1", "leftLift1");
    private final FeedbackCRServoEx leftLift2 = new FeedbackCRServoEx("leftenc2", "leftLift2");

    private final double kP_sync = 0.005;
    private final double kP_pos = 0.01; // Power per tick of distance

    @Override
    public void initialize() {
        leftLift1.reversed();
    }


    /*public Command goToPosition() {
        return new RunCommand(() -> {
            Double posL = leftLift1.getCurrentPosition();
            Double posR = leftLift2.getCurrentPosition();

            // Calculate base power to reach target
            double basePower = (500 - posL) * kP_pos;

            // Calculate sync correction
            double syncCorrection = (posL - posR) * kP_sync;

            leftLift1.setPower(basePower - syncCorrection);
            leftLift2.setPower(basePower + syncCorrection);
        }, this).setInterruptible(true);
    }*/

}
