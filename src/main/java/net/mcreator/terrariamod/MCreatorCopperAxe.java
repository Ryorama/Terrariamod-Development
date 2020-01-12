package net.mcreator.terrariamod;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.AxeItem;

@Elementsterrariamod.ModElement.Tag
public class MCreatorCopperAxe extends Elementsterrariamod.ModElement {
	@ObjectHolder("terrariamod:copperaxe")
	public static final Item block = null;

	public MCreatorCopperAxe(Elementsterrariamod instance) {
		super(instance, 8);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 100;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 3f;
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
		}, 1, -1.2F, new Item.Properties().group(MCreatorTerrariamod.tab)) {
		}.setRegistryName("copperaxe"));
	}
}
