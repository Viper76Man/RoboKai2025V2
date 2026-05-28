package org.firstinspires.ftc.teamcode.combined.subsystems;


import dev.nextftc.control.feedback.PIDController;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.LambdaCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.FeedbackCRServoEx;

public class LiftSub2 implements Subsystem {
    public static final LiftSub2 Instance = new LiftSub2();
    private LiftSub2(){}

    // Pair each servo with its encoder
//    private final FeedbackCRServoEx leftLift1 = new FeedbackCRServoEx("leftenc1", "leftLift1");
//    private final FeedbackCRServoEx leftLift2 = new FeedbackCRServoEx("leftenc2", "leftLift2");
    private final FeedbackCRServoEx leftLift1 = new FeedbackCRServoEx("rightenc1", "rightLift");
    private final FeedbackCRServoEx leftLift2 = new FeedbackCRServoEx("rightenc2", "rightLift2");

    private final double kP_sync = 0.005;
    private final double kP_pos = 0.01; // Power per tick of distance

    @Override
    public void initialize() {
        //leftLift1.reversed();
        leftLift2.reversed();
    }


    public Command goToPosition() {
        double target = 50.0;
        double tolerance = 1.0;

        return new LambdaCommand("GoToPosition")
                .setUpdate(() -> {
                    double posL = leftLift1.getCurrentPosition();
                    double posR = leftLift2.getCurrentPosition();

                    // Calculate base power to reach target
                    double basePower = (target - posL) * kP_pos;

                    // Calculate sync correction
                    double syncCorrection = (posL - posR) * kP_sync;

                    leftLift1.setPower(basePower - syncCorrection);
                    leftLift2.setPower(basePower + syncCorrection);
                })
                .setIsDone(() -> Math.abs(target - leftLift1.getCurrentPosition()) < tolerance)
                .requires(this); // Assuming 'this' is your Lift Subsystem
    }

}
