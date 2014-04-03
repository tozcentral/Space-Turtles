package net.tozcentral.spaceturtles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

import micdoodle8.mods.galacticraft.api.prefab.entity.EntitySpaceshipBase;
import micdoodle8.mods.galacticraft.api.entity.IDockable;
import micdoodle8.mods.galacticraft.api.tile.ILandingPadAttachable;
import micdoodle8.mods.galacticraft.core.tile.GCCoreTileEntityLandingPad;
import micdoodle8.mods.galacticraft.core.tile.TileEntityMulti;

import dan200.computercraft.api.peripheral.IComputerAccess;

public class TileEntityLaunchPadInterface extends TileEntity implements ILandingPadAttachable
{
	public LaunchPadAccess launchPadAccess;
	
	/*public List<IComputerAccess> computers = new ArrayList<IComputerAccess>();
	private EntitySpaceshipBase[] spaceships = new EntitySpaceshipBase[4];
	private int lastLaunchPhase = 0;
	
	public GCCoreTileEntityLandingPad getLaunchPad ( int x, int y, int z )
	{
		TileEntity tileEntity;
		
		tileEntity = this.worldObj.getBlockTileEntity ( x, y, z );
			
		if ( tileEntity != null && tileEntity instanceof TileEntityMulti && ((TileEntityMulti) tileEntity).mainBlockPosition != null )
		{
			tileEntity = ((TileEntityMulti) tileEntity).mainBlockPosition.getTileEntity ( this.worldObj );
			
			if ( tileEntity instanceof GCCoreTileEntityLandingPad )
				return ((GCCoreTileEntityLandingPad)tileEntity);
		}
		
		return null;
	}
	
	public List<GCCoreTileEntityLandingPad> getLaunchPads ( )
	{
		List<GCCoreTileEntityLandingPad> launchPads = new ArrayList<GCCoreTileEntityLandingPad>();
		
		launchPads.add ( this.getLaunchPad ( this.xCoord + 1, this.yCoord, this.zCoord ) );
		
		launchPads.add ( this.getLaunchPad ( this.xCoord - 1, this.yCoord, this.zCoord ) );
		
		launchPads.add ( this.getLaunchPad ( this.xCoord, this.yCoord, this.zCoord + 1 ) );
		
		launchPads.add ( this.getLaunchPad ( this.xCoord, this.yCoord, this.zCoord - 1 ) );
			
		return launchPads;
	}
	
	public int getLaunchPadCount ( )
	{
		return this.getLaunchPads ( ).size ( );
	}
	
	public GCCoreTileEntityLandingPad getLaunchPad ( int index )
	{
		List<GCCoreTileEntityLandingPad> launchPads = this.getLaunchPads ( );
		if ( index < launchPads.size ( ) )
			return launchPads.get ( index );
		
		return null;
	}
	
	public IDockable getDockedEntity ( int launchPad )
	{
		GCCoreTileEntityLandingPad tileEntity;
		tileEntity = this.getLaunchPad ( launchPad );
		
		if ( tileEntity == null )
			return null;
		
		return tileEntity.getDockedEntity ( );
	}
	
	public EntitySpaceshipBase getDockedRocket ( int launchPad )
	{
		IDockable dockable;
		dockable = this.getDockedEntity ( launchPad );
		
		if ( dockable == null )
			return null;
			
		if ( dockable instanceof EntitySpaceshipBase )
			return ((EntitySpaceshipBase)dockable);
		
		return null;
	}
	
	public boolean hasLaunchPad ( )
	{
		return this.getLaunchPads ( ).size ( ) > 0;
	}
	
	public boolean hasLaunchPad ( int launchPad )
	{
		return this.getLaunchPad ( launchPad ) != null;
	}
	
	public boolean hasRocket ( int launchPad )
	{
		return this.getDockedRocket ( launchPad ) != null;
	}

	public int getStoredFuel ( int launchPad )
	{
		EntitySpaceshipBase rocket;
		rocket = this.getDockedRocket ( launchPad );
		
		if ( rocket == null )
			return 0;
			
		return rocket.getScaledFuelLevel ( rocket.getMaxFuel ( ) );
	}
	
	public int getMaxFuel ( int launchPad )
	{
		EntitySpaceshipBase rocket;
		rocket = this.getDockedRocket ( launchPad );
		
		if ( rocket == null )
			return 0;
			
		return rocket.getMaxFuel ( );
	}
	
	public int getLaunchPhase ( int launchPad )
	{
		EntitySpaceshipBase rocket;
		rocket = this.getDockedRocket ( launchPad );
		
		if ( rocket == null )
			return 0;
			
		return rocket.launchPhase;
	}
	
	public int getCountdown ( int launchPad )
	{
		EntitySpaceshipBase rocket;
		rocket = this.getDockedRocket ( launchPad );
		
		if ( rocket == null )
			return 0;
			
		return rocket.timeUntilLaunch;
	}
	
	public int getPreLaunchWait ( int launchPad )
	{
		EntitySpaceshipBase rocket;
		rocket = this.getDockedRocket ( launchPad );
		
		if ( rocket == null )
			return 0;
			
		return rocket.getPreLaunchWait ( );
	}
	
	public boolean getLaunched ( int launchPad )
	{
		EntitySpaceshipBase rocket;
		rocket = this.getDockedRocket ( launchPad );
		
		if ( rocket == null )
			return false;
			
		return rocket.getLaunched ( );
	}
	
	public boolean canBeRidden ( int launchPad )
	{
		EntitySpaceshipBase rocket;
		rocket = this.getDockedRocket ( launchPad );
		
		if ( rocket == null )
			return false;
			
		return rocket.canBeRidden ( );
	}
	/*
	public boolean launch ( int launchPad )
	{
		EntitySpaceshipBase rocket;
		rocket = this.getDockedRocket ( );
		
		if ( rocket == null )
			return false;
			
		rocket.ignite ( );
		return true;
	}*/
	
	public LaunchPadAccess getAccess ( )
	{
		if ( this.launchPadAccess == null )
		{
			this.launchPadAccess = new LaunchPadAccess ( this.worldObj, this.xCoord, this.yCoord, this.zCoord );
			
			this.launchPadAccess.update ( );
		}
		
		return this.launchPadAccess;
	}

	@Override
	public void updateEntity ( )
	{
		this.getAccess ( ).update ( );
	/*
		for ( int i = 0; i < 4; i++ )
		{
			if ( this.spaceships[i] != null && ((Entity)this.spaceships[i] ).isEntityAlive ( ) )
			{	
				if ( this.spaceships[i] .launchPhase != lastLaunchPhase )
				{
					for ( IComputerAccess computer : computers )
						computer.queueEvent ( "spaceturtles_launch", new Object[] { computer.getAttachmentName ( ), i + 1, this.spaceships[i] .launchPhase } );
					
					lastLaunchPhase = this.spaceships[i] .launchPhase;
					
					this.spaceships[i] = this.getDockedRocket ( i );
				}
			}
			else
				this.spaceships[i] = this.getDockedRocket ( i );
		}*/
	}
/*
	public void addComputer(IComputerAccess computer)
	{
		if ( !this.computers.contains ( computer ) )
			this.computers.add ( computer );
	}

	public void removeComputer ( IComputerAccess computer )
	{
		if ( this.computers.contains ( computer ) )
			this.computers.remove ( computer );
	}*/
	
	public boolean canAttachToLandingPad ( IBlockAccess world, int x, int y, int z )
	{
		return true;
	}
}