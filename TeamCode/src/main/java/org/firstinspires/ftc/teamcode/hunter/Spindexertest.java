package org.firstinspires.ftc.teamcode.hunter;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.hunter.subsystem.SpindexerSub;

import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;

@TeleOp(name = "Practice_Servo")
public class Spindexertest extends NextFTCOpMode {

    public Spindexertest() {
        addComponents(
                new SubsystemComponent(SpindexerSub.INSTANCE)
        );
    }
    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().triangle()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toFirstPos);
        Gamepads.gamepad1().square()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toSecondPOS);
        Gamepads.gamepad1().cross()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toThirdPos);
        Gamepads.gamepad1().circle()
                .whenBecomesTrue(SpindexerSub.INSTANCE.toShoot);
    }

}
