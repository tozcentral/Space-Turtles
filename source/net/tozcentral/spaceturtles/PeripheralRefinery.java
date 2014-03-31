package net.tozcentral.spaceturtles;

import java.util.Arrays;

import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityRefinery;

public class PeripheralRefinery extends PeripheralElectric implements IPeripheral
{
	public GCCoreTileEntityRefinery refinery;

	public PeripheralRefinery ( GCCoreTileEntityRefinery refinery, World world, int x, int y, int z )
	{
		super ( refinery, world, x, y, z );
		
		this.refinery = refinery;
		
		this.methodNames.addAll ( Arrays.asList ( new String[] {
			"getStoredOil",
			"getMaxOil",
			"getStoredFuel",
			"getMaxFuel",
			"getEnabled",
			"setEnabled"
		} ) );
	}

	@Override
	public String getType() {
		return "spaceturtles_refinery";
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, String method, Object[] arguments ) throws Exception
	{
		if ( method.equals ( "getStoredOil" ) )
			return new Object[] { this.refinery.oilTank.getFluid() == null ? 0 : this.refinery.oilTank.getFluid ( ).amount };

		if ( method.equals ( "getMaxOil" ) )
			return new Object[] { this.refinery.oilTank.getCapacity ( ) };
			
		if ( method.equals ( "getStoredFuel" ) )
			return new Object[] { this.refinery.fuelTank.getFluid() == null ? 0 : this.refinery.fuelTank.getFluid ( ).amount };

		if ( method.equals ( "getMaxFuel" ) )
			return new Object[] { this.refinery.fuelTank.getCapacity ( ) };

		if ( method.equals ( "getEnabled" ) )
			return new Object[] { !((Boolean)this.refinery.getDisabled ( 0 )) };

		if ( method.equals ( "setEnabled" ) )
		{
			this.refinery.setDisabled ( 0, !((Boolean)arguments[0]) );
			return new Object[0];
		}

		return super.callMethod ( computer, context, method, arguments );
	}
}