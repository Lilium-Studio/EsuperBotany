package net.lawaxi.esuperbotany.client.model.armor;// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import vazkii.botania.client.model.armor.ModelArmor;

public class ModelArmorXT extends ModelArmor {
	
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leggings2;
	private final ModelRenderer boots2;
	private final ModelRenderer boots;
	private final ModelRenderer leggings;

	private final ModelRenderer helmet;
	private final ModelRenderer right1;
	private final ModelRenderer left1;
	private final ModelRenderer chestplate;
	private final ModelRenderer left;
	private final ModelRenderer right;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;

	public ModelArmorXT(EntityEquipmentSlot slot) {

		super(slot);

		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0, 0, 0);


		helmet = new ModelRenderer(this);
		helmet.setRotationPoint(0, 24, 0);
		head.addChild(helmet);
		helmet.setTextureOffset(20, 5).addBox(-2, -28, -5.3F, 4, 3, 1, false);
		helmet.setTextureOffset(20, 9).addBox(-1.5F, -33, -5.3F, 3, 4, 1, false);
		helmet.setTextureOffset(0, 15).addBox(-5, -33, -5, 10, 8, 10, false);
		helmet.setTextureOffset(28, 5).addBox(-1.5F, -33.5F, -5, 3, 1, 7, false);

		right1 = new ModelRenderer(this);
		right1.setRotationPoint(-2.5F, -33, 0.5F);
		helmet.addChild(right1);
		setRotationAngle(right1, 0, 0, 0.1745F);
		right1.setTextureOffset(0, 0).addBox(8, -4, -0.5F, 1, 6, 1, false);
		right1.setTextureOffset(0, 0).addBox(8, 1, -5, 1, 2, 9, false);
		right1.setTextureOffset(11, 0).addBox(-2, 3, -6.5F, 6, 2, 1, false);
		right1.setTextureOffset(26, 0).addBox(-2, 2.5F, -6, 6, 3, 1, false);
		right1.setTextureOffset(4, 0).addBox(-5, -5, -0.5F, 1, 4, 1, false);
		right1.setTextureOffset(30, 15).addBox(8.3F, 2.5F, -2, 1, 4, 4, false);

		left1 = new ModelRenderer(this);
		left1.setRotationPoint(2.5F, -33, 0.5F);
		helmet.addChild(left1);
		setRotationAngle(left1, 0, 0, -0.1745F);
		left1.setTextureOffset(0, 0).addBox(-9, -4, -0.5F, 1, 6, 1, true);
		left1.setTextureOffset(0, 0).addBox(-9, 1, -5, 1, 2, 9, true);
		left1.setTextureOffset(11, 0).addBox(-4, 3, -6.5F, 6, 2, 1, true);
		left1.setTextureOffset(26, 0).addBox(-4, 2.5F, -6, 6, 3, 1, true);
		left1.setTextureOffset(4, 0).addBox(4, -5, -0.5F, 1, 4, 1, true);
		left1.setTextureOffset(30, 15).addBox(-9.3F, 2.5F, -2, 1, 4, 4, true);

		body = new ModelRenderer(this);
		body.setRotationPoint(0, 0, 0);


		chestplate = new ModelRenderer(this);
		chestplate.setRotationPoint(0, 24, 0);
		body.addChild(chestplate);
		chestplate.setTextureOffset(0, 34).addBox(-10, -25, -3, 20, 4, 6, false);
		chestplate.setTextureOffset(42, 24).addBox(-4, -23.5F, -3.5F, 8, 8, 1, false);
		chestplate.setTextureOffset(37, 13).addBox(-4.5F, -16, -3, 9, 1, 4, false);
		chestplate.setTextureOffset(46, 34).addBox(-2, -21, -4, 4, 3, 1, false);

		left = new ModelRenderer(this);
		left.setRotationPoint(0, 0, 0);
		chestplate.addChild(left);
		setRotationAngle(left, 0, 0, -0.1222F);
		left.setTextureOffset(0, 44).addBox(12, -27, -3, 1, 5, 5, false);

		right = new ModelRenderer(this);
		right.setRotationPoint(0, 0, 0);
		chestplate.addChild(right);
		setRotationAngle(right, 0, 0, 0.1222F);
		right.setTextureOffset(0, 44).addBox(-13, -27, -3, 1, 5, 5, true);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(1.9F, 12.0F, 0.0F);


		boots2 = new ModelRenderer(this);
		boots2.setRotationPoint(-3.8F, 0.0F, 0.0F);
		right_leg.addChild(boots2);
		boots2.setTextureOffset(0, 56).addBox(-3.1F+0.5F, 9.5F, -2.5F, 5, 3, 5, false);
		boots2.setTextureOffset(40, 44).addBox(-1.6F+0.5F, 4.0F, -2.5F, 3, 6, 1,false);

		leggings2 = new ModelRenderer(this);
		leggings2.setRotationPoint(-3.8F, 0.0F, 0.0F);
		right_leg.addChild(leggings2);
		leggings2.setTextureOffset(24, 44).addBox(-3.0F+0.5F, -1.0F, -3.0F, 2, 6, 6, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(-1.9F, 12.0F, 0.0F);


		boots = new ModelRenderer(this);
		boots.setRotationPoint(3.8F, 0.0F, 0.0F);
		left_leg.addChild(boots);
		boots.setTextureOffset(0, 56).addBox(-1.9F+0.5F, 9.5F, -2.5F, 5, 3, 5, true);
		boots.setTextureOffset(40, 44).addBox(-1.4F+0.5F, 4.0F, -2.5F, 3, 6, 1, true);

		leggings = new ModelRenderer(this);
		leggings.setRotationPoint(3.8F, 0.0F, 0.0F);
		left_leg.addChild(leggings);
		leggings.setTextureOffset(24, 44).addBox(1.0F+0.5F, -1.0F, -3.0F, 2, 6, 6,  true);
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {


		this.head.showModel = this.slot == EntityEquipmentSlot.HEAD;
		this.body.showModel = this.slot == EntityEquipmentSlot.CHEST;
		this.leggings.showModel = this.slot == EntityEquipmentSlot.LEGS;
		this.leggings2.showModel = this.slot == EntityEquipmentSlot.LEGS;
		this.boots.showModel = this.slot == EntityEquipmentSlot.FEET;
		this.boots2.showModel = this.slot == EntityEquipmentSlot.FEET;

		this.bipedHead = head;
		this.bipedBody = body;
		this.bipedLeftArm.showModel = false;
		this.bipedRightArm.showModel = false;
		if(this.slot == EntityEquipmentSlot.LEGS)
		{
			bipedLeftLeg = leggings;
			bipedRightLeg = leggings2;
		}else if(this.slot == EntityEquipmentSlot.FEET){
			bipedLeftLeg = boots;
			bipedRightLeg = boots2;
		}

		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}