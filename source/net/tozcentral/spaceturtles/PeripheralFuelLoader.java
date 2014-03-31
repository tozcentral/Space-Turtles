package net.tozcentral.spaceturtles;

import java.util.Arrays;

import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityFuelLoader;

public class PeripheralFuelLoader extends PeripheralElectric implements IPeripheral
{
	public GCCoreTileEntityFuelLoader fuelLoader;

	public PeripheralFuelLoader ( GCCoreTileEntityFuelLoader fuelLoader, World world, int x, int y, int z )
	{
		super ( fuelLoader, world, x, y, z );
		
		this.fuelLoader = fuelLoader;
		
		this.methodNames.addAll ( Arrays.asList ( new String[] {
			"getStoredFuel",
			"getMaxFuel",
			"getEnabled",
			"setEnabled"
		} ) );
	}

	@Override
	public String getType() {
		return "spaceturtles_fuel_loader";
	}
/*
	@Override
	public String[] getMethodNames ( )
	{
		return new String[] {
			"getStoredEnergy",
			"getMaxEnergy",
			"getStoredFuel",
			"getMaxFuel",
			"getEnabled",
			"setEnabled"
		};
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, int method, Object[] arguments ) throws Exception
	{	
		switch ( method )
		{
			case 2:
				return new Object[] { this.fuelLoader.fuelTank.getFluid() == null ? 0 : this.fuelLoader.fuelTank.getFluid ( ).amount };
			case 3:
				return new Object[] { this.fuelLoader.fuelTank.getCapacity ( ) };
			case 4:
				return new Object[] { !((Boolean)this.fuelLoader.getDisabled ( 0 )) };
			case 5:
				if ( this.fuelLoader.disableCooldown == 0 && !(this.fuelLoader.fuelTank.getFluid() == null || this.fuelLoader.fuelTank.getFluid().amount == 0) )
					this.fuelLoader.setDisabled ( 0, !((Boolean)arguments[0]) );
				return null;
		}

		return null;
	}*/

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, String method, Object[] arguments ) throws Exception
	{
		/*if ( method.equals ( "getStoredEnergy" ) )
			return new Object[] { this.fuelLoader.getEnergyStored ( ) };

		if ( method.equals ( "getMaxEnergy" ) )
			return new Object[] { this.fuelLoader.getMaxEnergyStored ( ) };*/

		if ( method.equals ( "getStoredFuel" ) )
			return new Object[] { this.fuelLoader.fuelTank.getFluid() == null ? 0 : this.fuelLoader.fuelTank.getFluid ( ).amount };

		if ( method.equals ( "getMaxFuel" ) )
			return new Object[] { this.fuelLoader.fuelTank.getCapacity ( ) };

		if ( method.equals ( "getEnabled" ) )
			return new Object[] { !((Boolean)this.fuelLoader.getDisabled ( 0 )) };

		if ( method.equals ( "setEnabled" ) )
		{
			if ( this.fuelLoader.disableCooldown == 0 && !(this.fuelLoader.fuelTank.getFluid() == null || this.fuelLoader.fuelTank.getFluid().amount == 0) )
				this.fuelLoader.setDisabled ( 0, !((Boolean)arguments[0]) );
			return new Object[0];
		}

		return super.callMethod ( computer, context, method, arguments );
	}
}