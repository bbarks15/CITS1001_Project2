/**
 * Class for creating and modifiying StegoImages
 * 
 * This provides the functions to encrypt and decrypt 
 * images
 * 
 * @author  Brandon Barker
 * @version 25/10/2019
 */

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class StegoImage
{
    // INITIALISE IMAGE, WIDTH AND HEIGHT
    private BufferedImage image;
    private int width;
    private int height;

    /**
     * CONSTRUCTOR FOR STEGOIMAGE
     * CREATES A STEGOIMAGE BY READING AN IMAGE FROM A FILE
     * GETS WIDTH AND HEIGHT
     */
    public StegoImage(String filename)
    {
        // READ IMAGE
        try {
            image = ImageIO.read(new File(filename));
        } catch(IOException exception) {}

        // GET WIDTH AND HEIGHT
        width = image.getWidth();
        height = image.getHeight();
    }

    /**
     * EXTRA FEATURE
     * CONSTRUCTOR TO MAKE MESSAGE FROM TEXT
     * MAKES STEGO IMAGE FROM STRING WITH SPECIFIED WIDTH AND HEIGHT
     */
    public StegoImage(String message, int inputwidth, int inputheight)
    {
        // CHECK IF VALID DIMENSIONS
        if (inputheight > 0 && inputwidth > 0) {
            // ASSIGN WIDTH AND HEIGHT
            width = inputwidth;
            height = inputheight;

            // WRITE IMAGE
            image = text2Image(message);
        }
    }

    /**
     * GETTER METHOD FOR IMAGE
     * RETURNS THE BUFFEREDIMAGE
     */
    public BufferedImage getImage()
    {
        return image;
    }

    /**
     * GETTER METHOD FOR WIDTH
     * RETURNS THE WIDTH
     */
    public int getWidth()
    {
        return width;
    }

    /**
     *  GETTER METHOD FOR HEIGHT
     *  RETURNS HEIGHT
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * SCALE IMAGE METHOD
     * MODIFIES THE IMAGE BY CHANGING EVERY PIXEL BY SCALING ITS RGB
     */
    public void scaleImage(int div, int mult)
    {
        // LOOP THROUGH EVERY PIXEL IN THE IMAGE
        for (int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++){
                // GET RGB FROM PIXEL
                Color rgb = new Color(image.getRGB(x, y));
                // SCALE RGB
                rgb = new Color(rgb.getRed()/div*mult,
                    rgb.getGreen()/div*mult,
                    rgb.getBlue()/div*mult);
                // CHANGE PIXEL TO SCALED VALUE
                image.setRGB(x,y,rgb.getRGB());
            }
        }
    }

    /**
     * CLEARLOWBIT METHOD
     * MODIFIES THE IMAGE BY SETTING THE LOWEST BIT OF EACH PIXEL COLOR TO 0
     */
    public void clearLowBit()
    {
        // LOOP THROUGH EVERY PIXEL IN THE IMAGE
        for (int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++){
                // GET RGB FROM PIXEL
                Color rgb = new Color(image.getRGB(x, y));
                // CLEAR LOWBIT FROM PIXEL
                rgb = new Color(rgb.getRed()/2*2,
                    rgb.getGreen()/2*2,
                    rgb.getBlue()/2*2);
                // CHANGE PIXEL TO LOWBIT CLEARED VALUE
                image.setRGB(x,y,rgb.getRGB());
            }
        }
    }

    /**
     * SETZEROONE METHOD
     * MODIFIES THE IMAGE BY THRESHOLDING ITS PIXELS TO 0 OR 1 IN EACH PIXEL
     * COLOR
     */
    public void setZeroOne()
    {
        // LOOP THROUGH EVERY PIXEL IN THE IMAGE
        for (int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++){
                // GET RGB FROM PIXEL
                Color rgb = new Color(image.getRGB(x, y));
                // SET EACH PIXEL TO 0 OR 1
                rgb = new Color(rgb.getRed()/128*1,
                    rgb.getGreen()/128*1,
                    rgb.getBlue()/128*1);
                // CHANGE PIXEL TO MODIFIED VALUE
                image.setRGB(x,y,rgb.getRGB());
            }
        }

    }

    /**
     * SETBLACK WHITE METHOD
     * MODIFIES THE IMAGE BY UPSCALING EACH 0,1 PIXEL TO A GREYSCALE FOR
     * VIEWING
     */
    public void setBlackWhite()
    {
        // LOOP THROUGH EACH PIXEL OF THE IMAGE
        for (int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++){
                // GET RGB FROM PIXEL
                Color rgb = new Color(image.getRGB(x, y));
                // GRAYSCALE THE PIXEL
                rgb = new Color(rgb.getRed()*255,
                    rgb.getGreen()*255,
                    rgb.getBlue()*255);
                // CHANGE THE PIXEL TO THE MODIFIED VALUE
                image.setRGB(x,y,rgb.getRGB());
            }
        }
    }

    /**
     * SETTOLOWBIT METHOD
     * MODIFIES THE IMAGE BY SETTING EACH PIXEL TO JUST ITS LOW BIT VALUE
     * (0 OR 1)
     */
    public void setToLowBit()
    {
        // LOOP THROUGH EACH PIXEL OF THE IMAGE
        for (int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++){
                // GET RGB FROM PIXEL
                Color rgb = new Color(image.getRGB(x, y));
                // SET LOW BIT
                rgb = new Color(rgb.getRed()%2,
                    rgb.getGreen()%2,
                    rgb.getBlue()%2);
                // CHANGE THE PIXEL TO THE MODIFIED VALUE
                image.setRGB(x,y,rgb.getRGB());
            }
        }

    }

    /**
     * MERGEIMAGE METHOD
     * MODIFIES THE IMAGE TO BECOME THE COMBINATION OF IMAGE AND NEWIMAGE BY
     * ADDING EACH OF THEIR RGB PIXELS
     */
    public void mergeImage(StegoImage newimage)
    {
        // ENSURE IMAGES ARE THE SAME SIZE
        if (newimage.getWidth() == image.getWidth()
        && newimage.getHeight() == newimage.getHeight()) {
            // LOOP THROUGH EACH PIXEL OF THE IMAGE
            for (int x = 0; x < width; x++) {
                for(int y = 0; y < height; y++){
                    // GET RGB FROM NEW IMAGE PIXEL
                    Color new_image_rgb = new Color(newimage.image.getRGB(x, y)
                        );
                    // GET GRB FROM IMAGE PIXEL
                    Color image_rgb = new Color(image.getRGB(x, y));
                    // COMBINE IMAGE AND NEWIMAGE RGB
                    Color rgb = new Color(
                            new_image_rgb.getRed()+image_rgb.getRed(),
                            new_image_rgb.getGreen()+image_rgb.getGreen(),
                            new_image_rgb.getBlue()+image_rgb.getBlue());
                    // CHANGE THE PIXEL TO THE MODIFIED VALUE
                    image.setRGB(x,y,rgb.getRGB());
                }
            }
        }
    }

    /**
     * SAVEIMAGE METHOD
     * SAVES THE BUFFERED IMAGE TO A SPECIFIED FILENAME
     */
    public void saveImage(String filename)
    {
        // WRITES IMAGE TO FILENAME.jpg
        try {
            ImageIO.write(image, "jpg", new File(filename + ".jpg"));
        } catch(IOException exception) {}
    }

    /**
     * TEXT2IMAGE METHOD
     * WRITES THE STRING TO A BUFFFERED IMAGE WHICH IS RETURNED
     */
    public BufferedImage text2Image(String message)
    {
        // MAKE BUFFERED IMAGE
        BufferedImage newimage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // INITIALISE FONT
        int fontsize = 15;
        Font font = new Font("Calibri", Font.PLAIN, fontsize);

        // SET GRAPHICS OF IMAGE
        Graphics graphics = newimage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);
        graphics.setFont(font);

        // GET FONT METRICS FOR STRING
        FontMetrics fontinfo = graphics.getFontMetrics(font);
        int stringlength = fontinfo.stringWidth(message);

        // FORMATTING OF TEXT

        // FITS ON ONE LINE
        if (stringlength + 30 < width ) {
            // INCREASES FONT UNTIL STRING FILLS LINE
            while (stringlength + 30 < width) {
                font = new Font("Calibri", Font.PLAIN, fontsize);
                graphics.setFont(font);
                fontinfo = graphics.getFontMetrics(font);
                stringlength = fontinfo.stringWidth(message);
                fontsize++;
            }
            System.out.println(message);
            // DRAWS MESSAGE
            graphics.drawString(message, width/2 - stringlength/2, height/2);
        }
        // IF FITS ON MULTIPLE LINES
        else {
            // SPLIT MESSAGE
            String[] words = message.split("\\W+");

            // GET LINES
            int nlines = stringlength/width + 1;

            //MAKE ARRAY FOR LINES
            String[] lines = new String[nlines];

            // INITIALISE VARIABLES FOR LOOP
            int linelength = 0;
            int line = 0;

            // ADDS WORDS TO LINE UNTIL LINE IS CLOSE TO WIDTH
            // THEN GOES TO NEXT LINE
            for (String word : words) {
                // LINE IS FULL
                if(linelength + 30 + fontinfo.stringWidth(word) > width) {
                    linelength = 0;
                    lines[++line] = word + " ";
                    linelength += fontinfo.stringWidth(word + " ");
                }
                // LINE IS NOT FULL
                else if (linelength != 0) {
                    linelength += fontinfo.stringWidth(word + " ");
                    lines[line] += word + " ";
                }
                // NO WORDS IN LINE
                // TO REMOVE NULL FROM 1ST POSISTION IN ARRAY
                else {
                    linelength += fontinfo.stringWidth(word + " ");
                    lines[line] = word + " ";
                }

            }
            for (String string : lines) {
                System.out.println(string);
            }
            // GET LINE HEIGHT
            int lineheight = fontinfo.getHeight();

            //DRAW EACH LINE ONE BY ONE
            for(int i = 0; i <= line; i++) {
                graphics.drawString(lines[i], 15 ,lineheight*i+20);
            }
        }
        // RETURN THE IMAGE WITH MESSAGE DRAWN
        return newimage;
    }

}

