package org.firstinspires.ftc.teamcode.coach.subsystems;

import java.util.Set;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class IntakeSub implements Subsystem {
    public static final IntakeSub INSTANCE = new IntakeSub();
    private IntakeSub(){}

    private final MotorEx motor = new MotorEx("intake").reversed();

    public final Command inIntake = new SetPower(motor, 1.0).requires(this);
    public final  Command outIntake = new SetPower(motor, -1.0).requires(this);
    public final Command stopIntake = new SetPower(motor, 0).requires(this);

}
