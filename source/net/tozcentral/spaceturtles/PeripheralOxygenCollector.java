package net.tozcentral.spaceturtles;

import java.util.Arrays;

import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityOxygenCollector;

public class PeripheralOxygenCollector extends PeripheralOxygen implements IPeripheral
{
	public GCCoreTileEntityOxygenCollector oxygenCollector;
	
	public boolean lastActive;

	public PeripheralOxygenCollector ( GCCoreTileEntityOxygenCollector oxygenCollector, World world, int x, int y, int z )
	{
		super ( oxygenCollector, world, x, y, z );
		
		this.oxygenCollector = oxygenCollector;
		
		this.lastActive = this.isActive ( );
		
		this.methodNames.addAll ( Arrays.asList ( new String[] {
			"isActive"
		} ) );
	}
	
	@Override
	public void update ( )
	{
		boolean active = isActive ( );
		
		//SpaceTurtles.logger.info ( "this.lastActive: " + lastActive );
		//SpaceTurtles.logger.info ( "active: " + active );
		
		if ( this.lastActive != active )
		{
			this.queueEvent ( "spaceturtles_active", new Object[] { active } );
			this.lastActive = active;
		}
	}
	
	@Override
	public boolean hasUpdate ( )
	{
		return true;
	}
	
	public boolean isActive ( )
	{
		return ( this.oxygenCollector.getEnergyStored() > 0 && this.oxygenCollector.lastOxygenCollected > 0.0F );
	}

	@Override
	public String getType() {
		return "spaceturtles_oxygen_collector";
	}

	/*@Override
	public String[] getMethodNames ( )
	{
		return new String[] {
			"isActive",
			"getStoredOxygen",
			"getMaxOxygen",
			"getStoredEnergy",
			"getMaxEnergy"
		};
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, int method, Object[] arguments ) throws Exception
	{	
		switch ( method )
		{
			case 0:
				return new Object[] { this.isActive ( ) };
			case 1:
				return new Object[] { this.oxygenCollector.getOxygenStored ( ) };
			case 2:
				return new Object[] { this.oxygenCollector.getMaxOxygenStored ( ) };
			case 3:
				return new Object[] { this.oxygenCollector.getEnergyStored ( ) };
			case 4:
				return new Object[] { this.oxygenCollector.getMaxEnergyStored ( ) };
		}

		return null;
	}*/

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, String method, Object[] arguments ) throws Exception
	{
		if ( method.equals ( "isActive" ) )
			return new Object[] { this.isActive ( ) };
/*
		if ( method.equals ( "getStoredOxygen" ) )
			return new Object[] { this.oxygenCollector.getOxygenStored ( ) };

		if ( method.equals ( "getMaxOxygen" ) )
			return new Object[] { this.oxygenCollector.getMaxOxygenStored ( ) };

		if ( method.equals ( "getStoredEnergy" ) )
			return new Object[] { this.oxygenCollector.getEnergyStored ( ) };

		if ( method.equals ( "getMaxEnergy" ) )
			return new Object[] { this.oxygenCollector.getMaxEnergyStored ( ) };*/

		return super.callMethod ( computer, context, method, arguments );
	}
}