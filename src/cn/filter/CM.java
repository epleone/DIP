package cn.filter;

import cn.basic.Image;
import cn.basic.math;

public class CM implements filt {

	private Image img;
	private double[][] cm;//Color matrix
	public CM(Image img,double[][] m){
		this.img = img;
		this.cm = m;
	}
	
	@Override
	public Image filter() {
		double[][] mask = this.cm;
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
