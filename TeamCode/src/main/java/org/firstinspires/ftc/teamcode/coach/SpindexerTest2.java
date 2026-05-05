package org.firstinspires.ftc.teamcode.coach;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.coach.subsystems.SpindexerSub2;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "Spindexer Test 2", group = "Coach")
public class SpindexerTest2 extends NextFTCOpMode {
    public SpindexerTest2(){
        addComponents(
                new SubsystemComponent(SpindexerSub2.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onStartButtonPressed(){

        Gamepads.gamepad1().cross()
                .whenBecomesTrue(SpindexerSub2.INSTANCE.toZero);

        Gamepads.gamepad1().circle()
                .whenBecomesTrue(SpindexerSub2.INSTANCE.toMid);
    }
}
