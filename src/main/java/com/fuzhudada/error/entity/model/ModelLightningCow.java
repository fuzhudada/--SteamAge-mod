package com.fuzhudada.error.entity.model;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLightningCow extends ModelBase {
	private final ModelRenderer lightningCow;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer right_hand;
	private final ModelRenderer bone5;
	private final ModelRenderer cube_r1;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer left_hand;
	private final ModelRenderer bone7;
	private final ModelRenderer cube_r4;
	private final ModelRenderer bone8;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer head;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer body;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;

	public ModelLightningCow() {
		textureWidth = 128;
		textureHeight = 128;

		lightningCow = new ModelRenderer(this);
		lightningCow.setRotationPoint(0.0F, 28.0F, 0.0F);
		setRotationAngle(lightningCow, 0.0F, 1.5708F, 0.0F);
		

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(-16.0F, -12.0F, 7.0F);
		lightningCow.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 42, 56, -2.0F, -3.0F, -1.0F, 4, 10, 4, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-16.0F, -13.0F, -9.0F);
		lightningCow.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 0, 0, -2.0F, -2.0F, -1.0F, 4, 10, 4, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(4.0F, -20.0F, -8.0F);
		lightningCow.addChild(right_hand);
		

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-4.0F, 20.0F, 8.0F);
		right_hand.addChild(bone5);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(5.0F, -10.0F, -10.0F);
		bone5.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.2618F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 66, -2.0F, -3.0F, -1.0F, 5, 9, 3, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-4.0F, 20.0F, 8.0F);
		right_hand.addChild(bone3);
		

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(7.0F, -17.0F, -9.0F);
		bone3.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.5672F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 16, 66, -5.0F, -7.0F, -3.0F, 3, 6, 4, 0.0F, false));

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(4.0F, -15.0F, -9.0F);
		bone3.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.1309F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 51, -2.0F, -7.0F, -3.0F, 7, 11, 4, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(4.0F, -20.0F, 8.0F);
		lightningCow.addChild(left_hand);
		

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-4.0F, 20.0F, 12.0F);
		left_hand.addChild(bone7);
		

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(5.0F, -10.0F, -11.0F);
		bone7.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.2618F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 58, 56, -2.0F, -3.0F, -1.0F, 5, 9, 3, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-4.0F, 20.0F, 12.0F);
		left_hand.addChild(bone8);
		

		cube_r5 = new ModelRenderer(this);
		cube_r5.setRotationPoint(7.0F, -17.0F, -9.0F);
		bone8.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, -0.5672F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 27, -5.0F, -7.0F, -3.0F, 3, 6, 4, 0.0F, false));

		cube_r6 = new ModelRenderer(this);
		cube_r6.setRotationPoint(4.0F, -15.0F, -9.0F);
		bone8.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, -0.1309F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 22, 51, -2.0F, -7.0F, -3.0F, 6, 11, 4, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(11.0F, -19.0F, 0.0F);
		lightningCow.addChild(head);
		setRotationAngle(head, 0.0F, 0.0F, 0.1309F);
		head.cubeList.add(new ModelBox(head, 40, 0, -0.6613F, -3.3181F, -4.0F, 7, 6, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 76, 40, 8.3387F, -15.3181F, -1.0F, 2, 15, 2, 0.0F, false));

		cube_r7 = new ModelRenderer(this);
		cube_r7.setRotationPoint(12.3387F, -10.3181F, 0.0F);
		head.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, -0.6545F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 33, 72, -1.0F, -26.0F, -0.5F, 2, 21, 1, 0.0F, false));

		cube_r8 = new ModelRenderer(this);
		cube_r8.setRotationPoint(6.3387F, -0.3181F, 0.0F);
		head.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.3927F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 50, 45, -1.0F, -2.0F, -3.0F, 5, 5, 6, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -15.0F, 0.0F);
		lightningCow.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 0, -1.0F, -10.0F, -8.0F, 12, 11, 16, 0.0F, false));

		cube_r9 = new ModelRenderer(this);
		cube_r9.setRotationPoint(-20.0F, 0.0F, 0.0F);
		body.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, -0.7418F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, -1, 37, -4.0F, -2.0F, -0.5F, 5, 2, 1, 0.0F, false));

		cube_r10 = new ModelRenderer(this);
		cube_r10.setRotationPoint(2.0F, -1.0F, 0.0F);
		body.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, -0.1745F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 0, 27, -15.0F, -9.0F, -7.0F, 14, 10, 14, 0.0F, false));

		cube_r11 = new ModelRenderer(this);
		cube_r11.setRotationPoint(-21.0F, 4.0F, 0.0F);
		body.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, -0.1309F);
		cube_r11.cubeList.add(new ModelBox(cube_r11, 44, 15, 1.0F, -10.0F, -6.0F, 9, 9, 12, 0.0F, false));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.head.rotateAngleZ = headPitch * 0.017453292F;
		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.left_hand.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.left_leg.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.right_hand.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.right_leg.rotateAngleZ = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		lightningCow.render(scale);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}