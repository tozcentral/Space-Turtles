package net.tozcentral.spaceturtles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;

public class BlockLaunchPadInterface extends Block implements ITileEntityProvider
{
	private Icon iconTopBottom;
	private Icon iconSide;
	
	public BlockLaunchPadInterface ( int id, Material material )
	{
		super ( id, material );
	}
	
	public TileEntity createNewTileEntity ( World world )
	{
		return new TileEntityLaunchPadInterface ( );
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons ( IconRegister iconRegister )
	{
		this.iconTopBottom = iconRegister.registerIcon ( GalacticraftCore.ASSET_PREFIX + "machine_blank" );
		this.iconSide = iconRegister.registerIcon ( SpaceTurtles.ASSET_PREFIX + "interface_side" );
	}

	@Override
	public Icon getIcon ( int side, int metadata )
	{
		if ( side == 0 || side == 1 )
		{
			return this.iconTopBottom;
		}
		else
		{
			return this.iconSide;
		}
	}
}