package org.firstinspires.ftc.teamcode.coach;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.coach.subsystems.IntakeSub;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "Intake Test", group = "Coach")
public class IntakeTest extends NextFTCOpMode {
    public IntakeTest(){
        addComponents(
                new SubsystemComponent(IntakeSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onStartButtonPressed(){

        Gamepads.gamepad1().cross()
                .whenBecomesTrue(IntakeSub.INSTANCE.inIntake);

        Gamepads.gamepad1().circle()
                .whenBecomesTrue(IntakeSub.INSTANCE.outIntake);

        Gamepads.gamepad1().square()
                .whenBecomesTrue(IntakeSub.INSTANCE.stopIntake);
    }
}
