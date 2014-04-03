package net.tozcentral.spaceturtles;

import java.lang.Math;
import java.util.Arrays;

import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public class PeripheralLaunchPadInterface extends PeripheralTileEntity implements IPeripheral
{
	public TileEntityLaunchPadInterface launchPadInterface;
	public LaunchPadAccess launchPadAccess;

	public PeripheralLaunchPadInterface ( TileEntityLaunchPadInterface launchPadInterface, World world, int x, int y, int z )
	{
		super ( launchPadInterface, world, x, y, z );
		
		this.launchPadInterface = launchPadInterface;

		this.launchPadAccess = launchPadInterface.getAccess ( );
		
		this.methodNames.addAll ( Arrays.asList ( new String[] {
			"hasLaunchPad",
			"hasRocket",
			"getStoredFuel",
			"getMaxFuel",
			"getLaunchPhase",
			"getCountdown",
			"getPreLaunchWait"
		} ) );
	}

	@Override
	public String getType()
	{
		return "spaceturtles_launch_pad_interface";
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, String method, Object[] arguments ) throws Exception
	{
		if ( method.equals ( "hasLaunchPad" ) )
		{
			if ( arguments.length == 0 )
				return new Object[] { launchPadAccess.hasLaunchPad ( ) };
				
			if ( arguments.length != 1 || !(arguments[0] instanceof Double) )
				throw new Exception("Expected number between 1 and 4");
				
			int launchPad = (int)Math.round ( (Double)arguments[0] );
				
			if ( launchPad < 1 || launchPad > 4 )
				throw new Exception("Expected number between 1 and 4");
				
			return new Object[] { launchPadAccess.hasLaunchPad ( launchPad - 1 ) };
		}
			
		if ( method.equals ( "hasRocket" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Double) )
				throw new Exception("Expected number between 1 and 4");
				
			int launchPad = (int)Math.round ( (Double)arguments[0] );
				
			if ( launchPad < 1 || launchPad > 4 )
				throw new Exception("Expected number between 1 and 4");
				
			return new Object[] { launchPadAccess.hasRocket ( launchPad - 1 ) };
		}

		if ( method.equals ( "getStoredFuel" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Double) )
				throw new Exception("Expected number between 1 and 4");
				
			int launchPad = (int)Math.round ( (Double)arguments[0] );
				
			if ( launchPad < 1 || launchPad > 4 )
				throw new Exception("Expected number between 1 and 4");
				
			return new Object[] { launchPadAccess.getStoredFuel ( launchPad - 1 ) };
		}

		if ( method.equals ( "getMaxFuel" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Double) )
				throw new Exception("Expected number between 1 and 4");
				
			int launchPad = (int)Math.round ( (Double)arguments[0] );
				
			if ( launchPad < 1 || launchPad > 4 )
				throw new Exception("Expected number between 1 and 4");
				
			return new Object[] { launchPadAccess.getMaxFuel ( launchPad - 1 ) };
		}

		if ( method.equals ( "getLaunchPhase" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Double) )
				throw new Exception("Expected number between 1 and 4");
				
			int launchPad = (int)Math.round ( (Double)arguments[0] );
				
			if ( launchPad < 1 || launchPad > 4 )
				throw new Exception("Expected number between 1 and 4");
				
			return new Object[] { launchPadAccess.getLaunchPhase ( launchPad - 1 ) };
		}

		if ( method.equals ( "getCountdown" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Double) )
				throw new Exception("Expected number between 1 and 4");
				
			int launchPad = (int)Math.round ( (Double)arguments[0] );
				
			if ( launchPad < 1 || launchPad > 4 )
				throw new Exception("Expected number between 1 and 4");
				
			return new Object[] { ((float)launchPadAccess.getCountdown ( launchPad - 1 )) / 20 };
		}

		if ( method.equals ( "getPreLaunchWait" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Double) )
				throw new Exception("Expected number between 1 and 4");
				
			int launchPad = (int)Math.round ( (Double)arguments[0] );
				
			if ( launchPad < 1 || launchPad > 4 )
				throw new Exception("Expected number between 1 and 4");
				
			return new Object[] { ((float)launchPadAccess.getPreLaunchWait ( launchPad - 1 )) / 20 };
		}

		return super.callMethod ( computer, context, method, arguments );
	}
	
	@Override
	public void attach ( IComputerAccess computer )
	{
		super.attach ( computer );
		
		launchPadAccess.addComputer ( computer );
	}

	@Override
	public void detach ( IComputerAccess computer )
	{
		super.detach ( computer );
		
		launchPadAccess.removeComputer ( computer );
	}
}