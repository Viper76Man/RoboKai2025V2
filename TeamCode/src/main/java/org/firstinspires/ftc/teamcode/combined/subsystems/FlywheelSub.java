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

    private final MotorEx motorl = new MotorEx("leftArc");
    private final MotorEx motorr = new MotorEx("arcMotor").reversed();
    private final MotorGroup motorGroup = new MotorGroup(motorl, motorr);

    private final ControlSystem controller = ControlSystem.builder()
            .velPid(0.005, 0, 0)
            .basicFF(0.01, 0.02, 0.03)
            .build();

    public final Command flywheelOff = new RunToVelocity(controller, 0.0).requires(this).named("FlywheelOff");
    public final Command flywheelZone1 = new RunToVelocity(controller, 500.0).requires(this).named("FlywheelZone1");

    @Override
    public void periodic() {
        motorGroup.setPower(controller.calculate(motorGroup.getState()));
    }

}
