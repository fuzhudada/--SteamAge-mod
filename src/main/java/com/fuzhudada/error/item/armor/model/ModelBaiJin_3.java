package com.fuzhudada.error.item.armor.model;// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBaiJin_3 extends ModelBiped {
	public final ModelRenderer RightLeg;
	public final ModelRenderer LeftLeg;

	public ModelBaiJin_3() {
		textureWidth = 64;
		textureHeight = 64;

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 16, 13, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.3F, false));
		RightLeg.cubeList.add(new ModelBox(RightLeg, 15, 3, -2.0F, 10.0F, -4.0F, 4, 3, 7, 0.0F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg , 0, 10, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.3F, false));
		LeftLeg.cubeList.add(new ModelBox(LeftLeg , 0, 0, -2.0F, 10.0F, -4.0F, 4, 3, 7, 0.0F, false));

		this.bipedLeftLeg.addChild(LeftLeg);
		this.bipedRightLeg.addChild(RightLeg);
		this.bipedHeadwear.showModel=false;
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