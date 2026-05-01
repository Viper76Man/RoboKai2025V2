package org.firstinspires.ftc.teamcode.hunter.subsystem;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;

public class Servosub implements Subsystem {
public static final Servosub INSTANCE = new Servosub();
private Servosub() {}
    public final ServoEx myServo = new ServoEx("Shotservo");
//    public final Command servoDown = new InstantCommand(new Runnable() {
//        @Override
//        public void run() {
//            myServo.setPosition(0.0);
//        }
//    });
//    public final Command ServoUp = new InstantCommand(new Runnable() {
//        @Override
//        public void run() {
//            myServo.setPosition(1.0);
//        }
//    });
    public final Command Shot = new SequentialGroup(
            new InstantCommand(new Runnable() {
                @Override
                public void run() {
                    myServo.setPosition(1.0);
                }
            }),
                new Delay(0.8),
                new InstantCommand(new Runnable() {
                    @Override
                    public void run() {
                        myServo.setPosition(0.6);

                }
            })
    );
}
// I orginally had this why does this not work   public final Command Servoup = new InstantCommand(()myServo.setPosition(1.0));
// The new stuff is AI though i feel like it should work and makes sense though there is a more direct path.