package cn.filter;

import cn.basic.Image;

/**
 * 
 * @author eple 2014 July 24
 *	Negative image
 *	link:http://blog.sina.com.cn/s/blog_4ab2f5c801015dfs.html
 */

public class Nega implements filt{
	
	private Image img;
	
	public Nega(Image img){
		this.img = img;
	}
	
	@Override
	public Image filter() {
		if(this.img.gray)
			return this.img;  // Grayscale images can not be processed
		
		for (int y = 0; y < this.img.h; y++) {
            for (int x = 0; x < this.img.w; x++) {
            	
                int c = this.img.data[x + y * this.img.w];
                int A = (c >> 24) & 0xff; 
                int R = (c >> 16) & 0xFF;
                int G = (c >> 8) & 0xFF;
                int B = (c >> 0) & 0xFF;
                
                R = 255 - R;  
                G = 255 - G;  
                B = 255 - B;  
                  
                this.img.data[x + y * this.img.w] = (A << 24) | (R << 16) | (G << 8) | B;              
            }
        }
		
		return this.img;
	}

}
