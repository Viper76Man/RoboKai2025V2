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
//Add TeleOp with name and group
@TeleOp(name = "Work Bench Test", group = "Coach")
//Update class to extends NextFTCOpMode
public class WorkBenchTest extends NextFTCOpMode {

    //This is how we add components to allow us to access public statements in our subsystem
    //and also add bulk read and PS5 bindings
    public WorkBenchTest(){
        addComponents(
                new SubsystemComponent(WorkbenchSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    //Init stuff goes here
    @Override
    public void onInit(){

    }

    //Start button pressed stuff goes here
    @Override
    public void onStartButtonPressed(){
        //Simple telemetry statement to out put to hub
        telemetry.addLine("Running");

        //Special mecanum drive control command
        Command driveControlled = new MecanumDriverControlled(
                WorkbenchSub.INSTANCE.frontLeft,
                WorkbenchSub.INSTANCE.frontRight,
                WorkbenchSub.INSTANCE.backLeft,
                WorkbenchSub.INSTANCE.backRight,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
        );
        //Need schedule to make it run
        driveControlled.schedule();
    }

    //Code to run on every loop
    @Override
    public void onUpdate(){
        //Use this to update hub on every loop
        telemetry.update();
    }
}
