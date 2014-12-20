package cn.filter;

import cn.basic.Image;

/**
 * 
 * @author eple 2014 July 24
 * link:http://blog.csdn.net/wsfdl/article/details/7681088
 * oil painting
 */
public class oilPainting implements filt {
	
	private Image img;
	
	public oilPainting(Image img){
		this.img = img;
	}
	
	@Override
	public Image filter() {
		
		if(this.img.gray)
			return this.img;  // Grayscale images can not be processed
		
		int[] d = new int[this.img.w*this.img.h]; //must be writed as this.not int[] d = this.img.data;
		
		for (int y = 4; y < this.img.h -4; y++) {
            for (int x = 4; x < this.img.w -4; x++) {
            	
            	int range = (int)(Math.random()*4)+1;
            	int sub = (range +1)/2;
            	int a = (int)(Math.random()*range)+1;
            	int b = (int)(Math.random()*range)+1;
                d[x + y * this.img.w] = this.img.data[x+a-sub + (y+b-sub) * this.img.w];              
            }
        }
		
		this.img.data = d;
		return this.img;
	}

}
