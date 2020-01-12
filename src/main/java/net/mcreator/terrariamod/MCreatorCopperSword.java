package net.mcreator.terrariamod;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

@Elementsterrariamod.ModElement.Tag
public class MCreatorCopperSword extends Elementsterrariamod.ModElement {
	@ObjectHolder("terrariamod:coppersword")
	public static final Item block = null;

	public MCreatorCopperSword(Elementsterrariamod instance) {
		super(instance, 9);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 100;
			}

			public float getEfficiency() {
				return 8f;
			}

			public float getAttackDamage() {
				return 7f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 2;
			}

			public Ingredient getRepairMaterial() {
				return null;
			}
		}, 3, -1.2F, new Item.Properties().group(MCreatorTerrariamod.tab)) {
		}.setRegistryName("coppersword"));
	}
}
