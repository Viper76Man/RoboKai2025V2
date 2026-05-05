package org.firstinspires.ftc.teamcode.hunter.subsystem;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class IntakeMotorSub implements Subsystem {
    public static final IntakeMotorSub INSTANCE = new IntakeMotorSub();

    private IntakeMotorSub(){}

        private final MotorEx motor = new MotorEx("intake").reversed();

        public final Command inIntake = new SetPower(motor, 1.0).requires(this);
        public final  Command outIntake = new SetPower(motor, -1.0).requires(this);
        public final Command stopIntake = new SetPower(motor, 0).requires(this);
}


