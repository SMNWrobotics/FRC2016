package org.usfirst.frc1982.Robot2016.commands;

import org.usfirst.frc1982.Robot2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AutCommGoForward extends Command {
	
	int count = 0;

	public AutCommGoForward() {
		// TODO Auto-generated constructor stub
	}

	public AutCommGoForward(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public AutCommGoForward(double timeout) {
		super(timeout);
		// TODO Auto-generated constructor stub
	}

	public AutCommGoForward(String name, double timeout) {
		super(name, timeout);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
		

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
		RobotMap.driveSystemRobotDrive.arcadeDrive(.5, 0);
		
		count++;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if (count > 100)
			return true;
		else
			return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
