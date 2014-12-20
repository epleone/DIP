package cn.filter;

import cn.basic.Image;
import cn.basic.math;

/**
 * link:http://cssha.com/javascript-image-blending-algorithm/
 * @author eple
 * Image Fusion 图像融合
 */
public class ImageFusion {
	
	private Image img;
	private Image jb;
	public ImageFusion(Image img,Image jb){
		this.img = img;
		this.jb = jb;
	}
	/*
	 * a[i] * b[i] / 255
	 * 正片叠底
	 */
	public Image filter() {
		
		if(this.img.gray)
			return this.img;  // Grayscale images can not be processed
		
		for (int y = 0; y < this.img.h; y++) {
            for (int x = 0; x < this.img.w; x++) {
            	

                int fr = this.img.red[x + y * this.img.w]*jb.red[x + y * this.img.w]/255;  
                int fg = this.img.green[x + y * this.img.w]*jb.green[x + y * this.img.w]/255;  
                int fb = this.img.blue[x + y * this.img.w]*jb.blue[x + y * this.img.w]/255;  
                  
                this.img.data[x + y * this.img.w] = (255 << 24) | (math.st(fr) << 16) | (math.st(fg) << 8) | math.st(fb);              
            }
        }
		
		return this.img;
	}
	
	/*
	 * (a[i] + b[i] < 255) ? 0 : (a[i] + b[i] - 255)
	 * 线性加深
	 */
	public Image linearDeep() {
		
		if(this.img.gray)
			return this.img;  // Grayscale images can not be processed
		
		for (int y = 0; y < this.img.h; y++) {
            for (int x = 0; x < this.img.w; x++) {
            	
                int fr = (this.img.red[x + y * this.img.w]+jb.red[x + y * this.img.w])<255?0:(this.img.red[x + y * this.img.w]+jb.red[x + y * this.img.w]-255);  
                int fg = (this.img.green[x + y * this.img.w]+jb.green[x + y * this.img.w])<255?0:(this.img.green[x + y * this.img.w]+jb.green[x + y * this.img.w]-255);  
                int fb = (this.img.blue[x + y * this.img.w]+jb.blue[x + y * this.img.w])<255?0:(this.img.blue[x + y * this.img.w]+jb.blue[x + y * this.img.w]-255);  
                  
                this.img.data[x + y * this.img.w] = (255 << 24) | (math.st(fr) << 16) | (math.st(fg) << 8) | math.st(fb);              
            }
        }
		
		return this.img;
	}
	
	/*
	 * (b[i] > a[i]) ? b[i] : a[i]
	 * 变亮
	 */
	public Image max() {
		
		if(this.img.gray)
			return this.img;  // Grayscale images can not be processed
		
		for (int y = 0; y < this.img.h; y++) {
            for (int x = 0; x < this.img.w; x++) {
            	
                int fr = this.img.red[x + y * this.img.w] > jb.red[x + y * this.img.w]?this.img.red[x + y * this.img.w]:jb.red[x + y * this.img.w];
                int fg = this.img.green[x + y * this.img.w] > jb.green[x + y * this.img.w]?this.img.green[x + y * this.img.w]:jb.green[x + y * this.img.w];
                int fb = this.img.blue[x + y * this.img.w] > jb.blue[x + y * this.img.w]?this.img.blue[x + y * this.img.w]:jb.blue[x + y * this.img.w];
                  
                this.img.data[x + y * this.img.w] = (255 << 24) | (math.st(fr) << 16) | (math.st(fg) << 8) | math.st(fb);              
            }
        }
		
		return this.img;
	}
	
	
	/*
	 * 255 - (((255 - a[i]) * (255 - b[i])) >> 8)
	 * 滤色
	 */
	public Image ColorFilter() {
		
		if(this.img.gray)
			return this.img;  // Grayscale images can not be processed
		
		for (int y = 0; y < this.img.h; y++) {
            for (int x = 0; x < this.img.w; x++) {
            	
                int fr = 255-((255-this.img.red[x + y * this.img.w])*(255-jb.red[x + y * this.img.w])>>8);
                int fg = 255-((255-this.img.green[x + y * this.img.w])*(255-jb.green[x + y * this.img.w])>>8);
                int fb = 255-((255-this.img.blue[x + y * this.img.w])*(255-jb.blue[x + y * this.img.w])>>8);
                  
                this.img.data[x + y * this.img.w] = (255 << 24) | (math.st(fr) << 16) | (math.st(fg) << 8) | math.st(fb);              
            }
        }
		
		return this.img;
	}
	
	/*
	 * Math.min(255, (a[i] + b[i]))
	 * 线性减淡
	 */
	public Image LinearSub() {
		
		if(this.img.gray)
			return this.img;  // Grayscale images can not be processed
		
		for (int y = 0; y < this.img.h; y++) {
            for (int x = 0; x < this.img.w; x++) {
            	
                int fr = 255>(this.img.red[x + y * this.img.w]+jb.red[x + y * this.img.w])?(this.img.red[x + y * this.img.w]+jb.red[x + y * this.img.w]):255;
                int fg = 255>(this.img.green[x + y * this.img.w]+jb.green[x + y * this.img.w])?(this.img.green[x + y * this.img.w]+jb.green[x + y * this.img.w]):255;
                int fb = 255>(this.img.blue[x + y * this.img.w]+jb.blue[x + y * this.img.w])?(this.img.blue[x + y * this.img.w]+jb.blue[x + y * this.img.w]):255;
                  
                this.img.data[x + y * this.img.w] = (255 << 24) | (math.st(fr) << 16) | (math.st(fg) << 8) | math.st(fb);              
            }
        }
		
		return this.img;
	}
	
	/*
	 * a[i] + b[i] - 2 * a[i] * b[i] / 255
	 * 排除
	 */
	public Image Exclude() {
		
		if(this.img.gray)
			return this.img;  // Grayscale images can not be processed
		
		for (int y = 0; y < this.img.h; y++) {
            for (int x = 0; x < this.img.w; x++) {
            	
                int fr = this.img.red[x + y * this.img.w]+jb.red[x + y * this.img.w]-2*this.img.red[x + y * this.img.w]*jb.red[x + y * this.img.w]/255;
                int fg = this.img.green[x + y * this.img.w]+jb.green[x + y * this.img.w]-2*this.img.green[x + y * this.img.w]*jb.green[x + y * this.img.w]/255;
                int fb = this.img.blue[x + y * this.img.w]+jb.blue[x + y * this.img.w]-2*this.img.blue[x + y * this.img.w]*jb.blue[x + y * this.img.w]/255;
                  
                this.img.data[x + y * this.img.w] = (255 << 24) | (math.st(fr) << 16) | (math.st(fg) << 8) | math.st(fb);              
            }
        }
		
		return this.img;
	}
	
	/*
	 * Math.abs(a[i] - b[i])
	 * 差值
	 */
	public Image sub() {
		
		if(this.img.gray)
			return this.img;  // Grayscale images can not be processed
		
		for (int y = 0; y < this.img.h; y++) {
            for (int x = 0; x < this.img.w; x++) {
            	
                int fr = Math.abs(this.img.red[x + y * this.img.w]-jb.red[x + y * this.img.w]);
                int fg = Math.abs(this.img.green[x + y * this.img.w]-jb.green[x + y * this.img.w]);
                int fb = Math.abs(this.img.blue[x + y * this.img.w]-jb.blue[x + y * this.img.w]);
                  
                this.img.data[x + y * this.img.w] = (255 << 24) | (math.st(fr) << 16) | (math.st(fg) << 8) | math.st(fb);              
            }
        }
		
		return this.img;
	}
}
