package net.mcreator.terrariamod;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.BlockState;

import java.util.Random;
import java.util.EnumSet;

@Elementsterrariamod.ModElement.Tag
public class MCreatorSkelletron extends Elementsterrariamod.ModElement {
	public static EntityType entity = null;

	public MCreatorSkelletron(Elementsterrariamod instance) {
		super(instance, 42);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity> create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("skelletron")
				.setRegistryName("skelletron");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -6710887, -16777216, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("skelletron"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelskelletron(), 0.5f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("terrariamod:textures/skelletron.png");
				}
			};
		});
	}

	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 20;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			this.goalSelector.addGoal(1, new Goal() {
				{
					this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
				}

				@Override
				public boolean shouldExecute() {
					MovementController movecontroller = CustomEntity.this.getMoveHelper();
					if (!movecontroller.isUpdating()) {
						return true;
					} else {
						double dx = movecontroller.getX() - CustomEntity.this.posX;
						double dy = movecontroller.getY() - CustomEntity.this.posY;
						double dz = movecontroller.getZ() - CustomEntity.this.posZ;
						double d = dx * dx + dy * dy + dz * dz;
						return d < 1 || d > 3600;
					}
				}

				@Override
				public boolean shouldContinueExecuting() {
					return false;
				}

				@Override
				public void startExecuting() {
					Random random = CustomEntity.this.getRNG();
					double dir_x = CustomEntity.this.posX + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = CustomEntity.this.posY + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = CustomEntity.this.posZ + ((random.nextFloat() * 2 - 1) * 16);
					CustomEntity.this.getMoveHelper().setMoveTo(dir_x, dir_y, dir_z, 1);
				}
			});
			this.moveController = new MovementController(this) {
				private int patchChangeTimer;

				@Override
				public void tick() {
					if (this.action == MovementController.Action.MOVE_TO) {
						double dx = this.posX - CustomEntity.this.posX;
						double dy = this.posY - CustomEntity.this.posY;
						double dz = this.posZ - CustomEntity.this.posZ;
						double d = dx * dx + dy * dy + dz * dz;
						if (this.patchChangeTimer-- <= 0) {
							this.patchChangeTimer += CustomEntity.this.getRNG().nextInt(5) + 2;
							d = (double) MathHelper.sqrt(d);
							if (this.checkCollision(this.posX, this.posY, this.posZ, d)) {
								CustomEntity.this.moveRelative(1, new Vec3d(dx / d * 0.1, dy / d * 0.1, dz / d * 0.1));
							} else {
								this.action = MovementController.Action.WAIT;
							}
						}
					}
				}

				private boolean checkCollision(double x, double y, double z, double par) {
					double dx = (x - CustomEntity.this.posX) / par;
					double dy = (y - CustomEntity.this.posY) / par;
					double dz = (z - CustomEntity.this.posZ) / par;
					AxisAlignedBB axisalignedbb = CustomEntity.this.getBoundingBox();
					for (int i = 1; (double) i < par; ++i) {
						axisalignedbb = axisalignedbb.offset(dx, dy, dz);
						if (!CustomEntity.this.world.getEntitiesWithinAABBExcludingEntity(CustomEntity.this, axisalignedbb).isEmpty())
							return false;
					}
					return true;
				}
			};
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(4, new SwimGoal(this));
			this.goalSelector.addGoal(5, new LeapAtTargetGoal(this, (float) 0.8));
			this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.2, false));
			this.targetSelector.addGoal(7, new HurtByTargetGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public void fall(float l, float d) {
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(17);
		}

		@Override
		public void travel(Vec3d dir) {
			if (this.isInWater()) {
				this.moveRelative(0.02f, dir);
				this.move(MoverType.SELF, this.getMotion());
				this.setMotion(this.getMotion().scale((double) 0.8f));
				return;
			}
			if (this.isInLava()) {
				this.moveRelative(0.02f, dir);
				this.move(MoverType.SELF, this.getMotion());
				this.setMotion(this.getMotion().scale((double) 0.5f));
				return;
			}
			BlockPos ground = new BlockPos(this.posX, this.getBoundingBox().minY - 1, this.posZ);
			float f = 0.91f;
			if (this.onGround)
				f = this.world.getBlockState(ground).getSlipperiness(world, ground, this) * 0.91f;
			float f1 = 0.16f / (f * f * f);
			f = 0.91f;
			if (this.onGround)
				f = this.world.getBlockState(ground).getSlipperiness(world, ground, this) * 0.91f;
			this.moveRelative(this.onGround ? 0.1f * f1 : 0.02f, dir);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale((double) f));
			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.posX - this.prevPosX;
			double d0 = this.posZ - this.prevPosZ;
			float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
			if (f2 > 1)
				f2 = 1;
			this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}
	}

	// Made with Blockbench
	// Paste this code into your mod.
	public static class Modelskelletron extends EntityModel<Entity> {
		private final RendererModel bone;
		private final RendererModel bone2;
		private final RendererModel bone3;

		public Modelskelletron() {
			textureWidth = 32;
			textureHeight = 32;
			bone = new RendererModel(this);
			bone.setRotationPoint(0.0F, 24.0F, 0.0F);
			bone.cubeList.add(new ModelBox(bone, 0, 0, -5.0F, -20.0F, -5.0F, 10, 10, 10, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 0, 5.0F, -16.0F, -1.0F, 15, 2, 2, 0.0F, false));
			bone.cubeList.add(new ModelBox(bone, 0, 0, -19.0F, -16.0F, -1.0F, 15, 2, 2, 0.0F, false));
			bone2 = new RendererModel(this);
			bone2.setRotationPoint(0.0F, 24.0F, 0.0F);
			bone2.cubeList.add(new ModelBox(bone2, 0, 0, 20.0F, -17.0F, 0.0F, 4, 4, 0, 0.0F, false));
			bone2.cubeList.add(new ModelBox(bone2, 0, 0, 20.0F, -20.0F, 0.0F, 1, 3, 0, 0.0F, false));
			bone2.cubeList.add(new ModelBox(bone2, 0, 0, 22.0F, -19.0F, 0.0F, 1, 2, 0, 0.0F, false));
			bone2.cubeList.add(new ModelBox(bone2, 0, 0, 25.0F, -18.0F, 0.0F, -1, 1, 0, 0.0F, false));
			bone3 = new RendererModel(this);
			bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
			bone3.cubeList.add(new ModelBox(bone3, 0, 0, -23.0F, -18.0F, 0.0F, 4, 4, 0, 0.0F, false));
			bone3.cubeList.add(new ModelBox(bone3, 0, 0, -20.0F, -21.0F, 0.0F, 1, 3, 0, 0.0F, false));
			bone3.cubeList.add(new ModelBox(bone3, 0, 0, -22.0F, -20.0F, 0.0F, 1, 2, 0, 0.0F, false));
			bone3.cubeList.add(new ModelBox(bone3, 0, 0, -23.0F, -19.0F, 0.0F, -1, 1, 0, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			bone.render(f5);
			bone2.render(f5);
			bone3.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
