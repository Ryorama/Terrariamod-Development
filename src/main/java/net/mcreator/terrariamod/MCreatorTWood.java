package net.mcreator.terrariamod;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

@Elementsterrariamod.ModElement.Tag
public class MCreatorTWood extends Elementsterrariamod.ModElement {
	@ObjectHolder("terrariamod:twood")
	public static final Item block = null;

	public MCreatorTWood(Elementsterrariamod instance) {
		super(instance, 17);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(MCreatorTerrariamod.tab).maxStackSize(64));
			setRegistryName("twood");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
