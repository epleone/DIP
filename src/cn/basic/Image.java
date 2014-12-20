package cn.basic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;

/**
 * 
 * @author eple 2014 july 22
 *
 */

public class Image{
	
	public int h;
	public int w;
	public int[] data;
	public int[] red;
	public int[] green;
	public int[] blue;
	public boolean gray;
	
	public Image(BufferedImage img){
		
		this.h = img.getHeight();
		this.w = img.getWidth();
		
		this.data = img.getRGB(0, 0, w, h, null, 0, w);
		this.gray = false;
		
		this.red = new int[w*h];
		this.green = new int[w*h];
		this.blue = new int[w*h];
		
		toGray();
	}
	
	public Image(BufferedImage img,int gray){
		
		this.h = img.getHeight();
		this.w = img.getWidth();
		this.data = img.getRGB(0, 0, w, h, null, 0, w);
		
		this.red = new int[w*h];
		this.green = new int[w*h];
		this.blue = new int[w*h];
		
		int end = h*w;
		
		for (int i = 0; i < end; i++) {
            
                int c = this.data[i];
                this.red[i]   = (c >> 16) & 0xFF;
                this.green[i] = (c >> 8) & 0xFF;
                this.blue[i]  = (c >> 0) & 0xFF;
        }
		
		this.gray = false;

	}

	public Image(int[] data,int h,int w){
		this.data = (data == null) ? new int[w*h]:data;
		this.h = h;
		this.w = w;
		
		this.red = new int[w*h];
		this.green = new int[w*h];
		this.blue = new int[w*h];
		
		if(data != null){
			int end = h*w;
			for (int i = 0; i < end; i++) {
	                int c = this.data[i];
	                this.red[i]   = (c >> 16) & 0xFF;
	                this.green[i] = (c >> 8) & 0xFF;
	                this.blue[i]  = (c >> 0) & 0xFF;
	        }
		}
		
		this.gray = false;
	}
	
	public Image(int h,int w){
		this(null,h,w);
	}
	
	/*
	 * return an image copy
	 * @see java.lang.Object#clone()
	 */
	public Image clone(){
		Image im = new Image(this.h,this.w);
		im.gray = this.gray;
		System.arraycopy(this.data, 0, im.data, 0, im.data.length);
		System.arraycopy(this.red, 0, im.red, 0, im.red.length);
		System.arraycopy(this.green, 0, im.green, 0, im.green.length);
		System.arraycopy(this.blue, 0, im.blue, 0, im.blue.length);
        return im;
	}
	
	public BufferedImage toImage(){
		BufferedImage image = new BufferedImage(this.w, this.h, BufferedImage.TYPE_INT_ARGB);//
		int[] d= new int[w*h];
		for(int i=0;i<this.h;i++){
			for(int j=0;j<this.w;j++){
				if(this.gray){
					d[j+i*this.w] = (255<<24)|(data[j+i*this.w]<<16)|(data[j+i*this.w]<<8)|(data[j+i*this.w]);
				}else{
					d[j+i*this.w] = data[j+i*this.w];
				}
					
//				int rgb = (255<<24)|(data[j+i*this.w]<<16)|(data[j+i*this.w]<<8)|(data[j+i*this.w]);
//				image.setRGB(i, j, rgb);
			}
		}
		image.setRGB( 0, 0, w, h, d, 0, w );
		
		return image;
	}
	
