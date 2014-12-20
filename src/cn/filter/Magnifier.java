package cn.filter;

import cn.basic.Image;

/**
 * 
 * @author eple
 * 放大镜
 * link:http://www.icodelogic.com/?p=588
 */
public class Magnifier implements filt {
	
	private Image img;
	
	public Magnifier(Image img){
		this.img = img;
	}

	@Override
	public Image filter() {
		int[] d = new int[this.img.w*this.img.h]; //must be writed as this.not int[] d = this.img.data;
		int cenX = (int)(this.img.w/2);
		int cenY = (int)(this.img.h/2);
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
        int m= 2;//the rate
        
		if(Math.sqrt((cenX-x)*(cenX-x) +(cenY-y)*(cenY-y)) < therash){
			int tx = (x - cenX)/m + cenX; //此处不同的变换函数会导致不同的效果，link:http://blog.csdn.net/wsfdl/article/details/7719890
			int ty = (y - cenY)/m + cenY;
			rgb = this.img.data[tx + ty * this.img.w];
		}
		else{
			rgb = this.img.data[x + y * this.img.w];
		}
    	
		return rgb;
	}
	
}
