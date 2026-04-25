package org.firstinspires.ftc.teamcode.hunter;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hunter.subsystem.IntakeMotorSub;
import org.firstinspires.ftc.teamcode.hunter.subsystem.Servosub;
import org.firstinspires.ftc.teamcode.hunter.subsystem.ShooterSub;
import org.firstinspires.ftc.teamcode.hunter.subsystem.SpindexerSub;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.Commands;
import dev.nextftc.core.commands.utility.InstantCommand;
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
                new SubsystemComponent(org.firstinspires.ftc.teamcode.hunter.subsystem.Drivetrainsub.INSTANCE),
                new SubsystemComponent(Servosub.INSTANCE),
                new SubsystemComponent(SpindexerSub.INSTANCE),
                new SubsystemComponent(IntakeMotorSub.INSTANCE),
                new SubsystemComponent(ShooterSub.INSTANCE),
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


        Gamepads.gamepad1().a()
                .whenBecomesTrue(Servosub.INSTANCE.Shot);
//Servo
        Gamepads.gamepad1().dpadUp()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toFirstPos);
        Gamepads.gamepad1().dpadRight()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toSecondPOS);
        Gamepads.gamepad1().dpadDown()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toThirdPos);
        Gamepads.gamepad1().dpadLeft()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toShoot);
        //Spindexer
        IntakeMotorSub.INSTANCE.intakeon.schedule();
        Gamepads.gamepad1().leftBumper()
                .whenBecomesTrue(IntakeMotorSub.INSTANCE.intakereverse);
//intake
        Gamepads.gamepad1().rightBumper()
                .whenBecomesTrue(ShooterSub.INSTANCE.Backzone);
        Gamepads.gamepad1().touchpad()
                .whenBecomesTrue(ShooterSub.INSTANCE.Frontzone);
//shooter












    }
}
