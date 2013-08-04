package net.lomeli.insectia.items.bugs;

import java.util.Random;

import net.lomeli.insectia.api.EnumInsectQuartersType;
import net.lomeli.insectia.items.ItemBugs;
import net.lomeli.insectia.lib.ModInts;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemPinkWorm extends ItemBugs{

	public ItemPinkWorm(int par1, String texture, ItemStack[] producedItems,
			int dropChance, int time, EnumInsectQuartersType quartersType,
			int lifeSpan) {
		super(par1, texture, producedItems, dropChance, time, quartersType, lifeSpan);
	}
	
	private int updateTick;
	private Random rand = new Random();
	
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5){
		if(world != null && entity instanceof EntityPlayer){
			updateTick++;
			if(updateTick >= 30){
				int roll = rand.nextInt(100);
				if(roll < ModInts.chanceOfBite){
					((EntityPlayer)entity).addPotionEffect(new PotionEffect(Potion.heal.id, 4000));
				}
				updateTick = 0;
			}
		}
	}

}