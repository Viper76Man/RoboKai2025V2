package org.firstinspires.ftc.teamcode.hunter;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import dev.nextftc.core.commands.Command;

import org.firstinspires.ftc.teamcode.hunter.subsystems.Drivetrainsub;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.DriverControlledCommand;
import dev.nextftc.hardware.driving.MecanumDriverControlled;

@TeleOp(name = "Drivetrain")
public class Drivetrain2 extends NextFTCOpMode {
    public Drivetrain2() {
        addComponents(
                new SubsystemComponent(Drivetrainsub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
                // I looked these up so i better understand them though the bulk reading looks through all the data coming in at once
                // then the binding is to use the controller
        );
    }
    @Override
    public void onStartButtonPressed() {
      Command driveControlled = new MecanumDriverControlled(
                Drivetrainsub.INSTANCE.frontLeft,
                Drivetrainsub.INSTANCE.frontRight,
                Drivetrainsub.INSTANCE.backLeft,
                Drivetrainsub.INSTANCE.backRight,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
      );
      driveControlled.schedule();
    }
}
// This I was definely a little more confused about.
// Though I now understand Instance now a lot more.
// the idea of calling things from the subsystem was difficult
// If we could go back over that
// If we could also spend some time reviewing the differences in NextFtc vs the older model. I wrote a lot of this code in the other and then had to go back through in fix it so that I could adapt to the subsystem