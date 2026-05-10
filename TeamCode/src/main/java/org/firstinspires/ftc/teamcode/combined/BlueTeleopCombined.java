package org.firstinspires.ftc.teamcode.combined;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.ftccommon.external.OnCreateEventLoop;
import org.firstinspires.ftc.teamcode.combined.subsystems.ColorSensorSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.FlywheelSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.LiftSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.MecanumDriveSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.RGBSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.Servosub;
import org.firstinspires.ftc.teamcode.combined.subsystems.ShooterSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.SpindexerSub;
import org.firstinspires.ftc.teamcode.hunter.ColorSensor;

import java.util.List;

import dev.nextftc.bindings.BindingManager;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.delays.WaitUntil;
import dev.nextftc.core.commands.groups.ParallelGroup;
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
                new SubsystemComponent(Servosub.INSTANCE),
                new SubsystemComponent(RGBSub.INSTANCE),
                new SubsystemComponent(LiftSub.INSTANCE),
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
        ShooterSub.INSTANCE.Frontzone.schedule();

        loadingSequence().schedule();

        Gamepads.gamepad1().cross()
                .whenBecomesTrue(IntakeSub.INSTANCE.inIntake);

//        Gamepads.gamepad1().circle()
//                .whenBecomesTrue(IntakeSub.INSTANCE.outIntake);

        Gamepads.gamepad1().square()
                .whenBecomesTrue(IntakeSub.INSTANCE.stopIntake);

        Gamepads.gamepad1().dpadLeft()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toFirstPos);

        Gamepads.gamepad1().dpadUp()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toSecondPOS);

        Gamepads.gamepad1().dpadRight()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toThirdPos);

        Gamepads.gamepad1().dpadDown()
                .whenBecomesTrue(new SequentialGroup(
                                Servosub.INSTANCE.upramp,
                        new Delay(1),
                            Shot(),
                        new Delay(0.5),
                        Servosub.INSTANCE.downramp,
                        new Delay(0.5),
                        RGBSub.INSTANCE.off,
                        loadingSequence()
                        ));

        Gamepads.gamepad1().circle()
                        .whenBecomesTrue(LiftSub.INSTANCE.up);
        // I need to look at the axons and make sure that they are
        Gamepads.gamepad1().triangle()
                        .whenBecomesTrue(LiftSub.INSTANCE.down);


//                ));
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
        telemetry.addData("Spindexer Position",SpindexerSub.INSTANCE.getSpindexerPosition());
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
    private Command Shot(){
        return new SequentialGroup(

                SpindexerSub.INSTANCE.toShootPos
        );
    }
    public Command loadingSequence() {
        return new SequentialGroup(
                SpindexerSub.INSTANCE.toFirstPos,
                new WaitUntil(ColorSensorSub.INSTANCE::isBallin),
                SpindexerSub.INSTANCE.toSecondPOS,
                new WaitUntil(ColorSensorSub.INSTANCE::isBallin),
                SpindexerSub.INSTANCE.toThirdPos,
                RGBSub.INSTANCE.orange,
                new WaitUntil(ColorSensorSub.INSTANCE::isBallin),
                RGBSub.INSTANCE.green
        );
    }
}

