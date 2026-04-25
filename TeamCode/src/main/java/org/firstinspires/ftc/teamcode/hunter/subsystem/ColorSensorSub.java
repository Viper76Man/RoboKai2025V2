package org.firstinspires.ftc.teamcode.hunter.subsystem;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.ftc.ActiveOpMode;

public class ColorSensorSub implements Subsystem {
    public static final ColorSensorSub INSTANCE = new ColorSensorSub();
    private ColorSensorSub(){}
    private RevColorSensorV3 colorSensor;
    private RevColorSensorV3 distanceSensor;

    public enum DetectedColor {
        PURPLE,
        GREEN,
        UNKNOWN
    }

    @Override
    public void initialize(){
        colorSensor = ActiveOpMode.hardwareMap().get(RevColorSensorV3.class, "colorSensor");
        colorSensor.setGain(60);
        distanceSensor = ActiveOpMode.hardwareMap().get(RevColorSensorV3.class, "colorSensor");
    }

    public double getDistance(){
        //Less then 25mm means a ball is in
        return  distanceSensor.getDistance(DistanceUnit.MM);
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