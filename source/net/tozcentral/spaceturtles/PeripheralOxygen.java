package net.tozcentral.spaceturtles;

import java.util.Arrays;

import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityOxygen;

public class PeripheralOxygen extends PeripheralTileEntity implements IPeripheral
{
	GCCoreTileEntityOxygen oxygen;

	public PeripheralOxygen ( GCCoreTileEntityOxygen oxygen, World world, int x, int y, int z )
	{
		super ( oxygen, world, x, y, z );
		
		this.oxygen = oxygen;
		
		this.methodNames.addAll ( Arrays.asList ( new String[] {
			"getStoredOxygen",
			"getMaxOxygen",
		} ) );
		
		if ( this.oxygen.getMaxEnergyStored ( ) > 0 )
		{
			this.methodNames.addAll ( Arrays.asList ( new String[] {
				"getStoredEnergy",
				"getMaxEnergy"
			} ) );
		}
	}

	@Override
	public String getType() {
		return "spaceturtles_oxygen";
	}
/*
	@Override
	public String[] getMethodNames ( )
	{
		if ( this.oxygen.getMaxEnergyStored ( ) == 0 )
		{
			return new String[] {
				"getStoredOxygen",
				"getMaxOxygen"
			};
		}
		else
		{
			return new String[] {
				"getStoredOxygen",
				"getMaxOxygen",
				"getStoredEnergy",
				"getMaxEnergy"
			};
		}
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, int method, Object[] arguments ) throws Exception
	{	
		switch ( method )
		{
			case 0:
				return new Object[] { this.oxygen.getOxygenStored ( ) };
			case 1:
				return new Object[] { this.oxygen.getMaxOxygenStored ( ) };
			case 2:
				return new Object[] { this.oxygen.getEnergyStored ( ) };
			case 3:
				return new Object[] { this.oxygen.getMaxEnergyStored ( ) };
		}

		return null;
	}*/

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, String method, Object[] arguments ) throws Exception
	{
		if ( method.equals ( "getStoredOxygen" ) )
			return new Object[] { this.oxygen.getOxygenStored ( ) };

		if ( method.equals ( "getMaxOxygen" ) )
			return new Object[] { this.oxygen.getMaxOxygenStored ( ) };
			
		if ( method.equals ( "getStoredEnergy" ) )
			return new Object[] { this.oxygen.getEnergyStored ( ) };

		if ( method.equals ( "getMaxEnergy" ) )
			return new Object[] { this.oxygen.getMaxEnergyStored ( ) };

		return super.callMethod ( computer, context, method, arguments );
	}
}