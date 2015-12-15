package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;

import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.adapter.GaleriaImagensAdapter;
import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.adapter.MenuPrincipalAdapter;

public class PrincipalActivity extends Activity {

    protected static final int NOVA_PESSOA = 0;
    protected static final int LISTA_PESSOA = 1;
    protected static final int SAIR = 2;

    // declare view objects
    private ImageButton imgContato;
    private ListView menuPrincipalList;
    private MenuPrincipalAdapter menuPrincipalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        try{

            imgContato = (ImageButton) findViewById(R.id.imgContato);
            menuPrincipalList = (ListView) findViewById(R.id.menuPrincipal);

            Bitmap bitmap1 = null;
            bitmap1 = BitmapFactory.decodeByteArray(imageToBytes(R.drawable.img1), 0, imageToBytes(R.drawable.img1).length);

            int indice = 0;
            Bitmap[] bitmap = new Bitmap[6];
            bitmap[indice] = bitmap1;
            indice++;

            bitmap[indice] = BitmapFactory.decodeByteArray(imageToBytes(R.drawable.img2), 0, imageToBytes(R.drawable.img2).length);
            indice++;
            bitmap[indice] = BitmapFactory.decodeByteArray(imageToBytes(R.drawable.img3), 0, imageToBytes(R.drawable.img3).length);
            indice++;
            bitmap[indice] = BitmapFactory.decodeByteArray(imageToBytes(R.drawable.img4), 0, imageToBytes(R.drawable.img4).length);
            indice++;
            bitmap[indice] = BitmapFactory.decodeByteArray(imageToBytes(R.drawable.img5), 0, imageToBytes(R.drawable.img5).length);
            indice++;

            ViewPager galeria = (ViewPager) findViewById(R.id.galeria);
            GaleriaImagensAdapter adapter = new GaleriaImagensAdapter(this,bitmap);
            galeria.setAdapter(adapter);

            // get screen device width and height
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int wPix = dm.widthPixels;
            int hPix = wPix / 2 + 50;

            // change cover image width and height
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(wPix, hPix);

            menuPrincipalAdapter = new MenuPrincipalAdapter(this);
            menuPrincipalList.setAdapter(menuPrincipalAdapter);

            menuPrincipalList.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                        long arg3) {
                    switch (position) {
                        case NOVA_PESSOA:
                            // nova pessoa
                            startActivity(new Intent(PrincipalActivity.this, PessoaDetail.class));
                            break;
                        case LISTA_PESSOA:
                            // listar pessoa
                            startActivity(new Intent(PrincipalActivity.this, PessoaListView.class));
                            break;
                        case SAIR:
                            // sair
                            finishAffinity();
                    }
                }

            });

            // event listener ao clicar em contato
            imgContato.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    Intent contato = new Intent(PrincipalActivity.this, ContatoView.class);
                    startActivity(contato);
                }
            });

        } catch (Exception e){
            Log.e(PrincipalActivity.class.getName(), e.getMessage());
        }
    }

    public byte[] imageToBytes(int img){
        Drawable d = this.getResources().getDrawable (img);
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
       return stream.toByteArray();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
