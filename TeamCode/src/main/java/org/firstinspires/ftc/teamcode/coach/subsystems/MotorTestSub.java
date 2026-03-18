package org.firstinspires.ftc.teamcode.coach.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class MotorTestSub implements Subsystem {
    public static final MotorTestSub INSTANCE = new MotorTestSub();
    private MotorTestSub(){}

    private final MotorEx motor = new MotorEx("spindexer");

    // Define commands as objects to avoid using lambdas later
    public final Command runMotor = new SetPower(motor, 0.2).requires(this);
    public final Command stopMotor = new SetPower(motor, 0.0).requires(this);

}
