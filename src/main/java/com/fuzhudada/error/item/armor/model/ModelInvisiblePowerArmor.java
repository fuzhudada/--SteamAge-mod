package com.fuzhudada.error.item.armor.model;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ModelInvisiblePowerArmor extends ModelBiped {
	public final ModelRenderer Head;
	public final ModelRenderer Body;
	public final ModelRenderer RightArm;
	public final ModelRenderer LeftArm;
	public final ModelRenderer RightLeg;
	public final ModelRenderer LeftLeg;

	public ModelInvisiblePowerArmor() {
		textureWidth = 64;
		textureHeight = 64;

		ModelRenderer waist = new ModelRenderer(this);
		waist.setRotationPoint(0.0F, 12.0F, 0.0F);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		waist.addChild(Head);
		Head.cubeList.add(new ModelBox(Head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.6F, false));
		Head.cubeList.add(new ModelBox(Head, 24, 16, -5.0F, -9.0F, -5.0F, 10, 8, 3, 0.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		waist.addChild(Body);
		Body.cubeList.add(new ModelBox(Body, 0, 16, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.3F, false));
		Body.cubeList.add(new ModelBox(Body, 36, 40, -5.0F, 0.0F, -3.0F, 2, 1, 6, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 46, 27, 3.0F, 0.0F, -3.0F, 2, 1, 6, 0.0F, false));

		ModelRenderer cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 3.0F, -3.0F);
		Body.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.2182F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 16, 40, -4.0F, -2.0F, -1.0F, 8, 6, 2, 0.0F, false));

		ModelRenderer cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 1.0F, 3.0F);
		Body.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.1745F, 0.0F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 24, 27, -4.0F, -2.0F, -1.0F, 8, 10, 3, 0.0F, false));

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-5.0F, -10.0F, 0.0F);
		waist.addChild(RightArm);
		

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(5.0F, -10.0F, 0.0F);
		waist.addChild(LeftArm);
		

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 0, 32, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.27F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 32, 0, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.27F, false));

		this.bipedHeadwear.cubeList.clear();
		this.bipedHeadwear.addChild(Head);
		this.bipedBody.addChild(Body);
		this.bipedLeftLeg.addChild(LeftLeg);
		this.bipedRightLeg.addChild(RightLeg);
		this.bipedHeadwear.showModel=false;
	}

	@Override
	public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.bipedHead.showModel=false;
		this.bipedLeftArm.showModel=false;
		this.bipedRightArm.showModel=false;
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}