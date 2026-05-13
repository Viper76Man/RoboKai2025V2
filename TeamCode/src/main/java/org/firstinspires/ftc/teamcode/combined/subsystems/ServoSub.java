package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

public class ServoSub implements Subsystem {
public static final ServoSub INSTANCE = new ServoSub();
private ServoSub() {}
    public final ServoEx myServo = new ServoEx("Shotservo");
    public final Command upramp = new SetPosition(myServo, .80).requires(this);
    public final Command downramp = new SetPosition(myServo, 0.53).requires(this);



}
// I orginally had this why does this not work   public final Command Servoup = new InstantCommand(()myServo.setPosition(1.0));
// The new stuff is AI though i feel like it should work and makes sense though there is a more direct path.