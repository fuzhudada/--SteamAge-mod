package com.fuzhudada.error.entity.model;// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelZhengQiChe extends ModelBase {
	private final ModelRenderer bb_main;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;

	public ModelZhengQiChe() {
		textureWidth = 256;
		textureHeight = 256;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r1);
		setRotationAngle(cube_r1, -1.5708F, -0.7854F, 1.5708F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 94, -37.0F, -8.0F, -7.0F, 14, 4, 14, 0.0F, false));
		cube_r1.cubeList.add(new ModelBox(cube_r1, 44, 101, -36.0F, -5.0F, -6.0F, 12, 11, 12, 0.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -1.5708F, 0.0F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 92, 113, -26.0F, -14.0F, -16.0F, 21, 3, 5, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 118, 77, -26.0F, -14.0F, 11.0F, 21, 3, 5, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 120, 35, -11.0F, -21.0F, -7.0F, 1, 2, 14, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 93, 62, -10.0F, -20.0F, -7.0F, 12, 1, 14, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 0, 8.0F, -26.0F, 2.0F, 4, 7, 4, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 11, 8.0F, -26.0F, -6.0F, 4, 7, 4, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 35, 15.0F, -24.0F, 2.0F, 4, 5, 4, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 44, 15.0F, -24.0F, -6.0F, 4, 5, 4, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 42, 94, -35.0F, -12.0F, 8.0F, 8, 2, 3, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 61, 96, -35.0F, -12.0F, -11.0F, 8, 2, 3, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 84, 78, -36.0F, -24.0F, -8.0F, 9, 19, 16, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 35, -27.0F, -19.0F, -10.0F, 50, 3, 20, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 0, -27.0F, -16.0F, -11.0F, 53, 13, 22, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 58, -26.0F, -11.0F, 11.0F, 43, 11, 7, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 0, 76, -26.0F, -11.0F, -18.0F, 43, 11, 7, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(cube_r3);
		setRotationAngle(cube_r3, 1.5708F, -1.0472F, -1.5708F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 119, 121, 24.0F, -6.0F, -14.0F, 8, 15, 6, 0.0F, false));
		cube_r3.cubeList.add(new ModelBox(cube_r3, 32, 124, 24.0F, -6.0F, 8.0F, 8, 15, 6, 0.0F, false));
		cube_r3.cubeList.add(new ModelBox(cube_r3, 128, 0, 24.0F, -6.0F, -3.0F, 8, 15, 6, 0.0F, false));
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 112, 24.0F, -5.0F, -8.0F, 11, 16, 5, 0.0F, false));
		cube_r3.cubeList.add(new ModelBox(cube_r3, 87, 121, 24.0F, -5.0F, 3.0F, 11, 16, 5, 0.0F, false));
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