package cn.filter;

import cn.basic.Image;
import cn.basic.Mask;

/**
 * 
 * @author eple
 * link:http://blog.csdn.net/wsfdl/article/details/7610634
 * line drawing
 */
public class lineDrawing implements filt {
	
	private Image img;
	
	public lineDrawing(Image img){
		this.img = img;
	}
	
	//C =MIN( A +（A×B）/（255-B）,255)，其中C为混合结果，A为源像素点，B为目标像素点
	@Override
	public Image filter() {
		
		if(!this.img.gray)
			this.img.toGray(); // to gray
		
		Image copy = img.clone();
		//Negative image
		for (int y = 0; y < copy.h; y++) {
            for (int x = 0; x < copy.w; x++) {
            	copy.data[x + y * copy.w] = 255 - copy.data[x + y * copy.w];              
            }
        }
		copy.filter(Mask.Guass); //Gaussian Blur
		
		for (int y = 0; y < this.img.h; y++) {
            for (int x = 0; x < this.img.w; x++) {
            	int a = this.img.data[x + y * this.img.w];
            	int b = copy.data[x + y * this.img.w];
            	
            	int c = a + (a*b)/(255-b);
            	
            	this.img.data[x + y * this.img.w] = clamp(c);              
            }
        }
		return this.img;
	}
	
	private  int clamp(int c)  
    {  
        return c > 255 ? 255 :( (c < 0) ? 0: c);  
    } 
}
