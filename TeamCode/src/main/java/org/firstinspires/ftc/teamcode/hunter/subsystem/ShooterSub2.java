package org.firstinspires.ftc.teamcode.hunter.subsystem;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class ShooterSub2 implements Subsystem {
    public static final ShooterSub2 INSTANCE = new ShooterSub2();

    private ShooterSub2() {
    }
    public final MotorEx Shooter1 = new MotorEx("ShooterMotor");
    public final MotorEx Shooter2 = new MotorEx("FiringMotor");

    public final Command Backzone2 = new SequentialGroup(
            new InstantCommand(new Runnable() {
                @Override
                public void run() {
                    Shooter1.setPower(1.0);
                    Shooter2.setPower(1.0);
                }
            }),
            new Delay(1.0),
            Servosub.INSTANCE.Shot,
            SpindexerSub.INSTANCE.toShoot
    );
    public final Command FrontZone2 = new SequentialGroup(
            new InstantCommand(new Runnable() {
                @Override
                public void run() {
                    Shooter1.setPower(0.5);
                    Shooter2.setPower(0.5);
                }
            }),
            new Delay(1.0),
            Servosub.INSTANCE.Shot,
            SpindexerSub.INSTANCE.toShoot
    );














































}
