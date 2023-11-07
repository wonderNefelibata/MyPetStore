package csu.web.mypetstore.common;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class Captcha {
    private final int w = 60;
    private final int h = 30;

    private final String[] fontNames = {"宋体","微软雅黑"};

    private final String codes = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";

    private Color bfColor = new Color(255,255,255);

    private String text;

    private Random r = new Random();

    private Color randomColor(){
        int red = r.nextInt(150);
        int blue = r.nextInt(150);
        int green = r.nextInt(150);
        return new Color(red,green,blue);
    }

    private Font randomFont() {
        int idx = r.nextInt(fontNames.length);
        String fontName = fontNames[idx];
        int style = r.nextInt(4);
        int size = r.nextInt(5) + 20;
        return new Font(fontName, style, size);
    }

    private char randomChar(){
        int idx = r.nextInt(codes.length());
        return codes.charAt(idx);
    }

    private BufferedImage createImage(){
        BufferedImage image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(this.bfColor);
        g2.fillRect(0,0,w,h);
        return image;
    }

    private void addNoise(Graphics2D g2d, int width, int height) {
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int size = random.nextInt(5);
            g2d.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
            g2d.fillRect(x, y, size, size);
        }
    }

    public BufferedImage getImage(){
        BufferedImage image = createImage();
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < 4;i++){
            String s = randomChar() + "";
            sb.append(s);
            float x = i * 1.0F * w /4;
            g2.setFont(randomFont());
            g2.setColor(randomColor());
            g2.drawString(s,x,h-5);
        }
        addNoise(g2,w,h);
        this.text = sb.toString();
        return image;
    }

    public String getText(){
        return text;
    }

    public static void output(BufferedImage image, OutputStream out)throws IOException {
        ImageIO.write(image,"JPEG",out);
    }
}