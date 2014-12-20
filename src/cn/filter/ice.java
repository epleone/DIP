package cn.filter;

import cn.basic.Image;
import cn.basic.math;

/**
 * 
 * @author eple
 * android 中的算法，不兼容
 * link:http://blog.csdn.net/jingwen3699/article/details/7770287
 */

public class ice implements filt {

	private Image img;
	
	public ice(Image img){
		this.img = img;
	}
	
	@Override
	public Image filter() {
		
		int[] d = new int[this.img.w*this.img.h]; //what's the fuck. it must be writed as this. not int[] d = this.img.data;
		
		for (int y = 0; y < this.img.h -1; y++) {
            for (int x = 0; x < this.img.w -1; x++) {

            	int c = this.img.data[x + y * this.img.w];
                int R = (c >> 16) & 0xFF;
                int G = (c >> 8) & 0xFF;
                int B = (c >> 0) & 0xFF;
                
                int tR = (R- B -G)*3/2; 
                int tG = (G- R -B)*3/2;
                int tB = (B- R -G)*3/2;
                
                d[x + y * this.img.w] = (255 << 24) | (math.st(tR) << 16) | (math.st(tG) << 8) | math.st(tB);    
            }
        }
		
		this.img.data = d;
		return this.img;
	}
}
