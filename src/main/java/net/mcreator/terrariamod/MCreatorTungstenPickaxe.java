package net.mcreator.terrariamod;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

@Elementsterrariamod.ModElement.Tag
public class MCreatorTungstenPickaxe extends Elementsterrariamod.ModElement {
	@ObjectHolder("terrariamod:tungstenpickaxe")
	public static final Item block = null;

	public MCreatorTungstenPickaxe(Elementsterrariamod instance) {
		super(instance, 88);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 400;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return -0.5f;
			}

			public int getHarvestLevel() {
				return 4;
			}

			public int getEnchantability() {
				return 2;
			}

			public Ingredient getRepairMaterial() {
				return null;
			}
		}, 1, -1.2F, new Item.Properties().group(MCreatorTerrariamod.tab)) {
		}.setRegistryName("tungstenpickaxe"));
	}
}
