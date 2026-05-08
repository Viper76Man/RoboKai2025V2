package org.firstinspires.ftc.teamcode.combined.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;

public class SpindexerSub implements Subsystem {
    public static final SpindexerSub INSTANCE = new SpindexerSub();
    private SpindexerSub() {}

    private final double firstPos = 0;  //0 degrees
    private final double secondPos = 250.6; //120 degrees
    private final double thirdPos = 501.2;  //240 degrees
    private final double shootPos = -1503.6; //600 degrees



    private final MotorEx motor = new MotorEx("spindexer");



    private final ControlSystem controlSystem = ControlSystem.builder()
            .posPid(0.005, 0, 0)
            .build();

    public Command toFirstPos = new RunToPosition(controlSystem, firstPos).requires(this);
    public Command toSecondPOS = new RunToPosition(controlSystem, secondPos).requires(this);
    public Command toThirdPos = new RunToPosition(controlSystem, thirdPos).requires(this);
    public Command toShootPos = new RunToPosition(controlSystem, shootPos).requires(this);


    @Override
    public void initialize() {
        //reset encoder
        motor.getMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        controlSystem.setGoal(new KineticState(0));
    }

    @Override
    public void periodic() {
        motor.setPower(controlSystem.calculate(motor.getState()));
    }

}
