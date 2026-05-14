package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

public class Servosub implements Subsystem {
public static final Servosub INSTANCE = new Servosub();
private Servosub() {}
    public final ServoEx myServo = new ServoEx("Shotservo");
    public final Command upramp = new SetPosition(myServo, .80).requires(this);
    public final Command downramp = new SetPosition(myServo, .53).requires(this);



}
// I orginally had this why does this not work   public final Command Servoup = new InstantCommand(()myServo.setPosition(1.0));
// The new stuff is AI though i feel like it should work and makes sense though there is a more direct path.