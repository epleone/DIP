package cn.filter;

import cn.basic.Image;

public class hahaMirror implements filt {
	
	private Image img;
	
	public hahaMirror(Image img){
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
        int m= 2;
        int rate = therash/m;
		if(Math.sqrt((cenX-x)*(cenX-x) +(cenY-y)*(cenY-y)) < therash){
			int tx = (x - cenX)/m ; //此处不同的变换函数会导致不同的效果，link:http://blog.csdn.net/wsfdl/article/details/7719890
			int ty = (y - cenY)/m ;
			tx  =(int)(tx*getDistance(cenX, cenY, x, y)/rate) + cenX;
			ty  =(int)(ty*getDistance(cenX, cenY, x, y)/rate) + cenY;
			rgb = this.img.data[tx + ty * this.img.w];
		}
		else{
			rgb = this.img.data[x + y * this.img.w];
		}
    	
		return rgb;
	}
	
	private double getDistance(int centerX, int centerY, int px, int py) {  
        double xx = (centerX - px)*(centerX - px);  
        double yy = (centerY - py)*(centerY - py);  
        return (int)Math.sqrt(xx + yy);  
    }
}