	/*
	 * Enlarge doubled
	 */
	public void Todouble(){
		int[] doubleData = new int[2*w*2*h];
		for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
            	doubleData[2*x + 2*y *2* w] = data[x + y * w];
            }
		}
		
		this.h = 2*h;
		this.w = 2*w;
		for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
            	if(y%2 == 1)
            		doubleData[x + y*w] = doubleData[x + (y-1)*w];
            	if(x%2 == 1)
            		doubleData[x + y*w] = doubleData[x-1 + y*w];
            }
		}
		this.data = doubleData;
		getRGB();
		
	}
	
	/*
	 * Image reduction
	 */
	public void reduce(int a){
		int nw = w/a;
		int nh = h/a;
		int[] d = new int[nw*nh];
		
		for (int y = 0; y < nh; y++) {
            for (int x = 0; x < nw; x++) {
            	d[x + y*nw] = data[a*x + a*y * w];
            }
		}
		this.h = nh;
		this.w = nw;
		this.data = d;
		getRGB();
	}
	
	/*
	 * Image Rotation,a is angle
	 * x=cos(angle)*x-sin(angle)*y;
	 * y=cos(angle)*y+sin(angle)*x;
	 */
	public void Rotation(double degree){
		
		degree = Math.toRadians(degree);
        int sw = (int) Math.sqrt(w*w +h*h);
        int sh = sw;
        
        int ox = w/2;
        int oy = h/2;
        
		int[] d = new int[sw*sh];
		
//		for(int y = 0 ;y< sh ; y++){
//			for (int x = 0;x<sw; x++){
//				double x1 = Math.cos(degree)*(x-ox) - Math.sin(degree)*(y-oy);
//				double y1 = Math.sin(degree)*(x-ox) + Math.cos(degree)*(y-oy);
//				
//				int i = (int) Math.floor(x1); //向下取整
//				int j = (int) Math.floor(y1); //向下取整
//				if(i<0 || i >=h-1 || j<0 || j>= w-1){
//					d[x + y*sw] = 0;
//				}else{
//					//双线性插值
//					double ge = (i+1-x1)*data[j + i* w] + (x1 -i)*data[j + (i+1)* w];
//					double gf = (i+1-x1)*data[j+1 + i* w] + (x1 -i)*data[j+1 + (i+1)* w];
//					d[x + y*sw] = (int)( (j+1-y1)*ge + (y1 - j)*gf );
//				}
//				
//			}
//		}
		
		//从原图向旋转图的映射
		for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
            	int x1 = (int)(Math.cos(degree)*(x-ox) + Math.sin(degree)*(y-oy));
            	int y1 = (int)(Math.cos(degree)*(y-oy) - Math.sin(degree)*(x-ox));
            	d[x1-sw/2+ (y1+sh/2)* sw] = data[x + y * w];
            }
		}
		
		this.data = d;
		this.w = sw;
		this.h = sh;
	}
	
	/*
	 * 逆时针旋转 Counterclockwise rotation
	 */
	public void inClockwise(){
		
		int[] d = new int[w*h];
		
		for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
            	
            	d[y+ (w-x-1)* h] = data[x + y * w];
            }
		}

		this.data = d;
        int sh = this.w;
		this.w = h;
		this.h = sh;
	}
	
	/*
	 * 顺时针旋转 Clockwise
	 */
	public void Clockwise(){
		
		int[] d = new int[w*h];
		
		for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
            	
            	d[h-1-y+ x* h] = data[x + y * w];
            }
		}

		this.data = d;
        int sh = this.w;
		this.w = h;
		this.h = sh;
	}
	
	/*
	 * 对称，翻转
	 */
	public void Symmetry(){
        
		int[] d = new int[w*h];
		
		for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {

            	d[w-x-1+ y* w] = data[x + y * w];
            }
		}

		this.data = d;
	}

	/*
	 * Iterative binarization
	 */
	public void IterBinary(){
		toGray();
		
		int Threshold = 128;
		int preThreshold = 256;
		int end = h*w;
		
		while (Math.abs(Threshold-preThreshold) > 4){
			int s1 = 0;
			int s2 = 0;
			int f1 = 0;
			int f2 = 0;
			
			for (int i = 0; i < end; i++) {
            	if(data[i] < Threshold){
            		s1 += data[i];
            		f1++;
            	}else{
            		s2 += data[i];
            		f2++;
            	}
			}
			
			preThreshold = Threshold;
			Threshold = (int)((s1/f1+s2/f2)/2);
		}
		
		for (int i = 0; i < end; i++) {
			
        	if(data[i] < Threshold){
        		data[i] = 0;
        	}else{
        		data[i] = 255;
        	}
		}
					
	}
	/*
	 * the most simple binarization
	 */
	public void SimBinary(){
		toGray();
		int Threshold = 128;
		int end = h*w;
		for (int i = 0; i < end; i++) {
			
        	if(data[i] < Threshold){
        		data[i] = 0;
        	}else{
        		data[i] = 255;
        	}
		}
					
	}
	
	/*
	 * Otsu thresholding
	 */
	public void Otsu(){
		toGray();
		int num = h*w;
		int[] hist = hist();
		int sum = math.sum(hist);
		double[] res = new double[256];
		double m1=0,m2=0,w1=0,w2=0;
		
		for(int k=0;k<256;k++){
			for(int i=0;i<k;i++){
				m1 +=hist[i];
			}
			w1 = m1/num;
			w2 = 1-w1;
			m2 = (sum-m1)/(255-k);
			m1 = m1/(k+1);
			res[k] = w1*w2*Math.abs(m1 - m2)*Math.abs(m1 - m2);
		}
		
		int Threshold = math.maxIndex(res);
		
		int end = h*w;
		for (int i = 0; i < end; i++) {
			
        	if(data[i] < Threshold){
        		data[i] = 0;
        	}else{
        		data[i] = 255;
        	}
		}
	}
	
	
	public int[] hist(){
		toGray();
		int[] hist = new int[256];
		int len = h*w;
		
		for(int i=0;i<len;i++)
			hist[data[i]]++;
		
		return hist;
	}
	
	/*
	 * return Histogram picture
	 */
	public BufferedImage getHist(){
		toGray();
		int[] intensity = hist();
		int size = 300;
		BufferedImage pic = new BufferedImage(size,size, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = pic.createGraphics();  
        g2d.setPaint(Color.BLACK);  
        g2d.fillRect(0, 0, size, size);  
        g2d.setPaint(Color.WHITE);  
        g2d.drawLine(5, 250, 265, 250);  	
        g2d.drawLine(5, 250, 5, 5); 	 	
           
        g2d.setPaint(Color.GREEN);  
        int max = math.findMaxValue(intensity);  
        float rate = 200.0f/((float)max);  
        int offset = 2;  
        for(int i=0; i<intensity.length; i++) {  
            int frequency = (int)(intensity[i] * rate);  
            g2d.drawLine(5 + offset + i, 250, 5 + offset + i, 250-frequency);  
        }  
           
        g2d.setPaint(Color.RED);  
        g2d.drawString("", 100, 270); 
        return pic;
	}
	
	
	/*
	 * histogram equalization
	 */
	public void histequalization(){
		toGray();
		double[] hist = math.Norm(hist());
		int end = h*w;
		for (int i= 0; i < end; i++) {
                this.data[i] = (int)(math.sum(hist, this.data[i])*255);             
        }
	}
	
	
	private int setRed() {
		return (255<<24)|(255<<16)|(0<<8)|0;
	}
	
	private int setGreen() {
		return (255<<24)|(0<<16)|(255<<8)|0;
	}
	
	private int setBlue() {
		return (255<<24)|(0<<16)|(0<<8)|255;
	}
	
	/*
	 * to gray
	 */
	public void toGray(){
		
		if(!gray){
			this.gray = true;
//			for (int y = 0; y < h; y++) {
//	            for (int x = 0; x < w; x++) {
//	            	
//	            	/* android 中的获取方式RGB分量  
//	                pixelsA = Color.alpha(color);  
//	                pixelsR = Color.red(color);  
//	                pixelsG = Color.green(color);  
//	                pixelsB = Color.blue(color);
//	                
//	                // 根据新的RGB生成新像素  
//            		newPixels[i] = Color.argb(pixelsA, pixelsR, pixelsG, pixelsB);
//	                */
//	            	
//	                int c = this.data[x + y * w];
//	                int R = (c >> 16) & 0xFF;
//	                int G = (c >> 8) & 0xFF;
//	                int B = (c >> 0) & 0xFF;
//	                this.data[x + y * w] = (int)(0.3f*R + 0.59f*G + 0.11f*B); //to gray
//	                
//	            }
//	        }
			
			int end = h*w;
			for (int i = 0; i < end; i++) {
	            
	                int c = this.data[i];
	                int R = (c >> 16) & 0xFF;
	                int G = (c >> 8) & 0xFF;
	                int B = (c >> 0) & 0xFF;
	                this.red[i] = R;
	                this.green[i] =G;
	                this.blue[i] = B;
	                this.data[i] = (int)(0.3f*R + 0.59f*G + 0.11f*B); //to gray

	        }
		}
	}
	
	public void getRGB(){
		
			int end = h*w;
			for (int i = 0; i < end; i++) {
	            
	                int c = this.data[i];
	                int R = (c >> 16) & 0xFF;
	                int G = (c >> 8) & 0xFF;
	                int B = (c >> 0) & 0xFF;
	                this.red[i] = R;
	                this.green[i] =G;
	                this.blue[i] = B;
	        }
		}
	
	
	/*
	 * just for test
	 */
	public void test() {
		for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
            	if(x < w/3){
            		data[x + y * w] = setRed();
            	}else if(x > 2*w/3){
            		data[x + y * w] = setBlue();
            	}else{
            		data[x + y * w] = setGreen();
            	}
            }
		}
	}
	
	/*
	 * filter
	 */
	public void filter(double[][] mask) {
		toGray();
		int mh = mask.length;
		int mw = mask[1].length;
		int sh = (mh+1)/2;
		int sw = (mw+1)/2;
		double maskSum = math.sum(mask);
		int[] d= new int[w*h];
		
		for(int i=(mh-1)/2+1;i<h-(mh-1)/2;i++){
			for(int j=(mw-1)/2+1;j<w-(mw-1)/2;j++){
				
			   int s = 0;
		       for(int m=0; m<mh ; m++){
		    	   for(int n=0;n<mw;n++){
		    		   s = s + (int)(mask[m][n]*this.data[j+n-sw +(i+m-sh)*w]);
		    	   }
		       }
		       
		       if(maskSum != 0)
		    	   s /= maskSum;
		       
		       if(s < 0)
		    	   s =0;
		       if(s > 255)
		    	   s = 255;
		       d[j + i * w] = s;   
			}
		}
		
		this.data = d;
		//Otsu(); //binarization
	}
	
	/*
	 * Image expansion operation,black > white
	 */
	public void Expand(int[][] mask){
		IterBinary();
		int mh = mask.length;
		int mw = mask[1].length;
		int sh = (mh+1)/2;
		int sw = (mw+1)/2;
		
		int[] d= new int[w*h];
		
		for(int i=(mh-1)/2+1;i<h-(mh-1)/2;i++){
			for(int j=(mw-1)/2+1;j<w-(mw-1)/2;j++){
			   int s = 0;
			   
		       for(int m=0; m<mh ; m++){
		    	   for(int n=0;n<mw;n++){
		    		   if(mask[m][n]*this.data[j+n-sw +(i+m-sh)*w] == 255)
		    			   s = 255;
		    	   }
		       }
		       
		       d[j + i * w] = s;   
			}
		}
		
		this.data = d;
	}
	
	/*
	 * Image erosion operation,black > white
	 */
	public void Erosion(int[][] mask){
		
		IterBinary();
		int mh = mask.length;
		int mw = mask[1].length;
		int sh = (mh+1)/2;
		int sw = (mw+1)/2;
		
		int[] d= new int[w*h];
		
		for(int i=(mh-1)/2+1;i<h-(mh-1)/2;i++){
			for(int j=(mw-1)/2+1;j<w-(mw-1)/2;j++){
			   int s = 0;
			   
		       for(int m=0; m<mh ; m++){
		    	   for(int n=0;n<mw;n++){
		    		   if(mask[m][n]*255 == this.data[j+n-sw +(i+m-sh)*w])
		    			   s++;
		    	   }
		       }
		       
		       d[j + i * w] = (s==mh*mw)?255:0;   
			}
		}
		
		this.data = d;
	}
	
	/*
	 * 开运算是先腐蚀，再膨胀
	 * 闭运算是先膨胀，再腐蚀
	 */
	
	
	public void sobel(){
		toGray();
		int[] d= new int[w*h];
		for(int j=1;j<h-1;j++){
			for(int i=1;i<w-1;i++){
				int s1 = data[i-1+(j+1)*w]+2*data[i+(j+1)*w]+data[i+1+(j+1)*w]-data[i-1+(j-1)*w]-2*data[i+(j-1)*w]-data[i+1+(j-1)*w];
		        int s2 = data[i+1+(j-1)*w]+2*data[i+1+(j)*w]+data[i+1+(j+1)*w]-data[i-1+(j-1)*w]-2*data[i-1+(j)*w]-data[i-1+(j+1)*w];
		        int s  = Math.abs(s1)+Math.abs(s2);
		        if(s < 0)
			    	s =0;
		        if(s > 255)
		    	    s = 255;
		        d[i + j * w] = s;
			}    
		}
		
		this.data = d;
		//Otsu(); //binarization
	}
	
	public void sobelRed(){
		toGray();
		int[] d= new int[w*h];
		for(int j=1;j<h-1;j++){
			for(int i=1;i<w-1;i++){
				int s1 = red[i-1+(j+1)*w]+2*red[i+(j+1)*w]+red[i+1+(j+1)*w]-red[i-1+(j-1)*w]-2*red[i+(j-1)*w]-red[i+1+(j-1)*w];
		        int s2 = red[i+1+(j-1)*w]+2*red[i+1+(j)*w]+red[i+1+(j+1)*w]-red[i-1+(j-1)*w]-2*red[i-1+(j)*w]-red[i-1+(j+1)*w];
		        int s  = Math.abs(s1)+Math.abs(s2);
		        if(s < 0)
			    	s =0;
		        if(s > 255)
		    	    s = 255;
		        d[i + j * w] = (255<<24)|(s<<16)|(0<<8)|(0);;
			}    
		}
		this.gray = false;
		this.data = d;
		//Otsu(); //binarization
	}
	
	public void Prewitt(){
		toGray();
		int[] d= new int[w*h];
		for(int j=1;j<h-1;j++){
			for(int i=1;i<w-1;i++){
				int s1 = data[i-1+(j+1)*w]+data[i+(j+1)*w]+data[i+1+(j+1)*w]-data[i-1+(j-1)*w]-data[i+(j-1)*w]-data[i+1+(j-1)*w];
		        int s2 = data[i+1+(j-1)*w]+data[i+1+(j)*w]+data[i+1+(j+1)*w]-data[i-1+(j-1)*w]-data[i-1+(j)*w]-data[i-1+(j+1)*w];
		        int s  = Math.abs(s1)+Math.abs(s2);
		        if(s < 0)
			    	s =0;
		        if(s > 255)
		    	    s = 255;
		        d[i + j * w] = s;
			}    
		}
		
		this.data = d;
	}
	
	
	/*
	 * the hough Transform
	 */
	public void hough(){
		Image im = this.clone();
		im.sobel();
		im.IterBinary();
		
		int ro = (int)Math.sqrt(h*h+w*w);
		int theta = 180;
		int[][] hist = new int[ro][theta];
		
		for(int k=0;k<theta;k++){
			for(int i=0;i<h;i++){
				for(int j=0;j<w;j++){
					if(im.data[j+i*w] != 0){
						int rho=(int)(j*Math.cos(k*Math.PI/(theta*2))+i*Math.sin(k*Math.PI/(theta*2)));
			            hist[rho][k]++;
					}      
				}
			}
		}
		
		ArrayList<h> index = math.maxIndex(hist,70);
		
		for(int k =0;k<index.size();k++){
			
			double resTheta = index.get(k).angle*Math.PI/(theta*2);

		    for(int i=0;i<h;i++){
		    	for(int j=0;j<w;j++){
		    		int rho = (int)(j*Math.cos(resTheta)+i*Math.sin(resTheta));
		            if(im.data[j+i*w] != 0 && rho == index.get(k).ro){
		            	data[j+i*w] = setRed();
		            }else{
		            	data[j+i*this.w] = (255<<24)|(data[j+i*this.w]<<16)|(data[j+i*this.w]<<8)|(data[j+i*this.w]);
		            } 	
		    	}   
		    }
		}
	    
	    this.gray = false;
	}
	
	/*
	 * return the most line's angle By hough transform
	 */
	public int houghAngle(){
		Image im = this.clone();
		im.sobel();
		im.IterBinary();
		
		int ro = (int)Math.sqrt(h*h+w*w);
		int theta = 180;
		int[][] hist = new int[ro][theta];
		
		for(int k=0;k<theta;k++){
			for(int i=0;i<h;i++){
				for(int j=0;j<w;j++){
					if(im.data[j+i*w] != 0){
						int rho=(int)(j*Math.cos(k*Math.PI/(theta*2))+i*Math.sin(k*Math.PI/(theta*2)));
			            hist[rho][k]++;
					}      
				}
			}
		}
		
		int[] index = math.maxIndex(hist);
		return index[1]*90/theta;
	}
	
	
	/*
	 * Skin detection
	 * link:http://www.cnblogs.com/Imageshop/p/3265353.html
	 */
	public Image skinDet(){
		if(gray)
			return this;
		
		Image copy = this.clone();
		int[] d = new int[h*w];
		for(int i =0;i<this.h;i++){
			for(int j=0;j<this.w;j++){
				int R = red[j+i*w];
				int G = green[j+i*w];
				int B =  blue[j+i*w];
				//double Y  = 0.299*R+0.587*G+0.114*B;
				double Cb = 128 - 0.168736*R - 0.331264*G + 0.5*B;
				double Cr = 128 + 0.5*R - 0.418688*G - 0.081312*B;
				if(Cb > 77 && Cb<127 && Cr>133 && Cr<173)
					d[j+i*w] = 255;
			}
		}
		copy.data = d;
		copy.gray = true;
		return copy;
	}
	
	public Image skinDetection(){
		if(gray)
			return this;
		
		Image copy = this.clone();
		int[] d = new int[h*w];
		for(int i =0;i<this.h;i++){
			for(int j=0;j<this.w;j++){
				if(red[j+i*w] >95)
				if(green[j+i*w]>40)
				if(blue[j+i*w]>20)
				if(red[j+i*w] >green[j+i*w])
				if(red[j+i*w] >blue[j+i*w])
				if(check(red[j+i*w], green[j+i*w], blue[j+i*w]))
				if(Math.abs(red[j+i*w]-green[j+i*w])>15)
					d[j+i*w] = 255;
			}
		}
		copy.data = d;
		copy.gray = true;
		return copy;
	}
	
	private boolean check(int R,int G,int B){
		int max = R;
		int min = R;
		max = max > G?max:G;
		max = max > B?max:B;
		min = min < G?min:G;
		min = min < B?min:B;
		return (max - min>15)?true:false;
	}
	/*
	 * save image to /home/eple/DIP/save
	 */
	public static void save(BufferedImage newImage){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
        String name = sdf.format(new Date());
        File path = FileSystemView.getFileSystemView().getHomeDirectory();
        String format = "png";
        File f = new File(path + File.separator + name + "." + format);
        try {
            ImageIO.write(newImage, format, f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



/*
 * to save the hough transform's results
 */
class h{
	int ro;
	int angle;
	
	public h(int r,int a){
		this.ro = r;
		this.angle = a;
	}
}
