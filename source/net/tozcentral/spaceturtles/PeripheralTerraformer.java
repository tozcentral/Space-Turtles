package net.tozcentral.spaceturtles;

import java.util.Arrays;

import net.minecraft.world.World;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import micdoodle8.mods.galacticraft.mars.tile.GCMarsTileEntityTerraformer;

public class PeripheralTerraformer extends PeripheralElectric implements IPeripheral
{
	public GCMarsTileEntityTerraformer terraformer;

	public PeripheralTerraformer ( GCMarsTileEntityTerraformer terraformer, World world, int x, int y, int z )
	{
		super ( terraformer, world, x, y, z );
		
		this.terraformer = terraformer;
		
		this.methodNames.addAll ( Arrays.asList ( new String[] {
			"isActive",
			"getStoredWater",
			"getMaxWater",
			"getTreesEnabled",
			"setTreesEnabled",
			"getGrassEnabled",
			"setGrassEnabled"
		} ) );
	}

	@Override
	public String getType() {
		return "spaceturtles_refinery";
	}

	@Override
    public Object[] callMethod ( IComputerAccess computer, ILuaContext context, String method, Object[] arguments ) throws Exception
	{
		if ( method.equals ( "isActive" ) )
			return new Object[] { this.terraformer.active };
			
		if ( method.equals ( "getStoredWater" ) )
			return new Object[] { this.terraformer.waterTank.getFluid() == null ? 0 : this.terraformer.waterTank.getFluid ( ).amount };

		if ( method.equals ( "getMaxWater" ) )
			return new Object[] { this.terraformer.waterTank.getCapacity ( ) };
			
		if ( method.equals ( "getTreesEnabled" ) )
			return new Object[] { !((Boolean)this.terraformer.getDisabled ( 0 )) };

		if ( method.equals ( "setTreesEnabled" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Boolean) )
				throw new Exception("Expected boolean");
				
			this.terraformer.setDisabled ( 0, !((Boolean)arguments[0]) );
			return new Object[0];
		}
			
		if ( method.equals ( "getGrassEnabled" ) )
			return new Object[] { !((Boolean)this.terraformer.getDisabled ( 1 )) };

		if ( method.equals ( "setGrassEnabled" ) )
		{
			if ( arguments.length != 1 || !(arguments[0] instanceof Boolean) )
				throw new Exception("Expected boolean");
				
			this.terraformer.setDisabled ( 1, !((Boolean)arguments[0]) );
			return new Object[0];
		}

		return super.callMethod ( computer, context, method, arguments );
	}
}