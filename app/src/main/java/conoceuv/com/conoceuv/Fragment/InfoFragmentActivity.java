package conoceuv.com.conoceuv.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import conoceuv.com.conoceuv.Modelos.EdificioModel;
import conoceuv.com.conoceuv.R;

public class InfoFragmentActivity extends AppCompatActivity{

    TextView txt,txt2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_fragment);

        EdificioModel info = (EdificioModel) getIntent().getSerializableExtra("Edificio");

        txt = findViewById(R.id.textView2);
        txt2 = findViewById(R.id.textView3);

        txt.setText(info.title);
        txt2.setText(info.descripcion.toUpperCase());


    }
}
