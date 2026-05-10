package org.firstinspires.ftc.teamcode.combined.subsystems;

import org.firstinspires.ftc.teamcode.hunter.subsystem.SpindexerSub;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class ShooterSub implements Subsystem {
    public static final ShooterSub INSTANCE = new ShooterSub();

    private ShooterSub() {
    }

    public final MotorEx Shooter1 = new MotorEx("arcMotor");
    public final MotorEx Shooter2 = new MotorEx("leftArc");

    public final Command Backzone = new SequentialGroup(
            new InstantCommand(new Runnable() {
                @Override
                public void run() {
                    Shooter1.setPower(1.0);
                    Shooter2.setPower(-1.0);
                }
            }),
            new Delay(1.0),
            org.firstinspires.ftc.teamcode.hunter.subsystem.SpindexerSub.INSTANCE.toShoot

    );
    public final Command Frontzone = new SequentialGroup(
            new InstantCommand(new Runnable() {
        @Override
        public void run() {
            Shooter1.setPower(0.4);
            Shooter2.setPower(-0.4);
        }
    })
    );
// The worst part for me has been my syntax errors. I have had Ai fix much of those
    // When we do the rpm it is target rpm /60
    //then take this times 28







}
