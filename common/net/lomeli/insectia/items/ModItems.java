package net.lomeli.insectia.items;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.lomeli.insectia.api.EnumNetType;
import net.lomeli.insectia.api.EnumInsectQuartersType;
import net.lomeli.insectia.api.InsectRegistry;
import net.lomeli.insectia.api.InsectiaItems;
import net.lomeli.insectia.api.EnumNetType.EnumNetTypeHelper;
import net.lomeli.insectia.items.bugs.ItemBWidow;
import net.lomeli.insectia.items.bugs.ItemFireAnts;
import net.lomeli.insectia.items.bugs.ItemPinkWorm;
import net.lomeli.insectia.items.bugs.ItemRedWorms;
import net.lomeli.insectia.lib.ItemIDs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModItems {

	/* Spiders */
	public static Item bRecluseSpider, longLegSpider, bWidowSpider;
	
	/* Ants */
	public static Item fireAnts, armyAnts;
	
	/* Silk Worms */
	public static Item bivoltineWorm, polyvoltineWorm, univoltineWorm;
	
	/* Other */
	public static Item dungBeetles;
	
	/* Item pieces */
	
	//Spiders
	public static Item stickyString, trappedBug;
	public static ItemStack[] spiderProduction;
	
	//Ants
	public static Item leaf, appleBit, cookieCrumb, breadCrumb, steakPiece;
	public static ItemStack[] antProduction;
	
	//Silk Worms
	public static Item silk, woolFluff;
	public static ItemStack[] silkProduction;
	
	//Dung Beetles
	public static Item dung, fertilizer;
	public static ItemStack[] beetleProduction;
	
	/* Tools */
	public static Item webNet, dirtNet, sandNet, leafNet;
	
	/* Other Items */
	public static Item treatedStick;
	
	public static void loadItems(){
		//Produced Items
		stickyString = new ItemGeneric(ItemIDs.stickyStringID, "stickysilk").setUnlocalizedName("stickyString");
		trappedBug = new ItemTrappedBug(ItemIDs.trappedBugID, "trappedBug").setUnlocalizedName("trappedBug");
		leaf = new ItemGeneric(ItemIDs.leafID, "leaf").setUnlocalizedName("leaf");
		appleBit = new ItemGeneric(ItemIDs.appleBitID, "apple").setUnlocalizedName("applebit");
		cookieCrumb = new ItemGeneric(ItemIDs.cookieCrumbID, "cookie").setUnlocalizedName("cookieCrum");
		breadCrumb = new ItemGeneric(ItemIDs.breadCrumbID, "bread").setUnlocalizedName("breadCrum");
		steakPiece = new ItemGeneric(ItemIDs.steakPieceID, "steak").setUnlocalizedName("steakPiece");
		silk = new ItemGeneric(ItemIDs.silkID, "silk").setUnlocalizedName("cleanSilk");
		woolFluff = new ItemGeneric(ItemIDs.woolFluffID, "wool").setUnlocalizedName("woolFluff");
		dung = new ItemGeneric(ItemIDs.dungID, "dung").setUnlocalizedName("dungball");
		fertilizer = new ItemGeneric(ItemIDs.fertilizerID, "fertilizer").setUnlocalizedName("fertilizer");
		treatedStick = new ItemGeneric(ItemIDs.treatedStickID, "treatedStick").setUnlocalizedName("treatedStick");
		
		spiderProduction = new ItemStack[]{ new ItemStack(stickyString), 
				new ItemStack(trappedBug) };
		antProduction = new ItemStack[]{new ItemStack(leaf), new ItemStack(appleBit), 
				new ItemStack(cookieCrumb), new ItemStack(breadCrumb), new ItemStack(steakPiece)};
		silkProduction = new ItemStack[]{new ItemStack(silk), new ItemStack(woolFluff)};
		beetleProduction = new ItemStack[]{new ItemStack(dung), new ItemStack(fertilizer)};
		
		//Spiders
		bRecluseSpider = new ItemBugs(ItemIDs.bRecluseSpiderID, "bugs/bRecluseSpider", 
			spiderProduction, 5, 150, EnumInsectQuartersType.DARK, 3).setUnlocalizedName("bRecluseSpider");
		longLegSpider = new ItemBugs(ItemIDs.longLegSpiderID, "bugs/longLegSpider", 
			spiderProduction, 5, 130, EnumInsectQuartersType.DARK, 4).setUnlocalizedName("longLegSpider");
		bWidowSpider = new ItemBWidow(ItemIDs.bWidowSpiderID, "bugs/bWidowSpider", 
			spiderProduction, 5, 175, EnumInsectQuartersType.DARK, 3).setUnlocalizedName("bWidownSpider");
		
		//Ants
		fireAnts = new ItemFireAnts(ItemIDs.fireAntsID, "bugs/fireAnts", 
			antProduction, 60, 90, EnumInsectQuartersType.SWEET, 7).setUnlocalizedName("fireAnts");
		armyAnts = new ItemBugs(ItemIDs.armyAntsID, "bugs/armyAnts", 
			antProduction, 20, 75, EnumInsectQuartersType.SWEET, 10).setUnlocalizedName("armyAnts");
		
		//Silk Worms
		bivoltineWorm = new ItemBugs(ItemIDs.bivoltineWormID, "bugs/bivoltineWorm", 
			silkProduction, 25, 125, EnumInsectQuartersType.GREEN, 10).setUnlocalizedName("greenSilkWorm");
		polyvoltineWorm = new ItemRedWorms(ItemIDs.polyvoltineWormID, "bugs/polyvoltineWorm", silkProduction, 40, 60,
			EnumInsectQuartersType.GREEN, 10).setUnlocalizedName("redSilkWorm");
		univoltineWorm = new ItemPinkWorm(ItemIDs.univoltineWormID, "bugs/univoltineWorm",
			silkProduction, 25, 50, EnumInsectQuartersType.GREEN, 4).setUnlocalizedName("pinkSilkWorm");
		
		//Dung Beetle
		dungBeetles = new ItemBugs(ItemIDs.dungBeetlesID, "bugs/dungBeetles", 
			beetleProduction, 40, 60, EnumInsectQuartersType.SMELLY, 15).setUnlocalizedName("dungBeetle");
		
		InsectiaItems.bRecluseSpider = new ItemStack(bRecluseSpider);
		InsectiaItems.longLegSpider = new ItemStack(longLegSpider);
		InsectiaItems.bWidowSpider = new ItemStack(bWidowSpider);
		InsectiaItems.fireAnts = new ItemStack(fireAnts);
		InsectiaItems.armyAnts = new ItemStack(armyAnts);
		InsectiaItems.bivoltineWorm = new ItemStack(bivoltineWorm);
		InsectiaItems.polyvoltineWorm = new ItemStack(polyvoltineWorm);
		InsectiaItems.univoltineWorm = new ItemStack(univoltineWorm);
		InsectiaItems.dungBeetles = new ItemStack(dungBeetles);
		
		//Tools
		webNet = new ItemBugNet(ItemIDs.webNetID, "tools/webnet", EnumNetType.WEB).setUnlocalizedName("spiderNet");
		dirtNet = new ItemBugNet(ItemIDs.dirtNetID, "tools/dirtnet", EnumNetType.DIRT).setUnlocalizedName("scoope");
		sandNet = new ItemBugNet(ItemIDs.sandNetID, "tools/sandnet", EnumNetType.SAND).setUnlocalizedName("shifter");
		leafNet = new ItemBugNet(ItemIDs.leafNetID, "tools/leafnet", EnumNetType.LEAVES)
			.setUnlocalizedName("bugcatcher");
		
		registerItems();
	}
	
	public static void registerItems(){
		LanguageRegistry.addName(webNet, "Spider Net");
		LanguageRegistry.addName(dirtNet, "Scoope");
		LanguageRegistry.addName(sandNet, "Sand Shifter");
		LanguageRegistry.addName(leafNet, "Bug Catcher");//I can't come up with a better name....
		
		LanguageRegistry.addName(stickyString, "Spider Silk");
		LanguageRegistry.addName(trappedBug, "Trapped Bug");
		LanguageRegistry.addName(leaf, "Leaf");
		LanguageRegistry.addName(appleBit, "Apple Slice");
		LanguageRegistry.addName(cookieCrumb, "Cookie Crumb");
		LanguageRegistry.addName(breadCrumb, "Bread Crumb");
		LanguageRegistry.addName(steakPiece, "Piece of Steak");
		LanguageRegistry.addName(silk, "Clean Silk");
		LanguageRegistry.addName(woolFluff, "Wool Fluff");
		LanguageRegistry.addName(dung, "Ball of Dung");
		LanguageRegistry.addName(fertilizer, "Fertilizer (BETA - Do not use!)");
		LanguageRegistry.addName(treatedStick, "Treated Stick");
		
		LanguageRegistry.addName(bRecluseSpider, "Brown Reclusive Spider");
		LanguageRegistry.addName(longLegSpider, "Daddy Long Leg Spider");
		LanguageRegistry.addName(bWidowSpider, "Black Widow Spider");
		
		LanguageRegistry.addName(fireAnts, "Fire Ants");
		LanguageRegistry.addName(armyAnts, "Army Ants");
		
		LanguageRegistry.addName(bivoltineWorm, "Bivoltine Silk Worm");
		LanguageRegistry.addName(polyvoltineWorm, "Polyvoltine Silk Worm");
		LanguageRegistry.addName(univoltineWorm, "Univoltine Silk Worm");
		
		LanguageRegistry.addName(dungBeetles, "Dung Beetle");
		
		InsectiaItems.stickyString = new ItemStack(stickyString);
		InsectiaItems.trappedBug = new ItemStack(trappedBug);
		InsectiaItems.leaf = new ItemStack(leaf);
		InsectiaItems.appleBit = new ItemStack(appleBit);
		InsectiaItems.cookieCrumb = new ItemStack(cookieCrumb);
		InsectiaItems.breadCrumb = new ItemStack(breadCrumb);
		InsectiaItems.steakPiece = new ItemStack(steakPiece);
		InsectiaItems.silk = new ItemStack(silk);
		InsectiaItems.woolFluff = new ItemStack(woolFluff);
		InsectiaItems.dung = new ItemStack(dung);
		InsectiaItems.fertilizer = new ItemStack(fertilizer);
		InsectiaItems.treatedStick = new ItemStack(treatedStick);
		
		InsectiaItems.webNet = new ItemStack(webNet);
		InsectiaItems.sandNet = new ItemStack(sandNet);
		InsectiaItems.leafNet = new ItemStack(leafNet);
		InsectiaItems.dirtNet = new ItemStack(dirtNet);
		
		InsectRegistry.getInstance().registerInsect(bRecluseSpider, false);
		InsectRegistry.getInstance().registerInsect(longLegSpider, false);
		InsectRegistry.getInstance().registerInsect(bWidowSpider, false);
		InsectRegistry.getInstance().registerInsect(fireAnts, true);
		InsectRegistry.getInstance().registerInsect(armyAnts, true);
		InsectRegistry.getInstance().registerInsect(bivoltineWorm, true);
		InsectRegistry.getInstance().registerInsect(dungBeetles, true);
		InsectRegistry.getInstance().registerInsect(polyvoltineWorm, true);
		InsectRegistry.getInstance().registerInsect(univoltineWorm, false);
	}
}
