package cn.filter;

import cn.basic.Image;

/**
 * 
 * @author eple 2014 July 24
 *	cameo
 *	link:http://blog.csdn.net/wsfdl/article/details/7607565
 *  
 */
public class cameo implements filt {
	
	private Image img;
	
	public cameo(Image img){
		this.img = img;
	}
	@Override
	public Image filter() {
		
		if(this.img.gray)
			return this.img;  // Grayscale images can not be processed
		
		int[] d = new int[this.img.w*this.img.h]; //what's the fuck. it must be writed as this. not int[] d = this.img.data;
		
		for (int y = 0; y < this.img.h -1; y++) {
            for (int x = 0; x < this.img.w -1; x++) {

            	int pc = this.img.data[x + y * this.img.w];
            	
            	int PR = (pc >> 16) & 0xFF;
                int PG = (pc >> 8) & 0xFF;
                int PB = (pc >> 0) & 0xFF;
                
            	int c = this.img.data[x + 1 + (y+1) * this.img.w];
                int R = (c >> 16) & 0xFF;
                int G = (c >> 8) & 0xFF;
                int B = (c >> 0) & 0xFF;
                
                R = PR - R + 128;  
                G = PG - G + 128;  
                B = PB - B + 128;  
                
                d[x + y * this.img.w] = (255 << 24) | (R << 16) | (G << 8) | B;    
            }
        }
		
		this.img.data = d;
		this.img.toGray();
		return this.img;
	}

}
