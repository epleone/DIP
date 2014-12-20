package cn.filter;

import cn.basic.Image;

/**
 * 
 * @author eple 2014 July 24
 * http://blog.csdn.net/jia20003/article/details/9142111
 *
 */

public class oldPhoto implements filt{
	
	private Image img;
	
	public oldPhoto(Image img){
		this.img = img;
	}

	@Override
	public Image filter() {
		
		if(this.img.gray)
			return this.img;  // Grayscale images can not be processed
		
		for (int y = 0; y < this.img.h; y++) {
            for (int x = 0; x < this.img.w; x++) {
            	
                int c = this.img.data[x + y * this.img.w];
                int ta = (c >> 24) & 0xff; 
                int tr = (c >> 16) & 0xFF;
                int tg = (c >> 8) & 0xFF;
                int tb = (c >> 0) & 0xFF;
                
                int fr = (int)colorBlend(noise(), (tr * 0.393) + (tg * 0.769) + (tb * 0.189), tr);  
                int fg = (int)colorBlend(noise(), (tr * 0.349) + (tg * 0.686) + (tb * 0.168), tg);  
                int fb = (int)colorBlend(noise(), (tr * 0.272) + (tg * 0.534) + (tb * 0.131), tb);  
                  
                this.img.data[x + y * this.img.w] = (ta << 24) | (clamp(fr) << 16) | (clamp(fg) << 8) | clamp(fb);              
            }
        }
		
		return this.img;
	}
	
    private double noise() {  
        return Math.random()*0.5 + 0.5;  
    }  
      
    private double colorBlend(double scale, double dest, double src) {  
        return (scale * dest + (1.0 - scale) * src);  
    }  
      
    private  int clamp(int c)  
    {  
        return c > 255 ? 255 :( (c < 0) ? 0: c);  
    }  
}
