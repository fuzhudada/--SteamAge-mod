package com.fuzhudada.error.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;

public class GuiSteam extends GuiScreen {
    public GuiSteam(){
        super();
    }

    @Override
    public void initGui() {
        super.initGui();
        super.labelList.add(new GuiLabel(this.fontRenderer,1,50,50,200,200,223456));
        this.buttonList.add(new GuiButton(0,20,20,"gui.button1"));
    }
}
