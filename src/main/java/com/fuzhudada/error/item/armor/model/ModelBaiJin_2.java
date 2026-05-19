package com.fuzhudada.error.item.armor.model;// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBaiJin_2 extends ModelBiped {
	public final ModelRenderer Body;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	public final ModelRenderer RightLeg;
	public final ModelRenderer LeftLeg;

	public ModelBaiJin_2() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 0, 8, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.27F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 0, -5.0F, 10.0F, -3.0F, 10, 2, 6, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 12.0F, -2.0F);
		Body.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.5236F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 24, 8, -5.0F, -1.0F, -1.0F, 10, 5, 1, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 12.0F, 3.0F);
		Body.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.5236F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 24, 14, -5.0F, -1.0F, -1.0F, 10, 5, 1, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(-1.0F, 14.0F, 0.0F);
		Body.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.2618F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 10, 34, -5.0F, -3.0F, -3.0F, 1, 6, 6, 0.0F, false));

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(1.0F, 13.0F, 0.0F);
		Body.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, -0.2618F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 30, 30, 4.0F, -2.0F, -3.0F, 1, 6, 6, 0.0F, false));

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(5.0F, 9.0F, 4.0F);
		Body.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, -0.7854F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 32, 0, -1.0F, -1.0F, -7.0F, 2, 2, 6, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(-5.0F, 9.0F, 4.0F);
		Body.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, -0.7854F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 36, 20, -1.0F, -1.0F, -7.0F, 2, 2, 6, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(0.0F, 10.0F, 4.0F);
		Body.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, -0.7854F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 12, 24, -1.0F, -1.0F, -2.0F, 2, 2, 2, 0.0F, false));

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 0, 24, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.27F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 20, 20, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.27F, false));

		this.setRotationAngle(Body,0, (float) Math.PI ,0);
		this.setRotationAngle(LeftLeg,0, (float) Math.PI ,0);
		this.setRotationAngle(RightLeg,0, (float) Math.PI ,0);
		this.bipedBody.addChild(Body);
		this.bipedLeftLeg.addChild(LeftLeg);
		this.bipedRightLeg.addChild(RightLeg);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.bipedHead.showModel=false;
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}