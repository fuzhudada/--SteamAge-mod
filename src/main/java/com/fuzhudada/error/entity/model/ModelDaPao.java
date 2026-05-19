package com.fuzhudada.error.entity.model;// Made with Blockbench 4.8.1
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDaPao extends ModelBase {
	private final ModelRenderer bone3;
	private final ModelRenderer bone5;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer bone6;
	private final ModelRenderer bone;
	private final ModelRenderer cube_r4;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r5;
	private final ModelRenderer bone4;

	public ModelDaPao() {
		textureWidth = 256;
		textureHeight = 256;

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 23.0F, 0.0F);
		

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(7.0F, 7.0F, 0.0F);
		bone3.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 0, 0, -23.0F, -20.0F, -13.0F, 31, 5, 25, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 0, 19, -19.0F, -23.0F, -9.0F, 5, 3, 3, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 0, 19, -19.0F, -23.0F, 5.0F, 5, 3, 3, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone5.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.6545F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -30.0F, 0.0F, -10.0F, 3, 15, 4, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -30.0F, 0.0F, 5.0F, 3, 15, 4, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-7.0F, -7.0F, 0.0F);
		bone5.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -0.48F, -0.2182F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 52, 21.0F, -16.0F, -12.0F, 2, 21, 24, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-7.0F, -7.0F, 0.0F);
		bone5.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.48F, -0.2182F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 52, 21.0F, -16.0F, -12.0F, 2, 21, 24, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone3.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 80, 73, -2.0F, -8.0F, -12.0F, 1, 1, 24, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -2.0F, -13.0F);
		bone6.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 87, 0, -8.0F, 1.0F, -1.0F, 13, 2, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 87, 0, -8.0F, -14.0F, -1.0F, 13, 2, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 13, 16, -3.0F, -7.0F, -2.0F, 3, 3, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 14, 0, -2.0F, -4.0F, 0.0F, 1, 5, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 14, 0, -2.0F, -12.0F, 0.0F, 1, 5, 1, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 1.5708F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 14, 0, -6.0F, 3.0F, 0.0F, 1, 5, 1, 0.0F, false));
		cube_r4.cubeList.add(new ModelBox(cube_r4, 4, 30, -6.0F, -5.0F, 0.0F, 1, 5, 1, 0.0F, false));
		cube_r4.cubeList.add(new ModelBox(cube_r4, 87, 0, -12.0F, 8.0F, -1.0F, 13, 2, 2, 0.0F, false));
		cube_r4.cubeList.add(new ModelBox(cube_r4, 87, 0, -12.0F, -7.0F, -1.0F, 13, 2, 2, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-3.0F, -2.0F, 13.0F);
		bone6.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 3.1416F, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 87, 0, -8.0F, 1.0F, -1.0F, 13, 2, 2, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 87, 0, -8.0F, -14.0F, -1.0F, 13, 2, 2, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 13, 16, -3.0F, -7.0F, -2.0F, 3, 3, 3, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 14, 0, -2.0F, -4.0F, 0.0F, 1, 5, 1, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 14, 0, -2.0F, -12.0F, 0.0F, 1, 5, 1, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone2.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 1.5708F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 45, 52, -12.0F, -7.0F, -1.0F, 13, 2, 2, 0.0F, false));
		cube_r5.cubeList.add(new ModelBox(cube_r5, 14, 0, -6.0F, 3.0F, 0.0F, 1, 5, 1, 0.0F, false));
		cube_r5.cubeList.add(new ModelBox(cube_r5, 14, 0, -6.0F, -5.0F, 0.0F, 1, 5, 1, 0.0F, false));
		cube_r5.cubeList.add(new ModelBox(cube_r5, 87, 0, -12.0F, 8.0F, -1.0F, 13, 2, 2, 0.0F, false));
		cube_r5.cubeList.add(new ModelBox(cube_r5, 87, 0, -12.0F, -7.0F, -1.0F, 13, 2, 2, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(7.0F, 30.0F, 0.0F);
		setRotationAngle(bone4, 0.0F, 0.0F, -0.2618F);
		bone4.cubeList.add(new ModelBox(bone4, 80, 52, 15.0F, -30.0F, -5.0F, 23, 9, 9, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 28, 52, 38.0F, -31.0F, -6.0F, 3, 11, 11, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 30, -24.0F, -31.0F, -6.0F, 39, 11, 11, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 4, 34, -26.0F, -29.0F, -4.0F, 2, 7, 7, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 8, 38, -29.0F, -27.0F, -2.0F, 3, 3, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone3.render(f5);
		bone4.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}