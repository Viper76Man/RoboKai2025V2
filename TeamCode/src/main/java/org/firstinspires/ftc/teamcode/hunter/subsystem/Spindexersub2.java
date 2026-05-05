package org.firstinspires.ftc.teamcode.hunter.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;

public class Spindexersub2 implements Subsystem {
    public static final Spindexersub2 INSTANCE = new Spindexersub2();
    private Spindexersub2(){}
    private MotorEx motor = new MotorEx("spindexer");
    private final ControlSystem controlSystem = ControlSystem.builder()
            // Test robot PID
            //.posPid(0.5, 0, 0)
            .posPid(0.05, 0, 0)
            .build();
    public Command toZero = new RunToPosition(controlSystem, 0).requires(this);
    public Command toMid = new RunToPosition(controlSystem, 2000).requires(this);

    @Override

    public void initialize(){
        motor.getMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        controlSystem.setGoal(new KineticState(0));
    }

    @Override
    public void periodic() {
        motor.setPower(controlSystem.calculate(motor.getState()));
    }
}
