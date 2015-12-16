package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContatoView extends FragmentActivity {

    private ImageView imgAnterior;
    private TextView txtText, txtSubText;
    private Button btnChamar, btnEmail;
    private FrameLayout lytMap;

    private GoogleMap map;
    private GoogleMapOptions options = new GoogleMapOptions();
    private SupportMapFragment fragMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        try {

            imgAnterior = (ImageView) findViewById(R.id.imgAnterior);
            txtText = (TextView) findViewById(R.id.txtText);
            txtSubText = (TextView) findViewById(R.id.txtSubText);
            btnChamar = (Button) findViewById(R.id.btnChamar);
            btnEmail = (Button) findViewById(R.id.btnEmail);
            lytMap = (FrameLayout) findViewById(R.id.lytMap);
            fragMap = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            // get screen device width and height
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int wPix = dm.widthPixels;
            int hPix = wPix / 2 + 50;

            // change Map width and height
            LayoutParams lp = lytMap.getLayoutParams();
            lp.width = wPix;
            lp.height = hPix;

            // set up map
            setUpMapIfNeeded();

            showInformacoes();

            imgAnterior.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    Intent contato = new Intent(ContatoView.this, PrincipalActivity.class);
                    startActivity(contato);
                    finish();
                }
            });

            // event listener to handle call button when clicked
            btnChamar.setOnClickListener(new OnClickListener() {

                public void onClick(View arg0) {

                    if (!(((TelephonyManager)ContatoView.this.getSystemService(Context.TELEPHONY_SERVICE)).getPhoneType()
                            == TelephonyManager.PHONE_TYPE_NONE)){
                        String number ="tel:"+ getString(R.string.telefone);
                        Intent iCall = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                        startActivity(iCall);
                    }else{
                        Toast.makeText(ContatoView.this, getString(R.string.sem_chamadas), Toast.LENGTH_SHORT).show();
                    }

                }
            });

            // volta pagina inicial
            ((ImageButton) findViewById(R.id.imgHome)).setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    Intent contato = new Intent(ContatoView.this, PrincipalActivity.class);
                    startActivity(contato);
                    finish();
                }
            });

            btnEmail.setOnClickListener(new OnClickListener() {

                public void onClick(View arg0) {

                    // call email client app that installed in android device
                    String[] email = new String[]{getString(R.string.email)};
                    Intent iSend = new Intent(Intent.ACTION_SEND);
                    iSend.putExtra(Intent.EXTRA_EMAIL, email);
                    iSend.putExtra(Intent.EXTRA_SUBJECT, "APP Cadastro de Pessoas");
                    iSend.setType("plain/text");
                    iSend.putExtra(Intent.EXTRA_TEXT, "");
                    startActivity(iSend);
                }
            });

        } catch (Exception e) {
            Log.e(ContatoView.class.getName(), e.getMessage());
            Intent principal = new Intent(ContatoView.this, PrincipalActivity.class);
            startActivity(principal);
            finish();
        }

    }

    // checa o fragmento do mapa
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            // Try to obtain the map from the SupportMapFragment.
            map = fragMap.getMap();
            // Check if we were successful in obtaining the map.
            if (map != null) {
                setUpMap();
                showLocalizacao();

            }
        }
    }

    // configurar mapa
    void setUpMap(){
        options.mapType(GoogleMap.MAP_TYPE_NORMAL);
        options.compassEnabled(false);
        options.rotateGesturesEnabled(false);
        options.tiltGesturesEnabled(false);
        options.zoomControlsEnabled(false);
        SupportMapFragment.newInstance(options);

    }

    // mostrar informacoes
    void showInformacoes(){
        txtText.setText(R.string.nome);
        txtSubText.setText(getString(R.string.endereco) + "\n" +
                getString(R.string.telefone) + "\n" +
                getString(R.string.email));
    }

    // mostrar localizacao
    void showLocalizacao(){

        double Latitude = Double.parseDouble(getString(R.string.latitude));
        double Longitude = Double.parseDouble(getString(R.string.longitude));

        LatLng latlng = new LatLng(Latitude, Longitude);

        // marca no  mapa
        map.addMarker(new MarkerOptions()
                .title(getString(R.string.nome))
                .snippet(getString(R.string.endereco))
                .position(latlng)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.local)
                ));

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15.0f));
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
