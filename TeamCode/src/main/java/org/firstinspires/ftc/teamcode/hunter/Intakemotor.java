package org.firstinspires.ftc.teamcode.hunter;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import dev.nextftc.ftc.NextFTCOpMode;

@TeleOp (name = "intake Motor")
public class Intakemotor extends NextFTCOpMode {
    public DcMotor intakemotor = new DcMotor("intake_motor");
    
    // then the motor is being defined as "intake_motor" on the drivers hub
    // this allows you to be able to connect it to the correct motor.
    @Override
    public void onStartButtonPressed() {
        addComponents(intakemotor);

        if (gamepad1.left_bumper) {
            intakemotor.setPower(-1.0);
        } else {
            intakemotor.setPower(1.0);
        }
    }

}
// I wrote this not using much ai
// i came up with all the combinations after i reviewed some of the stuff in your code
// though i am having to put it into ai to help with syntax errors
// Is there a way that Android Studio can help with this
// I dont know if this will work do you think it will