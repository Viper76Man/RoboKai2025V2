package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;

public class TurretRedBacksub implements Subsystem {
    public static final TurretRedBacksub Instance = new TurretRedBacksub();

    private TurretRedBacksub() {
    }

    private static final double ServoRange = 355;
    private static final double Centerpos = 0.50;
    private static final double kP = 0.6;
    private static final double MinPos = -90;
    private static final double MaxPos = 90;
    private static final double Centerpos2 = 0.50;
    private static final double Deadbandangle = 0.05;
    private static final double txOffset = 6.5;
    // 6.5
    private static final double txOffsetBack = 1.85;
    // plus 2.15 might be negative
    private static final double Tx_sign = 1.0;
    public final ServoEx turret = new ServoEx("turretaxon");
    private double Commandposition = 0.0;

    @Override
    public void initialize() {
        turret.setPosition(Centerpos2);
    }

    @Override
    public void periodic() {
        VisionRedSub.DetectedZone zone = VisionRedSub.INSTANCE.getDectectedZone();

        if ((zone == VisionRedSub.DetectedZone.Zone4 || zone == VisionRedSub.DetectedZone.ZONE2 || zone == VisionRedSub.DetectedZone.ZONE1) && (VisionRedSub.INSTANCE.hastarget() && Math.abs(VisionRedSub.INSTANCE.getTx()) > Deadbandangle)) {
            double tx = VisionRedSub.INSTANCE.getTx();
            Commandposition += Tx_sign * kP * (tx - txOffset);
            Commandposition = Math.max(MinPos, Math.min(MaxPos, Commandposition));
            turret.setPosition(angleToPosition(Commandposition));
        } else if (zone == VisionRedSub.DetectedZone.Zone3 && VisionRedSub.INSTANCE.hastarget() && Math.abs(VisionRedSub.INSTANCE.getTx()) > Deadbandangle) {
            double tx = VisionRedSub.INSTANCE.getTx();
            Commandposition += Tx_sign * kP * (tx - txOffsetBack);
            Commandposition = Math.max(MinPos, Math.min(MaxPos, Commandposition));
            turret.setPosition(angleToPosition(Commandposition));
        }
    }
        private double angleToPosition ( double angleDegrees){
            return Centerpos + (angleDegrees / ServoRange);
        }
    }
// Zone 3 is the back zone
//Zone 1,2,4 is front