package net.tozcentral.spaceturtles;

import java.lang.Math;
import java.util.Arrays;

import net.minecraft.world.World;

import micdoodle8.mods.galacticraft.mars.tile.GCMarsTileEntityLaunchController;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public class PeripheralLaunchController extends PeripheralElectric implements IPeripheral
{
	public GCMarsTileEntityLaunchController launchController;
	public LaunchPadAccess launchPadAccess;

	public PeripheralLaunchController ( GCMarsTileEntityLaunchController launchController, World world, int x, int y, int z )
	{
		super ( launchController, world, x, y, z );
		
		this.launchController = launchController;
		
		this.launchPadAccess = new LaunchPadAccess ( this.tileEntity.worldObj, this.tileEntity.xCoord, this.tileEntity.yCoord, this.tileEntity.zCoord );
		this.launchPadAccess.update ( );
		
		this.methodNames.addAll ( Arrays.asList ( new String[] {
			"getOwnerName",
			"getFrequency",
			"setFrequency",
			"getDestinationFrequency",
			"setDestinationFrequency",
			"getRemoveLaunchPad",
			"setRemoveLaunchPad",
			"getLaunchOption",
			"setLaunchOption",
			"getEnabled",
			"setEnabled",
			
			"hasLaunchPad",
			"hasRocket",
			"getStoredFuel",
			"getMaxFuel",
			"getLaunchPhase",
			"getCountdown",
			"getPreLaunchWait",
			"launch"
		} ) );
	}
	
	@Override
	public void update ( )
	{
		if ( this.launchPadAccess != null )
			this.launchPadAccess.update ( );
	}
	
	@Override
	public boolean hasUpdate ( )
	{
		return true;
	}

	@Override
	public String getType()
	{
		return "spaceturtles_launch_controller";
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, String method, Object[] arguments ) throws Exception
	{
		if ( method.equals ( "getOwnerName" ) )
			return new Object[] { launchController.getOwnerName ( ) };
			
		if ( method.equals ( "getFrequency" ) )
			return new Object[] { launchController.frequency };

		if ( method.equals ( "setFrequency" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Double) )
				throw new Exception("Expected number");
				
			launchController.frequency = (int)Math.round ( (Double)arguments[0] );
			return new Object[0];
		}
		
		if ( method.equals ( "getDestinationFrequency" ) )
			return new Object[] { launchController.destFrequency };

		if ( method.equals ( "setDestinationFrequency" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Double) )
				throw new Exception("Expected number");
				
			launchController.destFrequency = (int)Math.round ( (Double)arguments[0] );
			return new Object[0];
		}
		
		if ( method.equals ( "getRemoveLaunchPad" ) )
			return new Object[] { launchController.launchPadRemovalDisabled };
		if ( method.equals ( "setRemoveLaunchPad" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Boolean) )
				throw new Exception("Expected boolean");
				
			launchController.launchPadRemovalDisabled = (Boolean) arguments[0];
			return new Object[0];
		}
		
		if ( method.equals ( "getLaunchOption" ) )
			return new Object[] { launchController.launchDropdownSelection };

		if ( method.equals ( "setLaunchOption" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Double) )
				throw new Exception("Expected number");
				
			launchController.launchDropdownSelection = (int)Math.round ( (Double)arguments[0] );
			return new Object[0];
		}

		if ( method.equals ( "getEnabled" ) )
			return new Object[] { !((Boolean)this.launchController.getDisabled ( 0 )) };

		if ( method.equals ( "setEnabled" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Boolean) )
				throw new Exception("Expected boolean");
				
			this.launchController.setDisabled ( 0, !((Boolean)arguments[0]) );
			return new Object[0];
		}
			
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

		if ( method.equals ( "launch" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Double) )
				throw new Exception("Expected number between 1 and 4");
				
			int launchPad = (int)Math.round ( (Double)arguments[0] );
				
			if ( launchPad < 1 || launchPad > 4 )
				throw new Exception("Expected number between 1 and 4");
				
			return new Object[] { launchPadAccess.launch ( launchPad - 1 ) };
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