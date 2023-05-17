package com.moffy5612.iinteg.integration.tconstruct.book.conarm;

import com.moffy5612.iinteg.integration.tconstruct.handler.TraitHandler;

import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.library.book.content.ContentListing;
import slimeknights.tconstruct.library.book.sectiontransformer.SectionTransformer;
import slimeknights.tconstruct.library.modifiers.Modifier;

public class ArmorModifierSectionTransformer extends SectionTransformer{

    public ArmorModifierSectionTransformer(){
        super("modifiers");
    }

    @Override
    public void transform(BookData book, SectionData section) {
        ContentListing listing = (ContentListing)section.pages.get(0).content;
        for(Modifier modifier : TraitHandler.REG_ARMOR_TRAIT){
            PageData page = new PageData();
            page.source = new FileRepository("conarm:book");
            page.parent = section;
            page.type = "armormodifier";
            page.data = "modifiers/" + modifier.identifier + ".json";
            section.pages.add(page);
            page.load();
            listing.addEntry(modifier.getLocalizedName(), page);
        }
    }
    
    
}
