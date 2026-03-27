package org.firstinspires.ftc.teamcode.hunter;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "Practice_Servo")
public class PracticeServo extends LinearOpMode {
private Servo myServo;

    @Override
            public void runOpMode() {
     myServo = hardwareMap.get(Servo.class,"Servo");
            waitForStart();
            //this is telling the code to wait until the button is clicked corrected I looked this up
    while (opModeIsActive())
        if (gamepad1.a) {
            myServo.setPosition(0.0);
        } else if (gamepad1.b)
            myServo.setPosition(1.0);
    }
}