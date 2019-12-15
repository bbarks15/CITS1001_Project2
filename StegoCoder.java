/**
 * Class for encrypting and decrypting StegoImages
 * 
 * Methods perfom the operations needed to encrypt
 * and decrypt StegoImages
 * 
 * @author  Brandon Barker
 * @version 25/10/2019
 */
public class StegoCoder
{
    /**
     * EMPTY CONSTRUCTOR
     */
    public StegoCoder()
    {
    }
    
    /**
     * ENCRYPT METHOD
     * RETURN A NEW MESSAGE BY HIDING A SECRET IN THE COVER IMAGE
     */
    public StegoImage encrypt(StegoImage cover, StegoImage secret)
    {
        // PERFORM ENCRYPT OPERATIONS ON COVER
        cover.clearLowBit();
        secret.setZeroOne();
        cover.mergeImage(secret);

        // RETURN COVER
        return cover;
    }

    /**
     * DECRYPT METHOD
     * RETURN A DECRYPTION OF THE RECIEVED MESSAGE BY EXTRACING AND THEN
     * UPSCALING ITS LOW BITS
     */
    public StegoImage decrypt(StegoImage message)
    {
        // PERFORM DECRYPT OPERATIONS ON MESSAGE
        message.setToLowBit();
        message.setBlackWhite();

        // RETURN MESSAGE
        return message;
    }

}
