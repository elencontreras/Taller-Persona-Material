package com.example.android.personasmaterialdiplomado;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ModificarPersona extends AppCompatActivity {
    private EditText txtCedula;
    private EditText txtNombre;
    private EditText txtApellido;
    private TextInputLayout cajaCedula;
    private TextInputLayout cajaNombre;
    private TextInputLayout cajaApellido;
    private Bundle bundle;
    private Intent i;
    private String cedula, nombre;

    private ArrayList<Integer> fotos;
    private Resources res;
    private Spinner sexo;
    private ArrayAdapter<String> adapter;
    private String[] opc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_personas);

        txtCedula = (EditText)findViewById(R.id.txtModCedula);
        txtNombre = (EditText)findViewById(R.id.txtModNombre);
        txtApellido=(EditText)findViewById(R.id.txtModApellido);
        res = this.getResources();
        cajaNombre = (TextInputLayout) findViewById(R.id.cajaModNombre);
        cajaApellido = (TextInputLayout)findViewById(R.id.cajaModApellido);
        cajaCedula = (TextInputLayout)findViewById(R.id.cajaModCedula);
        sexo = (Spinner)findViewById(R.id.cmbModSexo);
        opc = res.getStringArray(R.array.sexo);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opc);
        sexo.setAdapter(adapter);

        i = getIntent();
        bundle = i.getBundleExtra("datos");
        cedula = bundle.getString("cedula");
        nombre = bundle.getString("nombre");

        txtCedula.setText(cedula);
        txtNombre.setText(nombre);


        opc = res.getStringArray(R.array.sexo);

       iniciar_fotos();


    }

    public void iniciar_fotos(){
        fotos = new ArrayList<>();
        fotos.add(R.drawable.images);
        fotos.add(R.drawable.images2);
        fotos.add(R.drawable.images3);
    }


    public void modificar(View v){




    }



    public void limpiar(View v){
        limpiar();
    }

    public void limpiar(){
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        sexo.setSelection(0);
        txtCedula.requestFocus();

    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(ModificarPersona.this,Principal.class);
        startActivity(i);
    }

    public boolean validar(){
        if (validar_aux(txtCedula,cajaCedula)) return false;
        else  if (validar_aux(txtNombre,cajaNombre)) return false;
        else  if (validar_aux(txtApellido,cajaApellido)) return false;
        else if (Metodos.exitencia_persona(Datos.obtenerPersonas(),txtCedula.getText().toString())){
            txtCedula.setError(res.getString(R.string.persona_existente_error));
            txtCedula.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validar_aux(TextView t, TextInputLayout ct){
        if (t.getText().toString().isEmpty()){
           t.requestFocus();
            t.setError(res.getString(R.string.no_vacio_error));
            return true;
        }
        return false;
    }
}
