package com.fuzhudada.error.item.armor.model;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ModelBirdChest extends ModelBiped {
	public final ModelRenderer bone;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;

	public ModelBirdChest() {
		textureWidth = 24;
		textureHeight = 24;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -22.0F, 4.0F);
		bone.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.1745F, 0.3491F, 0.5236F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -7.0F, -2.0F, -1.0F, 12, 24, 0, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, -22.0F, 4.0F);
		bone.addChild(cube_r2);
		setRotationAngle(cube_r2, 2.9671F, 0.3491F, 2.618F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 0, -7.0F, -2.0F, 1.0F, 12, 24, 0, 0.0F, false));

		this.bipedBody.addChild(bone);
	}

	@Override
	public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		double v = 0.3491 + 0.18 * Math.cos(f2 * Math.PI / 15);
		this.cube_r1.rotateAngleY= (float) v;
		this.cube_r2.rotateAngleY= (float) v;
		bone.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}