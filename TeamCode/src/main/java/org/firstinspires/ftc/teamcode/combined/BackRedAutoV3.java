package org.firstinspires.ftc.teamcode.combined;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import dev.nextftc.ftc.NextFTCOpMode;

@Autonomous(name = "Auto Red Back V3")
public class BackRedAutoV3 extends NextFTCOpMode
{

        // Declare OpMode members
        private DcMotor leftFront, rightFront, leftBack, rightBack;

        @Override
        public void runOpMode() {
            // Initialize the hardware (ensure names match your configuration)
            leftFront  = hardwareMap.get(DcMotor.class, "fl");
            rightFront = hardwareMap.get(DcMotor.class, "fr");
            leftBack   = hardwareMap.get(DcMotor.class, "bl");
            rightBack  = hardwareMap.get(DcMotor.class, "br");

            // Most robots need the left motors reversed to drive forward
            leftFront.setDirection(DcMotor.Direction.REVERSE);
            leftBack.setDirection(DcMotor.Direction.REVERSE);

            waitForStart();

            if (opModeIsActive()) {
                // Set all motors to the same power to move forward
                double drivePower = 0.5;
                leftFront.setPower(drivePower);
                rightFront.setPower(drivePower);
                leftBack.setPower(drivePower);
                rightBack.setPower(drivePower);

                // Run for 1.5 seconds
                sleep(1000);

                // Stop all motors
                leftFront.setPower(0);
                rightFront.setPower(0);
                leftBack.setPower(0);
                rightBack.setPower(0);
            }
        }
    }

