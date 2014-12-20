package cn.filter;

import cn.basic.Image;
import cn.basic.math;

/**
 * link:http://www.eoeandroid.com/thread-176986-1-1.html
 * http://blog.csdn.net/jingwen3699/article/details/7770287
 * @author eple
 * 融岩特效 android中的算法，不太兼容
 *
 */
public class Molten implements filt {
	
	private Image img;
	
	public Molten(Image img){
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
                
                R = R * 128 / (G + B + 1); 
                G = G * 128 / (B + R + 1);
                B = B * 128 / (R + G + 1);
                
                d[x + y * this.img.w] = (255 << 24) | (math.st(R) << 16) | (math.st(G) << 8) | math.st(B);    
            }
        }
		
		this.img.data = d;
		return this.img;
	}

}
