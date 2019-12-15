import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Class for viewing StegoImages
 * 
 * This provides the functions to create a SimpleCanvas
 * and displays Buffered Images in the SimpleCavnas
 * 
 * @author  Brandon Barker
 * @version 25/10/2019
 */
public class StegoViewer
{
    // DEFINE CONSTANT FOR VIEWER
    public static int IMG_WIDTH = 300;
    public static int IMG_HEIGHT = 300;
    public static int CAPTION_MARGIN = 50;
    public static int NUM_IMAGES = 4;
    public final static Color BACKGROUND_COLOR = Color.white;
    public final static Color FOREGROUND_COLOR = Color.black;

    // INITIALISE VIEWER
    private SimpleCanvas viewer;

    /**
     * CONSTRUCT VIEWER
     * CREATES A STEGOVIEWER USING THE THE SIMPLE CANVAS CLASS
     */
    public StegoViewer()
    {
        // MAKE NEW VIEWER WITH THE DEFINED CONSTANT
        viewer = new SimpleCanvas("Steganography Viewer", IMG_WIDTH*NUM_IMAGES
        , IMG_HEIGHT + CAPTION_MARGIN, Color.white);
    }

    /**
     * METHOD TO ADD IMAGE TO VIEWER
     * ADDS IMAGE TO SIMPLECANVAS AND CENTERS TEXT
     */
    public void displayImage(BufferedImage image, int window)
    {
        // DRAW IMAGE
        viewer.drawImage(image, window*IMG_WIDTH, 0);

        // GET CAPTION FOR WINDOW NUMBER
        String caption;
        switch (window) {
            case 0:
            caption = "Cover";
            break;
            case 1:
            caption = "Secret";
            break;
            case 2:
            caption="Encrypted Message";
            break;
            case 3:
            caption="Decrypted Secret";
            break;
            default :
            caption="";
            break;
        }

        // GET FONT METRICS
        Font font = viewer.getFont();

        // ONLY WAY TO GET FONT METRICS WITHOUT CHANGING SIMPLE CANVAS
        BufferedImage tempImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics graphic = tempImage.getGraphics();
        graphic.setFont(font);
        FontMetrics fontMetric = graphic.getFontMetrics(font);

        // GET STRING LENGTH
        int length = fontMetric.stringWidth(caption);

        // CENTER TEXT UNDER IMAGE
        viewer.drawString(caption, window*IMG_WIDTH + IMG_WIDTH/2 - length/2,
            325, FOREGROUND_COLOR);
    }

}
