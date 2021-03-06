package net.lomeli.insectia.items;

import java.util.Random;

import net.lomeli.insectia.Insectia;
import net.lomeli.insectia.lib.ModStrings;
import net.lomeli.insectia.api.EnumNetType;
import net.lomeli.insectia.api.IInsect;
import net.lomeli.insectia.api.IBugNet;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ItemBugNet extends ItemTool implements IBugNet{
	
	private Random rand = new Random();
	private String itemTexture;
	private EnumNetType netType;
	
	public ItemBugNet(int par1, String texture, EnumNetType type) {
		super(par1, 0F, EnumToolMaterial.WOOD, type.getBlocks());
		itemTexture = texture;
		netType = type;
		this.setCreativeTab(Insectia.modTab);
		this.setMaxDamage(10);
	}

	@Override
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(ModStrings.MOD_ID.toLowerCase() + ":" + itemTexture);
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack itemStack, World world, 
			int blockID, int x, int y, int z, EntityLivingBase enity){
		if(!world.isRemote){
			for(Block destroyable : getNetType().getBlocks()){
				if(blockID == destroyable.blockID){
					int k = rand.nextInt(300);
					for(Item drops : getNetType().getDrops()){
						if(drops instanceof IInsect){
							boolean canDrop = false;
							for(BiomeGenBase biomes : ((IInsect)drops).getPreferedBiome()){
								if(world.getBiomeGenForCoords(x, z).biomeID == biomes.biomeID){
									canDrop = true;
									break;
								}
							}
							if(canDrop && k < ((IInsect)drops).getDropChance()){
								EntityItem item = new EntityItem(world, x, y, z, new ItemStack(drops, 1));
								world.spawnEntityInWorld(item);
							}
						}
					}
				}
			}
			itemStack.damageItem(1, enity);
		}
		return true;
    }

	@Override
	public EnumNetType getNetType() {
		return this.netType;
	}
}
