package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.controllable.RunToVelocity;
import dev.nextftc.hardware.impl.MotorEx;

public class FlywheelSub2 implements Subsystem {
    public static final FlywheelSub2 INSTANCE = new FlywheelSub2();
    private FlywheelSub2(){}

    private final MotorEx motorl = new MotorEx("leftArc").reversed();
    private final MotorEx motorr = new MotorEx("arcMotor");
    private final MotorGroup motorGroup = new MotorGroup(motorl, motorr);

    private final ControlSystem controller = ControlSystem.builder()
            .velPid(0.005, 0, 0)
            .basicFF(0.01, 0.02, 0.03)
            //What does this change?
            .build();

    public final Command flywheelOff = new RunToVelocity(controller, 0.0).requires(this).named("FlywheelOff");
    public final Command flywheelNear = new RunToVelocity(controller, 390).requires(this).named("FlywheelNear");

    // plus 10
    public final Command flywheelNear2 = new RunToVelocity(controller, 400).requires(this).named("FlywheelNear2");
    public final Command flywheelMiddle = new RunToVelocity(controller, 435).requires(this).named("FlywheelMiddle");
    // plus 5
    public final Command flywheelFar = new RunToVelocity(controller,600).requires(this).named("FlywheelFar");
    @Override
    public void periodic() {
        motorGroup.setPower(controller.calculate(motorGroup.getState()));
    }

}
// For the middle shot I used that rpm and then 2 on the hood
