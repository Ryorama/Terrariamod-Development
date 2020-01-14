package net.mcreator.terrariamod;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

@Elementsterrariamod.ModElement.Tag
public class MCreatorEnchantedSwor extends Elementsterrariamod.ModElement {
	@ObjectHolder("terrariamod:enchantedswor")
	public static final Item block = null;

	public MCreatorEnchantedSwor(Elementsterrariamod instance) {
		super(instance, 98);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 50000;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 21f;
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
		}, 3, -3F, new Item.Properties().group(MCreatorTerrariamod.tab)) {
		}.setRegistryName("enchantedswor"));
	}
}
