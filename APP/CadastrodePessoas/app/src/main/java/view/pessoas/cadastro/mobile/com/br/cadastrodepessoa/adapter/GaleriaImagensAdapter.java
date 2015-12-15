package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GaleriaImagensAdapter extends PagerAdapter {

    private Context context;

    private Bitmap[] imagens;

    public GaleriaImagensAdapter(Context context, Bitmap[] imagens) {
        this.context = context;
        this.imagens = imagens;
    }

    @Override
    public int getCount() {
        return imagens.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup pager, int position, Object object) {
        ((ViewPager) pager).removeView((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup pager, int position) {    	
    	ImageView imagem = new ImageView(context);
        imagem.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imagem.setImageBitmap(imagens[position]);
        ((ViewPager) pager).addView(imagem, 0);
        return imagem;    	    	
    }
}