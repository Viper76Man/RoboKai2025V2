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
    private final FeedbackCRServoEx lift1 = new FeedbackCRServoEx("leftenc1", "leftLift1");
    private final FeedbackCRServoEx lift2 = new FeedbackCRServoEx("leftenc2", "leftLift2");
    private final FeedbackCRServoEx lift3 = new FeedbackCRServoEx("rightenc1", "rightLift");
    private final FeedbackCRServoEx lift4 = new FeedbackCRServoEx("rightenc2", "rightLift2");

    private final double kP_sync = 0.005;
    private final double kP_pos = 0.01; // Power per tick of distance

    @Override
    public void initialize() {
        lift1.reversed();
        lift4.reversed();
    }


    public Command goToPosition() {
        double tolerance = 1.5; // Slightly wider for 4 servos to avoid hunting
        double target = 50;
        return new LambdaCommand("GoToPosition4Servo")
                .setUpdate(() -> {
                    // 1. Read all current positions
                    double p1 = lift1.getCurrentPosition();
                    double p2 = lift2.getCurrentPosition();
                    double p3 = lift3.getCurrentPosition();
                    double p4 = lift4.getCurrentPosition();

                    // 2. Calculate the group average position
                    double avgPos = (p1 + p2 + p3 + p4) / 4.0;

                    // 3. Calculate independent target-seeking powers
                    double base1 = (target - p1) * kP_pos;
                    double base2 = (target - p2) * kP_pos;
                    double base3 = (target - p3) * kP_pos;
                    double base4 = (target - p4) * kP_pos;

                    // 4. Calculate sync corrections (drift relative to average)
                    // If a servo is ahead of average (p > avg), correction is positive, slowing it down
                    double sync1 = (p1 - avgPos) * kP_sync;
                    double sync2 = (p2 - avgPos) * kP_sync;
                    double sync3 = (p3 - avgPos) * kP_sync;
                    double sync4 = (p4 - avgPos) * kP_sync;

                    // 5. Combine and clip outputs to safe motor power ranges (-1.0 to 1.0)
                    lift1.setPower(Math.max(-1.0, Math.min(1.0, base1 - sync1)));
                    lift2.setPower(Math.max(-1.0, Math.min(1.0, base2 - sync2)));
                    lift3.setPower(Math.max(-1.0, Math.min(1.0, base3 - sync3)));
                    lift4.setPower(Math.max(-1.0, Math.min(1.0, base4 - sync4)));
                })
                .setIsDone(() -> {
                    // All 4 servos must be within tolerance to complete the command
                    boolean d1 = Math.abs(target - lift1.getCurrentPosition()) < tolerance;
                    boolean d2 = Math.abs(target - lift2.getCurrentPosition()) < tolerance;
                    boolean d3 = Math.abs(target - lift3.getCurrentPosition()) < tolerance;
                    boolean d4 = Math.abs(target - lift4.getCurrentPosition()) < tolerance;
                    return d1 && d2 && d3 && d4;
                })
                .requires(this);
    }

}
