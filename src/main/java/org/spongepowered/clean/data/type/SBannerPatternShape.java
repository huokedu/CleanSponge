package org.spongepowered.clean.data.type;

import org.spongepowered.api.data.type.BannerPatternShape;
import org.spongepowered.clean.registry.AbstractCatalogType;
import org.spongepowered.clean.registry.FixedCatalogRegistryModule;

public class SBannerPatternShape extends AbstractCatalogType implements BannerPatternShape {

    public SBannerPatternShape(String id, String name) {
        super(id, name);
    }
    
    public static void registerTypes(FixedCatalogRegistryModule<BannerPatternShape> registry) {
        registry.register(new SBannerPatternShape("minecraft:base", "Base"));
        registry.register(new SBannerPatternShape("minecraft:border", "Border"));
        registry.register(new SBannerPatternShape("minecraft:bricks", "Bricks"));
        registry.register(new SBannerPatternShape("minecraft:circle_middle", "Circle Middle"));
        registry.register(new SBannerPatternShape("minecraft:creeper", "Creeper"));
        registry.register(new SBannerPatternShape("minecraft:cross", "Cross"));
        registry.register(new SBannerPatternShape("minecraft:curly_border", "Curly Border"));
        registry.register(new SBannerPatternShape("minecraft:diagonal_left", "Diagonal Left"));
        registry.register(new SBannerPatternShape("minecraft:diagonal_left_mirror", "Diagonal Left Mirror"));
        registry.register(new SBannerPatternShape("minecraft:diagonal_right", "Diagonal Right"));
        registry.register(new SBannerPatternShape("minecraft:diagonal_right_mirror", "Diagonal Right Mirror"));
        registry.register(new SBannerPatternShape("minecraft:flower", "Flower"));
        registry.register(new SBannerPatternShape("minecraft:gradient", "Gradient"));
        registry.register(new SBannerPatternShape("minecraft:gradient_up", "Gradient Up"));
        registry.register(new SBannerPatternShape("minecraft:half_horizontal", "Half Horizontal"));
        registry.register(new SBannerPatternShape("minecraft:half_horizontal_mirror", "Half Horizontal Mirror"));
        registry.register(new SBannerPatternShape("minecraft:half_virtical", "Half Virtical"));
        registry.register(new SBannerPatternShape("minecraft:half_virtical_mirror", "Half Vertical Mirror"));
        registry.register(new SBannerPatternShape("minecraft:mojang", "Mojang"));
        registry.register(new SBannerPatternShape("minecraft:rhombus_middle", "Rhombus Middle"));
        registry.register(new SBannerPatternShape("minecraft:skull", "Skull"));
        registry.register(new SBannerPatternShape("minecraft:square_botton_left", "Square Bottom Left"));
        registry.register(new SBannerPatternShape("minecraft:square_bottom_right", "Square Bottom Right"));
        registry.register(new SBannerPatternShape("minecraft:square_top_left", "Square Top Left"));
        registry.register(new SBannerPatternShape("minecraft:square_top_right", "Square Top Right"));
        registry.register(new SBannerPatternShape("minecraft:straight_cross", "Straight Cross"));
        registry.register(new SBannerPatternShape("minecraft:stripe_bottom", "Stripe Bottom"));
        registry.register(new SBannerPatternShape("minecraft:stripe_center", "Stripe Center"));
        registry.register(new SBannerPatternShape("minecraft:stripe_downleft", "Stripe Downleft"));
        registry.register(new SBannerPatternShape("minecraft:stripe_downright", "Stripe Downright"));
        registry.register(new SBannerPatternShape("minecraft:stripe_left", "Stripe Left"));
        registry.register(new SBannerPatternShape("minecraft:stripe_right", "Stripe Right"));
        registry.register(new SBannerPatternShape("minecraft:triangles_bottom", "Triangles Bottom"));
        registry.register(new SBannerPatternShape("minecraft:triangles_top", "Triangles Top"));
        registry.register(new SBannerPatternShape("minecraft:triangle_bottom", "Triangle Bottom"));
        registry.register(new SBannerPatternShape("minecraft:triangle_top", "Triangle Top"));
        
    }

}