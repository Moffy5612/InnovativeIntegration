package com.moffy5612.iinteg.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;
import net.minecraft.item.ItemStack;

public class ModRecipeListBase{

    public Map<String, ModRecipe> recipes = new HashMap<>(); 

    public Set<Pair<String, ItemStack>> getMaterials(){
        Set<Pair<String, ItemStack>> materials = new HashSet<>();
        for(String key : recipes.keySet()){
            ModRecipe value = recipes.get(key);
            for(ItemStack material : value.material){
                materials.add(new Pair<String,ItemStack>(key, material));
            }
        }
        return materials;
    }

    public Set<Pair<String, ItemStack>> getResults(){
        Set<Pair<String, ItemStack>> results = new HashSet<>();
        for(String key : recipes.keySet()){
            ModRecipe value = recipes.get(key);
            results.add(new Pair<String,ItemStack>(key, value.result));
        }
        return results;
    }
    
    public void addRecipe(String key, ModRecipe recipe){
        recipes.put(key, recipe);
    }

    public void addRecipe(String key, ItemStack result, ItemStack... materials){
        this.addRecipe(key, new ModRecipe(result, materials));
    }

    public void removeRecipe(String key){
        recipes.remove(key);
    }

    public ModRecipe getRecipe(String key){
        return recipes.get(key);
    }

    public class ModRecipe{
        public List<ItemStack> material;
        public ItemStack result;

        public ModRecipe(ItemStack result, ItemStack... materials){
            this.material = new ArrayList<ItemStack>();
            for(ItemStack material : materials){
                this.material.add(material);
            }
            this.result = result;
        }

        public boolean isMaterialsEquals(List<ItemStack> materialList){
            if(materialList.size() != this.material.size())return false;
            for(int i = 0; i < this.material.size(); i++){
                if(!ItemStack.areItemsEqual(material.get(i), materialList.get(i)))return false;
            }
            return true;
        }
    }
}
