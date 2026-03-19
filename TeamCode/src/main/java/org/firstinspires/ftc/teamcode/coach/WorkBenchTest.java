package org.firstinspires.ftc.teamcode.coach;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.coach.subsystems.WorkbenchSub;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.MecanumDriverControlled;

@TeleOp(name = "Work Bench Test", group = "Coach")
public class WorkBenchTest extends NextFTCOpMode {

    public WorkBenchTest(){
        addComponents(
                new SubsystemComponent(WorkbenchSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onInit(){
        //Init stuff goes here
    }

    @Override
    public void onStartButtonPressed(){
        telemetry.addLine("Running");

        Command driveControlled = new MecanumDriverControlled(
                WorkbenchSub.INSTANCE.frontLeft,
                WorkbenchSub.INSTANCE.frontRight,
                WorkbenchSub.INSTANCE.backLeft,
                WorkbenchSub.INSTANCE.backRight,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
        );
        driveControlled.schedule();
    }

    @Override
    public void onUpdate(){
        telemetry.update();
    }
}
