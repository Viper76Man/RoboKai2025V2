package org.firstinspires.ftc.teamcode.hunter;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hunter.subsystem.ColorSensorSub;
import org.firstinspires.ftc.teamcode.hunter.subsystem.IntakeMotorSub;
import org.firstinspires.ftc.teamcode.combined.subsystems.Servosub;
import org.firstinspires.ftc.teamcode.combined.subsystems.ShooterSub;
import org.firstinspires.ftc.teamcode.hunter.subsystem.ShooterSub2;
import org.firstinspires.ftc.teamcode.hunter.subsystem.SpindexerSub;

import java.util.List;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.CommandManager;
import dev.nextftc.core.commands.delays.WaitUntil;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.MecanumDriverControlled;

@TeleOp (name = "Blue Teleop")
public class BlueTeleop extends NextFTCOpMode {
    public BlueTeleop() {
        addComponents(
                new SubsystemComponent(Servosub.INSTANCE),
                new SubsystemComponent(SpindexerSub.INSTANCE),
                new SubsystemComponent(IntakeMotorSub.INSTANCE),
                new SubsystemComponent(ShooterSub.INSTANCE),
                new SubsystemComponent(ColorSensorSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }
    @Override
    public void onStartButtonPressed() {

        Command driveControlled = new MecanumDriverControlled(
                org.firstinspires.ftc.teamcode.hunter.subsystem.Drivetrainsub.INSTANCE.frontLeft,
                org.firstinspires.ftc.teamcode.hunter.subsystem.Drivetrainsub.INSTANCE.frontRight,
                org.firstinspires.ftc.teamcode.hunter.subsystem.Drivetrainsub.INSTANCE.backLeft,
                org.firstinspires.ftc.teamcode.hunter.subsystem.Drivetrainsub.INSTANCE.backRight,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
        );
        driveControlled.schedule();
//Drivetrain
        IntakeMotorSub.INSTANCE.inIntake.schedule();
        Gamepads.gamepad1().leftBumper()
                .whenBecomesTrue(IntakeMotorSub.INSTANCE.outIntake);
        Gamepads.gamepad1().leftBumper()
                        .whenBecomesFalse(IntakeMotorSub.INSTANCE.inIntake);
        Gamepads.gamepad1().rightStickButton()
                        .whenBecomesTrue(IntakeMotorSub.INSTANCE.stopIntake);
        Gamepads.gamepad1().rightStickButton()
                        .whenBecomesFalse(IntakeMotorSub.INSTANCE.inIntake);
//intake
        ShooterSub2.INSTANCE.Backzone2.schedule();
        Gamepads.gamepad1().rightBumper()
                .whenBecomesTrue(ShooterSub2.INSTANCE.FrontZone2);
        Gamepads.gamepad1().touchpad()
                .whenBecomesFalse(ShooterSub2.INSTANCE.Backzone2);
//shooter

        new SequentialGroup(
        SpindexerSub.INSTANCE.toFirstPos,
        new WaitUntil(ColorSensorSub.INSTANCE::isBallin),
        SpindexerSub.INSTANCE.toSecondPOS,
        new WaitUntil(ColorSensorSub.INSTANCE::isBallin),
        SpindexerSub.INSTANCE.toThirdPos
    ).schedule();

        Gamepads.gamepad1().square()
                .whenBecomesTrue(Servosub.INSTANCE.upramp);
// This I looked up and it said that :: tells the thing what to do rather than .isBallin which would just say the answer at one time.

//        Gamepads.gamepad1().triangle()
//                .whenBecomesTrue(SpindexerSub.INSTANCE.toFirstPos);
//        Gamepads.gamepad1().square()
//                .whenBecomesTrue(SpindexerSub.INSTANCE.toSecondPOS);
//        Gamepads.gamepad1().cross()
//                .whenBecomesTrue(SpindexerSub.INSTANCE.toThirdPos);
//        Gamepads.gamepad1().circle()
//                .whenBecomesTrue(SpindexerSub.INSTANCE.toShoot);

    }
    @Override
    public void onUpdate(){
        List<String> currentSnapshot = CommandManager.INSTANCE.snapshot();
        telemetry.addData("Running Commands", currentSnapshot);
        telemetry.addData("Detected Color", ColorSensorSub.INSTANCE.getDetectedColor(telemetry));
        telemetry.addData("Distance", ColorSensorSub.INSTANCE.getDistance());
        telemetry.addData("Do We Have a Ball", ColorSensorSub.INSTANCE.isBallin());
        telemetry.addData("Spindexer Position", SpindexerSub.INSTANCE.getSpindexerPosition());
        telemetry.addData("Motor Power", SpindexerSub.INSTANCE.getCurrentPower());
        telemetry.update();
    }
}
