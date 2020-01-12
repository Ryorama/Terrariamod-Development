//Made with Blockbench
//Paste this code into your mod.

public static class Modelharpy extends ModelBase {
	private final ModelRenderer bone3;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public Modelharpy() {
		textureWidth = 32;
		textureHeight = 32;

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-3.201F, 15.4558F, 2.0F);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.5236F);
		bone3.cubeList.add(new ModelBox(bone3, 2, 0, 3.201F, 2.5442F, -1.0F, 3,
				0, 2, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 15, 17, -2.0F, -10.0F, 0.0F, 3, 3,
				3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 8, 11, -2.0F, -11.0F, -2.0F, 3, 3,
				2, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.5236F);
		bone2.cubeList.add(new ModelBox(bone2, 15, 0, -8.0F, -6.0F, 1.0F, 3, 0,
				2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		bone3.render(f5);
		bone.render(f5);
		bone2.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y,
			float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}