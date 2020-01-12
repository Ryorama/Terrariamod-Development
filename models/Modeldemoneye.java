//Made with Blockbench
//Paste this code into your mod.

public static class Modeldemoneye extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public Modeldemoneye() {
		textureWidth = 32;
		textureHeight = 32;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -4.0F, -26.0F, -4.0F, 8, 8,
				8, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.7854F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 8, 16, 16.0F, -19.0F, 4.0F, 0,
				8, 8, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 16, 12.0F, -15.0F, 4.0F, 8,
				0, 8, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		bone.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y,
			float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}