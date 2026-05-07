package org.firstinspires.ftc.teamcode.combined;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.ftccommon.external.OnCreateEventLoop;
import org.firstinspires.ftc.teamcode.combined.subsystems.ColorSensorSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.MecanumDriveSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.SpindexerSub;

import java.util.List;

import dev.nextftc.bindings.BindingManager;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.MecanumDriverControlled;

@TeleOp(name = "Blue Teleop Combined", group = "Coach")
public class BlueTeleopCombined extends NextFTCOpMode {
    public BlueTeleopCombined(){
        addComponents(
                new SubsystemComponent(MecanumDriveSub.INSTANCE),
                new SubsystemComponent(SpindexerSub.INSTANCE),
                new SubsystemComponent(IntakeSub.INSTANCE),
                new SubsystemComponent(ColorSensorSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onInit(){
        //Init stuff here
    }

    @Override
    public void onStartButtonPressed(){
        telemetry.addLine("Running");

        startIntake().schedule();

        Gamepads.gamepad1().cross()
                .whenBecomesTrue(IntakeSub.INSTANCE.inIntake);

        Gamepads.gamepad1().circle()
                .whenBecomesTrue(IntakeSub.INSTANCE.outIntake);

        Gamepads.gamepad1().square()
                .whenBecomesTrue(IntakeSub.INSTANCE.stopIntake);

        Gamepads.gamepad1().dpadLeft()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toFirstPos);

        Gamepads.gamepad1().dpadUp()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toSecondPOS);

        Gamepads.gamepad1().dpadRight()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toThirdPos);

        Gamepads.gamepad1().dpadDown()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toShootPos);

        Command driveControlled = new MecanumDriverControlled(
                MecanumDriveSub.INSTANCE.frontLeft,
                MecanumDriveSub.INSTANCE.frontRight,
                MecanumDriveSub.INSTANCE.backLeft,
                MecanumDriveSub.INSTANCE.backRight,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
        );
        driveControlled.schedule();
    }

    @Override
    public void onUpdate(){
        List<String> currentSnapshot = CommandManager.INSTANCE.snapshot();
        telemetry.addData("Running Commands", currentSnapshot);
        telemetry.addData("Detected Color", ColorSensorSub.INSTANCE.getDetectedColor(telemetry));
        telemetry.addData("Distance", ColorSensorSub.INSTANCE.getDistance());
        telemetry.update();
    }

    public void onStop(){
        stopIntake().schedule();
    }

    private Command startIntake(){
        return new SequentialGroup(
                IntakeSub.INSTANCE.inIntake
        );
    }

    private Command stopIntake(){
        return new SequentialGroup(
                IntakeSub.INSTANCE.stopIntake
        );
    }

}

