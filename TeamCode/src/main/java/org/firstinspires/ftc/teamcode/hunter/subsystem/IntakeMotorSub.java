package org.firstinspires.ftc.teamcode.hunter.subsystem;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
public class IntakeMotorSub implements Subsystem {
    public static final  IntakeMotorSub INSTANCE = new IntakeMotorSub();
    private IntakeMotorSub(){}
  public final MotorEx Intakemotor = new MotorEx("intake");
  public final Command intakereverse = new InstantCommand(new Runnable() {
      @Override
      public void run() {
          Intakemotor.setPower(-1.0);
      }
  });
   public final Command intakeon = new InstantCommand(new Runnable() {
       @Override
       public void run() {
           Intakemotor.setPower(1.0);
       }
   });
}

