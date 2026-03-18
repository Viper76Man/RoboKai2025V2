package org.firstinspires.ftc.teamcode.coach;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.coach.subsystems.SpindexerSub;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name = "Spindexer Basic Test", group = "Coach")
public class SpindexerBasicTest extends NextFTCOpMode {

    public SpindexerBasicTest(){
        addComponents(
                new SubsystemComponent(SpindexerSub.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onInit(){
        //Init goes here
    }

    @Override
    public void onStartButtonPressed(){
        telemetry.addLine("Running");

        Gamepads.gamepad1().dpadDown()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toFirstPos());

        Gamepads.gamepad1().dpadLeft()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toSecondPOS());
    }


    @Override
    public void onUpdate() {
        telemetry.addData("Spindexer Position", SpindexerSub.INSTANCE.getSpindexerPosition());
        telemetry.addData("Last Command", SpindexerSub.INSTANCE.lastCommand);
        telemetry.update();
    }
}
