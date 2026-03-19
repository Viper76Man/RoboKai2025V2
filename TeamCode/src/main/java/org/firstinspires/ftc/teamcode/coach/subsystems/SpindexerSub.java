package org.firstinspires.ftc.teamcode.coach.subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;

public class SpindexerSub implements Subsystem {
    public static final SpindexerSub INSTANCE = new SpindexerSub();
    private SpindexerSub(){}

    private final double firstPos = 0; //0 degrees
    private final double secondPos = 1333.33; //120 degrees
    private final double thirdPos = 2666.66; //240 degrees
    private final double shoot = 6666.66; //600 degrees
    public String lastCommand = "None";

    private final MotorEx motor = new MotorEx("spindexer").brakeMode().reversed();

    private final ControlSystem controlSystem = ControlSystem.builder()
            .posPid(0.001, 0, 0)
            .build();

    public double getSpindexerPosition(){
        return motor.getCurrentPosition();
    }



    //public Command toFirstPos = new RunToPosition(controlSystem, firstPos).requires(this);
    //public Command toSecondPOS = new RunToPosition(controlSystem, secondPos).requires(this);
    //public Command toThirdPos = new RunToPosition(controlSystem, thirdPos).requires(this);
    //public Command toShoot = new RunToPosition(controlSystem, shoot).requires(this);

    public Command toFirstPos(){
        return new RunToPosition(controlSystem, firstPos, 50.0).requires(this).named("First Position");
    }

    public Command toSecondPos(){
        return new RunToPosition(controlSystem, secondPos, 50.0).requires(this).named("Second Position");
    }

    public Command toThirdPos(){
        return new RunToPosition(controlSystem, thirdPos, 50.0).requires(this).named("Third Position");
    }

    public Command toShoot(){
        return new RunToPosition(controlSystem, shoot, 50.0).requires(this).named("Shoot");
    }

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
