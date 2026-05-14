package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;
public class Servo123456Sub implements Subsystem {
    public static final Servo123456Sub INSTANCE = new Servo123456Sub();
    private Servo123456Sub() {}
    public final ServoEx myServo = new ServoEx("Shotservo");
    public final Command upramp = new SetPosition(myServo, .80).requires(this);
    public final Command downramp = new SetPosition(myServo, .53).requires(this)
}
