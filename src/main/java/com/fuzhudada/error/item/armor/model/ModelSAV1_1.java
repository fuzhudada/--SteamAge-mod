package com.fuzhudada.error.item.armor.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSAV1_1 extends ModelBiped {
	private final ModelRenderer bone;
	public final ModelRenderer Head;
	private final ModelRenderer cube_r1;
	public final ModelRenderer Body;
	private final ModelRenderer Chestplate_r1;
	public final ModelRenderer LeftLeg;
	public final ModelRenderer RightLeg;
	public final ModelRenderer LeftArm;
	private final ModelRenderer cube_r2;
	public final ModelRenderer RightArm;
	private final ModelRenderer cube_r3;
	public final ModelRenderer Rpg;
	private final ModelRenderer RpgLuncher_r1;

	public ModelSAV1_1() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);


		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -1.0F, 0.0F);
		bone.addChild(Head);
		Head.cubeList.add(new ModelBox(Head, 0, 0, -4.0F, -6.1F, -4.0F, 8, 8, 8, 1.0F, false));

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.3054F, 0.0F, 0.0F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 24, 16, -5.1F, -5.0F, -5.0F, 10, 6, 3, 0.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 1.0F, 0.0F);
		bone.addChild(Body);
		Body.cubeList.add(new ModelBox(Body, 0, 16, -4.0F, 0.0F, -2.0F, 8, 12, 4, 1.01F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 48, -3.0F, 0.0F, 2.0F, 6, 7, 1, 1.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 61, -3.0F, 3.0F, 4.0F, 6, 6, 6, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 18, 61, -2.0F, 9.0F, 5.0F, 4, 1, 4, 0.0F, false));

		Chestplate_r1 = new ModelRenderer(this);
		Chestplate_r1.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.addChild(Chestplate_r1);
		setRotationAngle(Chestplate_r1, 0.3491F, 0.0F, 0.0F);
		Chestplate_r1.cubeList.add(new ModelBox(Chestplate_r1, 48, 0, -4.0F, -23.0F, 3.0F, 8, 6, 2, 1.01F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(1.9F, 1.0F, 0.0F);
		bone.addChild(LeftLeg);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 24, 25, -4.0F, -1.0F, -2.0F, 4, 12, 4, 1.0F, false));

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-1.9F, 1.0F, 0.0F);
		bone.addChild(RightLeg);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 0, 32, 0.0F, -1.0F, -2.0F, 4, 12, 4, 1.0F, false));

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(5.0F, 3.0F, 0.0F);
		bone.addChild(LeftArm);
		LeftArm.cubeList.add(new ModelBox(LeftArm, 32, 0, -5.0F, -4.1F, -2.0F, 4, 12, 4, 1.0F, false));

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-5.0F, -2.0F, 0.0F);
		LeftArm.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.3491F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 30, 41, 3.0F, -2.0F, -4.0F, 2, 9, 1, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 16, 35, 4.0F, -1.0F, -3.0F, 1, 9, 6, 0.0F, false));
		cube_r2.cubeList.add(new ModelBox(cube_r2, 48, 8, 3.0F, -2.0F, 3.0F, 2, 9, 1, 0.0F, false));

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-5.0F, 3.0F, 0.0F);
		bone.addChild(RightArm);
		RightArm.cubeList.add(new ModelBox(RightArm, 36, 37, 1.0F, -4.1F, -2.0F, 4, 12, 4, 1.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(5.0F, -2.0F, 0.0F);
		RightArm.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.3491F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 14, 50, -5.0F, -2.0F, 3.0F, 2, 9, 1, 0.0F, false));
		cube_r3.cubeList.add(new ModelBox(cube_r3, 44, 19, -5.0F, -1.0F, -3.0F, 1, 9, 6, 0.0F, false));
		cube_r3.cubeList.add(new ModelBox(cube_r3, 20, 50, -5.0F, -2.0F, -4.0F, 2, 9, 1, 0.0F, false));

		Rpg = new ModelRenderer(this);
		Rpg.setRotationPoint(3.0F, -17.0F, 7.0F);
		bone.addChild(Rpg);
		setRotationAngle(Rpg, 0.0F, 0.0F, -1.5708F);
		Rpg.cubeList.add(new ModelBox(Rpg, 38, 66, -21.0F, -5.0F, -2.0F, 1, 4, 4, 0.0F, false));
		Rpg.cubeList.add(new ModelBox(Rpg, 54, 71, -20.0F, -4.5F, -1.5F, 1, 3, 3, 0.0F, false));
		Rpg.cubeList.add(new ModelBox(Rpg, 38, 54, -19.0F, -4.0F, -1.0F, 1, 2, 2, 0.0F, false));
		Rpg.cubeList.add(new ModelBox(Rpg, 38, 58, -18.0F, -3.5F, -0.5F, 1, 1, 1, 0.0F, false));

		RpgLuncher_r1 = new ModelRenderer(this);
		RpgLuncher_r1.setRotationPoint(-5.0F, -3.0F, 0.0F);
		Rpg.addChild(RpgLuncher_r1);
		setRotationAngle(RpgLuncher_r1, 0.0F, 0.0F, 1.5708F);
		RpgLuncher_r1.cubeList.add(new ModelBox(RpgLuncher_r1, 25, 68, -1.0F, 23.0F, -1.0F, 2, 1, 2, 0.0F, false));

		this.bipedHeadwear.addChild(Head);
		this.bipedBody.addChild(Body);
		this.bipedLeftArm.addChild(LeftArm);
		this.bipedLeftLeg.addChild(LeftLeg);
		this.bipedRightArm.addChild(RightArm);
		this.bipedRightLeg.addChild(RightLeg);
		this.bipedBody.addChild(Rpg);
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