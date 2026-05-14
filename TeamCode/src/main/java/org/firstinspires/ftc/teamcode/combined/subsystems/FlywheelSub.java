package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.controllable.RunToVelocity;
import dev.nextftc.hardware.impl.MotorEx;

public class FlywheelSub implements Subsystem {
    public static final FlywheelSub INSTANCE = new FlywheelSub();
    private FlywheelSub(){}

    private final MotorEx motorl = new MotorEx("leftArc").reversed();
    private final MotorEx motorr = new MotorEx("arcMotor");
    private final MotorGroup motorGroup = new MotorGroup(motorl, motorr);

    private final ControlSystem controller = ControlSystem.builder()
            .velPid(0.005, 0, 0)
            .basicFF(0.01, 0.02, 0.03)
            //What does this change?
            .build();

    public final Command flywheelOff = new RunToVelocity(controller, 0.0).requires(this).named("FlywheelOff");
    public final Command flywheelNear = new RunToVelocity(controller, 0.0).requires(this).named("FlywheelNear");
    public final Command flywheelMiddle = new RunToVelocity(controller, 583.35).requires(this).named("FlywheelMiddle");
    public final Command flywheelFar = new RunToVelocity(controller,2000.0).requires(this).named("FlywheelFar");
    @Override
    public void periodic() {
        motorGroup.setPower(controller.calculate(motorGroup.getState()));
    }

}
