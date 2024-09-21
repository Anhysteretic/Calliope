package com.team841.calliope.superstructure.shooter;

import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.team841.betaSwerve2024.Constants.ConstantsIO;
import com.team841.betaSwerve2024.Constants.SC;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {



    public Shooter() {

        // bottomShooter.setControl(new Follower(topShooter.getDeviceID(), false));
    }

    private void setVelocity(double velocity) {
        topShooter.setControl(new MotionMagicVelocityVoltage(velocity));
    }

    protected void Shoot() {
        setVelocity(60);
    }

    public void spinUp() {
        topShooter.setControl(
                new MotionMagicVelocityVoltage(95).withFeedForward(6).withAcceleration(200).withSlot(0));
        bottomShooter.setControl(
                new MotionMagicVelocityVoltage(95).withFeedForward(6).withAcceleration(200).withSlot(0));
    }

    public void ampShot() {
        topShooter.setControl(
                new MotionMagicVelocityVoltage(1.5).withFeedForward(6).withAcceleration(200).withSlot(0));
        bottomShooter.setControl(
                new MotionMagicVelocityVoltage(12.5).withFeedForward(6).withAcceleration(200).withSlot(0));
    }

    public Command idleBack() {
        return new RunCommand(
                () -> {
                    topShooter.set(-0.05);
                    bottomShooter.set(-0.015);
                },
                this);
    }

    public void trapShot() {
        double top = 9.9375;
        double bottom = 82.8125;
        topShooter.setControl(
                new MotionMagicVelocityVoltage(top).withFeedForward(6).withAcceleration(200).withSlot(0));
        bottomShooter.setControl(
                new MotionMagicVelocityVoltage(bottom)
                        .withFeedForward(6)
                        .withAcceleration(200)
                        .withSlot(0));
    }

    public void flyShot() {
        topShooter.setControl(
                new MotionMagicVelocityVoltage(70).withFeedForward(6).withAcceleration(200).withSlot(0));
        bottomShooter.setControl(
                new MotionMagicVelocityVoltage(70).withFeedForward(6).withAcceleration(200).withSlot(0));
    }

    public void disruptshot() {
        topShooter.setControl(
                new MotionMagicVelocityVoltage(12.5).withFeedForward(6).withAcceleration(200).withSlot(0));
        bottomShooter.setControl(
                new MotionMagicVelocityVoltage(12.5).withFeedForward(6).withAcceleration(200).withSlot(0));
    }

    protected double getMotorVoltage() {
        return topShooter.getMotorVoltage().getValue();
    }

    public boolean isShooting() {
        return this.topShooter.getVelocity().getValue() > 0;
    }

    public boolean isHighShot() {
        return this.topShooter.getVelocity().getValue() > 75;
    }

  /* public void stopShooter() {
    topShooter.stopMotor();
    bottomShooter.stopMotor();
  } */

    public void stopShooter() {
        bottomShooter.set(-0.05);
        topShooter.set(-0.05);
    }

    public Command runShooter(double velocity) {
        return new InstantCommand(() -> this.setVelocity(velocity));
    }

    @Override
    public void periodic() {}
}
