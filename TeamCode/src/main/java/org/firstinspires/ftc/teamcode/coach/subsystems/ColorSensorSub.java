package org.firstinspires.ftc.teamcode.coach.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.ftc.ActiveOpMode;

public class ColorSensorSub implements Subsystem {
    public static final ColorSensorSub INSTANCE = new ColorSensorSub();
    private ColorSensorSub(){}
    private NormalizedColorSensor colorSensor;

    public enum DetectedColor {
        PURPLE,
        GREEN,
        UNKNOWN
    }

    @Override
    public void initialize(){
        colorSensor = ActiveOpMode.hardwareMap().get(NormalizedColorSensor.class, "colorSensor");
        colorSensor.setGain(60);
    }

    public DetectedColor getDetectedColor(Telemetry telemetry){
        NormalizedRGBA colors = colorSensor.getNormalizedColors();

        float normRed, normGreen, normBlue;
        normRed = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;

        telemetry.addData("Red", normRed);
        telemetry.addData("Green", normGreen);
        telemetry.addData("Blue", normBlue);

        /*
        red, green, blue
        Green = .2882 (.4782), .8211 (1.2702), .5566 (.826) <.35 >.75 <.65
        Purple = .4391 (.5881), .6841 (.9095), .7097 (.9506) >.35 <.75 >.65
        None =
        */

        if (normRed > .8){
            return DetectedColor.UNKNOWN;
        }
        else if (normGreen > .75){
            return DetectedColor.GREEN;
        }
        else if (normGreen < .75 && normBlue > .65){
            return DetectedColor.PURPLE;
        }
        else {
            return DetectedColor.UNKNOWN;
        }

    }

}
