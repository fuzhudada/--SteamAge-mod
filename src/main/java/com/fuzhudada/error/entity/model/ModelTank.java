package com.fuzhudada.error.entity.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelTank extends ModelBase {
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer bone;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;

	public ModelTank() {
		textureWidth = 256;
		textureHeight = 256;

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bone3, 0.0F, 3.1416F, 0.0F);


		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, -6.0F);
//		bone3.addChild(bone2);
//		setRotationAngle(bone2, 0.0F, (float)Math.PI, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 68, 102, -5.0F, 8.0F, -6.0F, 10, 4, 13, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -1.0F, 9.0F, 7.0F, 2, 2, 13, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, -7.0F);
		bone2.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.2618F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 15, 64, -8.0F, 8.0F, 7.0F, 2, 4, 5, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 15, 2.0F, 8.0F, 2.0F, 3, 4, 9, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, -7.0F);
		bone2.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -0.2618F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 28, 5.0F, 8.0F, 7.0F, 3, 4, 5, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 15, 19, -5.0F, 8.0F, 2.0F, 3, 4, 9, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone3.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -10.0F, -10.0F, -18.0F, 20, 8, 39, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 102, -10.0F, -12.0F, -12.0F, 20, 2, 28, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 51, 55, -16.0F, -11.0F, -19.0F, 6, 8, 39, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 108, 126, -16.0F, -13.0F, -19.0F, 6, 2, 14, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 82, 119, 10.0F, -13.0F, -19.0F, 6, 2, 14, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 80, 9, -15.0F, -3.0F, -18.0F, 5, 3, 38, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 47, 10.0F, -11.0F, -19.0F, 6, 8, 39, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 101, 49, 10.0F, -3.0F, -18.0F, 5, 3, 38, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.3927F, 0.0F, 0.0F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 17, 0, -16.0F, -3.0F, 19.0F, 6, 8, 4, 0.0F, false));
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 56, 10.0F, -3.0F, 19.0F, 6, 8, 4, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r4);
		setRotationAngle(cube_r4, -0.3054F, 0.0F, 0.0F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 21, 53, 10.0F, -5.0F, -22.0F, 6, 8, 3, 0.0F, false));
		cube_r4.cubeList.add(new ModelBox(cube_r4, 51, 56, -16.0F, -5.0F, -22.0F, 6, 8, 3, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.2618F, 0.0F, 0.0F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 114, 114, -10.0F, -14.0F, -18.0F, 20, 8, 4, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.5672F, 0.0F, 0.0F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 101, 102, -10.0F, -20.0F, 12.0F, 20, 7, 5, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.1309F, 0.0F, 0.0F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 0, 47, 2.0F, -14.0F, -19.0F, 6, 3, 6, 0.0F, false));
		cube_r7.cubeList.add(new ModelBox(cube_r7, 51, 47, -8.0F, -14.0F, -19.0F, 6, 3, 6, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		if(entity instanceof EntityTank){
			EntityTank tank=(EntityTank) entity;
			setRotationAngle(bone2,0,tank.getRotation(),0);
		}
//		setRotationAngle(bone2,0,(float)(Math.PI+Math.PI*3*Math.cos(f2*Math.PI/30)/4),0);
		bone2.render(f5);
		bone3.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}