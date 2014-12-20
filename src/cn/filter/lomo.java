package cn.filter;

import cn.basic.Image;
import cn.basic.math;

/**
 * link:http://deeryrl.blog.163.com/blog/static/1525428742012931112616825/
 * link:http://blog.csdn.net/easyer2012/article/details/8053161
 * @author eple
 * by ColorMatrix
 */

public class lomo implements filt {
	
	private Image img;
	
	public lomo(Image img){
		this.img = img;
	}
	
	@Override
	public Image filter() {
		double[][] mask = { {1.7f,  0.1f, 0.1f, 0, -73.1f},

			    			{0,  1.7f, 0.1f, 0, -73.1f},

			    			{0,  0.1f, 1.6f, 0, -73.1f},

			    			{0,  0, 0, 1.0f, 0}
			    		  };
		int[] d = new int[this.img.w*this.img.h]; //must be writed as this.not int[] d = this.img.data;
		
		for (int y = 0; y < this.img.h-1; y++) {
            for (int x = 0; x < this.img.w-1; x++) {
                d[x + y * this.img.w] = math.imgConv(mask, this.img.data[x + y * this.img.w]);
            }
        }
		
		this.img.data = d;
		return this.img;
	}

}
