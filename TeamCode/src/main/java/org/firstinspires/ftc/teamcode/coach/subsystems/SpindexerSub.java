package org.firstinspires.ftc.teamcode.coach.subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;

public class SpindexerSub implements Subsystem {
    public static final SpindexerSub INSTANCE = new SpindexerSub();
    private SpindexerSub(){}

    private static double firstPos = 0; //0 degrees
    private static double secondPos = 1333.33; //120 degrees
    private static double thirdPos = 2666.66; //240 degrees
    private static double shoot = 6666.66; //600 degrees
    public String lastCommand = "None";

    private final MotorEx motor = new MotorEx("spindexer");

    private final ControlSystem controlSystem = ControlSystem.builder()
            .posPid(0.5, 0, 0)
            .build();

    public double getSpindexerPosition(){
        return motor.getCurrentPosition();
    }



    public Command toFirstPos = new RunToPosition(controlSystem, firstPos).requires(this);
    public Command toSecondPOS = new RunToPosition(controlSystem, secondPos).requires(this);
    public Command toThirdPos = new RunToPosition(controlSystem, thirdPos).requires(this);
    public Command toShoot = new RunToPosition(controlSystem, shoot).requires(this);

    //public void toFirstPos(){
    //    lastCommand = "0 Degrees";
     //   new RunToPosition(controlSystem, firstPos).requires(this).schedule();
    //}

    //public Command toSecondPOS(){
    //    lastCommand = "120 Degrees";
    //    new RunToPosition(controlSystem, secondPos).requires(this).schedule();
    //    return null;
    //}

    @Override
    public void initialize() {
        //reset encoder
        motor.zero();
    }

    @Override
    public void periodic() {
        motor.setPower(controlSystem.calculate(motor.getState()));
    }





}
