//Made with Blockbench
//Paste this code into your mod.

public static class Modelskelletron extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;

	public Modelskelletron() {
		textureWidth = 32;
		textureHeight = 32;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -5.0F, -20.0F, -5.0F, 10,
				10, 10, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 5.0F, -16.0F, -1.0F, 15, 2,
				2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -19.0F, -16.0F, -1.0F, 15,
				2, 2, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, 20.0F, -17.0F, 0.0F, 4, 4,
				0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, 20.0F, -20.0F, 0.0F, 1, 3,
				0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, 22.0F, -19.0F, 0.0F, 1, 2,
				0, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, 25.0F, -18.0F, 0.0F, -1,
				1, 0, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -23.0F, -18.0F, 0.0F, 4,
				4, 0, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -20.0F, -21.0F, 0.0F, 1,
				3, 0, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -22.0F, -20.0F, 0.0F, 1,
				2, 0, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -23.0F, -19.0F, 0.0F, -1,
				1, 0, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		bone.render(f5);
		bone2.render(f5);
		bone3.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y,
			float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}