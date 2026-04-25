package org.firstinspires.ftc.teamcode.hunter.subsystem;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;

public class SpindexerSub implements Subsystem {
    public static final SpindexerSub INSTANCE = new SpindexerSub();
    private SpindexerSub(){}

    //ELC through bore encoder is 4000 ticks
    private static final double COUNTS_PER_REV = 751.8;

    private static double firstPos = 0 * (COUNTS_PER_REV / 360.0); //0 degrees
    private static double secondPos = 120 * (COUNTS_PER_REV / 360.0); //120 degrees
    private static double thirdPos = 240 * (COUNTS_PER_REV / 360.0); //240 degrees
    private static double shoot = 600 * (COUNTS_PER_REV / 360.0); //600 degrees
    public String lastCommand = "None";

    private final MotorEx motor = new MotorEx("spindexer");

    private final ControlSystem controlSystem = ControlSystem.builder()
            // Test robot PID
            //.posPid(0.5, 0, 0)
            .posPid(0.005, 0, 0)
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