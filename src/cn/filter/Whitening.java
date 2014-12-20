package cn.filter;

import cn.basic.Image;
import cn.basic.math;

/**
 * 基于肤色检测的美白算法
 * @author eple
 * link:http://www.cnblogs.com/Imageshop/p/3265353.html
 */
public class Whitening implements filt {
	
	private Image img;
	
	public Whitening(Image img){
		this.img = img;
	}
	
	@Override
	public Image filter() {
		if(img.gray)
			return this.img;
		
		for(int i =0;i<this.img.h;i++){
			for(int j=0;j<this.img.w;j++){
				int th = 50;
				int R = this.img.red[j+i*this.img.w];
				int G = this.img.green[j+i*this.img.w];
				int B =  this.img.blue[j+i*this.img.w];
				//double Y  = 0.299*R+0.587*G+0.114*B;
				double Cb = 128 - 0.168736*R - 0.331264*G + 0.5*B;
				double Cr = 128 + 0.5*R - 0.418688*G - 0.081312*B;
				if(Cb > 77 && Cb<127 && Cr>133 && Cr<173){
					R = math.st(R+th);
					G = math.st(G+th);
					B = math.st(B+th);
					this.img.data[j+i*this.img.w] = (255<<24)|(R<<16)|(G<<8)|B;
				}
			}
		}
		
		return this.img;
	}

}
