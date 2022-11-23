package dev.jackwhelan.celt.entity;

import dev.jackwhelan.celt.main.GamePanel;
import dev.jackwhelan.celt.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_walk_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_walk_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_walk_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_walk_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_walk_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_walk_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_walk_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_walk_2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.upPressed) {
            direction = "up";
            y -= speed;
            spriteCounter++;
        }
        if (keyHandler.downPressed) {
            direction = "down";
            y += speed;
            spriteCounter++;
        }
        if (keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
            spriteCounter++;
        }
        if (keyHandler.rightPressed) {
            direction = "right";
            x += speed;
            spriteCounter++;
        }

        if (spriteCounter > 15) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNumber == 1) image = up1;
                if (spriteNumber == 2) image = up2;
            }
            case "down" -> {
                if (spriteNumber == 1) image = down1;
                if (spriteNumber == 2) image = down2;
            }
            case "left" -> {
                if (spriteNumber == 1) image = left1;
                if (spriteNumber == 2) image = left2;
            }
            case "right" -> {
                if (spriteNumber == 1) image = right1;
                if (spriteNumber == 2) image = right2;
            }
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
