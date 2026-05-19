package com.fuzhudada.error.entity.model;// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelZhongPaoDan extends ModelBase {
	private final ModelRenderer bb_main;

	public ModelZhongPaoDan() {
		textureWidth = 32;
		textureHeight = 32;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 6, 17, 0.0F, -5.0F, -2.0F, 1, 4, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 1.0F, -6.0F, -3.0F, 2, 6, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 12, 4.0F, -5.0F, -2.0F, 1, 4, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 6.0F, -4.0F, -1.0F, 1, 2, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 4, 7.0F, -3.5F, -0.5F, 1, 1, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 16, 17, 5.0F, -4.5F, -1.5F, 1, 3, 3, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 11, 7, 3.0F, -5.5F, -2.5F, 1, 5, 5, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 10, 0, -8.0F, -4.0F, -1.0F, 8, 2, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngle(bb_main,0,0,f4 * 0.017453292F);
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}