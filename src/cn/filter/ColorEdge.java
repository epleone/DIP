package cn.filter;

import cn.basic.Image;
import cn.basic.math;
/**
 * link:http://blog.csdn.net/matrix_space/article/details/30283003
 * @author eple  2014 July 27
 * 边缘采用sobel算子
 */
public class ColorEdge implements filt {

	private Image img;
	
	public ColorEdge(Image img){
		this.img = img;
	}

	@Override
	public Image filter() {
		
		if(this.img.gray)
			return this.img;  // Grayscale images can not be processed
		
		for (int j = 1; j < this.img.h-1; j++) {
            for (int i = 1; i < this.img.w-1; i++) {
            	int w = this.img.w;
            	int r1 = this.img.red[i-1+(j+1)*w]+2*this.img.red[i+(j+1)*w]+this.img.red[i+1+(j+1)*w]-this.img.red[i-1+(j-1)*w]-2*this.img.red[i+(j-1)*w]-this.img.red[i+1+(j-1)*w];
		        int r2 = this.img.red[i+1+(j-1)*w]+2*this.img.red[i+1+(j)*w]+this.img.red[i+1+(j+1)*w]-this.img.red[i-1+(j-1)*w]-2*this.img.red[i-1+(j)*w]-this.img.red[i-1+(j+1)*w];
		        int g1 = this.img.green[i-1+(j+1)*w]+2*this.img.green[i+(j+1)*w]+this.img.green[i+1+(j+1)*w]-this.img.green[i-1+(j-1)*w]-2*this.img.green[i+(j-1)*w]-this.img.green[i+1+(j-1)*w];
		        int g2 = this.img.green[i+1+(j-1)*w]+2*this.img.green[i+1+(j)*w]+this.img.green[i+1+(j+1)*w]-this.img.green[i-1+(j-1)*w]-2*this.img.green[i-1+(j)*w]-this.img.green[i-1+(j+1)*w];
		        int b1 = this.img.blue[i-1+(j+1)*w]+2*this.img.blue[i+(j+1)*w]+this.img.blue[i+1+(j+1)*w]-this.img.blue[i-1+(j-1)*w]-2*this.img.blue[i+(j-1)*w]-this.img.blue[i+1+(j-1)*w];
		        int b2 = this.img.blue[i+1+(j-1)*w]+2*this.img.blue[i+1+(j)*w]+this.img.blue[i+1+(j+1)*w]-this.img.blue[i-1+(j-1)*w]-2*this.img.blue[i-1+(j)*w]-this.img.blue[i-1+(j+1)*w];
		        
                int tr = (Math.abs(r1)+Math.abs(r2));
                int tg = (Math.abs(g1)+Math.abs(g2));
                int tb = (Math.abs(b1)+Math.abs(b2));
                
                this.img.data[i + j * this.img.w] = (255 << 24) | (math.st(tr) << 16) | (math.st(tg) << 8) | math.st(tb);              
            }
        }
		this.img.gray = false;
		return this.img;
	}

}
