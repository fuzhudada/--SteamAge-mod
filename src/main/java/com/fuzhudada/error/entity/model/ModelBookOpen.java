package com.fuzhudada.error.entity.model;// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBookOpen extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer cube_r1;

	public ModelBookOpen() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(7.0F, 2.0F, 6.0F);
		setRotationAngle(bone, 1.9635F, 1.0472F, 1.5708F);
		bone.cubeList.add(new ModelBox(bone, 0, 15, -11.0F, -1.0F, -5.0F, 20, 1, 13, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 29, -9.0F, -4.0F, -5.0F, 18, 3, 13, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 45, -11.0F, -5.0F, -7.0F, 20, 5, 2, 0.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, -5.0F, -6.0F);
		bone.addChild(cube_r1);
		setRotationAngle(cube_r1, 2.3562F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 0, -11.0F, -0.1F, 0.0F, 20, 1, 14, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}