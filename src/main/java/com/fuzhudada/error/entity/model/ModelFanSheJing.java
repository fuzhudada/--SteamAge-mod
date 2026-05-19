package com.fuzhudada.error.entity.model;// Made with Blockbench 4.6.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFanSheJing extends ModelBase {
	private final ModelRenderer bb_main;

	public ModelFanSheJing() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -5.0F, -1.0F, -5.0F, 10, 1, 10, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 11, -4.0F, -2.0F, -4.0F, 8, 1, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 20, 31, -1.0F, -15.0F, -1.0F, 2, 14, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 30, 1, -1.0F, -27.0F, -5.0F, 2, 1, 10, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 20, 20, -1.0F, -16.0F, -5.0F, 2, 1, 10, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 34, 12, -1.0F, -26.0F, -5.0F, 2, 10, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 28, 31, -1.0F, -26.0F, 4.0F, 2, 10, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 20, -1.0F, -26.0F, -4.0F, 2, 10, 8, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}