package Modules;

import com.qualcomm.robotcore.hardware.DcMotor;

private void SetDriveMotorsPower(double FR, double FL, double BR, double BL) throws InterruptedException {
    robot.frontRightMotor.setPower(FR);
    robot.frontLeftMotor.setPower(FL);
    robot.rearRightMotor.setPower(BR);
    robot.rearLeftMotor.setPower(BL);
}

private void SetDriveMotorsMode(DcMotor.RunMode Mode) {
    robot.frontLeftMotor.setMode(Mode);
    robot.frontRightMotor.setMode(Mode);
    robot.rearLeftMotor.setMode(Mode);
    robot.rearRightMotor.setMode(Mode);
}

private void DriveByVector2(Vector2 MovementDelta, double Speed, double Rotation) throws InterruptedException {
    String robotAction = "";
    int newLeftTarget;
    int newRightTarget;

    // Remember current motors direction, will reset in the end
    DcMotor.Direction dirFL = robot.frontLeftMotor.getDirection();
    DcMotor.Direction dirFR = robot.frontRightMotor.getDirection();
    DcMotor.Direction dirRL = robot.rearLeftMotor.getDirection();
    DcMotor.Direction dirRR = robot.rearRightMotor.getDirection();
    DcMotor.RunMode runModeFL = robot.frontLeftMotor.getMode();
    DcMotor.RunMode runModeFR = robot.frontRightMotor.getMode();
    DcMotor.RunMode runModeRL = robot.rearLeftMotor.getMode();
    DcMotor.RunMode runModeRR = robot.rearRightMotor.getMode();

    SetDriveMotorsMode(DcMotor.RunMode.RUN_USING_ENCODER);

    // power is removed from the motor, set the current encoder position to zero
    SetDriveMotorsMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    // All mortors will move forward
    robot.frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
    robot.frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
    robot.rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
    robot.rearRightMotor.setDirection(DcMotor.Direction.FORWARD);
    //robot.frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
    //robot.frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
    //robot.rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
    //robot.rearRightMotor.setDirection(DcMotor.Direction.REVERSE);

    // Determine new target position, and pass to motor controller
    newLeftTarget = (int) (leftInches * COUNTS_PER_INCH);
    newRightTarget = (int) (rightInches * COUNTS_PER_INCH);


    robot.frontLeftMotor.setTargetPosition(newLeftTarget);
    robot.frontRightMotor.setTargetPosition(newRightTarget);
    robot.rearLeftMotor.setTargetPosition(newLeftTarget);
    robot.rearRightMotor.setTargetPosition(newRightTarget);

    // Turn On RUN_TO_POSITION
    SetDriveMotorsMode(DcMotor.RunMode.RUN_TO_POSITION);

    robot.frontRightMotor.setPower((MovementDelta.Y - MovementDelta.X + Rotation) * Speed);
    robot.frontLeftMotor.setPower((MovementDelta.Y + MovementDelta.X - Rotation) * Speed);
    robot.rearRightMotor.setPower((MovementDelta.Y + MovementDelta.X + Rotation) * Speed);
    robot.rearLeftMotor.setPower((MovementDelta.Y - MovementDelta.X - Rotation) * Speed);

    //Ramp up motor speed to match target
//        while(power <= speed) {
//            power += RAMP_INCREMENT;
//        }

    // keep looping while we are still active, and there is time left, and both motors are running.
    while (
        robot.frontLeftMotor.isBusy()
            &&
        robot.frontRightMotor.isBusy()
            &&
        robot.rearLeftMotor.isBusy()
            &&
        robot.rearRightMotor.isBusy()
    ) {}


    // Stop all motion;
    SetDriveMotorsPower(0, 0, 0, 0);

    // Turn off RUN_TO_POSITION
    SetDriveMotorsMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    // Reset back motors direction
    robot.frontLeftMotor.setDirection(dirFL);
    robot.frontRightMotor.setDirection(dirFR);
    robot.rearLeftMotor.setDirection(dirRL);
    robot.rearRightMotor.setDirection(dirRR);
    robot.frontLeftMotor.setMode(runModeFL);
    robot.frontRightMotor.setMode(runModeFR);
    robot.rearLeftMotor.setMode(runModeRL);
    robot.rearRightMotor.setMode(runModeRR);

}
