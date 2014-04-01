package net.tozcentral.spaceturtles;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.turtle.ITurtleAccess;

public class TurtleOxygenDetectorPeripheral implements IPeripheral
{
	public ITurtleAccess turtle;
	public IComputerAccess computer;
	private boolean airDetected;

	public TurtleOxygenDetectorPeripheral ( ITurtleAccess turtle )
	{
		this.turtle = turtle;
	}

	@Override
	public String getType()
	{
		return "spaceturtles_oxygen_detector";
	}

	@Override
	public String[] getMethodNames ( )
	{
		return new String[] {
			"isAirDetected"
		};
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, int method, Object[] arguments ) throws Exception
	{
		switch(method) {
			case 0:
				return new Object[] { this.airDetected };
			default:
				return null;
		}
	}
	
	@Override
	public void attach ( IComputerAccess computer )
	{
		this.computer = computer;
	}

	@Override
	public void detach ( IComputerAccess computer )
	{
		this.computer = null;
	}

	public boolean equals ( IPeripheral other )
	{
		return other != null && other.getClass() == this.getClass();
	}
	
	public void setAirDetected ( boolean airDetected )
	{
		if ( this.airDetected != airDetected && this.computer != null )
		{
			this.airDetected = airDetected;
			this.computer.queueEvent ( "spaceturtles_oxygen_detect", new Object[] { this.computer.getAttachmentName ( ), airDetected } );
		}
	}
}