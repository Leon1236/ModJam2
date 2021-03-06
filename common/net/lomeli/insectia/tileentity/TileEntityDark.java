package net.lomeli.insectia.tileentity;

import java.util.Random;

import net.lomeli.insectia.api.EnumInsectQuartersType;
import net.lomeli.insectia.api.IInsect;
import net.lomeli.insectia.api.ILivingQuarters;
import net.lomeli.insectia.api.EnumInsectQuartersType.EnumInsectQuartersHelper;
import net.lomeli.insectia.blocks.ModBlocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDark extends TileEntity
	implements IInventory, ILivingQuarters{
	
	private ItemStack[] inventory;
	private EnumInsectQuartersType type;
	
	public TileEntityDark(){
		this.inventory = new ItemStack[4];
		this.type = EnumInsectQuartersType.DARK;
	}
	
	private int tick;
	
	private Random rand = new Random();
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound){
		super.readFromNBT(par1NBTTagCompound);
		this.loadNBT(par1NBTTagCompound);
    }
	
	public void loadNBT(NBTTagCompound nbtTag){
		NBTTagList list = nbtTag.getTagList("Inventory");
		for(int index = 0; index < list.tagCount(); ++index){
			NBTTagCompound tag = (NBTTagCompound)list.tagAt(index);
			byte slot = tag.getByte("Slot");
			if(slot >=0 && slot < this.inventory.length)
				this.inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
		}
		
		this.setQuartersType(EnumInsectQuartersHelper.getTypeByID(nbtTag.getInteger("QuarterType")));
	}
	
	public void writeToNBT(NBTTagCompound par1NBTTagCompound){
		super.writeToNBT(par1NBTTagCompound);
		this.addToNBT(par1NBTTagCompound);
	}
	
	public void addToNBT(NBTTagCompound nbtTag){
		NBTTagList list = new NBTTagList();
		for(int index = 0; index < this.inventory.length; ++index){
			if(this.inventory[index] != null){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) index);
				this.inventory[index].writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		nbtTag.setTag("Inventory", list);
		if(this.getQuartersType() != null)
			nbtTag.setInteger("QuarterType", this.getQuartersType().getID());
	}
	
	public void producedItem(IInsect item, int num){
		for(int i = 0; i < num; i++){
			ItemStack randItem = item.getRandomItem();
			for(int j = 1; j < this.inventory.length; j++){
				if(this.isItemValidForSlot(j, randItem)){
					this.setInventorySlotContents(j, randItem);
					break;
				}
			}	
		}
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(!this.worldObj.isRemote){
			if(this.worldObj.getBlockId(xCoord, yCoord + 1, zCoord) == ModBlocks.statusBlock.blockID){
				int l = 0;
				if(this.inventory[0] != null)
					l = this.getInsectLifePercentage() + 1;
				this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord + 1, zCoord, l , 2);
				this.worldObj.markBlockForUpdate(xCoord, yCoord + 1, zCoord);
			}
			if(this.inventory[0] != null && (this.inventory[0].getItem() instanceof IInsect)){
				tick++;
				if(tick >= ((IInsect)this.inventory[0].getItem()).getProductionTime()){
					int j = rand.nextInt(1 + ((IInsect)this.inventory[0].getItem()).getItemsProduced().length);
					producedItem(((IInsect)this.inventory[0].getItem()), j - 1);
					
					if(this.getQuartersType().equals(((IInsect)this.inventory[0].getItem()).getPreferedLivingType()) && rand.nextInt(100) < 50){}
					else
						((IInsect)this.inventory[0].getItem()).hurtBug(this.inventory[0], 1);
					if(this.worldObj.getBlockId(xCoord, yCoord + 1, zCoord) == ModBlocks.statusBlock.blockID){
						this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord + 1, zCoord, this.getInsectLifePercentage() + 1, 2);
						this.worldObj.markBlockForUpdate(xCoord, yCoord + 1, zCoord);
					}
					tick = 0;	
				}
				if(this.inventory[0].getItemDamage() >= this.inventory[0].getMaxDamage())
					this.setInventorySlotContents(0, null);
			}
		}
	}
	
	@Override
	public Packet getDescriptionPacket(){
		Packet132TileEntityData packet = (Packet132TileEntityData)super.getDescriptionPacket();
		NBTTagCompound tag = packet != null ? packet.customParam1 : new NBTTagCompound();
		this.addToNBT(tag);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}
	
	@Override
	public void onDataPacket(INetworkManager networkManager, Packet132TileEntityData packet){
		super.onDataPacket(networkManager, packet);
		NBTTagCompound nbtTag = packet.customParam1;
		this.loadNBT(nbtTag);
	}

	@Override
	public EnumInsectQuartersType getQuartersType() {
		return this.type;
	}

	@Override
	public void setQuartersType(EnumInsectQuartersType type) {
		this.type = type;
	}

	@Override
	public int getSizeInventory() {
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(j < this.inventory[i].stackSize)
			this.inventory[i].stackSize -= j;
		else
			this.inventory[i] = null;
		return this.inventory[i];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return this.inventory[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.inventory[i] = itemstack;
	}

	@Override
	public String getInvName() {
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return false;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(itemstack != null){
			if(this.inventory[i] == null)
				return true;
			if(itemstack.getItem().equals(this.inventory[i].getItem())){
				return true;
			}
		}
		return false;
	}

	@Override
	public int getInsectLifePercentage() {
		double percentage = this.inventory[0].getItemDamage() / (this.inventory[0].getMaxDamage() * 0.3);
		return (int)percentage > 2 ? 2 : (int)percentage;
	}
}
