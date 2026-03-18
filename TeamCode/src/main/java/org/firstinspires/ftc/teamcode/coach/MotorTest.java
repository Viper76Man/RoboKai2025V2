package org.firstinspires.ftc.teamcode.coach;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.coach.subsystems.MotorTestSub;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "Motor Test", group = "Coach")
public class MotorTest extends NextFTCOpMode {
    public MotorTest(){
        addComponents(
                new SubsystemComponent(MotorTestSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onStartButtonPressed(){

        Gamepads.gamepad1().cross()
                .whenBecomesTrue(MotorTestSub.INSTANCE.runMotor);

        Gamepads.gamepad1().circle()
                .whenBecomesTrue(MotorTestSub.INSTANCE.stopMotor);
    }
}
