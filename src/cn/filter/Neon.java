package cn.filter;

import cn.basic.Image;
import cn.basic.math;

/**
 * link:http://blog.csdn.net/alin0725/article/details/1543860   #3
 * @author eple
 * 霓虹算法
 */
public class Neon implements filt {

	private Image img;
	
	public Neon(Image img){
		this.img = img;
	}
	
	@Override
	public Image filter() {
		
		int[] d = new int[this.img.w*this.img.h]; //must be writed as this.not int[] d = this.img.data;
		
		for (int y = 0; y < this.img.h-1; y++) {
            for (int x = 0; x < this.img.w-1; x++) {
            	
                int c = this.img.data[x + y * this.img.w];
                int r = (c >> 16) & 0xFF;
                int g = (c >> 8) & 0xFF;
                int b = (c >> 0) & 0xFF;
                
                int Rowc = this.img.data[x + 1 + y * this.img.w];//相同行
                int r1 = (Rowc >> 16) & 0xFF;
                int g1 = (Rowc >> 8) & 0xFF;
                int b1 = (Rowc >> 0) & 0xFF; 
                int Colc = this.img.data[x + (y + 1)* this.img.w];//相同列
                int r2 = (Colc >> 16) & 0xFF;
                int g2 = (Colc >> 8) & 0xFF;
                int b2 = (Colc >> 0) & 0xFF;
                
                int tr =(int)(2*Math.sqrt(((r-r1)*(r-r1)+(r-r2)*(r-r2))));
                int tg =(int)(2*Math.sqrt(((g-g1)*(g-g1)+(g-g2)*(g-g2))));
                int tb =(int)(2*Math.sqrt(((b-b1)*(b-b1)+(b-b2)*(b-b2))));
                
                d[x + y * this.img.w] = (255 << 24) | (math.st(tr) << 16) | (math.st(tg) << 8) | math.st(tb);
            }
        }
		
		this.img.data = d;
		return this.img;
	}

}
