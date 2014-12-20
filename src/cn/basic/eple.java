package cn.basic;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import cn.filter.*;

public class eple extends JFrame{

	private static final long serialVersionUID = 1L;

	public eple(String msg,BufferedImage img){
		super(msg);
		JLabel label =new JLabel(new ImageIcon(img));
	    add(label);
	    
	    this.setSize(img.getWidth(),img.getHeight());
		this.setLocation(500,300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setVisible(true);
	}

	public static void main(String[] args){
		BufferedImage img = null;
		BufferedImage jb  = null;
		try{
//			img = ImageIO.read(new FileInputStream("/home/eple/DIP/1.bmp"));
			img = ImageIO.read(new FileInputStream("/home/eple/DIP/1.jpg"));
//			img = ImageIO.read(new FileInputStream("/home/eple/DIP/mb.jpg"));
//			img = ImageIO.read(new FileInputStream("/home/eple/DIP/cat.jpg"));
//			img = ImageIO.read(new FileInputStream("/home/eple/DIP/skin.jpg"));
//			img = ImageIO.read(new FileInputStream("/home/eple/DIP/o.jpg"));
//			img = ImageIO.read(new FileInputStream("/home/eple/DIP/f1.jpg"));
//			img = ImageIO.read(new FileInputStream("/home/eple/DIP/11.jpg"));
//			img = ImageIO.read(new FileInputStream("/home/eple/DIP/2.jpg"));
//			img = ImageIO.read(new FileInputStream("/home/eple/DIP/1.png"));
			jb = ImageIO.read(new FileInputStream("/home/eple/DIP/jb.jpg"));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
//		Image image = new Image(300,300);
//		image.test();
		Image image = new Image(img,1);
//		Image jbImage =new Image(jb,1);
//		image.reduce(2);
		new eple("原图",image.toImage());
		Image copy = image.clone();
		
//		Image skin1 =image.skinDetection(); // RGB 空间
//		new eple("RGB肤色检测",skin1.toImage());
//		Image skin =image.skinDet();		//YCbCr空间
//		new eple("YCbCr肤色检测",skin.toImage());
//		copy.reduce(2);;
//		new eple("缩小2倍",copy.toImage());
		
		double angle = 90;
		image.Rotation(angle);
		new eple("旋转"+angle+"度",image.toImage());
//		image.IterBinary();
//		image.Otsu();
//		image.Todouble();
		
		copy.Clockwise();
		new eple("顺时针旋转",copy.toImage());
//		copy = image.clone();
//		copy.inClockwise();
//		new eple("逆时针旋转",copy.toImage());
//		copy = image.clone();
//		copy.Symmetry();
//		new eple("翻转",copy.toImage());
		
//		new eple("直方图", image.getHist());
//		image.histequalization();
//		new eple("直方图均衡化",image.toImage());
//		new eple("直方图", image.getHist());
		
//		copy = image.clone();
//		Guass guass = new Guass(5, 1.5f);
//		copy.filter(Mask.Laplacian);
//		copy.filter(guass.mask);
//		new eple("高斯模糊",copy.toImage());
//		Image.save(copy.toImage());
		
//		image.filter(Mask.Laplacian);
//		new eple("Laplacian",image.toImage());
				
//		copy = image.clone();
//		copy.Erosion(Mask.Expand);
//		new eple("Erosion",copy.toImage());
//		
//		copy = image.clone();
//		copy.Expand(Mask.Expand);
//		new eple("Expand",copy.toImage());
//		
//		copy = image.clone();
//		copy.Erosion(Mask.Expand);
//		new eple("Erosion",copy.toImage());
		
//		copy = image.clone();
//		copy.sobel();
//		copy.Otsu();
//		new eple("sobel",copy.toImage());

//		copy = image.clone();
//		copy.sobelRed();
//		new eple("sobel",copy.toImage());
//		
//		copy = image.clone();
//		copy.Prewitt();
//		new eple("Prewitt",copy.toImage());
		
//		image.hough();
//		new eple("hough",image.toImage());	
		
//		int angle=image.houghAngle();
//		image.Rotation(angle);
//		new eple("hough",image.toImage());
		
//		oldPhoto f = new oldPhoto(copy); 
//		copy = f.filter();
//		new eple("old photo",copy.toImage());
		
//		Nega f = new Nega(copy); 
//		copy = f.filter();
//		new eple("Negative image",copy.toImage());
		
//		cameo f = new cameo(copy); 
//		copy = f.filter();
//		new eple("cameo",copy.toImage());
		
//		lineDrawing f = new lineDrawing(copy); 
//		copy = f.filter();
//		new eple("lineDrawing",copy.toImage());
		
//		oilPainting f = new oilPainting(copy); 
//		copy = f.filter();
//		new eple("Oil Painting",copy.toImage());
		
//		randomBrightness f = new randomBrightness(copy); 
//		copy = f.filter();
//		new eple("Random Brightness",copy.toImage());
		
//		Magnifier f = new Magnifier(copy); 
//		copy = f.filter();
//		new eple("Magnifier",copy.toImage());
		
//		hahaMirror f = new hahaMirror(copy); 
//		copy = f.filter();
//		new eple("hahaMirror",copy.toImage());
		
//		Neon f = new Neon(copy); 
//		copy = f.filter();
//		new eple("Neon",copy.toImage());
		
//		lomo f = new lomo(copy); 
//		copy = f.filter();
//		new eple("lomo",copy.toImage());
		
//		CM f1 = new CM(copy,ColorMatrix.toBGR); 
//		copy = f1.filter();
//		new eple("通过颜色矩阵实现的颜色反转",copy.toImage());
		
//		Molten f = new Molten(copy); 
//		copy = f.filter();
//		new eple("Molten",copy.toImage());
		
//		ice f = new ice(copy); 
//		copy = f.filter();
//		new eple("ice",copy.toImage());
		
//		ColorEdge f = new ColorEdge(copy); 
//		copy = f.filter();
//		new eple("ColorEdge",copy.toImage());
		
//		Whitening f = new Whitening(copy); 
//		copy = f.filter();
//		new eple("Whitening",copy.toImage());
		
//		ImageFusion f = new ImageFusion(copy,jbImage); 
//		copy = f.sub();
//		new eple("ImageFusion",copy.toImage());
		

		
		/*
		 * 实现径向模糊效果:http://blog.csdn.net/xoyojank/article/details/5146297
		 */
		System.out.println("run over!");
		
		/*
		 * 美白就是肤色检测加 增加亮度
		 */
		
		
	}
}
