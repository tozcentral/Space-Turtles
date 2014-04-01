package net.tozcentral.spaceturtles;

import java.util.Arrays;

import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityAirLockController;

public class PeripheralAirLockController extends PeripheralTileEntity implements IPeripheral
{
	GCCoreTileEntityAirLockController airLockController;
	
	boolean lastSealed;

	public PeripheralAirLockController ( GCCoreTileEntityAirLockController airLockController, World world, int x, int y, int z )
	{
		super ( airLockController, world, x, y, z );
		
		this.airLockController = airLockController;
		
		this.lastSealed = isSealed ( );
			
		this.methodNames.addAll ( Arrays.asList ( new String[] {
			"isAirLockSealed", "sealAirLock", "unsealAirLock",
			"getOwnerName",
			"getRedstoneActivation", "setRedstoneActivation",
			"getPlayerActivation", "setPlayerActivation",
			"getPlayerDistance", "setPlayerDistance",
			"getPlayer", "setPlayer",
			"getInvertSelection", "setInvertSelection",
			"getHorizontal", "setHorizontal"
		} ) );
	}
	
	@Override
	public void update ( )
	{
		boolean sealed = isSealed ( );
		
		if ( this.lastSealed != sealed )
		{
			this.queueEvent ( "spaceturtles_air_lock_sealed", new Object[] { sealed } );
			this.lastSealed = sealed;
		}
	}
	
	@Override
	public boolean hasUpdate ( )
	{
		return true;
	}
	
	public boolean isSealed ( )
	{
		return airLockController.active;
	}

	@Override
	public String getType() {
		return "spaceturtles_air_lock_controller";
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, String method, Object[] arguments ) throws Exception
	{	
		// Lock
		if ( method.equals ( "isAirLockSealed" ) )
			return new Object[] { isSealed ( ) };
			
		if ( method.equals ( "sealAirLock" ) )
		{
			airLockController.sealAirLock ( );
			return new Object[0];
		}
			
		if ( method.equals ( "unsealAirLock" ) )
		{
			airLockController.unsealAirLock ( );
			return new Object[0];
		}
				
		// Owner name
		if ( method.equals ( "getOwnerName" ) )
			return new Object[] { airLockController.ownerName };
				
		// Redstone activation
		if ( method.equals ( "getRedstoneActivation" ) )
			return new Object[] { airLockController.redstoneActivation };
		if ( method.equals ( "setRedstoneActivation" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Boolean) )
				throw new Exception("Expected boolean");
				
			airLockController.redstoneActivation = (Boolean) arguments[0];
			return new Object[0];
		}
			
		// Player distance detection
		if ( method.equals ( "getPlayerActivation" ) )
			return new Object[] { airLockController.playerDistanceActivation };
		if ( method.equals ( "setPlayerActivation" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Boolean) )
				throw new Exception("Expected boolean");
				
			airLockController.playerDistanceActivation = (Boolean) arguments[0];
			return new Object[0];
		}
		if ( method.equals ( "getPlayerDistance" ) )
			return new Object[] { airLockController.playerDistanceSelection };
		if ( method.equals ( "setPlayerDistance" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Integer) )
				throw new Exception("Expected number");
				
			airLockController.playerDistanceSelection = (Integer) arguments[0];
			return new Object[0];
		}
		if ( method.equals ( "getPlayer" ) )
			return new Object[] { airLockController.playerToOpenFor };
		if ( method.equals ( "setPlayer" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof String) )
				throw new Exception("Expected string");
				
			airLockController.playerToOpenFor = (String) arguments[0];
			return new Object[0];
		}
		
		// Invert selection
		if ( method.equals ( "getInvertSelection" ) )
			return new Object[] { airLockController.invertSelection };
		if ( method.equals ( "setInvertSelection" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Boolean) )
				throw new Exception("Expected boolean");
				
			airLockController.invertSelection = (Boolean) arguments[0];
			return new Object[0];
		}
			
		// Horizontal mode
		if ( method.equals ( "getHorizontal" ) )
			return new Object[] { airLockController.horizontalModeEnabled };
		if ( method.equals ( "setHorizontal" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Boolean) )
				throw new Exception("Expected boolean");
				
			airLockController.horizontalModeEnabled = (Boolean) arguments[0];
			return new Object[0];
		}

		return super.callMethod ( computer, context, method, arguments );
	}
}