package com.fuzhudada.error.item.gun;


import com.fuzhudada.error.item.food.ItemBombFruit;
import net.minecraft.item.Item;

public class ItemBullet extends com.somebody.idlframewok.item.ItemBase {
    int LEVEL;
    int TYPE;
    public ItemBullet(String name,int level,int type) {
        super(name);
        this.LEVEL= level;
        this.TYPE= type;
    }

    public static int GetBulletType(Item Item){
        if(Item instanceof ItemBullet) {
            return ((ItemBullet) Item).TYPE;
        }else if(Item instanceof ItemBombFruit){
            return 7;
        }else{
            return -1;
        }
    }
}
