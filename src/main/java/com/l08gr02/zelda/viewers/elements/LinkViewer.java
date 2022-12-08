package com.l08gr02.zelda.viewers.elements;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.l08gr02.zelda.models.Sprite;
import com.l08gr02.zelda.models.elements.Link;
import com.l08gr02.zelda.viewers.SpriteViewer;

import java.awt.*;

import static com.l08gr02.zelda.presenters.GameplayPresenter.ACTION;

public class LinkViewer implements SpriteViewer<Link> {
    private Sprite sprite;
    private Sprite heartSprite;
    private int xGrid = 0, yGrid = 0;
    private final static String TRANSPARENCY = "#828282";

    // constructor
    public LinkViewer(){
        sprite = new Sprite(44, 44, "Link", "spritesheet");
        sprite.setPixels(xGrid, yGrid);

        heartSprite = new Sprite(16, 16,"gfx","objects");
    }

    // method
    @Override
    public void draw(TextGraphics graphics, Link link) {
        int x = link.getPosition().getX();
        int y = link.getPosition().getY();

        Color pixels[][] = sprite.getPixels();

        for(int i = 0; i < sprite.getHeight(); i++){
            for(int j = 0; j < sprite.getWidth(); j++){
                int R = pixels[i][j].getRed();
                int G = pixels[i][j].getGreen();
                int B = pixels[i][j].getBlue();

                // verificar se o pixel é transparente
                if (R == 131 && G == 131 && B == 131){
                    continue;
                }

                graphics.setBackgroundColor(new TextColor.RGB(R, G, B));
                graphics.setCharacter(x + i, y + j, ' ');
            }
        }

        drawLife(graphics, link);
    }

    public void drawLife(TextGraphics graphics, Link link){
        heartSprite.setPixels(4,0);
        Color pixels[][] = heartSprite.getPixels();

        float life = link.getHearts();

        // desenhar os corações inteiros
        for (int i = 0; i < (int) life; i++){
            for (int j = 0; j < heartSprite.getHeight(); j++){
                for (int k = 0; k < heartSprite.getWidth(); k++){
                    graphics.setBackgroundColor(new TextColor.RGB(pixels[j][k].getRed(), pixels[j][k].getGreen(),pixels[j][k].getBlue()));
                    graphics.setCharacter(i * 16 + j, k, ' ');
                }
            }
        }

        // desenhar o coração não inteiro, caso exista
        if (life % 1 != 0){
            heartSprite.setPixels(8 - (int) (life % 1 * 4),0);
            pixels = heartSprite.getPixels();

            for (int j = 0; j < heartSprite.getHeight(); j++){
                for (int k = 0; k < heartSprite.getWidth(); k++){
                    graphics.setBackgroundColor(new TextColor.RGB(pixels[j][k].getRed(), pixels[j][k].getGreen(),pixels[j][k].getBlue()));
                    graphics.setCharacter((int) life * 16 + j, k, ' ');
                }
            }
        }
    }

    @Override
    public void setSprite(ACTION action){
         switch (action) {
            case UP -> {yGrid = 1;}

            case DOWN -> {yGrid = 0;}

            case LEFT -> {yGrid = 2;}

            case RIGHT -> {yGrid = 3;}
        }

        if (xGrid == 5){
            xGrid = 0;
        }
        else if (action != ACTION.NOTHING){
            xGrid++;
        }

        sprite.setPixels(xGrid, yGrid);
    }

}
