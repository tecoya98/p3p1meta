package mx.edu.tesoem.isc.teco.p3p1meta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtnombre;
    TextView contenido;
    private final String  nomarch="Datos.txt";
    private ArrayList<String> TextoCompleto =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtnombre= findViewById(R.id.txtNombre);
        contenido=findViewById(R.id.contenido);
        llamadatos();
}
public void recargar(View v){
    manejoArchivo objmanar = new manejoArchivo();
    objmanar.agrega(txtnombre.getText().toString(),TextoCompleto);
    TextoCompleto=objmanar.getContenido();
    if(objmanar.grabar(this,TextoCompleto,nomarch)){
        Toast.makeText(this,"grabado",Toast.LENGTH_SHORT).show();
        llamadatos();
    }
    else {
        Toast.makeText(this,"no grabado",Toast.LENGTH_SHORT).show();
    }
}
    public void cargardatos(View v){
        llamadatos();

    }
    public void llamadatos(){
        manejoArchivo informacion = new manejoArchivo();
        if (informacion.verificaArch(this,nomarch)){
            Toast.makeText(this,"si existe",Toast.LENGTH_LONG).show();
            if (informacion.leer(this,nomarch)){
                TextoCompleto= informacion.getContenido();
                String temp="";
                for (String cadena:TextoCompleto) temp+="\n"+cadena +"\n";
                contenido.setText(temp);
            }
        }else{
            Toast.makeText(this,"no existe",Toast.LENGTH_LONG).show();
        }

    }
}
