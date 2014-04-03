package net.tozcentral.spaceturtles;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.FMLLog;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;

import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityAirLockController;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityUniversalElectrical;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityFuelLoader;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityOxygen;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityOxygenCollector;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityOxygenCompressor;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityOxygenDecompressor;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityOxygenDistributor;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityOxygenDetector;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityOxygenSealer;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityRefinery;

import micdoodle8.mods.galacticraft.mars.tile.GCMarsTileEntityLaunchController;

public final class PeripheralHandler implements IPeripheralProvider {

	@Override
	public IPeripheral getPeripheral ( World world, int x, int y, int z, int side )
	{
		TileEntity tileEntity = world.getBlockTileEntity ( x, y, z );
		
		if ( Loader.isModLoaded ( "GalacticraftMars" ) )
		{
			if ( tileEntity instanceof GCMarsTileEntityLaunchController )
				return new PeripheralLaunchController ( (GCMarsTileEntityLaunchController) tileEntity, world, x, y, z );
		}
		
		if ( tileEntity instanceof GCCoreTileEntityAirLockController )
			return new PeripheralAirLockController ( (GCCoreTileEntityAirLockController) tileEntity, world, x, y, z );
		
		if ( tileEntity instanceof GCCoreTileEntityFuelLoader )
			return new PeripheralFuelLoader ( (GCCoreTileEntityFuelLoader) tileEntity, world, x, y, z );
		
		if ( tileEntity instanceof TileEntityLaunchPadInterface )
			return new PeripheralLaunchPadInterface ( (TileEntityLaunchPadInterface) tileEntity, world, x, y, z );
			 
		if ( tileEntity instanceof GCCoreTileEntityOxygenCollector )
			return new PeripheralOxygenCollector ( (GCCoreTileEntityOxygenCollector) tileEntity, world, x, y, z );
			
		if ( tileEntity instanceof GCCoreTileEntityOxygenCompressor )
			return new PeripheralOxygenCompressor ( (GCCoreTileEntityOxygenCompressor) tileEntity, world, x, y, z );
			
		if ( tileEntity instanceof GCCoreTileEntityOxygenDecompressor )
			return new PeripheralOxygenDecompressor ( (GCCoreTileEntityOxygenDecompressor) tileEntity, world, x, y, z );
			
		if ( tileEntity instanceof GCCoreTileEntityOxygenDistributor )
			return new PeripheralOxygenDistributor ( (GCCoreTileEntityOxygenDistributor) tileEntity, world, x, y, z );
			
		if ( tileEntity instanceof GCCoreTileEntityOxygenDetector )
			return new PeripheralOxygenDetector ( (GCCoreTileEntityOxygenDetector) tileEntity, world, x, y, z );
			
		if ( tileEntity instanceof GCCoreTileEntityOxygenSealer )
			return new PeripheralOxygenSealer ( (GCCoreTileEntityOxygenSealer) tileEntity, world, x, y, z );
			
		if ( tileEntity instanceof GCCoreTileEntityRefinery )
			return new PeripheralRefinery ( (GCCoreTileEntityRefinery) tileEntity, world, x, y, z );
			
		if ( tileEntity instanceof GCCoreTileEntityOxygen )
			return new PeripheralOxygen ( (GCCoreTileEntityOxygen) tileEntity, world, x, y, z );
			
		if ( tileEntity instanceof GCCoreTileEntityUniversalElectrical )
			return new PeripheralElectric ( (GCCoreTileEntityUniversalElectrical) tileEntity, world, x, y, z );

		return null;
	}

}