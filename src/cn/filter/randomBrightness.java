package cn.filter;

import cn.basic.Image;
import cn.basic.math;

public class randomBrightness implements filt {

	private Image img;
	
	public randomBrightness(Image img){
		this.img = img;
	}

	@Override
	public Image filter() {
		
		int[] d = new int[this.img.w*this.img.h]; //must be writed as this.not int[] d = this.img.data;
		int cenX = (int)(Math.random()*this.img.w);
		int cenY = (int)(Math.random()*this.img.h);
		int therash = this.img.w/6;
		for (int y = 0; y < this.img.h ; y++) {
            for (int x = 0; x < this.img.w; x++) {
            	d[x + y * this.img.w] = getRGB(cenX,cenY,x,y,therash);
            }
        }
		
		this.img.data = d;
		return this.img;
	}

	
	private int getRGB(int cenX,int cenY,int x,int y,int therash){
		int rgb =0;
		int c = this.img.data[x + y * this.img.w];
		int R = (c >> 16) & 0xFF;
        int G = (c >> 8) & 0xFF;
        int B = (c >> 0) & 0xFF;
        double maxDistance = Math.sqrt(2*therash * therash);
        
		if(Math.sqrt((cenX-x)*(cenX-x) +(cenY-y)*(cenY-y)) < therash){
			double scale = 1.0 - getDistance(cenX, cenY, x, y)/maxDistance;
			scale = scale*scale;
			R += scale*255;
			G += scale*255;
			B += scale*255;
			rgb = (255 << 24) | (math.st(R) << 16) | (math.st(G) << 8) | math.st(B);
		}
		else{
			rgb = c;
		}
    	
		return rgb;
	}
	
	private double getDistance(int centerX, int centerY, int px, int py) {  
        double xx = (centerX - px)*(centerX - px);  
        double yy = (centerY - py)*(centerY - py);  
        return (int)Math.sqrt(xx + yy);  
    }
}
